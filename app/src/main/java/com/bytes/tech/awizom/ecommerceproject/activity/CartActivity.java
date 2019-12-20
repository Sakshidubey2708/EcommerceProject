package com.bytes.tech.awizom.ecommerceproject.activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
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
import android.widget.TextView;

import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.adapter.CartAdapter;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceproject.models.AmountTotalShow;
import com.bytes.tech.awizom.ecommerceproject.models.ProductDetailModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private String result = "";
    List<ProductDetailModel> productModelList;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout gridlayout,baglayout;
    private Button proceed;
    private ProgressDialog progressDialog;
    private AmountTotalShow amountTotalShow;
    private TextView subtotal_prices,shippingcharge_amounts,payable_amounts;
  //  private    ArrayList<String> AssuredPriceincreasingRate = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtocart_layout);

        initview();
    }

    private void initview() {

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Cart");
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
        progressDialog = new ProgressDialog(this);
        try{
            gridlayout = findViewById(R.id.details);
            baglayout =  findViewById(R.id.goneBagLayout);
            proceed =findViewById(R.id.proceed);
            recyclerView = findViewById(R.id.recyclerViewCart);
            mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutCart);

            subtotal_prices =findViewById(R.id.subtotal_price);
            shippingcharge_amounts =findViewById(R.id.shippingcharge_amount);
            payable_amounts=findViewById(R.id.payable_amount);


            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            if(SharedPrefManager.getInstance(CartActivity.this).getUser().getUserID() == String.valueOf(0)){
                gridlayout.setVisibility(View.GONE);
                baglayout.setVisibility(View.VISIBLE);
                proceed.setEnabled(false);
            }else {
                getCArt();

                getAssuredTotalAmount(SharedPrefManager.getInstance(CartActivity.this).getUser().getUserID().toString());
            }
            getCArt();

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


            ArrayList<String> AssuredPriceincreasingRate = new ArrayList<String>();
            for (int i = 0; i < productModelList.size(); i++) {
                View view = recyclerView.getChildAt(i);

                TextView textView1= (TextView) view.findViewById(R.id.total);
                String AssuredString=textView1.getText().toString();
                AssuredPriceincreasingRate.add(AssuredString);
                double total = Double.parseDouble(String.valueOf(amountTotalShow.getTotalAssuredPriceINR())+AssuredString);
                subtotal_prices.setText("₹" + total );
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getCArt() {
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
            mSwipeRefreshLayout.setRefreshing(true);
            result = new HelperApi.GetCartList().execute(SharedPrefManager.getInstance(this).getUser().getUserID().toString()).get();
            if (result.isEmpty()) {

                gridlayout.setVisibility(View.GONE);
                baglayout.setVisibility(View.VISIBLE);
                proceed.setEnabled(false);
                progressDialog.dismiss();
                mSwipeRefreshLayout.setRefreshing(false);
            } else {
                if (result.isEmpty()) {
                    progressDialog.dismiss();
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {

                    /*   Toast.makeText(getApplicationContext(),result+"",Toast.LENGTH_LONG).show();*/
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<ProductDetailModel>>() {
                    }.getType();
                    productModelList = new Gson().fromJson(result, listType);
                    Log.d("Error", productModelList.toString());
                    CartAdapter cartAdapter= new CartAdapter(CartActivity.this, productModelList);
                    recyclerView.setAdapter(cartAdapter);

                    progressDialog.dismiss();
                    mSwipeRefreshLayout.setRefreshing(false);

                }
            }
        } catch (Exception e) {
            mSwipeRefreshLayout.setRefreshing(false);
            e.printStackTrace();
        }

    }


    private void getAssuredTotalAmount(String s) {
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
            result = new HelperApi.GetCartAmountList().execute(s.toString()).get();
            progressDialog.dismiss();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (result.isEmpty()) {
            progressDialog.dismiss();
        } else {
            progressDialog.dismiss();
            Gson gson = new Gson();
            Type listType = new TypeToken<AmountTotalShow>() {
            }.getType();
            amountTotalShow = new Gson().fromJson(result, listType);
            if (amountTotalShow != null) {
               // subtotal_prices.setText("₹" + String.valueOf(amountTotalShow.getTotalAssuredPriceINR()) );

                Double  discountamount;
                discountamount = Double.parseDouble(String.valueOf(amountTotalShow.getTotalAssuredPriceINR()));
                int i = Math.round(Float.parseFloat(discountamount.toString()));
                subtotal_prices.setText(String.valueOf(i) + ".00");
            }
        }


    }
}
