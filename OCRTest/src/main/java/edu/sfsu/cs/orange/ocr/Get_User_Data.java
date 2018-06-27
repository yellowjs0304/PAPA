package edu.sfsu.cs.orange.ocr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;


import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.Random;

public class Get_User_Data extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private Button Btn;
    private String user_Age;
    private String user_Diseases;
    private String user_Email;
    private String user_Medicine;
    private String user_Phone;
    private TextView textView_Email;
    private TextView textView_Age;
    private TextView textView_Phone;
    private TextView textView_Diseases;
    private TextView textView_Medicine;
    private ListView listview_pres;
    private String user_email;
    private String user_age;
    private String user_phone;
    private String user_diseases;
    private String user_medicine;
    private String pres_name;
    private String pres_time;
    //private String [] drug_list = new String[5];


    private String user_db;
    private String user_info;
    private TextView textView1;
    private TextView textView2;
    private String[] user_DB = new String[8];
    private int t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__user__data);

        textView_Email = (TextView)findViewById(R.id.textview_email);
        textView_Age = (TextView)findViewById(R.id.textview2_age);
        textView_Phone = (TextView)findViewById(R.id.textview3_phone);
        textView_Diseases = (TextView)findViewById(R.id.textview4_diseases);
        textView_Medicine = (TextView)findViewById(R.id.textView5_medicine);

        //textView1 = (TextView)findViewById(R.id.textview_email);
        //textView2 = (TextView)findViewById(R.id.textview2_age);
        //final Random rando = new Random();

        Btn = (Button)findViewById(R.id.btn_pls);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView_Email.setText(user_email);
                textView_Age.setText(user_age);
                textView_Phone.setText(user_phone);
                textView_Diseases.setText(user_diseases);
                textView_Medicine.setText(user_medicine);

                /*
                t = rando.nextInt(7);

                textView1.setText(user_Medicine);
                textView1.setText(user_Diseases);
                textView1.setText(user_Email);
                textView1.setText(user_Age);
                textView1.setText(user_db);
                textView2.setText(user_DB[t]);
*/
            }
        });
        databaseReference.child("user_db").orderByKey().equalTo("email").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                user_email = dataSnapshot.getValue().toString();
                /*
                for(int t = 0;t<7;t++){
                    user_DB[t] = dataSnapshot.getValue().toString();
                }
                */
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

        databaseReference.child("user_db").orderByKey().equalTo("age").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                user_age = dataSnapshot.getValue().toString();
                //user_db = dataSnapshot.getValue().toString();
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

        databaseReference.child("user_db").orderByKey().equalTo("phone").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                user_phone = dataSnapshot.getValue().toString();
                //user_db = dataSnapshot.getValue().toString();
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

        databaseReference.child("user_db").orderByKey().equalTo("diseases").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                user_diseases =dataSnapshot.getValue().toString();
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

        databaseReference.child("user_db").orderByKey().equalTo("medicine").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                user_medicine = dataSnapshot.getValue().toString();
                //user_db = dataSnapshot.getValue().toString();
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

        databaseReference.child("Prescription").orderByKey().equalTo("Pres_name").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pres_name = dataSnapshot.getValue().toString();
                //user_db = dataSnapshot.getValue().toString();
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
        databaseReference.child("Prescription").orderByKey().equalTo("Pres_time").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pres_time = dataSnapshot.getValue().toString();
                //user_db = dataSnapshot.getValue().toString();
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
//        databaseReference.child("Prescription").orderByKey().equalTo("druglist").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                for(int i = 0 ; i< 5 ;i ++)
//                    drug_list[i] = dataSnapshot.getValue().toString();
//                //user_db = dataSnapshot.getValue().toString();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


    }
}
