package com.bytes.tech.awizom.ecommerceproject.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceproject.models.OrderDetailMain;
import com.bytes.tech.awizom.ecommerceproject.models.OrderMainModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {
    private String OrderId="",result="";
    private EditText name,mobileno,pincode,address,city,state;
    private Button saveBtn,cancelBtn;
    OrderDetailMain orderDetailMain;
    long orderDetailID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_activity_layout);

        initview();
    }

    private void initview() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Address");
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

        OrderId = getIntent().getStringExtra("ID").toString();

        name =findViewById(R.id.NAme);
        mobileno =findViewById(R.id.mobileNo);
        pincode =findViewById(R.id.pinCode);
        address =findViewById(R.id.addreSs);
        city =findViewById(R.id.cityy);
        state = findViewById(R.id.statee);

        saveBtn =findViewById(R.id.cancel);
        cancelBtn = findViewById(R.id.submit);

        saveBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                orderDetails(OrderId);
                break;
            case R.id.cancel:
                break;
        }
    }

    private void orderDetails(String orderMainID) {

        try {

            result = new HelperApi.PostOrderMain().execute(
                    String.valueOf(orderDetailID),
                    orderMainID.toString(),
                    SharedPrefManager.getInstance(this).getUser().getUserID().toString(),
                    "", "productId",
                    "unitprice",
                    "quantity",
                    "totalamount").get();
            if (result.isEmpty()) {

            } else {

                Gson gson = new Gson();
                Type listType = new TypeToken<List<OrderMainModel>>() {
                }.getType();
                orderDetailMain = new Gson().fromJson(result, listType);
                orderDetailID =orderDetailMain.getOrderId();

                Intent intent = new Intent(this,AddressActivity.class);
                intent.putExtra("ID" , String.valueOf(orderMainID));
                startActivity(intent);
                Toast.makeText(this,String.valueOf(orderMainID),Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
