package com.exampleandroiddemottest.petagramrecyclerview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;

import com.exampleandroiddemottest.petagramrecyclerview.adapter.PageAdapter;
import com.exampleandroiddemottest.petagramrecyclerview.fragment.Fragment_DetallePets;
import com.exampleandroiddemottest.petagramrecyclerview.fragment.RecyclerViewFragmentView;

import java.util.ArrayList;

public class MainActivity extends CreateMenuOpctions {


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         toolbar = findViewById(R.id.miActionBar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        setSupportActionBar(toolbar);

        setUpViewPager();
        if (toolbar != null){
            setSupportActionBar(toolbar);
        }

    }

    //lista de fragment a mostrar
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragmentView());
        fragments.add(new Fragment_DetallePets());
        return  fragments;

    }

    //add list gragment to adapter
    private  void setUpViewPager(){

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

      tabLayout.getTabAt(0).setIcon(R.drawable.ic_individual);
      tabLayout.getTabAt(1).setIcon(R.drawable.ic_details);
    }



















}
