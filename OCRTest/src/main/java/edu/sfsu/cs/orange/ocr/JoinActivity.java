package edu.sfsu.cs.orange.ocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class JoinActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{


    public EditText editTextEmail;
    public EditText editTextPassword;
    public EditText editTextphone;
    public EditText editTextAge;
    public EditText editTextDiseases;
    public EditText editTextMedicine;
    public CheckBox check_pregnancy;

    public String phone;
    public String email;
    public String password;
    public long age;
    public String diseases;
    public String medicine;
    public String pregnancy = "";


    private GoogleApiClient mGoogleApiClient;
    public static final int RC_SIGN_IN = 10;
    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;//사용자의 로그인 상태변화에 응답

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();//db2
    private DatabaseReference databaseReference = firebaseDatabase.getReference();//db2


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        Button button1=(Button) findViewById(R.id.btnCancel);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent Intent1 = new Intent(JoinActivity.this,LoginActivity.class);
                startActivity(Intent1);
            }
        });
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)//firebase로 인증하기
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();



        editTextEmail = (EditText)findViewById(R.id.etEmail);
        editTextPassword = (EditText)findViewById(R.id.etPassword);

        editTextphone = (EditText)findViewById(R.id.phone);
        editTextAge = (EditText)findViewById(R.id.question_age);
        editTextDiseases = (EditText)findViewById(R.id.question_diseases);
        editTextMedicine = (EditText)findViewById(R.id.question_medicine);


        String key = "phone";
        Button emailLogin = (Button) findViewById(R.id.btnJoinComplete);
        emailLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //여기에다가 push만 하면 원래대로 token붙으면서 됨
                //지금 DB에 계속 기존 회원정보 사라지면서 새로 덮어 씌우는 이유는 key값이 동일하기 때문.
                //key값 자체를 user_num + phone이런식으로 바꿔 서로 겹쳐지지만 않으면 ㄱㅊ
                //지금 데모에서는 user이름을 갖고와야 되기 때문에
                //일단 이 상태로 진행
                phone = editTextphone.getText().toString();
                databaseReference.child("user_db").child("phone").setValue(phone);

                email = editTextEmail.getText().toString();
                databaseReference.child("user_db").child("email").setValue(email);

                password= editTextPassword.getText().toString();
                databaseReference.child("user_db").child("password").setValue(password);

                age = Long.parseLong(editTextAge.getText().toString());
                databaseReference.child("user_db").child("age").setValue(age);

                diseases = editTextDiseases.getText().toString();
                databaseReference.child("user_db").child("diseases").setValue(diseases);

                medicine = editTextMedicine.getText().toString();
                databaseReference.child("user_db").child("medicine").setValue(medicine);

                createUser(editTextEmail.getText().toString(), editTextPassword.getText().toString());

                Intent Intent = new Intent(JoinActivity.this,LoginActivity.class);
                startActivity(Intent);
            }
        });

//데이터베이스 읽기 #ValueEventListener
        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Log.d("MainActivity","ValueEventListener:"+snapshot.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void createUser(final String email, final String password) { //email login
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            loginUser(email, password);
                        } else {
                        // If sign in fails, display a message to the user.
                            Toast.makeText(JoinActivity.this, "회원가입성공", Toast.LENGTH_SHORT).show();

                        }
                        // ...
                    }
                });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {

                        } else {
// If sign in fails, display a message to the user.
                            Toast.makeText(JoinActivity.this, "이메일 로그인 완료.", Toast.LENGTH_SHORT).show();
                        }

// ...
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

// Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            if(result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {

            }
        }
    }
    //firebase로 전달
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {

                        } else {
// If sign in fails, display a message to the user.
                            Toast.makeText(JoinActivity.this, "firebase아이디 생성이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        }

// ...
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { //this문제 OnConnect~

    }
}
