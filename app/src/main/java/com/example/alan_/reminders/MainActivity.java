package com.example.alan_.reminders;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.alan_.reminders.Activity.HomeFragment;
import com.example.alan_.reminders.Activity.RegisterFragment;
import com.example.alan_.reminders.Model.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private int currentPosition;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        //Asigna el fragmento que utilizaremos por default
        transaction.replace(R.id.frame, new HomeFragment()).commit();
        TabLayout tabLayout = findViewById(R.id.tabOptions);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                currentPosition = tab.getPosition();
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                if(currentPosition==0){
                    transaction.replace(R.id.frame, new HomeFragment()).commit();
                }else if(currentPosition==1){
                    transaction.replace(R.id.frame, new RegisterFragment()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

}
