package com.bytes.tech.awizom.ecommerceproject;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.TextView;

import com.bytes.tech.awizom.ecommerceproject.activity.BrandCatagoriesActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.ProductListActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.SearchActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.SignInActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.ViewMainCatagoryActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.ViewSubCatagoryActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.ViewTypeCatagoriesActivity;
import com.bytes.tech.awizom.ecommerceproject.adapter.BrandCatagoryAdapter;
import com.bytes.tech.awizom.ecommerceproject.adapter.CatagoryGridViewAdapter;
import com.bytes.tech.awizom.ecommerceproject.adapter.SubCatagoryAdapter;
import com.bytes.tech.awizom.ecommerceproject.adapter.TypeOfCatagoryAdapter;
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


    RecyclerView recyclerView;
    List<CatagoriesModel> catagoriesModels;
    SubCatagoryAdapter subCatagoryAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    RecyclerView recyclerViewBrand;
    List<CatagoriesModel> catagoriesModelsBrand;
    TypeOfCatagoryAdapter subCatagoryAdapterBrand;
    SwipeRefreshLayout mSwipeRefreshLayoutBrand;

    private TextView offerTextViews;

//    private VideoView vv;
//    private MediaController mediacontroller;
//    private Uri uri;
//    private boolean isContinuously = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
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
        offerTextViews =findViewById(R.id.offerTextView);

        recyclerView = findViewById(R.id.recyclerViewItems);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true));


        recyclerViewBrand = findViewById(R.id.recyclerViewBrand);
        mSwipeRefreshLayoutBrand = findViewById(R.id.swipeRefreshLayoutBrand);
        recyclerViewBrand.setHasFixedSize(true);
        recyclerViewBrand.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true));

        mSwipeRefreshLayoutBrand.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBrandCatagory();
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                getAllSubCatagory();
            }
        });

//        recyclerViewProduct = findViewById(R.id.recyclerViewProduct);
//        mSwipeRefreshLayoutProduct = findViewById(R.id.swipeRefreshLayoutProduct);
//        recyclerViewProduct.setHasFixedSize(true);
//        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
//        mSwipeRefreshLayoutProduct.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Refresh items
//                getProductList();
//            }
//        });
        getProductList();
        getAllSubCatagory();
        getBrandCatagory();
        getCategoryList();

        final Handler handler = new Handler();
        final int[] colors = {Color.BLUE, Color.RED, Color.CYAN, Color.GREEN};
        final int[] i = new int[1];
        Runnable runnable = new Runnable () {
            @Override
            public void run() {
                i[0] = i[0] % colors.length;
                offerTextViews.setTextColor(colors[i[0]]);
                i[0]++;
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);

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




    }

    private void getAllSubCatagory() {


        try {
            mSwipeRefreshLayout.setRefreshing(true);
            result = new HelperApi.GetAllSubCategoriesList().execute().get();
            if (result.isEmpty()) {
                mSwipeRefreshLayout.setRefreshing(false);
            } else {

                if (result.isEmpty()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    /*   Toast.makeText(getApplicationContext(),result+"",Toast.LENGTH_LONG).show();*/
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<CatagoriesModel>>() {
                    }.getType();
                    catagoriesModels = new Gson().fromJson(result, listType);
                    Log.d("Error", catagoriesModels.toString());
                    SubCatagoryAdapter subCatagoryAdapter= new SubCatagoryAdapter(this, catagoriesModels);
                    recyclerView.setAdapter(subCatagoryAdapter);
                    mSwipeRefreshLayout.setRefreshing(false);
                }

            }
        } catch (Exception e) {
            mSwipeRefreshLayout.setRefreshing(false);
            e.printStackTrace();

        }
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
                CatagoryGridViewAdapter catagoryGridViewAdapter = new CatagoryGridViewAdapter(MainActivity.this, categorylist);
                gridView.setAdapter(catagoryGridViewAdapter);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void getBrandCatagory() {
        try {
            mSwipeRefreshLayoutBrand.setRefreshing(true);
            result = new HelperApi.GetAllBrandsList().execute().get();
            if (result.isEmpty()) {
                mSwipeRefreshLayoutBrand.setRefreshing(false);
            } else {
                if (result.isEmpty()) {
                    mSwipeRefreshLayoutBrand.setRefreshing(false);
                } else {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<CatagoriesModel>>() {
                    }.getType();
                    catagoriesModelsBrand = new Gson().fromJson(result, listType);
                    Log.d("Error", catagoriesModelsBrand.toString());
                    BrandCatagoryAdapter subCatagoryAdapter= new BrandCatagoryAdapter(MainActivity.this, catagoriesModelsBrand);
                    recyclerViewBrand.setAdapter(subCatagoryAdapter);
                    mSwipeRefreshLayoutBrand.setRefreshing(false);
                }
            }
        } catch (Exception e) {
            mSwipeRefreshLayoutBrand.setRefreshing(false);
            e.printStackTrace();
        }
    }
    private void getProductList() {
//        try {
//            mSwipeRefreshLayoutProduct.setRefreshing(true);
//            result = new HelperApi.GetAllProductList().execute().get();
//            if (result.isEmpty()) {
//                mSwipeRefreshLayoutProduct.setRefreshing(false);
//            } else {
//                if (result.isEmpty()) {
//                    mSwipeRefreshLayoutProduct.setRefreshing(false);
//                } else {
//                    /*   Toast.makeText(getApplicationContext(),result+"",Toast.LENGTH_LONG).show();*/
//                    Gson gson = new Gson();
//                    Type listType = new TypeToken<List<ProductModel>>() {
//                    }.getType();
//                    productModel = new Gson().fromJson(result, listType);
//                    Log.d("Error", productModel.toString());
//                    ProductListAdapter productListAdapter= new ProductListAdapter(MainActivity.this, productModel);
//                    recyclerViewProduct.setAdapter(productListAdapter);
//                    mSwipeRefreshLayoutProduct.setRefreshing(false);
//                }
//            }
//        } catch (Exception e) {
//            mSwipeRefreshLayout.setRefreshing(false);
//            e.printStackTrace();
//        }
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

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

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
        }else if (id == R.id.nav_listproduct) {
            intent = new Intent(this, ProductListActivity.class);
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
