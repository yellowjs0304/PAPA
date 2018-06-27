package edu.sfsu.cs.orange.ocr;
//참고 사이트 : http://name267.tistory.com/46
//참고 2 : http://www.kandroid.org/board/board.php?board=AndroidTechQnA&command=body&no=4362
//제작된 클래스 : Alarm.java , AlarmReceiver.java / notification.xml
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Alarm extends Activity implements DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    //알람 관련 멤버 변수
    //알람 매니저
    private AlarmManager mManager;
    //설정 일시
    private GregorianCalendar mCalendar;
    //일자 설정 클래스
    private DatePicker mDate;
    //시작 설정 클래스
    private TimePicker mTime;
    private NotificationManager mNotification;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //알람 매니저를 취득
        mManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //현재 시각을 취득
        mCalendar = new GregorianCalendar();
        Log.i("HelloAlarmActivity", mCalendar.getTime().toString());

        //SET 버튼. 리셋버튼의 리스너를 등록
        setContentView(R.layout.notification);//xml 파일 연결된건 notification.xml파일을 일단 연결해 놓았음
        Button btn_Set = (Button) findViewById(R.id.set);
        btn_Set.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setAlarm();
                Toast.makeText(getApplicationContext(), "알람 시간이 설정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        Button btn_Reset = (Button) findViewById(R.id.reset);
        btn_Reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetAlarm();
                Toast.makeText(getApplicationContext(), "알람 시간이 초기화 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        mDate = (DatePicker) findViewById(R.id.date_picker);
        mDate.init(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH), this);
        mTime = (TimePicker)findViewById(R.id.time_picker);
        mTime.setCurrentHour(mCalendar.get(Calendar.HOUR_OF_DAY));
        mTime.setCurrentMinute(mCalendar.get(Calendar.MINUTE));
        mTime.setOnTimeChangedListener(this);
    }
    //알람 설정
    private void setAlarm(){
        mManager.set(AlarmManager.RTC_WAKEUP,mCalendar.getTimeInMillis(),pendingintent());
    }

    //알람 해제
    private void resetAlarm(){
        mManager.cancel(pendingintent());
    }

    //알람 설정 시각에 발생하는 Intent 작성
    private PendingIntent pendingintent(){
        Intent inte = new Intent(getApplicationContext(),MedicineTime.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,inte,0);
        return pi;
    }

    //일자 설정 클래스의 상태 변화 리스너
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth){
        mCalendar.set(year,monthOfYear,dayOfMonth,mTime.getCurrentHour(),mTime.getCurrentMinute());
        Log.i("HelloAlarmActivity",mCalendar.getTime().toString());
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute){
        mCalendar.set(mDate.getYear(),mDate.getMonth(),mDate.getDayOfMonth(),hourOfDay,minute);
        Log.i("HelloAlarmActivity",mCalendar.getTime().toString());
    }
}
//        Toast.makeText(getApplicationContext(),R.string.app_name,Toast.LENGTH_SHORT).show();
//                mNotification = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//                NotificationCompat.Builder mBuilder= new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setContentTitle("PAPA알리미")
//                .setContentText("알람ㄱㄱ")
//                .setAutoCancel(true)
//                .setTicker("PAPA알리미-소식왔어요!")
//                .setDefaults(Notification.DEFAULT_SOUND)
//                ;
//                Toast.makeText(getApplicationContext(),"알람발생!!!!!",Toast.LENGTH_SHORT).show();
//                mNotification.notify(0,mBuilder.build());

