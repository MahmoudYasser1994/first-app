package com.example.to7fademo.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.to7fademo.Models.ConfirmationModel.ConfirmationModel;
import com.example.to7fademo.R;
import com.example.to7fademo.api.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmationActivity extends AppCompatActivity {
    EditText txtcode;
    Button btnconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_confirmation);
        txtcode = findViewById (R.id.textcodeconfirm);
        btnconfirm= findViewById (R.id.buttonconfirm);
    }
    private void ConfirmCode(){
        String code = txtcode.getText ().toString ();
        Bundle b = getIntent ().getExtras ();

        String email =b.getString ("email");

        Call<ConfirmationModel> confirm = RetrofitInstance.getInstance ().getApi ().confirm (code,email);

        confirm.enqueue (new Callback<ConfirmationModel> () {
            @Override
            public void onResponse(Call<ConfirmationModel> call, Response<ConfirmationModel> response) {
                String s = response.body ().getStatus ().getTitle ().getAr ();
                Toast.makeText (ConfirmationActivity.this, s, Toast.LENGTH_LONG).show ();
            }

            @Override
            public void onFailure(Call<ConfirmationModel> call, Throwable t) {
                Toast.makeText (ConfirmationActivity.this, t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });

    }

    public void ConfirmMail(View view) {
        ConfirmCode();

    }
}
