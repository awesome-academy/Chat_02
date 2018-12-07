package com.framgia.f_talk.screen.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.framgia.f_talk.BR;
import com.framgia.f_talk.BaseActivity;
import com.framgia.f_talk.R;
import com.framgia.f_talk.databinding.ActivityLoginBinding;
import com.framgia.f_talk.util.Constant;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>
        implements LoginNavigator, FacebookCallback<LoginResult> {
    private static final int SIGN_IN_WITH_GOOGLE_CODE = 101;
    @Inject
    LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mActivityLoginBinding;

    private GoogleSignInClient mClient;

    private CallbackManager mCallbackManager;

    public static Intent getIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        return mLoginViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginViewModel.setNavigator(this);
        mActivityLoginBinding = getViewDataBinding();
        setUpGoogleSignInButton();
        setUpFacebookLoginButton();
    }

    private void setUpFacebookLoginButton() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = mActivityLoginBinding.buttonFacebookLogIn;
        loginButton.setReadPermissions(Constant.PERMISSION_EMAIL,
                Constant.PERMISSION_PUBLIC_PROFILE);
        loginButton.registerCallback(mCallbackManager, this);
    }

    private void setUpGoogleSignInButton() {
        SignInButton signInButton = mActivityLoginBinding.buttonGoogleSignIn;
        signInButton.setSize(SignInButton.SIZE_WIDE);
        GoogleSignInOptions options = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build();
        mClient = GoogleSignIn.getClient(this, options);
    }

    @Override
    public void loginWithEmailAndPassword() {
        mActivityLoginBinding.textInputEmail.setError(null);
        mActivityLoginBinding.textInputPassword.setError(null);
        String email = mActivityLoginBinding
                .textInputEmail.getEditText().getText().toString();
        String password = mActivityLoginBinding
                .textInputPassword.getEditText().getText().toString();
        mLoginViewModel.loginWithEmail(FirebaseAuth.getInstance(), email, password);
    }

    @Override
    public void loginWithGoogle() {
        Intent intent = mClient.getSignInIntent();
        startActivityForResult(intent, SIGN_IN_WITH_GOOGLE_CODE);
    }

    @Override
    public void onLoginSuccess() {
        // TODO: 12/7/18 Move to MainActivity
    }

    @Override
    public void onLoginFailure() {
        Toast.makeText(this, getString(R.string.msg_login_fail), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onEmailInvalid() {
        mActivityLoginBinding.textInputEmail.setError(getString(R.string.msg_email_invalid));
    }

    @Override
    public void onPasswordInvalid() {
        mActivityLoginBinding.textInputPassword.setError(getString(R.string.msg_password_invalid));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_WITH_GOOGLE_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider
                        .getCredential(account.getIdToken(), null);
                mLoginViewModel.loginWithGoogle(FirebaseAuth.getInstance(), credential);
            } catch (ApiException e) {
                e.printStackTrace();
            }
            return;
        }
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        AuthCredential credential = FacebookAuthProvider
                .getCredential(loginResult.getAccessToken().getToken());
        mLoginViewModel.loginWithFacebook(FirebaseAuth.getInstance(), credential);
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, getString(R.string.msg_login_cancel), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Toast.makeText(this, getString(R.string.msg_login_fail), Toast.LENGTH_SHORT).show();
    }
}
