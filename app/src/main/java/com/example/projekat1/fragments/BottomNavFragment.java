package com.example.projekat1.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.projekat1.R;
import com.example.projekat1.viewPagers.PagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavFragment extends Fragment {

    private ViewPager viewPager;


    public BottomNavFragment() {
        super(R.layout.fragment_bottom_navigation);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view){
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(this.requireActivity().getSupportFragmentManager()));

        ((BottomNavigationView)view.findViewById(R.id.bottomNavigation)).setOnItemSelectedListener(item -> {//todo zasto je castovano umesto da napravimo objekat?
            switch (item.getItemId()) {
                // setCurrentItem metoda viewPager samo obavesti koji je Item trenutno aktivan i onda metoda getItem u adapteru setuje odredjeni fragment za tu poziciju
                case R.id.navigation_1: viewPager.setCurrentItem(PagerAdapter.FRAG_STAT_0, false);
                    System.out.println("U PRVOM");
                break;
                case R.id.navigation_2: viewPager.setCurrentItem(PagerAdapter.FRAG_LIST_2, false);
                    System.out.println("U DRUGOM");
                break;
                case R.id.navigation_3: viewPager.setCurrentItem(PagerAdapter.FRAG_NEW_1, false);
                    System.out.println("U TRECEM");
                break;
                case R.id.navigation_4: viewPager.setCurrentItem(PagerAdapter.FRAG_PROFILE_3, false);
                    System.out.println("U CET");
                break;
            }
            return true;
        });
    }
}
