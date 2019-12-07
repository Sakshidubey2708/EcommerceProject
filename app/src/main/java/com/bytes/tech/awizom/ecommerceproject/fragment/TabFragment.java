package com.bytes.tech.awizom.ecommerceproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.adapter.SubCatagoryAdapter;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.models.CatagoriesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class TabFragment extends Fragment {

    RecyclerView recyclerView;
    private String result = "";
    List<CatagoriesModel> catagoriesModels;
    SubCatagoryAdapter subCatagoryAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    int position;
    private TextView textView;

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentlayout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);


    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.recyclerViewItems);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayoutItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, true));


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
                    SubCatagoryAdapter subCatagoryAdapter= new SubCatagoryAdapter(getContext(), catagoriesModels);
                    recyclerView.setAdapter(subCatagoryAdapter);
                    mSwipeRefreshLayout.setRefreshing(false);
                }

            }
        } catch (Exception e) {
            mSwipeRefreshLayout.setRefreshing(false);
            e.printStackTrace();

        }
    }
}
