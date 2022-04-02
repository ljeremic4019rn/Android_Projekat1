package com.example.projekat1.viewPagers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projekat1.fragments.PlaceHolderFragment;
import com.example.projekat1.fragments.tabs.DoneFragment;
import com.example.projekat1.fragments.tabs.InProgessFragment;
import com.example.projekat1.fragments.tabs.ToDoFragment;

import org.jetbrains.annotations.Nullable;


public class TabPagerAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 3;
    public static final int FRAG_STAT_0 = 0;
    public static final int FRAG_NEW_1 = 1;
    public static final int FRAG_LIST_2 = 2;


    public TabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case FRAG_STAT_0: fragment = new ToDoFragment();
                System.out.println("-----pozicija: " + position);
            break;
            case FRAG_NEW_1: fragment = new InProgessFragment();//todo ima bug da su frag list i frag new zamenjeni, okrenuo sam brojeve tako da radi, ali je cudno
//                System.out.println("U DRUGOM");
                System.out.println("----pozicija: " + position);
            break;
            default: fragment = new DoneFragment();
                System.out.println("----pozicija: " + "U default");
            break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case FRAG_STAT_0: return "Todo";
            case FRAG_NEW_1: return "In Progress";
            default: return "Done";
        }
    }
}
