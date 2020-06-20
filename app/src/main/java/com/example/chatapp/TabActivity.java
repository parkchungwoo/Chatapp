package com.example.chatapp;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.chatapp.ui.dashboard.MessageFragment;
import com.example.chatapp.ui.home.UsersFragment;
import com.example.chatapp.ui.notifications.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);


        UsersFragment usersFragment = new UsersFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, usersFragment)
                .commit();
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        UsersFragment usersFragment1 = new UsersFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, usersFragment1)
                                .commit();
                        return true;
                    case R.id.navigation_dashboard:
                        MessageFragment messageFragment1 = new MessageFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, messageFragment1)
                                .commit();
                        return true;
                    case R.id.navigation_notifications:
                        ProfileFragment profileFragment1 = new ProfileFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, profileFragment1)
                                .commit();
                        return true;
                }
                return false;
            }
        });
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.nav_host_fragment, new UsersFragment());
//        transaction.addToBackStack(null);
//        transaction.commit();

    }

}