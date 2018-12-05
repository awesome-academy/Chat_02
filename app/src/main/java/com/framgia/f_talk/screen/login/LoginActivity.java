package com.framgia.f_talk.screen.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.framgia.f_talk.R;
import com.google.android.gms.common.SignInButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SignInButton signInButton = findViewById(R.id.button_google_sign_in);
        signInButton.setSize(SignInButton.SIZE_WIDE);
    }
}
