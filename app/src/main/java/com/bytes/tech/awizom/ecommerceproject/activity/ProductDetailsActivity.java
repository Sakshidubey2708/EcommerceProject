package com.bytes.tech.awizom.ecommerceproject.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.adapter.ProductDetailsAdapter;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_layout);
        initview();
    }

    private void initview() {

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Product Detail");
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

        getProductList();
    }

    private void getProductList() {
        try {

            result = new HelperApi.GetAllProductList().execute().get();
            if (result.isEmpty()) {
                result = new HelperApi.GetAllProductList().execute().get();
            } else {
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
}

