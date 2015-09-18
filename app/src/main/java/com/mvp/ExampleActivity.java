package com.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.korest.mvp.example.R;
import com.mvp.fragment.ExampleFragment;

/**
 * Created by orest.kyrylchuk on 18.09.2015.
 */
public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_example);

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, Fragment.instantiate(this, ExampleFragment.class.getName()))
                    .commit();
        }
    }
}
