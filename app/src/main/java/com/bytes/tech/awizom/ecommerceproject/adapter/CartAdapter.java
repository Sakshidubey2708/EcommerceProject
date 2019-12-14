package com.bytes.tech.awizom.ecommerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.activity.CartActivity;
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceproject.models.CartModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CartAdapter extends  RecyclerView.Adapter<CartAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<CartModel> cardmodellist;
    private CartModel propertyName;
    TextView calltext;
    private String result = "";
    private boolean isTailor = true;
    long pid = 0;
    private static int _counter = 1;
    private String _stringVal;


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
            holder.minusbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("src", "Decreasing value...");
                    _counter--;
                    _stringVal = Integer.toString(_counter);
                    holder.quantity.setText(_stringVal);
                }
            });
            holder.plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("src", "Increasing value...");
                    _counter++;
                    _stringVal = Integer.toString(_counter);
                    holder.quantity.setText(_stringVal);
                }
            });
            holder.deletBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        result = new HelperApi.DeleteCartPost().execute(SharedPrefManager.getInstance(mCtx).getUser().getUserID().toString()).get();
                        if (result.isEmpty()) {
                            result = new HelperApi.DeleteCartPost().execute(SharedPrefManager.getInstance(mCtx).getUser().getUserID().toString()).get();
                        } else {
                            if (result.isEmpty()) {

                            } else {
                                   Toast.makeText(mCtx,"Deleted",Toast.LENGTH_LONG).show();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
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
        private TextView product_name,prices,productIdd,quantity,imglinks;
        private List<CartModel> cardmodellist;
        private Context mCtx;
        private Button minusbtn,plusBtn;

        public OrderItemViewHolder(View view, Context mCtx, List<CartModel> OrderNewOnes) {
            super(view);
            this.mCtx = mCtx;
            this.cardmodellist = OrderNewOnes;
            deletBTN = view.findViewById(R.id.deleteButton);
            product_img =view.findViewById(R.id.productImage);
            product_name = view.findViewById(R.id.productname);

            quantity = view.findViewById(R.id.quantities);

            prices =view.findViewById(R.id.assuredRate);
            productIdd =view.findViewById(R.id.productId);
            imglinks = view.findViewById(R.id.imaglink);

            minusbtn =view.findViewById(R.id.minus);
            plusBtn = view.findViewById(R.id.add);

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


