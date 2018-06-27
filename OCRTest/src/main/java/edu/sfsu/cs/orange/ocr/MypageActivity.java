package edu.sfsu.cs.orange.ocr;

import android.app.Activity;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.google.firebase.database.DatabaseReference;

import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;

import java.util.ArrayList;
import java.util.List;

public class MypageActivity extends AppCompatActivity {

    public TextView textview_User;
    public ListView prescription_List;

    private List<UserDTO> userDTOs = new ArrayList<>();
    private DataSnapshot mPostReference;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
    }
}
       // firebaseDatabase = FirebaseDatabase.getInstance();

//        firebaseDatabase.getReference().child("user_db").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                userDTOs.clear();
//
//            }
//
//            @Override
//            public void onCancelled(DataSnapshot dataSnapshot) {
//            }
//        });
//
//    }
//}