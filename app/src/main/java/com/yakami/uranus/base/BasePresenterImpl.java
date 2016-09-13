package com.yakami.uranus.base;

import android.content.Context;

/**
 * Created by Yakami on 2015/11/28.
 */
public interface BasePresenterImpl {

    void sendMessage(String msg);

    void sendMessage(String msg, byte[] data);

    Context getContext();

    void showMessage(String str);
}
