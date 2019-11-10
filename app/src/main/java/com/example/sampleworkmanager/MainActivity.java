package com.example.sampleworkmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWorkerManager();
    }

    private void initWorkerManager(){
        Log.d(TAG, "initWorkerManager: started");

        Data data = new Data.Builder()
                        .putInt("number", 10)
                        .putString("name", "Work for me baby")
                        .build();

        Constraints constraints = new Constraints.Builder()
                                        .setRequiredNetworkType(NetworkType.UNMETERED)
                                        .setRequiresBatteryNotLow(true)
                                        .setRequiresCharging(false)
                                        .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SampleWorkManager.class)
                                                    .setInputData(data)
                                                    .setConstraints(constraints)
                                                    .build();
        //WorkManager.getInstance().enqueue(oneTimeWorkRequest);

//        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
//                SampleWorkManager.class, 1, TimeUnit.SECONDS
//        ).setInputData(data)
//         .setConstraints(constraints)
//         .build();
//
//        WorkManager.getInstance().enqueue(periodicWorkRequest);

        OneTimeWorkRequest anotherTimeWorkRequest = new OneTimeWorkRequest.Builder(
                SampleWorkManager.class).
                setInputData(new Data.Builder().putInt("number", 5).putString("name", "Work for me bebe").build())
                .setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.NOT_REQUIRED).build())
                .build();

        WorkManager.getInstance().beginWith(oneTimeWorkRequest)
                .then(anotherTimeWorkRequest)
                .enqueue();
    }
}
