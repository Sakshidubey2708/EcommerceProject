package com.bytes.tech.awizom.ecommerceproject.activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.adapter.CartAdapter;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceproject.models.AmountTotalShow;
import com.bytes.tech.awizom.ecommerceproject.models.CartModel;
import com.bytes.tech.awizom.ecommerceproject.models.OrderMainModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartActivity extends AppCompatActivity implements View.OnClickListener , OnItemClick {

    RecyclerView recyclerView;
    private String result = "";
    List<CartModel> productModelList;
   // SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout gridlayout,baglayout;
    private Button proceed;
    private ProgressDialog progressDialog;
    private AmountTotalShow amountTotalShow;
    private TextView subtotal_prices,total_amounts,shippingcharge_amounts,anyother_charge;
    ArrayList<String> mrps = new ArrayList<String>();
    private final int SPLASH_DISPLAY_DURATION = 1000;
    String AssuredString="",deliveryAddress="Raipur",productId="",quantities="",mrpPric="",dicounts="";
    OrderMainModel orderMainModel;
    long orderMainID=0;
    private static int orderNo = 1;

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
            proceed.setOnClickListener(this);
            recyclerView = findViewById(R.id.recyclerViewCart);
           // mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutCart);

            subtotal_prices =findViewById(R.id.subtotal_price);
            total_amounts=findViewById(R.id.total_amount);
            shippingcharge_amounts =findViewById(R.id.shippingcharge_amount);
            anyother_charge=findViewById(R.id.payable_amount);


            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            if(SharedPrefManager.getInstance(CartActivity.this).getUser().getUserID() == String.valueOf(0)){
                gridlayout.setVisibility(View.GONE);
                baglayout.setVisibility(View.VISIBLE);
                total_amounts.setVisibility(View.GONE);
                proceed.setEnabled(false);
            }else {
                getCArt();
                getAssuredTotalAmount(SharedPrefManager.getInstance(CartActivity.this).getUser().getUserID().toString());

                ArrayList<String> AssuredPriceincreasingRate = new ArrayList<String>();
                for (int i = 0; i < productModelList.size(); i++) {
                    View view = recyclerView.getChildAt(i);

                    TextView productIDs= view.findViewById(R.id.productId);
                    productId=productIDs.getText().toString();

                    TextView quantity= view.findViewById(R.id.quantities);
                    quantities=quantity.getText().toString();

                    TextView textView1=  view.findViewById(R.id.total);
                    AssuredString=textView1.getText().toString();

                    TextView mrpPrices=view.findViewById(R.id.mrpPrice);
                    mrpPric=mrpPrices.getText().toString();

                    TextView dicountt= view.findViewById(R.id.dicount);
                    dicounts=dicountt.getText().toString();

                    TextView totalAssured= view.findViewById(R.id.total);
                    AssuredString=totalAssured.getText().toString();
                    AssuredPriceincreasingRate.add(AssuredString);
                    double total = Double.parseDouble(String.valueOf(amountTotalShow.getTotalAssuredPriceINR())+AssuredString);
                    subtotal_prices.setText("₹" + total );


                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            getAssuredTotalAmount(SharedPrefManager.getInstance(CartActivity.this).getUser().getUserID().toString());
                            Toast.makeText(CartActivity.this,AssuredString.toString(),Toast.LENGTH_LONG).show();

                        }
                    }, SPLASH_DISPLAY_DURATION);
                }

            }
            getCArt();

//            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    // Refresh items
//                    if(SharedPrefManager.getInstance(CartActivity.this).getUser().getUserID() == String.valueOf(0)){
//                        gridlayout.setVisibility(View.GONE);
//                        baglayout.setVisibility(View.VISIBLE);
//                        total_amounts.setVisibility(View.GONE);
//                        proceed.setEnabled(false);
//                    }else {
//                        getCArt();
//                    }
//                }
//            });





        }catch (Exception e){
            e.printStackTrace();
            gridlayout.setVisibility(View.GONE);
            baglayout.setVisibility(View.VISIBLE);
            total_amounts.setVisibility(View.GONE);
            proceed.setEnabled(false);
        }
    }

    private void getCArt() {
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
         //   mSwipeRefreshLayout.setRefreshing(true);
            result = new HelperApi.GetCartList().execute(SharedPrefManager.getInstance(this).getUser().getUserID().toString()).get();
            if (result.isEmpty()) {
                progressDialog.dismiss();
                gridlayout.setVisibility(View.GONE);
                baglayout.setVisibility(View.VISIBLE);
                total_amounts.setVisibility(View.GONE);
                proceed.setEnabled(false);
                progressDialog.dismiss();
            //    mSwipeRefreshLayout.setRefreshing(false);
            } else {
                if (result.isEmpty()) {
                    progressDialog.dismiss();
                    gridlayout.setVisibility(View.GONE);
                    baglayout.setVisibility(View.VISIBLE);
                    total_amounts.setVisibility(View.GONE);
                    proceed.setEnabled(false);
                    progressDialog.dismiss();
               //     mSwipeRefreshLayout.setRefreshing(false);
                } else {
                //    mSwipeRefreshLayout.setRefreshing(false);
                    /*   Toast.makeText(getApplicationContext(),result+"",Toast.LENGTH_LONG).show();*/
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<CartModel>>() {
                    }.getType();
                    productModelList = new Gson().fromJson(result, listType);
                    Log.d("Error", productModelList.toString());
                    CartAdapter cartAdapter= new CartAdapter(CartActivity.this, productModelList,this);
                    recyclerView.setAdapter(cartAdapter);

                    progressDialog.dismiss();


                }
            }
        } catch (Exception e) {
           // mSwipeRefreshLayout.setRefreshing(false);
            e.printStackTrace();
            progressDialog.dismiss();
            gridlayout.setVisibility(View.GONE);
            baglayout.setVisibility(View.VISIBLE);
            total_amounts.setVisibility(View.GONE);
            proceed.setEnabled(false);
            progressDialog.dismiss();
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
                total_amounts.setText( "Total Amount =" +String.valueOf(i) + ".00");
            }
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.proceed:

                    postOrderMain();

                break;
        }
    }

    private  void postOrderMain(){

        try {
            result = new HelperApi.PostOrderMain().execute(
                    String.valueOf(orderMainID),
                    String.valueOf(1),
                    SharedPrefManager.getInstance(this).getUser().getUserID().toString(),
                    "",
                    proceed.getText().toString().trim(),
                    total_amounts.getText().toString(),
                    shippingcharge_amounts.getText().toString().split("₹")[1],
                    anyother_charge.getText().toString().split("₹")[1]).get();
            if (result.isEmpty()) {
                result = new HelperApi.PostOrderMain().execute(
                        String.valueOf(orderMainID),
                        String.valueOf(1),
                        SharedPrefManager.getInstance(this).getUser().getUserID().toString(),
                        "",
                        proceed.getText().toString().trim(),
                        total_amounts.getText().toString(),
                        shippingcharge_amounts.getText().toString().split("₹")[1],
                        anyother_charge.getText().toString().split("₹")[1]).get();
            } else {

                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<OrderMainModel>>() {
                    }.getType();
                    orderMainModel = new Gson().fromJson(result, listType);
                    orderMainID =orderMainModel.getOrderId();

                    Intent intent = new Intent(this,AddressActivity.class);
                    intent.putExtra("ID" , String.valueOf(orderMainID));
                    startActivity(intent);
                    Toast.makeText(this,String.valueOf(orderMainID),Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(String value) {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("key_name5", value.toString());
        editor.apply();

        subtotal_prices.setText(value.toString() + ".00");
        total_amounts.setText( "Total Amount =" +value.toString()+ ".00");
        Toast.makeText(getApplicationContext(),value.toString(),Toast.LENGTH_LONG).show();
    }


}
