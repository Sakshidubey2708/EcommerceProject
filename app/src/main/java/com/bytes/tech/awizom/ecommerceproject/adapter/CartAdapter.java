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
import com.bytes.tech.awizom.ecommerceproject.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceproject.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceproject.models.CartAssured;
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
    private String _stringVal,stringMM="";
    Double total = null;
    private long CartAssuredId=0;
    private CartAssured cartAssureds;


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
            holder.cartIds.setText(String.valueOf(catagoriesModel.getCartId()));
//            CartAssuredId =  Integer.parseInt(holder.cartIds.getText().toString());
            holder.product_name.setText(catagoriesModel.getProductName());
            holder.assuredprice.setText("₹"+String.valueOf(catagoriesModel.getAssuredPriceINR()));
            holder.mrpPrice.setText("₹"+String.valueOf(catagoriesModel.getMRPINR()));
            holder.discount.setText(String.valueOf(catagoriesModel.getMRPDiscountINR()) + "%");


            holder.minusbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("src", "Decreasing value...");
                    _counter--;
                    _stringVal = Integer.toString(_counter);
                    holder.quantity.setText(_stringVal);

                    Double  qty, ass_price,mm;
                    if(1 >= _counter) {

                        qty = Double.parseDouble(holder.quantity.getText().toString());
                        ass_price = Double.parseDouble(holder.assuredprice.getText().toString().split("₹")[1]);
                        total = qty * ass_price;


                        holder.totals.setText("₹"+total.toString());
                    }



                }
            });




            holder.plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("src", "Increasing value...");
                    _counter++;
                    _stringVal = Integer.toString(_counter);
                    holder.quantity.setText(_stringVal);
                    Double  qty, ass_price;
                    if(_counter >=1){
                        qty = Double.parseDouble(holder.quantity.getText().toString());
                        ass_price = Double.parseDouble(holder.assuredprice.getText().toString().split("₹")[1]);
                        total = qty*ass_price;
                        holder.totals.setText("₹"+total.toString());

//                        if(!holder.totals.getText().toString().isEmpty()){
//                            try {
//
//                                result = new HelperApi.PostCartAmount().execute(
//                                        String.valueOf(CartAssuredId),
//                                        holder.cartIds.getText().toString(),
//                                        holder.productIdd.getText().toString(),
//                                        SharedPrefManager.getInstance(mCtx).getUser().getUserID().toString(),
//                                        holder.quantity.getText().toString(),
//                                        holder.totals.getText().toString().split("₹")[1]).get();
//                                if (result.isEmpty()) {
//                                    result = new HelperApi.PostCartAmount().execute(
//                                            String.valueOf(CartAssuredId),
//                                            holder.cartIds.getText().toString(),
//                                            holder.productIdd.getText().toString(),
//                                            SharedPrefManager.getInstance(mCtx).getUser().getUserID().toString(),
//                                            holder.quantity.getText().toString(),
//                                            holder.totals.getText().toString().split("₹")[1]).get();
//                                } else {
//                                    if (result.isEmpty()) {
//
//                                    } else {
//                                        Gson gson = new Gson();
//                                        Type listType = new TypeToken<List<CartAssured>>() {
//                                        }.getType();
//                                        cartAssureds = new Gson().fromJson(result, listType);
//                                        CartAssuredId =cartAssureds.getCartAssuredId();
//                                        Toast.makeText(mCtx,String.valueOf(CartAssuredId),Toast.LENGTH_LONG).show();
//                                    }
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }else {
//
//                        }
                    }


                  //  stringMM = holder.assuredprice.getText().toString().split("₹")[1];
                }
            });
            holder.deletBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        result = new HelperApi.DeleteCartPost().execute(  holder.cartIds.getText().toString()).get();
                        if (result.isEmpty()) {
                            result = new HelperApi.DeleteCartPost().execute(  holder.cartIds.getText().toString()).get();                        } else {
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

        private ImageView product_img;
        private TextView product_name,assuredprice,mrpPrice,discount,productIdd,quantity,totals,imglinks,deletBTN,cartIds;
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
            cartIds = view.findViewById(R.id.cartId);

            quantity = view.findViewById(R.id.quantities);

            assuredprice =view.findViewById(R.id.assuredRate);
            mrpPrice =view.findViewById(R.id.mrpPrice);
            discount = view.findViewById(R.id.dicount);
            totals = view.findViewById(R.id.total);

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


