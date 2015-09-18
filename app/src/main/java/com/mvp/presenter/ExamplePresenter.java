package com.mvp.presenter;

import com.korest.mvp.BasePresenter;
import com.mvp.presenter.view.ExamplePresenterView;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by orest.kyrylchuk on 18.09.2015.
 */
public class ExamplePresenter extends BaseRxPresenter<ExamplePresenterView> {

    public void doSomeStuff() {
        compositeSubscription.add(Observable.from(Arrays.asList("Hello world !!!"))
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        presenterView.showToast(s);
                    }
                }));
    }

}
