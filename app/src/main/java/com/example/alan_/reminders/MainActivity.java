package com.example.alan_.reminders;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.alan_.reminders.Activity.HomeFragment;
import com.example.alan_.reminders.Activity.RegisterFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //Asigna el fragmento que utilizaremos por default
        transaction.replace(R.id.frame, new HomeFragment()).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Marca que utilizaremos la primera opcion del menu
        navigation.getMenu().getItem(0).setChecked(true);
    }

    //Listener del BottomNavigationView
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //Cambia el fragment dependiendo de la opcion que seleccionemos en el navigation
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.frame, new HomeFragment()).commit();
                    return true;
                case R.id.navigation_reminders:
                    transaction.replace(R.id.frame, new RegisterFragment()).commit();
                    return true;
            }
            return false;
        }
    };

}
