package edu.sfsu.cs.orange.ocr;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DetailActivity extends AppCompatActivity {
    //FireBase 연결
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private Context context;

    //mname : 약품명 , mdescription: description / musage; 용법 용량 / mcaution: 주의사항 / mpro : 효능 효과 / mnumber : 약품 번호
    TextView mname,mdescription,mnumber,mpro,musage,mcaution;
    ImageView mimageiv; //알약 이미지
    Button AddBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setTitle("Drug Detail");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowCustomEnabled(true);

        mdescription = findViewById(R.id.dphone);
        mname = findViewById(R.id.dname);
        mpro = findViewById(R.id.dpro);
        musage = findViewById(R.id.dusage);
        mcaution = findViewById(R.id.dcaution);
        mimageiv = findViewById(R.id.dthumbnail);
        AddBtn = findViewById(R.id.addMedicineBtn);

        Intent intent = getIntent();
        String drug_name = intent.getStringExtra("NAME");
        String drug_description = intent.getStringExtra("DESCRIPTION");
        String drug_image = intent.getStringExtra("DRUGIMAGE");
        final String drug_number = intent.getStringExtra("DRUGNUMBER");
        String drug_pro = intent.getStringExtra("DRUGPRO");
        String drug_caution = intent.getStringExtra("DRUGCAUTION");
        String drug_usage = intent.getStringExtra("DRUGUSAGE");
        final String key = intent.getStringExtra("NAME");

        mname.setText(drug_name);
        mdescription.setText(drug_description);
        //mnumber.setText(drug_number);
        musage.setText(drug_usage);
        mpro.setText(drug_pro);
        mcaution.setText(drug_caution);
        Glide.with(this).load(drug_image).into(mimageiv);

        final String ANEMO = "아네모정";
        if(key.equals(ANEMO))
            Toast.makeText(getApplicationContext(), "선택하신 약품은 당뇨에는 적합하지 않은 약물입니다.", Toast.LENGTH_LONG).show();
        AddBtn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                    databaseReference.child("user_temp").child(key).setValue(drug_number); //FireBase에 추가~찡끗~!
            }
        });
    }
    public boolean onSupportNavigationUp(){ //상단바 뒤로 갈 때
        onBackPressed();
        return true;
    }

}
