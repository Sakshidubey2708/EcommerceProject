package com.bytes.tech.awizom.ecommerceproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceproject.MainActivity;
import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.activity.ProductDetailsActivity;
import com.bytes.tech.awizom.ecommerceproject.models.CatagoriesModel;
import java.util.List;

public class CatagoryGridViewAdapter extends BaseAdapter {

    //  private final String[] catalogNameList;

    private List<CatagoriesModel> catalogNameList;
    private Context mContext;
    private String skipdata="";
    private CardView cardView;

    public CatagoryGridViewAdapter(MainActivity newCustomerHome, List<CatagoriesModel> categorylist) {

        this.mContext = newCustomerHome;
        this.catalogNameList = categorylist;
        this.skipdata = skipdata;
    }

    @Override
    public int getCount() {
            return catalogNameList.size();
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
            gridViewAndroid = inflater.inflate(R.layout.catalogname_gridview, null);
            TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.catalogName);
            final TextView catagoryIDss = (TextView) gridViewAndroid.findViewById(R.id.catagoryIDs);
                       final TextView imglinkurl = gridViewAndroid.findViewById(R.id.imgLink);
            cardView = gridViewAndroid.findViewById(R.id.homeCleancardViewOne);

            //  final ProgressBar progressBar = gridViewAndroid.findViewById(R.id.homeprogress);
            try {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, ProductDetailsActivity.class);
                        intent.putExtra("ID",catagoryIDss.getText().toString());
                        mContext.startActivity(intent);
                    }
                });
                textViewAndroid.setText(catalogNameList.get(i).getMainCatName());
                catagoryIDss.setText(String.valueOf(catalogNameList.get(i).getMainCatId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }



}