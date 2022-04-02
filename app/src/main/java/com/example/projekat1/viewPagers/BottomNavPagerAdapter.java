package com.example.projekat1.viewPagers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projekat1.fragments.NewTicketFragment;
import com.example.projekat1.fragments.ProfileFragment;
import com.example.projekat1.fragments.StatisticsFragment;
import com.example.projekat1.fragments.tabs.TabsFragment;


public class BottomNavPagerAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 4;
    public static final int FRAG_STAT_0 = 0;
    public static final int FRAG_NEW_1 = 1;
    public static final int FRAG_LIST_2 = 2;
    public static final int FRAG_PROFILE_3 = 3;


    public BottomNavPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case FRAG_STAT_0: fragment = new StatisticsFragment();
                System.out.println("-----pozicija: " + position);
            break;
            case FRAG_LIST_2: fragment = new NewTicketFragment();//todo ima bug da su frag list i frag new zamenjeni, okrenuo sam brojeve tako da radi, ali je cudno
//                System.out.println("U DRUGOM");
                System.out.println("----pozicija: " + position);
            break;
            case FRAG_NEW_1: fragment = new TabsFragment();
//                System.out.println("U TRECEM");
                System.out.println("----pozicija: " + position);
            break;
            case FRAG_PROFILE_3: fragment = new ProfileFragment();
//                System.out.println("U CET");
                System.out.println("----pozicija: " + position);
                break;
            default: fragment = new StatisticsFragment();
                System.out.println("----pozicija: " + "U default");
            break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch(position){
//            case FRAG_STAT: return "asdf";
//            case FRAG_NEW: return "2";
//            case FRAG_LIST: return "3";
//            default: return "4";
//        }
//    }
}
