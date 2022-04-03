package com.example.projekat1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.projekat1.R;
import com.example.projekat1.fragments.EditTicketFragment;
import com.example.projekat1.fragments.TicketDetailsFragment;
import com.example.projekat1.models.Ticket;

public class SingleFragmentDisplay extends AppCompatActivity {

    private Ticket ticket;
    private boolean failsafe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment_display);
        initFragment();
    }

    private void initFragment(){
        ticket = (Ticket) getIntent().getSerializableExtra("ticket");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.singleFratView, new TicketDetailsFragment(ticket));
        transaction.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!failsafe){//za slucaj da se ubila aplikacija ovo ce idalje da sacuva podatke
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
            sharedPreferences//stavimo koji je user trenutno ulogovan
                    .edit()
                    .putString(MainActivity.MAIN_FRAGMENT, ticket.toString())
                    .apply();
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        sharedPreferences//stavimo koji je user trenutno ulogovan
                .edit()
                .putString(MainActivity.MAIN_FRAGMENT, ticket.toString())
                .apply();
        setResult(RESULT_OK);
        failsafe = true;
        finish();
    }
}