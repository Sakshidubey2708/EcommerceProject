package com.bytes.tech.awizom.ecommerceproject.activity;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bytes.tech.awizom.ecommerceproject.R;
import com.bytes.tech.awizom.ecommerceproject.configure.AccountControlerHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 007;
    String TAG = "Check",result="";

    EditText emails, passWord, contact;
    TextView paswdHidShows;
    Button login_click;
    String Check;
    private final static int ALL_PERMISSIONS_RESULT = 107;
    private final static int IMAGE_RESULT = 200;
    private static int TIMER = 300;
    private boolean isPasswordVisible;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_logoin_activity);

        signInButton = findViewById(R.id.sign_in_button);
        login_click = findViewById(R.id.loginbtn);
        login_click.setOnClickListener(this);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        emails = findViewById(R.id.email);
        passWord = findViewById(R.id.pasword);
        contact = findViewById(R.id.mobile);
        paswdHidShows = findViewById(R.id.paswdHidShow);
        progressDialog = new ProgressDialog(this);
        paswdHidShows.setOnClickListener(this);

        emails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent googlePicker = AccountPicker.newChooseAccountIntent(null, null, new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE}, true, null, null, null, null);
//                startActivityForResult(googlePicker, 201);
            }
        });

//        contact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        TelephonyManager tm = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        AccountManager am = AccountManager.get(this);
        Account[] accounts = am.getAccounts();
        ArrayList<String> googleAccounts = new ArrayList<String>();
        for (Account ac : accounts) {
            String acname = ac.name;
            String actype = ac.type;
            // Take your time to look at all available accounts
            Log.w(TAG, "Account : " +  acname + ", " + actype);
        }
    }

    private void togglePassVisability() {
        if (isPasswordVisible) {
            String pass = passWord.getText().toString();
            passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passWord.setText(pass);
            passWord.setSelection(pass.length());
            paswdHidShows.setText("Show");
        } else {
            String pass = passWord.getText().toString();
            passWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passWord.setInputType(InputType.TYPE_CLASS_TEXT);
            passWord.setText(pass);
            passWord.setSelection(pass.length());
            paswdHidShows.setText("Hide");
        }
        isPasswordVisible= !isPasswordVisible;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signInWithGoogle();
                break;
            case R.id.paswdHidShow:
                togglePassVisability();
                break;

            case R.id.loginbtn:
                loginEvent();
                break;
        }
    }

    private void loginEvent() {

        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
            result = new AccountControlerHelper.PostLogin().execute(emails.getText().toString(),passWord.getText().toString()).get();
            if(result.isEmpty()){
                progressDialog.dismiss();
                result = new AccountControlerHelper.PostLogin().execute(emails.getText().toString(),passWord.getText().toString()).get();
            }else {
                try {
                    progressDialog.dismiss();
                    Snackbar.make(getWindow().getDecorView().getRootView(), result.toString(), Snackbar.LENGTH_LONG).show();
                    Gson gson = new Gson();

                        Toast.makeText(SignInActivity.this, "User Id Password Incorrect", Toast.LENGTH_SHORT).show();


                }catch (Exception e){

                }

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void signInWithGoogle() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }else if (requestCode == 201 && resultCode == RESULT_OK) {
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            //emails.setText(accountName);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account);
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            Toast.makeText(this,"not null", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"null", Toast.LENGTH_LONG).show();
        }
    }

}
