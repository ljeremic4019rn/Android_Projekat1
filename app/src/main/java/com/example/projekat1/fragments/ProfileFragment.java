package com.example.projekat1.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projekat1.R;
import com.example.projekat1.activities.MainActivity;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    private TextView username;
    private TextView email;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {//todo popravi xml file
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view){
        username = (TextView) view.findViewById(R.id.usernameTV);
        email = (TextView) view.findViewById(R.id.emailTV);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(this.getActivity().getPackageName(), Context.MODE_PRIVATE);


        username.setText(sharedPreferences.getString(MainActivity.LOGGED_USER,""));
        System.out.println(sharedPreferences.getString(MainActivity.LOGGED_USER,""));
        email.setText(sharedPreferences.getString(MainActivity.LOGGED_MAIL,""));


    }
}
