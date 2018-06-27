package edu.sfsu.cs.orange.ocr;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Random;

public class HomeActivity extends Activity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    String user_disease;
    TextView textview1;
    TextView textview2;
    String[] life_style = new String[3];
    String disease_style;
    int i; //생활 관련 index(1 ~ 10)
    int j; //disease 관련 index(1 ~ 3)
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        textview1 = (TextView)findViewById(R.id.textView_data1);
        textview1.setMovementMethod(new ScrollingMovementMethod());
        textview2 = (TextView)findViewById(R.id.textView_data2);
        textview2.setMovementMethod(new ScrollingMovementMethod());
        final Random rand = new Random();
        Button RecycleBtn = (Button)findViewById(R.id.Recycle_Btn);
        RecycleBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent intent_recycle = new Intent(HomeActivity.this, HomeActivity.class);
                //intent_recycle.putExtra("DISEASE",user_disease);
                //startActivity(intent_recycle);
                i = rand.nextInt(2);
                //j = rand.nextInt(3);
                textview1.setText(disease_style);
                textview2.setText(life_style[i]);
            }
        });
        //기본적인 생활 상식을 받아오는 부분
            databaseReference.child("commonknowledge").orderByValue().addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    for(int i =0 ;i<2;i++) {
                        life_style[i] = dataSnapshot.getValue().toString();
                    }
                   //Toast.makeText(getApplicationContext(), life_style, Toast.LENGTH_LONG).show();
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

        databaseReference.child("user_db").orderByKey().equalTo("disease").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                user_disease= dataSnapshot.getValue().toString();
                //Toast.makeText(getApplicationContext(), "병명::"+user_disease, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //질병 관련 문구
        databaseReference.child("diseaseString").orderByKey().equalTo("당뇨").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                disease_style = dataSnapshot.getValue().toString();
                //Toast.makeText(getApplicationContext(), disease_style, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        Button SearchBtn = (Button) findViewById(R.id.M_Search_Btn);
        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_search = new Intent(HomeActivity.this, RecyclerViewActivity.class);
                startActivity(intent_search);
            }
        });
        Button CameraBtn = (Button) findViewById(R.id.Prescription_Camera);
        CameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_camera = new Intent(HomeActivity.this, CaptureActivity.class);
                startActivity(intent_camera);
            }
        });
        Button AlarmBtn = (Button) findViewById(R.id.AlarmBtn);
        AlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_alarm = new Intent(HomeActivity.this, Alarm.class);
                startActivity(intent_alarm);
            }
        });
        Button MyPageBtn = (Button) findViewById(R.id.MypageBtn);
        MyPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(HomeActivity.this, Get_User_Data.class);
                startActivity(intent_home);
            }
        });
    }
}
