package edu.sfsu.cs.orange.ocr;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.StringTokenizer;

//Ocr_result_check.xml안에 있는 listview(medicine_listview)에 들어갈 레이아웃 (row_listview.xml)
//CaptureActivity에서 Button의 Intent 받고
//OcrResultCheck에서 변경 된 Activitiy를 받아
public class OcrResultCheck extends Activity {
    ListView listview;
    String[] ocr_list;
    OCRAdapter ocr_adapter;
    Button sendBtn;
    DrugDTO[] drugDTOS;

    //FireBase 연결
    //private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ocr_result_check);

        listview = (ListView) findViewById(R.id.medicine_listview);
        ocr_adapter = new OCRAdapter();
        sendBtn = (Button) findViewById(R.id.btnConfirm);
        //sendAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, android.R.id.text1);

        Intent intent = getIntent(); //이 액티비티를 부른 인텐트를 받는다.
        String ocr_result = intent.getStringExtra("OCR_RESULT_TEXT");
        final String key = "temp_drug";

        StringTokenizer st = new StringTokenizer(ocr_result, "\n");
        ocr_list = new String[st.countTokens()];
        drugDTOS = new DrugDTO[st.countTokens()];
        //토크나이저 수행, 각 라인 별 인식 값이 ocr_list로 대입.
        final int count = st.countTokens();
        for (int j = 0; j < count; j++)
            ocr_list[j] = st.nextToken();

        listview.setAdapter(ocr_adapter);

        //--------인식된 번호 일단 보내기-----
        sendBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                for (int j = 0; j < count; j++){
                    //drugDTOS[j] = new DrugDTO(ocr_list[j]);
                    String s = ocr_list[j];
                    databaseReference.child("user_temp").child(key+j).setValue(s); //FireBase에 추가~찡끗~!
                }
                Intent intent = new Intent(OcrResultCheck.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });
    }

    class OCRAdapter extends BaseAdapter {//참고: http://blog.naver.com/PostView.nhn?blogId=rkdwnsdud555&logNo=220298826210
        @Override
        public int getCount() {
            return ocr_list.length;
        }

        @Override
        public Object getItem(int position) {
            return ocr_list[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView txview = new TextView(getApplicationContext());
            txview.setText(ocr_list[position]);
            txview.setTextSize(30.0f);
            txview.setTextColor(Color.BLACK);
            return txview;
        }
    }
}

