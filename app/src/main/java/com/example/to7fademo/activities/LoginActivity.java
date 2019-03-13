

package com.example.to7fademo.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.to7fademo.Helper.MyAlertDialog;
import com.example.to7fademo.Models.Login.LoginModel;
import com.example.to7fademo.R;
import com.example.to7fademo.SharedPreference.SharedPreferencesConfig;
import com.example.to7fademo.api.RetrofitInstance;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView (R.id.edittextemaillogin) EditText txtemaillogin;
    @BindView (R.id.edittextpasswordlogin) EditText txtpasswordlogin;

    AlertDialog alertDialog;
    MyAlertDialog myAlertDialog;
    SharedPreferencesConfig preferencesConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        ButterKnife.bind (this);
        myAlertDialog = new MyAlertDialog (alertDialog);
        preferencesConfig = new SharedPreferencesConfig (getApplicationContext ( ));

    }


    private void UserLogin() {
        myAlertDialog.showDialogue (this);

        final String email = txtemaillogin.getText ( ).toString ( );
        final String password = txtpasswordlogin.getText ( ).toString ( );

        if (email.isEmpty ( )) {
            txtemaillogin.setError ("Email is Required");
            txtemaillogin.requestFocus ( );
            return;
        }


        if (password.isEmpty ( )) {
            txtpasswordlogin.setError ("password is Required");
            txtpasswordlogin.requestFocus ( );
            return;
        }
        if (txtpasswordlogin.length ( ) < 8) {
            txtpasswordlogin.setError ("password must be greater than 8 letters ");
            txtpasswordlogin.requestFocus ( );
            return;
        }


        Call<LoginModel> login = RetrofitInstance.getInstance ( ).getApi ( ).login (email, password);

        login.enqueue (new Callback<LoginModel> ( ) {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                String ss = response.body ( ).getStatus ( ).getTitle ( ).getAr ( );
                Toast.makeText (LoginActivity.this, ss, Toast.LENGTH_LONG).show ( );
                if (response.body ( ).getStatus ( ).getType ( ).equals ("1")) {
                    Intent myintent1 = new Intent (LoginActivity.this, FirstActivity.class);
                    startActivity (myintent1);
                    preferencesConfig.writeLoginStatus (true);

                }

            }


            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText (LoginActivity.this, t.getMessage ( ), Toast.LENGTH_LONG).show ( );
            }
        });


    }


    public void btncreate(View view) {
        Intent myintent = new Intent (this, CreateActivity.class);
        startActivity (myintent);
    }

    public void btnlog(View view) {
        UserLogin ( );
    }

    public void Forget(View view) {
        Intent myintent = new Intent (this, CheckMailActivity.class);
        startActivity (myintent);
    }


}
