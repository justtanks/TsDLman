package com.ts.databending.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ts.databending.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class First2ragment extends Fragment {


    public First2ragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first2ragment, container, false);
    }

}
