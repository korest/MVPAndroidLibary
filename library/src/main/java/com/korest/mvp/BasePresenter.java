package com.korest.mvp;

import java.util.UUID;

/**
 * Created by orest.kyrylchuk on 18.09.2015.
 */
public abstract class BasePresenter<PV extends PresenterView> {

    protected PV presenterView;

    public void bindPresenterView(PV presenterView) {
        this.presenterView = presenterView;
    }

    public void unbindPresenterView() {
        this.presenterView = null;
    }

    protected void onDestroy() {
        // do some custom stuff here
    }
}