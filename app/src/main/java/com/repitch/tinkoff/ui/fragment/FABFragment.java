package com.repitch.tinkoff.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.clans.fab.FloatingActionMenu;
import com.repitch.tinkoff.R;

/**
 * Created by repitch on 08.09.15.
 */
public class FABFragment extends BaseFragment {

    private FrameLayout mFABSubstrate;
    private FloatingActionMenu mFAM;

    public static FABFragment newInstance() {
        return new FABFragment();
    }

    @Override
    public String getFragmentTitle() {
        return "FAB";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fab, container, false);

        mFABSubstrate = (FrameLayout) rootView.findViewById(R.id.fabSubstrate);
        mFAM = (FloatingActionMenu) rootView.findViewById(R.id.fam);

        mFABSubstrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFAM();
            }
        });

        mFAM.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFAM.isOpened()){
                    closeFAM();
                } else {
                    openFAM();
                }
            }
        });

        return rootView;
    }

    private void openFAM() {
        mFABSubstrate.setClickable(true);
        mFABSubstrate.setAlpha(0.0f);
        mFABSubstrate.animate()
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        mFABSubstrate.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mFABSubstrate.setVisibility(View.VISIBLE);
                    }

                });
        mFAM.open(true);
    }

    private void closeFAM() {
        mFABSubstrate.setClickable(false);
        mFABSubstrate.setAlpha(1.0f);
        mFABSubstrate.animate()
                .alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        mFABSubstrate.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mFABSubstrate.setVisibility(View.INVISIBLE);
                    }
                });
        mFAM.close(true);
    }
}
