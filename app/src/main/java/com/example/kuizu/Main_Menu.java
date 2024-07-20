package com.example.kuizu;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class Main_Menu extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    DrawerLayout drawer_layout;
    NavigationView navigation_view;

    ImageView cpp_image,java_image;
    Toolbar tool_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        drawer_layout = findViewById(R.id.drawer_layout);
        navigation_view = findViewById(R.id.navigation_view);
        tool_bar = findViewById(R.id.tool_bar);


        cpp_image = findViewById(R.id.cpp_image);
        java_image = findViewById(R.id.java_image);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);



        cpp_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inte = new Intent(Main_Menu.this, Cpp_quiz.class);
                startActivity(inte);
                finish();
            }
        });

        java_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment1(new Java_Fragment());
            }
        });




        setSupportActionBar(tool_bar);
        navigation_view.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, tool_bar, R.string.OpenDrawer, R.string.CloseDrawer);

        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.home)
                {
                    loadFragment(new HomeFragment());
                }
                else if (id == R.id.profile)
                {
                    loadFragment(new Profile_Fragment());
                }
                else if (id == R.id.progress)
                {
                    Toast.makeText(Main_Menu.this, "Setting", Toast.LENGTH_SHORT).show();
                }
                else if(id== R.id.setting)
                {
                    Toast.makeText(Main_Menu.this, "Logout", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.logout)
                {
                    signOut();
                }

                drawer_layout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

    }
    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(Main_Menu.this,SignUp.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }



    private void loadFragment(Fragment fragment) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.container1,fragment);
                ft.commit();
            }

    private void loadFragment1(Fragment fragment) {
        // Create a new fragment transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the fragment container with the new fragment
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    }
