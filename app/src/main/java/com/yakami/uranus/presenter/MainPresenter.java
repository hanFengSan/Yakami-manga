package com.yakami.uranus.presenter;

import com.yakami.uranus.base.BasePresenter;
import com.yakami.uranus.view.activity.MainActivity;

/**
 * Created by Yakami on 2015/11/28.
 */
public class MainPresenter extends BasePresenter {

    public MainActivity mMainView;

    public MainPresenter(MainActivity mainView) {
        mMainView = mainView;
        mBaseView = mainView;
    }
}
