package com.example.to7fademo.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.to7fademo.Models.Check.CheckModel;
import com.example.to7fademo.R;
import com.example.to7fademo.api.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckMailActivity extends AppCompatActivity {
    TextView text;
    public EditText edittxtmailsend;
    Button btnhave, btnsend;

    private void CheckButton() {

        String mail = edittxtmailsend.getText ( ).toString ( );

        Call<CheckModel> check = RetrofitInstance.getInstance ( ).getApi ( ).check (mail);
        check.enqueue (new Callback<CheckModel> ( ) {
            @Override
            public void onResponse(Call<CheckModel> call, Response<CheckModel> response) {
                String s = response.body ( ).getStatus ( ).getTitle ( ).getAr ( );
                Toast.makeText (CheckMailActivity.this, s, Toast.LENGTH_LONG).show ( );
            }

            @Override
            public void onFailure(Call<CheckModel> call, Throwable t) {

            }
        });
        Intent myintent = new Intent (this, ConfirmationActivity.class);
        Bundle b = new Bundle ( );
        b.putString ("email", edittxtmailsend.getText ( ).toString ( ));
        myintent.putExtras (b);
        startActivity (myintent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_check_mail);
        text = findViewById (R.id.textsend);
        edittxtmailsend = findViewById (R.id.textemailsend);
        btnhave = findViewById (R.id.buttonhavecode);
        btnsend = findViewById (R.id.buttonsendemail);

    }

    public void SendMail(View view) {

        CheckButton ( );
    }

    public void havecode(View view) {
        Intent myintent = new Intent (this, ConfirmationActivity.class);

        startActivity (myintent);
    }
}
