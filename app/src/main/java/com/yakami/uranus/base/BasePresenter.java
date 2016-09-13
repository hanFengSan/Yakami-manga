package com.yakami.uranus.base;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yakami on 2015/11/28.
 */
public class BasePresenter implements BasePresenterImpl {

    protected BaseViewImpl mBaseView;

    @Override
    public Context getContext() {
        return mBaseView.getContext();
    }

    @Override
    public void sendMessage(String msg) {
        mBaseView.sendMessage(msg);
    }

    @Override
    public void sendMessage(String msg, byte[] bytes) {
    }

    @Override
    public void showMessage(String str) {
        mBaseView.showMessage(str);
    }
}
