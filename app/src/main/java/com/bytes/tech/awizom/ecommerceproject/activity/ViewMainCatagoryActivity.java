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
import com.bytes.tech.awizom.ecommerceproject.adapter.MainCatagoryAdapter;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.models.CatagoriesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bytes.tech.awizom.ecommerceproject.R;
import java.lang.reflect.Type;
import java.util.List;

public class ViewMainCatagoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private String result = "";
    List<CatagoriesModel> catagoriesModels;
    MainCatagoryAdapter mainCatagoryAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_list_layout);
        initview();
    }

    private void initview() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Categories");
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

        recyclerView = findViewById(R.id.recyclerViewItems);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                getMainCatagories();
            }
        });
        getMainCatagories();

    }

    private void getMainCatagories() {
        try {
            mSwipeRefreshLayout.setRefreshing(true);
            result = new HelperApi.GetAllGetMainCategoriesList().execute().get();
            if (result.isEmpty()) {
                mSwipeRefreshLayout.setRefreshing(false);
            } else {

                Gson gson = new Gson();
                Type listType = new TypeToken<List<CatagoriesModel>>() {
                }.getType();
                catagoriesModels = new Gson().fromJson(result, listType);
                Log.d("Error", catagoriesModels.toString());
                MainCatagoryAdapter mainCatagoryAdapter= new MainCatagoryAdapter(ViewMainCatagoryActivity.this, catagoriesModels);
                recyclerView.setAdapter(mainCatagoryAdapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        } catch (Exception e) {
            mSwipeRefreshLayout.setRefreshing(false);
            e.printStackTrace();

        }


    }


}
