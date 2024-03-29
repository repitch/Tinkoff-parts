package com.repitch.tinkoff.ui.activities;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;

import com.repitch.tinkoff.R;

public abstract class BaseActivity extends ActionBarActivity {

    protected Fragment mCurrentFragment;

    public void launchDialog(DialogFragment dialogFragment, String tag) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        dialogFragment.show(ft, tag);
    }

    public void launchFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, tag)
//        ft.add(R.id.content_frame, fragment, fragment.getTag())
                .addToBackStack(tag)
                .commit();
    }

    public void launchFragmentNoBackStack(Fragment fragment, String tag) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, tag)
                .commit();
    }

    public void clearBackStack() {
        FragmentManager fm = getFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("STATES", "onCreate by " + this.getClass());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    protected void onStop() {
        Log.e("STATES", "onStop by " + this.getClass());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("STATES", "onDestroy by " + this.getClass());
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.e("STATES", "onPause by " + this.getClass());
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e("STATES", "onSaveInstanceState by " + this.getClass());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        Log.e("STATES", "onStart by " + this.getClass());
        super.onStart();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        mCurrentFragment = fragment;
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
        Log.e("BACK", "back is pressed!");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return goUp();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean goUp() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            // pop
            getFragmentManager().popBackStack();
        } else {
            NavUtils.navigateUpFromSameTask(this);
        }
        shouldDisplayHomeUp();
        return true;
    }

    // methods for back stack toggle
    private final FragmentManager.OnBackStackChangedListener
            mOnBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            shouldDisplayHomeUp();
        }
    };

    protected void showBackStackToggle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        shouldDisplayHomeUp();
        getFragmentManager().addOnBackStackChangedListener(mOnBackStackChangedListener);
    }

    public void shouldDisplayHomeUp() {
        boolean canBack;
        Log.e("BACK", "getParentAct: " + NavUtils.getParentActivityName(this));
        if (getClass().getName().equals(MainActivity.class.getName())) {
            // главный класс
            canBack = getFragmentManager().getBackStackEntryCount() > 0;
            Log.e("BACK", "This is MainMenuActivity!");
        } else {
            canBack = true;
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(canBack);
        }
    }

}