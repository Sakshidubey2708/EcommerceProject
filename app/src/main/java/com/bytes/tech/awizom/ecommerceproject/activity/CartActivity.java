package com.bytes.tech.awizom.ecommerceproject.activity;

import android.app.ActivityOptions;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.adapter.CartAdapter;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceproject.models.CartModel;
import com.bytes.tech.awizom.ecommerceproject.models.ProductModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private String result = "";
    List<CartModel> cartModels;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout gridlayout,baglayout;
    private Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtocart_layout);

        initview();
    }

    private void initview() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My Cart");
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
        
        try{
            gridlayout = findViewById(R.id.details);
            baglayout =  findViewById(R.id.goneBagLayout);
            proceed =findViewById(R.id.proceed);
            recyclerView = findViewById(R.id.recyclerViewCart);
            mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutCart);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // Refresh items
                    if(SharedPrefManager.getInstance(CartActivity.this).getUser().getUserID() == String.valueOf(0)){
                        gridlayout.setVisibility(View.GONE);
                        baglayout.setVisibility(View.VISIBLE);
                        proceed.setEnabled(false);
                    }else {
                        getCArt();
                    }

                }
            });
            if(SharedPrefManager.getInstance(CartActivity.this).getUser().getUserID() == null){

            }else {
                getCArt();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getCArt() {
        try {
            mSwipeRefreshLayout.setRefreshing(true);
            result = new HelperApi.GetCartList().execute(SharedPrefManager.getInstance(this).getUser().toString()).get();
            if (result.isEmpty()) {
                gridlayout.setVisibility(View.GONE);
                baglayout.setVisibility(View.VISIBLE);
                proceed.setEnabled(false);
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
                    CartAdapter cartAdapter= new CartAdapter(CartActivity.this, cartModels);
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
