package edu.sfsu.cs.orange.ocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button1 = (Button) findViewById(R.id.btnJoin);
        button1.setOnClickListener(new View.OnClickListener() {
            public void  onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this,JoinActivity.class);
                startActivity(myIntent);
            }
        });

        Button button2 = (Button) findViewById(R.id.btnLogin);
        button2.setOnClickListener(new View.OnClickListener() {
            public void  onClick(View v) {
                Intent Intent2 = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(Intent2);
            }
        });
    }
}
