package com.demo.intelsdk;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.crittercism.app.Crittercism;

import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {

    final String TAG = "IntelSDK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Crittercism.initialize(getApplicationContext(), "3af5b80b383b48af914ab178cb3f244d00555300");  // CN420 - aron OG
        Crittercism.setLoggingLevel(Crittercism.LoggingLevel.Debug);
        Crittercism.setUsername("demo1");
        Crittercism.leaveBreadcrumb("BreadcrumbSampleApplication");
        Crittercism.beginUserFlow("UserFlowSampleApplication");
        Crittercism.setUserFlowValue("UserFlowSampleApplication", 12345);
        Crittercism.sendAppLoadData();

        Log.i(TAG, "App crashed on previous run:  " + Crittercism.didCrashOnLastLoad());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void crashAppOnClick(View view) throws InterruptedException {
        Toast.makeText(this, "Crashing app now!", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Crash button clicked");
        Thread.sleep(2000);
        boolean[] array = {false, true};
        if (array[10]) {
            Log.e(TAG, "Array index out of bounds exception did not occur");
            Crittercism.leaveBreadcrumb("CrashBreadcrumb");
            Crittercism.endUserFlow("UserFlowSampleApplication");
            Crittercism.sendAppLoadData();
        }
    }

    public void handleExceptionOnClick(View view) {
        Toast.makeText(this, "Handling NullPointerException now!", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Handle exception button clicked");
        try {
            throw new NullPointerException();
        }
        catch (NullPointerException npe) {
            Log.i(TAG, "Caught NullPointerException");
            Crittercism.leaveBreadcrumb("HandleExceptionBreadcrumb");
            Crittercism.endUserFlow("UserFlowSampleApplication");
            Crittercism.logHandledException(npe);
            Crittercism.sendAppLoadData();
        }
    }
}