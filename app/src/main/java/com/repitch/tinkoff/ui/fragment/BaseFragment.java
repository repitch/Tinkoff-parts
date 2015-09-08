package com.repitch.tinkoff.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public abstract class BaseFragment extends Fragment {

    private static String TAG;

    protected Activity mActivity;
    protected boolean mUseFAB;
    protected boolean mShowBackBtn;

    public abstract String getFragmentTitle();

    public static String getFragmentTag() {
        return TAG;
    }

    @Override
    public void onAttach(Activity activity) {
        Log.e("STATES", "onAttach (frag) by " + this.getClass());
        super.onAttach(activity);
        mActivity = activity;
        mUseFAB = false;
        mShowBackBtn = true;
        TAG = getClass().getSimpleName();
    }

    @Override
    public void onDetach() {
        Log.e("STATES", "onDetach (frag) by " + this.getClass());
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onResume() {
        Log.e("STATES", "onResume (frag) by " + this.getClass());
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.e("STATES", "onDestroyView (frag) by " + this.getClass());
        super.onDestroyView();
    }

    protected void hideKeyboard() {
        View view = mActivity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
