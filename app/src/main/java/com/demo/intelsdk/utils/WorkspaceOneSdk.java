package com.demo.intelsdk.utils;

import android.content.Context;
import android.util.Log;

import com.airwatch.sdk.AirWatchSDKException;
import com.airwatch.sdk.SDKManager;

public class WorkspaceOneSdk {
    private final Context mContext;
    private final SDKManager mSDKManager;


    public WorkspaceOneSdk(Context context, SDKManager sdkManager) {
        this.mContext = context;
        this.mSDKManager = createSDKManager();
    }

    private SDKManager createSDKManager() {
        SDKManager sdkManager = null;

        try {
            sdkManager = SDKManager.init(mContext.getApplicationContext());
        } catch (AirWatchSDKException e) {
            Log.e("WorkspaceOneSdk.createSDKManager Exception: ", e.getMessage());
            e.printStackTrace();
        }
        return sdkManager;
    }

    public WorkspaceOneUemData getData() {
        WorkspaceOneUemData workspaceOneUemData = new WorkspaceOneUemData();

        try {
            workspaceOneUemData.deviceSerial = mSDKManager.getDeviceSerialId();
            workspaceOneUemData.deviceUser = mSDKManager.getEnrollmentUsername();
        } catch (AirWatchSDKException e) {
            Log.e("WorkspaceOneSdk.getData Exception: ", e.getMessage());
        }
        return workspaceOneUemData;
    }
}