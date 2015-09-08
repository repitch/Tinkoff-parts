package com.repitch.tinkoff.ui.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.repitch.tinkoff.R;
import com.repitch.tinkoff.ui.fragment.FABFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        launchFragmentNoBackStack(
                FABFragment.newInstance(),
                FABFragment.getFragmentTag()
        );
    }

}
