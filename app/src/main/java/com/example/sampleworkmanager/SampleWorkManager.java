package com.example.sampleworkmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SampleWorkManager extends Worker {
    private static final String TAG = "SampleWorkManager";

    public SampleWorkManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Data inputData = getInputData();

        int number = inputData.getInt("number", 0);
        String name = inputData.getString("name");

        Log.d(TAG, "doWork: Currently executing: " + name);

        for(int i=0; i<=number; i++){
            Log.d(TAG, "doWork: Current status is: " + i + "/" + number);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Result.success();
    }
}
