package com.bytes.tech.awizom.ecommerceproject.activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.models.ProductModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class SingleDetailView extends AppCompatActivity {

    String productIDs ="",projectName="",propertID = "", result = "",ImagLinks="";
    private List<ProductModel> OrderNewOnes;
    private ProductModel productModel;
    private ImageView imaged;
    private TextView descriptiondesign, hightlightdesc, mrp, assuredprice, discounts, productname,titlename;
    private ProgressDialog progressDialog;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        productIDs = getIntent().getStringExtra("ID").toString();
        progressDialog = new ProgressDialog(this);

        imaged = findViewById(R.id.image);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle("Property Detail");


        if (ImagLinks != null) {
            Glide.with(imaged)
                    .load(ImagLinks)
                    .centerCrop()
                    .placeholder(R.drawable.ec)
                    .error(R.drawable.ec)
                    .fallback(R.drawable.ec)
                    .into(imaged) ;
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        descriptiondesign = findViewById(R.id.description_design);
        hightlightdesc = findViewById(R.id.hightlight_desc);
        mrp = findViewById(R.id.mrp_s);
        assuredprice = findViewById(R.id.assuredPrice);
        discounts = findViewById(R.id.discount);

        productname = findViewById(R.id.product_name);
        titlename = findViewById(R.id.title_name);


        try {
            getDetailList(productIDs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getDetailList(String productIDs) {

        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
            result = new HelperApi.GetSingleProductList().execute(productIDs.toString()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (result.isEmpty()) {

            progressDialog.dismiss();
        } else {
            Gson gson = new Gson();
            Type listType = new TypeToken<ProductModel>() {
            }.getType();
            productModel = new Gson().fromJson(result, listType);
            if (productModel != null) {
                progressDialog.dismiss();

                descriptiondesign.setText(productModel.getDescriptions());
                hightlightdesc.setText(productModel.getHighlightsDesign());
                mrp.setText(String.valueOf(productModel.getMRP()));
                assuredprice.setText(String.valueOf(productModel.getAssuredPrice()));
                discounts.setText("Offer" + String.valueOf(productModel.getTotalDiscounts()) + " %");
                productname.setText(productModel.getProductName());
                titlename.setText(productModel.getTypeWeight());


            }
        }

    }


}
