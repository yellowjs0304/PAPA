package edu.sfsu.cs.orange.ocr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PrescriptionCheck extends Activity {

    private String uid,drug_name,key;

    private Button Submit_btn;
    private EditText pres_name, pres_time;
    private ListView listview;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescription_check);
        listview = (ListView) findViewById(R.id.drug_list);
        pres_name = (EditText) findViewById(R.id.Presc_name);
        pres_time = (EditText) findViewById(R.id.Presc_time);
        Submit_btn = (Button)findViewById(R.id.submitBtn);
        key = "prescription";//user+pres_name
        //uid = mAuth.getCurrentUser().getUid();

        Submit_btn.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View v) {
                                              databaseReference.child("prescription").child("pres_name").setValue(pres_name.getText().toString());
                                              databaseReference.child("prescription").child("pres_time").setValue(pres_time.getText().toString());
                                              Toast.makeText(getApplicationContext(), "처방전이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                                              Intent intent = new Intent(PrescriptionCheck.this, HomeActivity.class);
                                              startActivity(intent);
                                          }
        });
    }
}

//            public void getDrugList() {
//                databaseReference.child("user_temp").addValueEventListener(new ValueEventListener() {
//
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot drug : dataSnapshot.getChildren()) {
//
//                        }
//                    }
//                });
//            }
//        });

