package com.framgia.f_talk.screen.signup;

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
import com.framgia.f_talk.databinding.ActivitySignUpBinding;
import com.framgia.f_talk.screen.createaccount.CreateAccountActivity;
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

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding, SignUpViewModel>
        implements SignUpNavigator, FacebookCallback<LoginResult> {
    public static final String EXTRA_FULL_NAME = "com.framgia.ftalk.extras.EXTRA_FULL_NAME";
    public static final String EXTRA_EMAIL = "com.framgia.ftalk.extras.EXTRA_EMAIL";
    private static final int SIGN_UP_WITH_GOOGLE_CODE = 102;
    @Inject
    SignUpViewModel mSignUpViewModel;
    private ActivitySignUpBinding mActivitySignUpBinding;

    private GoogleSignInClient mClient;

    private CallbackManager mCallbackManager;

    public static Intent getIntent(Context context) {
        return new Intent(context, SignUpActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    public SignUpViewModel getViewModel() {
        return mSignUpViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignUpViewModel.setNavigator(this);
        mActivitySignUpBinding = getViewDataBinding();
        mActivitySignUpBinding.buttonGoogleSignIn.setSize(SignInButton.SIZE_WIDE);
        setUpGoogleSignUpButton();
        setUpFacebookSignUpButton();
    }


    @Override
    public void provideNameAndEmail() {
        mActivitySignUpBinding.textInputFullName.setError(null);
        mActivitySignUpBinding.textInputEmail.setError(null);
        String fullName = mActivitySignUpBinding
                .textInputFullName.getEditText().getText().toString();
        String email = mActivitySignUpBinding
                .textInputEmail.getEditText().getText().toString();
        mSignUpViewModel.setUserData(fullName, email);
    }

    @Override
    public void signUpWithGoogle() {
        Intent intent = mClient.getSignInIntent();
        startActivityForResult(intent, SIGN_UP_WITH_GOOGLE_CODE);
    }

    @Override
    public void onSignUpGoogleSuccess() {
        // TODO: 12/8/18 Sign up Google Success
    }

    @Override
    public void onSignUpFacebookSuccess() {
        // TODO: 12/8/18 Sign up Facebook Success
    }

    @Override
    public void onSignUpFailure() {
        Toast.makeText(this, getString(R.string.msg_create_account_fail),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFullNameInvalid() {
        mActivitySignUpBinding.textInputFullName.setError(getString(R.string.msg_full_name_invalid));
    }

    @Override
    public void onEmailInvalid() {
        mActivitySignUpBinding.textInputEmail.setError(getString(R.string.msg_email_invalid));
    }

    @Override
    public void moveToCreateAccountActivity() {
        String fullName = mActivitySignUpBinding
                .textInputFullName.getEditText().getText().toString();
        String email = mActivitySignUpBinding
                .textInputEmail.getEditText().getText().toString();
        Intent intent = SignUpActivity.getCreateAccountIntent(this, fullName, email);
        startActivity(intent);
    }

    private void setUpFacebookSignUpButton() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = mActivitySignUpBinding.buttonFacebookLogIn;
        loginButton.setReadPermissions(Constant.PERMISSION_EMAIL,
                Constant.PERMISSION_PUBLIC_PROFILE);
        loginButton.registerCallback(mCallbackManager, this);
    }

    private void setUpGoogleSignUpButton() {
        SignInButton signInButton = mActivitySignUpBinding.buttonGoogleSignIn;
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
    public void onSuccess(LoginResult loginResult) {
        AuthCredential credential = FacebookAuthProvider
                .getCredential(loginResult.getAccessToken().getToken());
        mSignUpViewModel.signUpWithFacebook(FirebaseAuth.getInstance(), credential);
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, getString(R.string.msg_sign_up_cancel), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Toast.makeText(this, getString(R.string.msg_create_account_fail), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_UP_WITH_GOOGLE_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider
                        .getCredential(account.getIdToken(), null);
                mSignUpViewModel.signUpWithGoogle(FirebaseAuth.getInstance(), credential);
            } catch (ApiException e) {
                Toast.makeText(this, getString(R.string.msg_sign_up_error),
                        Toast.LENGTH_SHORT).show();
            }
            return;
        }
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public static Intent getCreateAccountIntent(Context context, String fullName, String email) {
        Intent intent = new Intent(context, CreateAccountActivity.class);
        intent.putExtra(EXTRA_FULL_NAME, fullName);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }
}
