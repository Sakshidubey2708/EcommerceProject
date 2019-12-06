package com.bytes.tech.awizom.ecommerceproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bytes.tech.awizom.ecommerceproject.activity.BrandCatagoriesActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.SearchActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.SignInActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.ViewMainCatagoryActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.ViewSubCatagoryActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.ViewTypeCatagoriesActivity;
import com.bytes.tech.awizom.ecommerceproject.adapter.CustomerHomePageAdapter;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.models.CatagoriesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GridView gridView;
    private Intent intent;
    private String result="";
    List<CatagoriesModel> categorylist;

//    private VideoView vv;
//    private MediaController mediacontroller;
//    private Uri uri;
//    private boolean isContinuously = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initview();
    }

    private void initview() {
        gridView = (GridView) findViewById(R.id.gridview);
//        vv =  findViewById(R.id.vv);
//
//        mediacontroller = new MediaController(this);
//        mediacontroller.setAnchorView(vv);
//        String uriPath = "android.resource://com.bytes.tech.awizom.ecommerceproject/"+R.raw.v1;//update package name
//        uri = Uri.parse(uriPath);
//
//
//        vv.setMediaController(mediacontroller);
//        vv.setVideoURI(uri);
//        vv.requestFocus();
//        vv.start();

        getCategoryList();

    }
    private void getCategoryList() {
        String catalogname = "Home Cleaning & Repairs";
        try {
            result = new HelperApi.GetAllGetMainCategoriesList().execute().get();
            if (result.isEmpty()) {
                result = new HelperApi.GetAllGetMainCategoriesList().execute().get();
            } else {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<CatagoriesModel>>() {
                }.getType();
                categorylist = new Gson().fromJson(result, listType);
                CustomerHomePageAdapter customerCatagoryAdapter = new CustomerHomePageAdapter(MainActivity.this, categorylist);
                gridView.setAdapter(customerCatagoryAdapter);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Categories) {
            intent = new Intent(this, ViewMainCatagoryActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
        } else if (id == R.id.nav_SubCategories) {
            intent = new Intent(this, ViewSubCatagoryActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
        } else if (id == R.id.nav_TypeCategories) {
            intent = new Intent(this, ViewTypeCatagoriesActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
        } else if (id == R.id.nav_brandCategories) {
            intent = new Intent(this, BrandCatagoriesActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
        } else if (id == R.id.nav_ofcLink) {
            Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.awizomtech.com/"));
            startActivity(i);
            overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
        }else if (id == R.id.nav_Search) {
            intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
        }else if (id == R.id.nav_login) {
            intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
