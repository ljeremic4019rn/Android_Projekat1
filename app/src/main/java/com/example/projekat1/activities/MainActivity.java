package com.example.projekat1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.projekat1.R;
import com.example.projekat1.User;
import com.example.projekat1.fragments.BottomNavFragment;
import com.example.projekat1.fragments.LogInFragment;

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

//
//        setContentView(R.layout.activity_main);//ZA TESTIRANJE
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.mainFragContainer, new BottomNavFragment());
//        transaction.commit();

    }



    private void checkLogin(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (loggedIn){
            transaction.add(R.id.mainFragContainer, new BottomNavFragment());//todo change to main fragment
        }
        else {
            transaction.add(R.id.mainFragContainer, new LogInFragment());
        }
        transaction.commit();
    }
}