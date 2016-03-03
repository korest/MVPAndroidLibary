package com.mvp.presenter;

import com.korest.mvp.BasePresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by orest.kyrylchuk on 18.09.2015.
 */
public abstract class BaseRxPresenter<PV> extends BasePresenter<PV> {

    protected final CompositeSubscription compositeSubscription = new CompositeSubscription();

    /**
     * call this method when you have Subscription to safely remove it when on destroy
     * @param subscription
     */
    protected void registerSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }
}
