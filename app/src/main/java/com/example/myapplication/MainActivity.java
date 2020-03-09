package com.example.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.myapplication.AlarmManager.MyBroadCastReceiver;
import com.example.myapplication.AlarmManager.MyService;
import com.example.myapplication.AlarmManager.PollReceiver;
import com.example.myapplication.AlarmManager.RingAlarm;
import com.example.myapplication.AlarmManager.Utils;

import java.util.Calendar;

import static android.app.job.JobInfo.getMinFlexMillis;
import static android.app.job.JobInfo.getMinPeriodMillis;

public class MainActivity extends AppCompatActivity {


    private static final int JOBID = 110;
    private JobScheduler myScheduler;
    private JobInfo myjobInfo;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
  // static BroadcastReceiver m_ScreenOffReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        PollReceiver.scheduleAlarms(this);
        // m_ScreenOffReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
////        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(Utils.getReciverInstance(), intentFilter);

        IntentFilter rebootFilter = new IntentFilter();
        rebootFilter.addAction(Intent.ACTION_REBOOT);
        rebootFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
        rebootFilter.addAction(Intent.ACTION_LOCKED_BOOT_COMPLETED);

        registerReceiver(Utils.getRebootReciverInstance(), rebootFilter);

//        Intent intent = new Intent(getApplicationContext(), RingAlarm.class);
//        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 12345, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
//        am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), 1000, pendingIntent);


//        Intent alarmIntent = new Intent(this, MyService.class);
//        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 1000, pendingIntent);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, 1000, pendingIntent);
//        } else {
//            alarmManager.set(AlarmManager.RTC_WAKEUP, 1000, pendingIntent);
//        }

//        ComponentName myComp = new ComponentName(this, MJobScheduler.class);
//        JobInfo.Builder myBuilder = new JobInfo.Builder(JOBID, myComp);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            myBuilder.setPeriodic(15 * 60 * 1000,15 * 60 * 1000);
//        } else {
//            myBuilder.setPeriodic(15 * 60 * 1000);
//        }
////                    myBuilder.setPeriodic(15 * 60 * 1000);
////        myBuilder.setMinimumLatency(1 * 1000); // wait at least
////        myBuilder.setOverrideDeadline(1 * 1000); // maximum delay
//
//        myBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
//        myBuilder.setPersisted(true);
//
//        myScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        myjobInfo = myBuilder.build();
//
//
//        myScheduler.schedule(myjobInfo);
//        Intent service = new Intent(getApplicationContext(), MJobScheduler.class);
//        startService(service);
      //  setAlarmManager();
        //LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("JOB_FINISHED"));
    }

//    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, intent.getStringExtra("result"), Toast.LENGTH_LONG).show();
//        }
//    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(m_ScreenOffReceiver);
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    public void setAlarmManager()
    {

        Calendar calendar = Calendar.getInstance();

        ComponentName myComp = new ComponentName(this, MJobScheduler.class);
        JobInfo.Builder myBuilder = new JobInfo.Builder(JOBID, myComp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            myBuilder.setPeriodic(15 * 60 * 1000,15 * 60 * 1000);
        } else {
            myBuilder.setPeriodic(15 * 60 * 1000);
        }
//                    myBuilder.setPeriodic(15 * 60 * 1000);
//        myBuilder.setMinimumLatency(1 * 1000); // wait at least
//        myBuilder.setOverrideDeadline(1 * 1000); // maximum delay

        myBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        myBuilder.setPersisted(true);

        myScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        myjobInfo = myBuilder.build();


        myScheduler.schedule(myjobInfo);


        Intent intent = new Intent(this, MJobScheduler.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000, pintent);

//
//        Intent ll24 = new Intent(this, MyReceiver.class);
//        PendingIntent recurringLl24 = PendingIntent.getBroadcast(this, 0, ll24, PendingIntent.FLAG_CANCEL_CURRENT);
//        AlarmManager alarms = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        alarms.setRepeating(AlarmManager.RTC_WAKEUP, 0, AlarmManager.INTERVAL_HOUR, recurringLl24); // Log repetition

//        int interval = 15 * 60 * 1000;
//        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, MJobScheduler.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
//                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        if (Build.VERSION.SDK_INT >= 23)
//        {
//            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, interval, pendingIntent);
//        }
//        else if (Build.VERSION.SDK_INT >= 19)
//        {
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, interval, pendingIntent);
//        }
//        else
//        {
//            alarmManager.set(AlarmManager.RTC_WAKEUP, interval, pendingIntent);
//        }
//        Intent service = new Intent(getApplicationContext(), MJobScheduler.class);
//        startService(service);

//        Intent myIntent = new Intent(this, MyReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,  0, myIntent, 0);
//
//        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, 60); // first time
//        long frequency= 60 * 1000; // in ms
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), frequency, pendingIntent);
//
////        IntentFilter intentFilter = new IntentFilter();
////        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
////        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
////        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
//        BroadcastReceiver m_ScreenOffReceiver = new MyReceiver();
//////        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
//        registerReceiver(m_ScreenOffReceiver, new IntentFilter("DUMMY"));
    }

}
