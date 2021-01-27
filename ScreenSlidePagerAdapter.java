package com.example.whereileftoff;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 4;

    public ScreenSlidePagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SeriesFragment();
            case 1:
                return new MoviesFragment();
            case 2:
                return new BooksFragment();
            default:
                return new ComicsFragment();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Series";
            case 1:
                return "Movies";
            case 2:
                return "Books";
            default:
                return "Comics";
        }
    }
}
