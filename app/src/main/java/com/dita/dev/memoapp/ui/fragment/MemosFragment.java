package com.dita.dev.memoapp.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dita.dev.memoapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemosFragment extends Fragment {


    public MemosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memos, container, false);
    }

}
