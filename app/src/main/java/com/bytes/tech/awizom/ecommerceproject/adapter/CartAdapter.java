package com.bytes.tech.awizom.ecommerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.models.CartModel;
import java.util.List;

public class CartAdapter extends  RecyclerView.Adapter<CartAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<CartModel> cardmodellist;
    private CartModel propertyName;
    TextView calltext;
    private String result = "";
    private boolean isTailor = true;
    long pid = 0;

    public CartAdapter(Context mCtx, List<CartModel> OrderNewOnes) {
        this.mCtx = mCtx;
        this.cardmodellist = OrderNewOnes;
    }

    @NonNull
    @Override
    public CartAdapter.OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.addtocart_adapter_layout, null);
        return new CartAdapter.OrderItemViewHolder(view, mCtx, cardmodellist);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.OrderItemViewHolder holder, int position) {
        CartModel catagoriesModel = cardmodellist.get(position);
        try{
            holder.productIdd.setText(String.valueOf(catagoriesModel.getProductId()));
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return cardmodellist.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private ImageView deletBTN,product_img;
        private TextView product_name,prices,productIdd;
        private List<CartModel> cardmodellist;
        private Context mCtx;

        public OrderItemViewHolder(View view, Context mCtx, List<CartModel> OrderNewOnes) {
            super(view);
            this.mCtx = mCtx;
            this.cardmodellist = OrderNewOnes;
            deletBTN = view.findViewById(R.id.deleteButton);
            product_img =view.findViewById(R.id.productImage);
            product_name = view.findViewById(R.id.productname);

            prices =view.findViewById(R.id.assuredRate);
            productIdd =view.findViewById(R.id.productId);





        }

        @Override
        public void onClick(final View v) {
//            v.startAnimation(buttonClick);
        }

        @Override
        public boolean onLongClick(View v) {
            return true;
        }
    }


}


