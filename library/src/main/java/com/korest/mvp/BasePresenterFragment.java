package com.korest.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by orest.kyrylchuk on 18.09.2015.
 */
public abstract class BasePresenterFragment<PR extends BasePresenter<PV>, PV extends PresenterView> extends Fragment {

    private static final String FRAGMENT_ID = "fragment_id";
    private static final AtomicLong ID = new AtomicLong();

    // unique identifier for each fragment
    protected long fragmentId;

    // flag to know if fragment will be restored
    protected boolean willBeRestored;

    // presenter field
    protected PR presenter;

    public BasePresenterFragment() {
        final Class<PR> presenterClass = (Class<PR>) ((ParameterizedType)getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        presenter = PresentersManager.getInstance().getPresenter(fragmentId, presenterClass);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {
            fragmentId = ID.getAndIncrement();
        } else {
            fragmentId = savedInstanceState.getLong(FRAGMENT_ID);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        willBeRestored = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // bind presenter view after it is created, it should be of type PV
        presenter.bindPresenterView((PV)this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(FRAGMENT_ID, fragmentId);

        willBeRestored = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // unbind presenter view after view is destroyed
        presenter.unbindPresenterView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(!willBeRestored) {
            PresentersManager.getInstance().onDestroy(fragmentId);
        }
    }
}