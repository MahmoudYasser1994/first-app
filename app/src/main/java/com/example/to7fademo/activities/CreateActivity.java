package com.example.to7fademo.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.to7fademo.Models.Register.RegisterModel;
import com.example.to7fademo.R;
import com.example.to7fademo.api.RetrofitInstance;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {
    @BindView(R.id.editText)
    EditText txtname;
    @BindView(R.id.editText2)
    EditText txtemail;
    @BindView(R.id.editText3)
    EditText txtphone;
    @BindView(R.id.editText4)
    EditText txtpassword;
    @BindView(R.id.editText5)
    EditText txtpasswordconfirm;
    @BindView(R.id.button4)
    Button btn1;


    public void UserSignUp() {
        String name = txtname.getText ().toString ();
        String email = txtemail.getText ().toString ();
        String phone = txtphone.getText ().toString ();
        String pass = txtpassword.getText ().toString ();
        String password_confirmation = txtpasswordconfirm.getText ().toString ();

        if (name.isEmpty ()) {
            txtname.setError ("Name is Required");
            txtname.requestFocus ();
            return;
        }
        if (email.isEmpty ()) {
            txtemail.setError ("Email is Required");
            txtemail.requestFocus ();
            return;
        }

        if (phone.isEmpty ()) {
            txtphone.setError ("Phone is Required");
            txtphone.requestFocus ();
            return;
        }
        if (pass.isEmpty ()) {
            txtpassword.setError ("password is Required");
            txtpassword.requestFocus ();
            return;
        }
        if (pass.length () < 8) {
            txtpassword.setError ("password must be greater than 8 letters ");
            txtpassword.requestFocus ();
            return;
        }

        if (password_confirmation.isEmpty ()) {
            txtpasswordconfirm.setError ("password is Required");
            txtpasswordconfirm.requestFocus ();
            return;
        }


        Call<RegisterModel> data = RetrofitInstance.getInstance ().getApi ().register (name, email, phone, pass, password_confirmation);

        data.enqueue (new Callback<RegisterModel> () {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {

                String s = response.body ().getStatus ().getTitle ().getAr ();
                Toast.makeText (CreateActivity.this, s, Toast.LENGTH_SHORT).show ();

            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                Toast.makeText (CreateActivity.this, t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_create);
        ButterKnife.bind (this);

        View decorView = getWindow ().getDecorView ();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility (uiOptions);



        btn1.setOnClickListener (v -> UserSignUp ());

    }
}
