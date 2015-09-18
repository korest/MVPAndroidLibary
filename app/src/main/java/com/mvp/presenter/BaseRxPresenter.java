package com.mvp.presenter;

import com.korest.mvp.BasePresenter;
import com.korest.mvp.PresenterView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by orest.kyrylchuk on 18.09.2015.
 */
public abstract class BaseRxPresenter<PV extends PresenterView> extends BasePresenter<PV> {

    protected final CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }
}
