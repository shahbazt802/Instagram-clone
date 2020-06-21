package com.floydd.instagram_clone;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {


    private EditText edtsignup,edtsignuppassword,edtlogin,edtloginpassword;
    private Button btnsignnup,btnlogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        edtsignup=findViewById(R.id.edtsignup);
        edtsignuppassword=findViewById(R.id.edtsignuppassword);
        edtlogin=findViewById(R.id.edtlogin);
        edtloginpassword=findViewById(R.id.edtloginpassword);
        btnsignnup=findViewById(R.id.btnsignup);
        btnlogin=findViewById(R.id.btnlogin);
        btnsignnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appUser=new ParseUser();
               appUser.setUsername(edtsignup.getText().toString());
               appUser.setPassword(edtsignuppassword.getText().toString());
              appUser.signUpInBackground(new SignUpCallback() {
                  @Override
                  public void done(ParseException e) {
                      if(e==null){
                          FancyToast.makeText(SignUpLoginActivity.this, appUser.get("username")+" is signup successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                      }
                      else{
                          FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                      }
                  }
              });
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtlogin.getText().toString(), edtloginpassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null && e==null){
                            FancyToast.makeText(SignUpLoginActivity.this, user.get("username")+" is login successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }
                        else{
                            FancyToast.makeText(SignUpLoginActivity.this ,e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });
    }
}
