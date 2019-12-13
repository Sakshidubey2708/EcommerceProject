package com.bytes.tech.awizom.ecommerceproject.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.activity.ProductDetailsActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.SignInActivity;
import com.bytes.tech.awizom.ecommerceproject.activity.SplashActivity;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceproject.models.ProductModel;
import com.google.gson.Gson;

import java.util.List;

public class ProductDetailsAdapter extends BaseAdapter {

    //  private final String[] productModelList;
    private List<ProductModel> productModelList;
    private Context mContext;
    private String skipdata="",pid="";
    private ProgressDialog progressDialog;
    private String   result="";

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
            progressDialog = new ProgressDialog(mContext);

            //  final ProgressBar progressBar = gridViewAndroid.findViewById(R.id.homeprogress);
            try {
                productnames.setText(productModelList.get(i).getProductName());
                titlenames.setText(productModelList.get(i).getTypeWeight());
                discount.setText(String.valueOf(productModelList.get(i).getTotalDiscounts()) +"%" + " " +"OFF");
                mrp.setText("₹"+String.valueOf(productModelList.get(i).getMRP()));
                assured_price.setText("₹" +String.valueOf(productModelList.get(i).getAssuredPrice()));
                pID.setText(String.valueOf(productModelList.get(i).getProductId()));
                pid= pID.getText().toString();
                bookmark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(SharedPrefManager.getInstance(mContext).getUser().getUserID() == null){
                            showCustomDialog(v);
                        }else {
                            postCArt();
                        }
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
    private void showCustomDialog(View v) {
//        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
//        ViewGroup viewGroup = v.findViewById(android.R.id.content);
//        //then we will inflate the custom alert dialog xml that we created
//        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.bottom_slide_dailog, viewGroup, false);
//        //Now we need an AlertDialog.Builder object
//        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//        ImageView closebtn = dialogView.findViewById(R.id.close);
//        TextView loginview = dialogView.findViewById(R.id.loginClickevent);
//
//        loginview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mContext, SignInActivity.class);
//                mContext.startActivity(i);
//            }
//        });
//        closebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        //finally creating the alert dialog and displaying it
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();


        final Dialog dialog = new Dialog(mContext);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setAttributes(lp);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.bottom_slide_dailog);


        ImageView closebtn = dialog.findViewById(R.id.close);
        TextView loginview = dialog.findViewById(R.id.loginClickevent);

        loginview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, SignInActivity.class);
                mContext.startActivity(i);
            }
        });
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void postCArt() {
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();

            result =   new HelperApi.PostCarts().execute(pid.toString()).get();
            try {
                if (result.isEmpty()) {
                    Log.d("Result Empty", "Error");
                    progressDialog.dismiss();
                } else {
                    Gson gson = new Gson();
                    Toast.makeText(mContext, "Added", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            } catch (Exception e) {
                Log.d("Result Empty", "Error");
                e.printStackTrace();
            }

        } catch (Exception e) {
            Log.d("Result Empty", "Error");
            e.printStackTrace();
        }
    }


}
