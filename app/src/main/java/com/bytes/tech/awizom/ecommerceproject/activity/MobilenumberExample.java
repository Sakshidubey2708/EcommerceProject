package com.bytes.tech.awizom.ecommerceproject.activity;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceproject.R;

public class MobilenumberExample extends AppCompatActivity {

    TextView number;
    Button click;
    String info;
    String strPhoneType;
    static final int PERMISSION_READ_STATE = 123;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_slide_dailog);


    }

}