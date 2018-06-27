package edu.sfsu.cs.orange.ocr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MedicineTime extends Activity {
    Button okayBtn;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_time);//medicineTime.xml을 만들어 거기 있는 확인 버튼 누르면 Main으로 돌아오게끔
        okayBtn = (Button) findViewById(R.id.btnok);
        okayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MedicineTime.this, HomeActivity.class);
                startActivity(myIntent);
            }
        });
    }
}