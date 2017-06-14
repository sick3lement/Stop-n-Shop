package com.project.umang.googlesignin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);


        TextView text = (TextView)header.findViewById(R.id.name);
        TextView text1 = (TextView )header.findViewById(R.id.email1);
        ImageView imageView = (ImageView)header.findViewById(R.id.profile_image);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);

        text1.setText(email);

    /*
    menimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,MenActivity.class));

            }
        });
        womenimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,WomenActivity.class));

            }
        });
        childrenimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,ChildrenActivity.class));
            }
        });*/

    /*   Picasso.with(this).load("https://static.pexels.com/photos/26939/pexels-photo-26939.jpg").into(menimage);
        Picasso.with(this).load("https://static.pexels.com/photos/94731/pexels-photo-94731.jpeg").into(womenimage);
        Picasso.with(this).load("https://s-media-cache-ak0.pinimg.com/originals/97/ef/76/97ef768c14a38a631bb8494444502e9b.jpg").into(childrenimage);
*/

        final List<Homedata> data = new ArrayList<>();
        data.add(new Homedata("http://www.antoinesalonoftroy.com/images/dapper-hairstyles-for-men-hd-fashion-tips-for-men--how-to-combine-the-dapper-style-clothes-pict.jpg?crc=3936621076","men wardrobe"));
        data.add(new Homedata("https://static.pexels.com/photos/94731/pexels-photo-94731.jpeg","women wardrobe"));
        data.add(new Homedata("https://s-media-cache-ak0.pinimg.com/originals/97/ef/76/97ef768c14a38a631bb8494444502e9b.jpg","children wardrobe"));

       RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
       Recycler_View_Adapter_Home adapter = new Recycler_View_Adapter_Home(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(this, recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(data.get(position).button.equalsIgnoreCase("men wardrobe"))
                {
                    startActivity( new Intent(HomeActivity.this,MenActivity.class));
                }
                else if(data.get(position).button.equalsIgnoreCase("women wardrobe"))
                {
                    startActivity( new Intent(HomeActivity.this,WomenActivity.class));
                }
                else
                {
                    startActivity( new Intent(HomeActivity.this,ChildrenActivity.class));
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.carttool) {
            startActivity(new Intent(this,CartActivity.class));
            return true;
        }
       else if (id == R.id.favouritestool) {
            startActivity(new Intent(this,FavouritesActivity.class));
            return true;
        }
        else if (id == R.id.logout) {
            logout();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_men) {
            startActivity(new Intent(HomeActivity.this,MenActivity.class));

            // Handle the camera action
        } else if (id == R.id.nav_women) {
            startActivity(new Intent(HomeActivity.this,WomenActivity.class));

        } else if (id == R.id.nav_children) {
            startActivity(new Intent(HomeActivity.this,ChildrenActivity.class));


        }
        else if (id == R.id.carticon) {
            startActivity(new Intent(HomeActivity.this,CartActivity.class));


        }
        else if (id == R.id.favouritesicon) {
            startActivity(new Intent(HomeActivity.this,FavouritesActivity.class));


        } else if (id == R.id.nav_logout) {
        logout();
        }
        else if (id == R.id.contact1) {
            startActivity(new Intent(HomeActivity.this,AboutActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.EMAIL_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


}
