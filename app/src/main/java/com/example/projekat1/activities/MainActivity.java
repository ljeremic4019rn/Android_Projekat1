package com.example.projekat1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.projekat1.R;
import com.example.projekat1.User;
import com.example.projekat1.fragments.FirstFragment;
import com.example.projekat1.fragments.LogInFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public static final String LOGGED_USER = "loggeduser";
    private boolean loggedIn;
    private String logged;
    public static final Map<String, User> users = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        users.put("admin_marko", new User("admin_marko","1234","marko@gmail.com"));
        users.put("luka", new User("luka","1234","luka@gmail.com"));
        users.put("marko", new User("marko","1234","marko@gmail.com"));

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {

            setContentView(R.layout.activity_main);
            logged = sharedPreferences.getString(LOGGED_USER, "");

            if (logged.equals("")){
                loggedIn = false;
            }
            else loggedIn = true;

            checkLogin();
            return false;
        });
    }


    private void checkLogin(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (loggedIn){
            transaction.add(R.id.mainFragContainer, new FirstFragment());//todo change to main fragment
        }
        else {
            transaction.add(R.id.mainFragContainer, new LogInFragment());
        }
        transaction.commit();
    }
}