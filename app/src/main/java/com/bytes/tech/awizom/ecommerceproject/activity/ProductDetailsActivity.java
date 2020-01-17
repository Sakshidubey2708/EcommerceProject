package com.bytes.tech.awizom.ecommerceproject.activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.adapter.ProductDetailsAdapter;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.models.ProductModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    GridView gridView;
    private Intent intent;
    private String result = "", maincatID = "", subID = "";
    List<ProductModel> productModelList;
    private ProgressDialog progressDialog;
    private ImageView addcarts;
    private TextView gotocart;


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


        gridView = findViewById(R.id.gridviewproduct);
        addcarts = findViewById(R.id.cartviews);
        gotocart = findViewById(R.id.gotoCart);
        maincatID = getIntent().getStringExtra("mainID");
        subID = getIntent().getStringExtra("subID");

        progressDialog = new ProgressDialog(this);


        //getProductList();


        gotocart.setOnClickListener(this);

        try {
//            if(!maincatID.isEmpty()){
//                GetAllProductsListByMainCatID(maincatID.toString());
//
//            }else
            if (!subID.isEmpty()) {
                GetProductsListBySubCAtagory(subID.toString());
            } else {
                getProductList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void GetAllProductsListByMainCatID(String catIDs) {
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
            result = new HelperApi.GetAllProductsListByMainCatID().execute(catIDs.toString()).get();
            if (result.isEmpty()) {
                progressDialog.dismiss();
                result = new HelperApi.GetAllProductsListByMainCatID().execute().get();
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

    private void GetProductsListBySubCAtagory(String catIDs) {
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
            result = new HelperApi.GetProductsListBySubCAtagory().execute(catIDs.toString()).get();
            if (result.isEmpty()) {
                progressDialog.dismiss();
                result = new HelperApi.GetProductsListBySubCAtagory().execute(catIDs.toString()).get();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cartviews:
                intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
                startActivity(intent);
                break;
            case R.id.gotoCart:
                intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
                startActivity(intent);
                break;
        }
    }
}

