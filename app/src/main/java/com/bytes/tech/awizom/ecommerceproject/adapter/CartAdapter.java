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
import com.bytes.tech.awizom.ecommerceproject.models.ProductDetailModel;
import java.util.List;

public class CartAdapter extends  RecyclerView.Adapter<CartAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<ProductDetailModel> cardmodellist;
    private ProductDetailModel propertyName;
    TextView calltext;
    private String result = "";
    private boolean isTailor = true;
    long pid = 0;
    private static int _counter = 1;
    private String _stringVal,stringMM="";
    Double total = null;


    public CartAdapter(Context mCtx, List<ProductDetailModel> OrderNewOnes) {
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
        ProductDetailModel catagoriesModel = cardmodellist.get(position);
        try{
            holder.productIdd.setText(String.valueOf(catagoriesModel.getProductId()));
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
                    }


                  //  stringMM = holder.assuredprice.getText().toString().split("₹")[1];
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
        private TextView product_name,assuredprice,mrpPrice,discount,productIdd,quantity,totals,imglinks;
        private List<ProductDetailModel> cardmodellist;
        private Context mCtx;
        private Button minusbtn,plusBtn;

        public OrderItemViewHolder(View view, Context mCtx, List<ProductDetailModel> OrderNewOnes) {
            super(view);
            this.mCtx = mCtx;
            this.cardmodellist = OrderNewOnes;
            deletBTN = view.findViewById(R.id.deleteButton);
            product_img =view.findViewById(R.id.productImage);
            product_name = view.findViewById(R.id.productname);

            quantity = view.findViewById(R.id.quantities);

            assuredprice =view.findViewById(R.id.assuredRate);
            mrpPrice =view.findViewById(R.id.mrpPrice);
            discount = view.findViewById(R.id.dicount);
            totals = view.findViewById(R.id.total);

            productIdd =view.findViewById(R.id.productId);
            imglinks = view.findViewById(R.id.imaglink);

            minusbtn =view.findViewById(R.id.minus);
            plusBtn =
                    view.findViewById(R.id.add);

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


