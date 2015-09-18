package com.mvp.fragment;

import android.widget.Toast;

import com.korest.mvp.BasePresenterFragment;
import com.mvp.presenter.ExamplePresenter;
import com.mvp.presenter.view.ExamplePresenterView;

/**
 * Created by orest.kyrylchuk on 18.09.2015.
 */
public class ExampleFragment extends BasePresenterFragment<ExamplePresenter, ExamplePresenterView> implements ExamplePresenterView {

    @Override
    public void onResume() {
        super.onResume();
        presenter.doSomeStuff();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
