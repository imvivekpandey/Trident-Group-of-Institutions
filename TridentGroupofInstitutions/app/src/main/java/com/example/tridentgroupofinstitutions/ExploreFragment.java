package com.example.tridentgroupofinstitutions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;


public class ExploreFragment extends Fragment {

    ViewPager viewPager;
    MyAdapter mViewPagerAdapter;
     int[] images={R.drawable.slide1,R.drawable.slide3,R.drawable.slide6,R.drawable.slide5};

    public ExploreFragment(){
        // require a empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        viewPager= view.findViewById(R.id.viewPager);

        mViewPagerAdapter = new MyAdapter(this.getActivity(),images);
        viewPager.setAdapter(mViewPagerAdapter);
        final int[] ci = {0};
        java.util.Timer timer;
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("viewPager",""+ ci[0]);
                        viewPager.setCurrentItem(ci[0] %7);
                        ci[0]++;

                    }
                });
            }
        },500,3000);

        return view;
    }
}