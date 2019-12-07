package com.bytes.tech.awizom.ecommerceproject.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceproject.MainActivity;
import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.models.CatagoriesModel;
import java.util.List;

public class CatagoryGridViewAdapter extends BaseAdapter {

    //  private final String[] catalogNameList;

    private List<CatagoriesModel> catalogNameList;
    private Context mContext;
    private String skipdata="";

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
                       final TextView imglinkurl = gridViewAndroid.findViewById(R.id.imgLink);
            //  final ProgressBar progressBar = gridViewAndroid.findViewById(R.id.homeprogress);
            try {
                textViewAndroid.setText(catalogNameList.get(i).getMainCatName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }



}