package com.yakami.uranus.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Message;

/**
 * Created by Yakami on 2015/11/27.
 */
public interface BaseViewImpl {

    void showMessage(String str);

    Context getContext();

    void sendMessage(String type);

    void sendMessage(String msgStr, byte[] data);

    Resources getResources();

    void openActivity(Intent intent);

    void handleMessage(Message msg);

}


