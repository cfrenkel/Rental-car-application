package com.example.this_user.ourproject5778_9075_4711_02.controller.fragments.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.this_user.ourproject5778_9075_4711_02.R;

public class BranchesFragment extends Fragment {

    public BranchesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_branches, container, false);

        //branch list fragment in the first patr
        Fragment fragment = new BranchListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment).commit();
        return rootView;
    }

}
