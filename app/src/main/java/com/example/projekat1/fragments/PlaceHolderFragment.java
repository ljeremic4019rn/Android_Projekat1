package com.example.projekat1.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projekat1.R;


public class PlaceHolderFragment extends Fragment {

    public PlaceHolderFragment() {
        super(R.layout.fragment_placeholder);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FragmentTransaction transaction = this.requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFragContainer, new EditTicketFragment());
        transaction.addToBackStack(null);
        transaction.commit();

    }

}