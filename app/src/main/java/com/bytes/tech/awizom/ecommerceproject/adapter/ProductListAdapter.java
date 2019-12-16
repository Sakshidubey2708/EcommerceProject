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
import com.bytes.tech.awizom.ecommerceproject.models.ProductModel;
import java.util.List;
import com.bytes.tech.awizom.ecommerceproject.R;

public class ProductListAdapter extends  RecyclerView.Adapter<ProductListAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<ProductModel> productModelList;
    private ProductModel propertyName;
    TextView calltext;
    private String result = "";
    private boolean isTailor = true;
    long pid = 0;

    public ProductListAdapter(Context mCtx, List<ProductModel> OrderNewOnes) {
        this.mCtx = mCtx;
        this.productModelList = OrderNewOnes;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.producet_adpater, null);
        return new OrderItemViewHolder(view, mCtx, productModelList);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderItemViewHolder holder, int position) {
        ProductModel catagoriesModel = productModelList.get(position);
        try{
            holder.titleNames.setText(catagoriesModel.getTypeWeight().toString());
            holder.productNames.setText(catagoriesModel.getProductName().toString());
            holder.descriPtions.setText(catagoriesModel.getDescriptions().toString());
            holder.highliGhts.setText(catagoriesModel.getHighlightsDesign().toString());

            holder.mrpPrice.setText("MRP"+"₹"+String.valueOf(catagoriesModel.getMRP()));
            holder.assuredPrices.setText("AssuredPrice:"+"₹"+String.valueOf(catagoriesModel.getAssuredPrice()));
            holder.discounts.setText("Dis:" +String.valueOf(catagoriesModel.getTotalDiscounts())+"%");

            holder.productId.setText(String.valueOf(catagoriesModel.getTotalDiscounts()));
            holder.brandId.setText(String.valueOf(catagoriesModel.getTotalDiscounts()));
            holder.categoryId.setText(String.valueOf(catagoriesModel.getTotalDiscounts()));
            holder.mainId.setText(String.valueOf(catagoriesModel.getMainCatId()));
//            holder.book.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(mCtx, CartActivity.class);
//                    mCtx.startActivity(intent);
//                }
//            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private Context mCtx;
        private TextView titleNames,productNames,descriPtions,highliGhts, mrpPrice,assuredPrices,discounts,productId,brandId,categoryId,mainId;
        private List<ProductModel> productModelList;
        private ProductModel productModel;
        private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
       // private Button book;

        public OrderItemViewHolder(View view, Context mCtx, List<ProductModel> OrderNewOnes) {
            super(view);
            this.mCtx = mCtx;
            this.productModelList = OrderNewOnes;
            titleNames = view.findViewById(R.id.TitleNAme);
            productNames =view.findViewById(R.id.productNAme);
            descriPtions = view.findViewById(R.id.description);
            highliGhts =view.findViewById(R.id.highlights);
            mrpPrice =view.findViewById(R.id.MRP);
            assuredPrices = view.findViewById(R.id.assuredPrice);
            discounts = view.findViewById(R.id.discount);

            productId =view.findViewById(R.id.ProductId);
            brandId = view.findViewById(R.id.BrandId);
            categoryId = view.findViewById(R.id.CategoryId);
            mainId = view.findViewById(R.id.mainID);
           // book = view.findViewById(R.id.productBook);

            final Handler handler = new Handler();
            final int[] colors = {Color.BLUE, Color.RED};
            final int[] i = new int[1];
            Runnable runnable = new Runnable () {
                @Override
                public void run() {
                    i[0] = i[0] % colors.length;
                    discounts.setTextColor(colors[i[0]]);
                    i[0]++;
                    handler.postDelayed(this, 1000);
                }
            };
            handler.postDelayed(runnable, 1000);


//            final Handler handlers = new Handler();
//            final int[] colorss = {Color.BLUE, Color.RED};
//            final int[] j = new int[1];
//            Runnable runnables = new Runnable () {
//                @Override
//                public void run() {
//                    j[0] = j[0] % colorss.length;
//                    mrpPrice.setTextColor(colorss[j[0]]);
//                    j[0]++;
//                    handlers.postDelayed(this, 1000);
//                }
//            };
//            handlers.postDelayed(runnables, 1000);
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


