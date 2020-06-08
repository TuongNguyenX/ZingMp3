package com.example.appnghenhac.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.appnghenhac.Fragment.HomeFragment;
import com.example.appnghenhac.Fragment.NotiFragment;
import com.example.appnghenhac.Fragment.SearchFragment;
import com.example.appnghenhac.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(HomeFragment.newInstance("", ""));

    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
//
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            openFragment(HomeFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_search:
                            openFragment(SearchFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_notifications:
                            openFragment(NotiFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };

//    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    Fragment fragment = null;
//                    switch (item.getItemId()) {
//                        case R.id.navigation_home:
//                            fragment = new HomeFragment();
//                            break;
//                        case R.id.navigation_search:
//                            fragment = new SearchFragment();
//                            break;
//                        case R.id.navigation_notifications:
//                            fragment = new NotiFragment();
//                            break;
//                    }
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.container, fragment)
//                            .commit();
//
//                    return true;
//                }
//            };

}
