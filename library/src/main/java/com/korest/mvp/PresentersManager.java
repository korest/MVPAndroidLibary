package com.korest.mvp;

import android.util.Log;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by orest.kyrylchuk on 18.09.2015.
 */
public class PresentersManager {

    private static final String TAG = PresentersManager.class.getName();

    private final ConcurrentHashMap<Long, BasePresenter<?>> presenters = new ConcurrentHashMap<>();

    private static class FragmentPresentersManagerHolder {
        public static final PresentersManager INSTANCE = new PresentersManager();
    }

    public static PresentersManager getInstance() {
        return FragmentPresentersManagerHolder.INSTANCE;
    }

    public <T extends BasePresenter> T getPresenter(long fragmentId, Class<T> clazz) {
        return (T) getPresenterFromMap(fragmentId, clazz);
    }

    private Object getPresenterFromMap(long fragmentId, Class<?> clazz) {
        BasePresenter<?> basePresenter = presenters.get(fragmentId);
        if (basePresenter == null) {
            try {
                final BasePresenter<?> newInstance = (BasePresenter<?>) clazz.newInstance();
                basePresenter = presenters.putIfAbsent(fragmentId, newInstance);
                if(basePresenter == null) {
                    basePresenter = newInstance;
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }

        return basePresenter;
    }

    public void onDestroy(long fragmentId) {
        final BasePresenter<?> basePresenter = presenters.remove(fragmentId);
        if(basePresenter != null) {
            basePresenter.onDestroy();
        }
    }

}
