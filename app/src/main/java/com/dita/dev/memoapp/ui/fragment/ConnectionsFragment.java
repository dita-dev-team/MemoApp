package com.dita.dev.memoapp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
        View view = inflater.inflate(R.layout.fragment_connections, container, false);
        FragmentTransaction transaction1 = getFragmentManager().beginTransaction().replace(R.id.groupContainer, new GroupFragment());
        FragmentTransaction transaction2 = getFragmentManager().beginTransaction().replace(R.id.individualsContainer, new IndividualsFragment());
        transaction1.commit();
        transaction2.commit();

        return view;
    }

    public static class CustomFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

}
