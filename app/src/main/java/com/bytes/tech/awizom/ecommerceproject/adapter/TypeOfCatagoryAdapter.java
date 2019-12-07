package com.bytes.tech.awizom.ecommerceproject.adapter;

import android.content.Context;
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

public class TypeOfCatagoryAdapter extends  RecyclerView.Adapter<TypeOfCatagoryAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<CatagoriesModel> catagoriesModelList;
    private CatagoriesModel propertyName;
    TextView calltext;
    private String result = "";
    private boolean isTailor = true;
    long pid = 0;

    public TypeOfCatagoryAdapter(Context mCtx, List<CatagoriesModel> OrderNewOnes) {
        this.mCtx = mCtx;
        this.catagoriesModelList = OrderNewOnes;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.maincatagory_adapter, null);
        return new OrderItemViewHolder(view, mCtx, catagoriesModelList);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderItemViewHolder holder, int position) {
        CatagoriesModel catagoriesModel = catagoriesModelList.get(position);

        holder.catagory_names.setText(catagoriesModel.getTypeSubCatName().toString());
        holder.catagoryIDs.setText(String.valueOf(catagoriesModel.getTypeSubCatId()));

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
