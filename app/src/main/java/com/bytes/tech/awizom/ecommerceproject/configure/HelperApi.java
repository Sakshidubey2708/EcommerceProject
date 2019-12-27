package com.bytes.tech.awizom.ecommerceproject.configure;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HelperApi extends AppCompatActivity {

    public static final class GetAllGetMainCategoriesList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetMainCategoriesList");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class GetAllSubCategoriesList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetSubCategoriesList");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static final class GetAllTypeCategoriesList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetTypeSubCategoriesList");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class GetAllBrandsList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetBrandsList");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class GetAllProductList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetProductsList");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class GetAllProductsListByMainCatID extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String mID = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetAllProductsListByMainCatID/" + mID);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class GetSubCategoriesListByMAinCAtID extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String mID = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetSelectedMainCatagories/" + mID);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class GetProductsListBySubCAtagory extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String subcatiD = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetProductsListBySubCAtagoryss/" + subcatiD);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class GetSingleBrandsList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String bid = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetBrandsList/" +bid);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class GetSingleProductList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String pID = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetProductsLists/" + pID);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class GetSingleNAmeMainCategoriesList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String mainID = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetProductsListByMainCAtagory/" + mainID);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class GetSingleNAmeSubCategoriesList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String subID = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetProductsListBySubCAtagory/" + subID);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class GetSingleNAmeTypeCategoriesList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String typeID = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetProductsListByTypeCAtagory/" + typeID);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static final class  PostCarts extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {


            String productID = params[0];
            String UserId = params[1];

            String json = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/CartPost");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();

                parameters.add("ProductId", productID);
                parameters.add("UserId", UserId);



                builder.post(parameters.build());
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;

        }
        protected void onPostExecute(String result) {

            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static final class GetCartList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String userID = strings[0];

            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetViewCartShows/"+userID );
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class DeleteCartPost extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String CartId = strings[0];
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/PostDeleteCartItem/" +CartId);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static final class  PostOrderMain extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String OrderId = params[0];
            String OrderNo = params[1];
            String UserId = params[2];
            String DeliveryAddress = params[3];
            String Statuss = params[4];
            String TotalAmount = params[5];
            String DeliveryCharge = params[6];
            String AnyOtherCharge = params[7];

            String json = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/OrderMainPost");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();

                parameters.add("OrderId", OrderId);
                parameters.add("OrderNo", OrderNo);
                parameters.add("UserId", UserId);
                parameters.add("DeliveryAddress", DeliveryAddress);
                parameters.add("Status", Statuss);
                parameters.add("TotalAmount", TotalAmount);
                parameters.add("DeliveryCharge", DeliveryCharge);
                parameters.add("AnyOtherCharge", AnyOtherCharge);
                builder.post(parameters.build());
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;

        }
        protected void onPostExecute(String result) {

            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static final class  PostOrderDetailMain extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String OrderDetailId = params[0];
            String OrderId = params[1];
            String ProductId = params[2];
            String UnitPrice = params[3];
            String Quantity = params[4];
            String TotalAmount = params[5];
            String Discount = params[6];
            String DiscountAmount = params[7];
            String SGST = params[8];
            String CGST = params[9];
            String TotalTaxAmount = params[10];
            String TaxableAmount = params[11];

            String json = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/OrderDetailMainPost");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();

                parameters.add("OrderDetailId", OrderDetailId);
                parameters.add("OrderId", OrderId);
                parameters.add("ProductId", ProductId);
                parameters.add("UnitPrice", UnitPrice);
                parameters.add("Quantity", Quantity);
                parameters.add("TotalAmount", TotalAmount);
                parameters.add("Discount", Discount);
                parameters.add("DiscountAmount", DiscountAmount);
                parameters.add("SGST", SGST);
                parameters.add("CGST", CGST);
                parameters.add("TotalTaxAmount", TotalTaxAmount);
                parameters.add("TaxableAmount", TaxableAmount);

                builder.post(parameters.build());
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class  PostCartAmount extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String CartAssuredId = params[0];
            String CartId = params[1];
            String ProductId = params[2];
            String UserId = params[3];
            String Quantity = params[4];
            String AssuredPriceINR = params[5];


            String json = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/CartAmountDetailsPost");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();

                parameters.add("CartAssuredId" ,CartAssuredId);
                parameters.add("CartId", CartId);
                parameters.add("ProductId", ProductId);
                parameters.add("UserId", UserId);
                parameters.add("Quantity", Quantity);
                parameters.add("AssuredPriceINR", AssuredPriceINR);

                builder.post(parameters.build());
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final class GetCartAmountList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String json = "";
            String userID = strings[0];

            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API + "/GetProductAmountTotal/"+userID );
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
        protected void onPostExecute(String result) {
            try {
                if (result.isEmpty()) {
                } else {
                    super.onPostExecute(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
