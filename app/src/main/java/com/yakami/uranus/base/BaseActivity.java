package com.yakami.uranus.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.yakami.uranus.utils.Tools;

/**
 * Created by Yakami on 2015/11/28.
 */
public class BaseActivity extends AppCompatActivity implements BaseViewImpl {

    private  MyHandler mHandler = new MyHandler(this);

    @Override
    public void showMessage(String str) {
        Tools.toast(getContext(), str);
    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }

    @Override
    public void sendMessage(String msgStr) {
        Message msg = new Message();
        Bundle b = new Bundle();
        b.putString("MsgType", msgStr);
        msg.setData(b);
        mHandler.sendMessage(msg);
    }

    @Override
    public void sendMessage(String msgStr, byte[] bytes) {
        Message msg = new Message();
        Bundle b = new Bundle();
        b.putString("MsgType", msgStr);
        b.putByteArray("data", bytes);
        msg.setData(b);
        mHandler.sendMessage(msg);
    }

    @Override
    public void handleMessage(Message msg) {
        Bundle b = msg.getData();
        String msgType = (String) b.get("MsgType");
        if (msgType != null) {
            switch(msgType) {
                case "showError":
                    String error = b.getString("data");
                    showMessage(error);
                    break;
            }
        }
    }

    @Override
    public void openActivity(Intent intent) {
        this.startActivity(intent);
    }

    static class MyHandler extends Handler {

        BaseViewImpl mIView;

        public MyHandler(BaseViewImpl iView) {
            mIView = iView;
        }

        @Override
        public void handleMessage(Message msg) {
            try {
                super.handleMessage(msg);
                final Message tmpMsg = msg;
                new Thread() {
                    @Override
                    public void run() {
                        mIView.handleMessage(tmpMsg);
                    }
                }.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
