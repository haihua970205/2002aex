package com.example.test_kao;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView nav_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_topic, R.id.navigation_sort,R.id.navigation_shop,R.id.navigation_me)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(nav_view, navController);
        nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        menuItem.setIcon(R.mipmap.ic_menu_choice_pressed);
                        break;
                    case R.id.navigation_topic:
                        menuItem.setIcon(R.mipmap.ic_menu_topic_pressed);
                        break;
                    case R.id.navigation_sort:
                        menuItem.setIcon(R.mipmap.ic_menu_sort_pressed);
                        break;
                    case R.id.navigation_shop:
                        menuItem.setIcon(R.mipmap.ic_menu_shopping_pressed);

                        break;
                    case R.id.navigation_me:
                        menuItem.setIcon(R.mipmap.ic_menu_me_pressed);
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        nav_view = (BottomNavigationView) findViewById(R.id.nav_view);
    }
}