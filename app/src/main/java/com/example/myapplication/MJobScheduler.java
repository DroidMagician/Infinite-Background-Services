package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class MJobScheduler extends JobService {

    //private MJobExecutor executor;
    private static BroadcastReceiver m_ScreenOffReceiver;
    String TAG = getClass().getSimpleName();

    private static final int JOBID = 110;
    private JobScheduler myScheduler;
    private JobInfo myjobInfo;

    @Override
    public boolean onStartJob(final JobParameters params) {
        registerScreenOffReceiver();
        Log.i(TAG, "*** onStartJob: " );
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        //executor.cancel(true);
        unregisterReceiver(m_ScreenOffReceiver);
        Log.i(TAG, "*** onStopJob: " );


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
        Intent service = new Intent(getApplicationContext(), MJobScheduler.class);
        startService(service);

        return false;
    }



    private void registerScreenOffReceiver() {
        m_ScreenOffReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Broadcast "+intent.getAction(), Toast.LENGTH_SHORT).show();
              //  Log.i(TAG, "*** onReceive:  Network change ");
                Log.i(TAG, "*** ACTION_SCREEN_OFF"+"Action is:"+intent.getAction());
                // do something, e.g. send Intent to main app
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);

//        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(m_ScreenOffReceiver, intentFilter);
    }
}