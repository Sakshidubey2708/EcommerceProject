package com.bytes.tech.awizom.ecommerceproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.activity.ProductDetailsActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.SingleDetailView;
import com.bytes.tech.awizom.ecommerceproject.models.ProductModel;

import java.util.List;

public class ProductDetailsAdapter extends BaseAdapter {

    //  private final String[] productModelList;

    private List<ProductModel> productModelList;
    private Context mContext;
    private String skipdata="";

    public ProductDetailsAdapter(ProductDetailsActivity newCustomerHome, List<ProductModel> categorylist) {

        this.mContext = newCustomerHome;
        this.productModelList = categorylist;
        this.skipdata = skipdata;
    }

    @Override
    public int getCount() {
        return productModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, final View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.product_detail_adapter, null);
            TextView productnames = (TextView) gridViewAndroid.findViewById(R.id.product_name);
            TextView titlenames = (TextView) gridViewAndroid.findViewById(R.id.title_name);

            final TextView imglinkurl = gridViewAndroid.findViewById(R.id.imgLink);
            final ImageView bookmark = gridViewAndroid.findViewById(R.id.cartbpookmark);
           // final Button clickGo = gridViewAndroid.findViewById(R.id.go);
            final TextView pID = (TextView) gridViewAndroid.findViewById(R.id.pid);

            final TextView mrp = (TextView) gridViewAndroid.findViewById(R.id.mrp);
            final TextView assured_price = (TextView) gridViewAndroid.findViewById(R.id.assuredMRP);
            final TextView discount = (TextView) gridViewAndroid.findViewById(R.id.discount);

            //  final ProgressBar progressBar = gridViewAndroid.findViewById(R.id.homeprogress);
            try {
                productnames.setText(productModelList.get(i).getProductName());
                titlenames.setText(productModelList.get(i).getTitleName());
                discount.setText(String.valueOf(productModelList.get(i).getTotalDiscounts()) +"%" + " " +"OFF");
                mrp.setText("₹"+String.valueOf(productModelList.get(i).getMRP()));
                assured_price.setText("₹" +String.valueOf(productModelList.get(i).getAssuredPrice()));
                pID.setText(String.valueOf(productModelList.get(i).getProductId()));

                bookmark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(mContext, SingleDetailView.class);
//                        intent.putExtra("ID", pID.getText().toString());
//                        mContext.startActivity(intent);
                    }
                });
                final Handler handler = new Handler();
                final int[] colors = {Color.BLUE, Color.RED };
                final int[] j = new int[1];
                Runnable runnable = new Runnable () {
                    @Override
                    public void run() {
                        j[0] = j[0] % colors.length;
                        discount.setTextColor(colors[j[0]]);
                        j[0]++;
                        handler.postDelayed(this, 1000);
                    }
                };
                handler.postDelayed(runnable, 1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }



}
