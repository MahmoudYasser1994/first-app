package com.example.to7fademo.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.to7fademo.Adapter.JobsAdapter;
import com.example.to7fademo.Fragments.HomeFragment;
import com.example.to7fademo.Helper.BottomNavigationViewHelper;
import com.example.to7fademo.Helper.MyAlertDialog;
import com.example.to7fademo.Models.GetSection.Datum;
import com.example.to7fademo.R;

import java.util.List;

public class FirstActivity extends AppCompatActivity {
    RecyclerView.LayoutManager layoutManager;
    public List<Datum> getdata;
    private JobsAdapter jobsAdapter;
    AlertDialog alertDialog;
    MyAlertDialog myAlertDialog;
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_first);

        settoolbar ( );
        navigationView = findViewById (R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener ( ) {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId ( )) {
                    case R.id.logout_button:
                        Toast.makeText (FirstActivity.this, "GoodBye", Toast.LENGTH_SHORT).show ( );
                        Intent myintent = new Intent (FirstActivity.this, LoginActivity.class);
                        startActivity (myintent);
                        break;
                }
                return true;
            }
        });


        BottomNavigationViewHelper bottomNavigationViewHelper = new BottomNavigationViewHelper ( );
        BottomNavigationView bottomNavigationView = findViewById (R.id.navbot);
        BottomNavigationViewHelper.removeShiftMode (bottomNavigationView);

        final HomeFragment homeFragment = new HomeFragment ( );
        FragmentManager fragmentManager = getSupportFragmentManager ( );
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ( );
        fragmentTransaction.replace (R.id.container_a, homeFragment);

        fragmentTransaction.commit ( );


        myAlertDialog = new MyAlertDialog (alertDialog);


        bottomNavigationView.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener ( ) {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId ( )) {
                    case R.id.favicon:
                        break;
                    case R.id.infoicon:
                        break;
                    case R.id.homeicon:

                        HomeFragment homeFragment = new HomeFragment ( );
                        FragmentManager fragmentManager = getSupportFragmentManager ( );
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ( );
                        fragmentTransaction.replace (R.id.container_a, homeFragment);
                        fragmentTransaction.commit ( );

                        break;
                    case R.id.shoppingicon:
                        break;
                }


                return false;
            }
        });


    }

    public void settoolbar() {

        drawerLayout = findViewById (R.id.drawer);

    }


}
