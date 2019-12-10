package com.bytes.tech.awizom.ecommerceproject.activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.adapter.CartAdapter;
import com.bytes.tech.awizom.ecommerceproject.adapter.ProductDetailsAdapter;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.models.CartModel;
import com.bytes.tech.awizom.ecommerceproject.models.ProductModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {


    GridView gridView;
    private Intent intent;
    private String result="";
    List<ProductModel> productModelList;
    private ProgressDialog progressDialog;
    private ImageView addcarts;

    RecyclerView recyclerView;
    List<CartModel> cartModels;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_layout);
        initview();
    }

    private void initview() {

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Product");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in, R.anim.slide_out);
                onBackPressed();
            }
        });
        toolbar.setSubtitleTextAppearance(getApplicationContext(), R.style.styleA);
        toolbar.setTitleTextAppearance(getApplicationContext(), R.style.styleA);
        toolbar.setTitleTextColor(Color.WHITE);

        gridView = (GridView) findViewById(R.id.gridviewproduct);
        addcarts =  (ImageView) findViewById(R.id.cartviews);

        progressDialog = new ProgressDialog(this);

        getProductList();

        addcarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCArt();
//                Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
//
//                        startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerViewCart);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutCart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                getCArt();
            }
        });
        getCArt();
    }

    private void getProductList() {
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
            result = new HelperApi.GetAllProductList().execute().get();
            if (result.isEmpty()) {
                progressDialog.dismiss();
                result = new HelperApi.GetAllProductList().execute().get();
            } else {
                progressDialog.dismiss();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ProductModel>>() {
                }.getType();
                productModelList = new Gson().fromJson(result, listType);
                ProductDetailsAdapter productDetailsAdapter = new ProductDetailsAdapter(ProductDetailsActivity.this, productModelList);
                gridView.setAdapter(productDetailsAdapter);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCArt() {
        try {
            mSwipeRefreshLayout.setRefreshing(true);
            result = new HelperApi.GetCartList().execute().get();
            if (result.isEmpty()) {
                mSwipeRefreshLayout.setRefreshing(false);
            } else {
                if (result.isEmpty()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    /*   Toast.makeText(getApplicationContext(),result+"",Toast.LENGTH_LONG).show();*/
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<CartModel>>() {
                    }.getType();
                    cartModels = new Gson().fromJson(result, listType);
                    Log.d("Error", cartModels.toString());
                    CartAdapter cartAdapter= new CartAdapter(ProductDetailsActivity.this, cartModels);
                    recyclerView.setAdapter(cartAdapter);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        } catch (Exception e) {
            mSwipeRefreshLayout.setRefreshing(false);
            e.printStackTrace();
        }

    }
}

