package com.dita.dev.memoapp.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dita.dev.memoapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectionsFragment extends Fragment {


    public ConnectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentTransaction transaction1 = getFragmentManager().beginTransaction().replace(R.id.groupContainer, new GroupFragment());
        FragmentTransaction transaction2 = getFragmentManager().beginTransaction().replace(R.id.individualContainer, new IndividualsFragment());
        transaction1.commit();
        transaction2.commit();
        return inflater.inflate(R.layout.fragment_connections, container, false);
    }

}
