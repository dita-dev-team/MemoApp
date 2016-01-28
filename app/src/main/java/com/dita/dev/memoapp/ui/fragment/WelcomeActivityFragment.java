package com.dita.dev.memoapp.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dita.dev.memoapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class WelcomeActivityFragment extends BaseFragment {

    public WelcomeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }
}
