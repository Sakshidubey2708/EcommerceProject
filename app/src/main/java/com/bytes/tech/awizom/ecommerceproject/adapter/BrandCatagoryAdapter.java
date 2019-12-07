package com.bytes.tech.awizom.ecommerceproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceproject.models.CatagoriesModel;
import java.util.List;
import com.bytes.tech.awizom.ecommerceproject.R;

public class BrandCatagoryAdapter extends  RecyclerView.Adapter<BrandCatagoryAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<CatagoriesModel> catagoriesModelList;
    private CatagoriesModel propertyName;
    TextView calltext;
    private String result = "";
    private boolean isTailor = true;
    long pid = 0;

    public BrandCatagoryAdapter(Context mCtx, List<CatagoriesModel> OrderNewOnes) {
        this.mCtx = mCtx;
        this.catagoriesModelList = OrderNewOnes;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.brand_catagory_adapter, null);
        return new OrderItemViewHolder(view, mCtx, catagoriesModelList);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderItemViewHolder holder, int position) {
        CatagoriesModel catagoriesModel = catagoriesModelList.get(position);
        try{
            holder.catagory_names.setText(catagoriesModel.getBrandName().toString());
            holder.catagoryIDs.setText(String.valueOf(catagoriesModel.getBrandId()));
        }catch (Exception e){
                e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return catagoriesModelList.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private Context mCtx;
        private TextView catagory_names,catagoryIDs;

        private List<CatagoriesModel> catagoriesModelsList;
        private CatagoriesModel catagoriesModel;
        private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

        public OrderItemViewHolder(View view, Context mCtx, List<CatagoriesModel> OrderNewOnes) {
            super(view);
            this.mCtx = mCtx;
            this.catagoriesModelsList = OrderNewOnes;

            catagory_names = view.findViewById(R.id.catagory_name);
            catagoryIDs=view.findViewById(R.id.catagoryID);

            final Handler handler = new Handler();
            final int[] colors = {Color.BLUE, Color.RED, Color.CYAN, Color.GREEN};
            final int[] i = new int[1];
            Runnable runnable = new Runnable () {
                @Override
                public void run() {
                    i[0] = i[0] % colors.length;
                    catagory_names.setTextColor(colors[i[0]]);
                    i[0]++;
                    handler.postDelayed(this, 1000);
                }
            };
            handler.postDelayed(runnable, 1000);

        }

        @Override
        public void onClick(final View v) {
            v.startAnimation(buttonClick);
        }

        @Override
        public boolean onLongClick(View v) {
            return true;
        }
    }


}

