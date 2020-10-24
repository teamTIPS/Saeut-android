package com.teamtips.android.saeut.func.login.join.ui.generalLogin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.teamtips.android.saeut.MainActivity;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.login.SessionCallback;
import com.teamtips.android.saeut.func.login.data.model.LoggedInUser;
import com.teamtips.android.saeut.func.login.join.JoinActivity;

public class LoginActivity extends AppCompatActivity {


    private static final String Tag = "LoginActivity";
    private LoginViewModel loginViewModel;
    private SessionCallback sessionCallback = new SessionCallback();
    Session session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        SaveSharedPreference.clearUser(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_total);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText emailEditText = findViewById(R.id.email);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.email_sign_in_button);
//        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final Button kakao_sign_in_button = findViewById(R.id.kakao_sign_in_button);
        final Button email_sign_up_button = findViewById(R.id.email_sign_up_button);

        session = Session.getCurrentSession();
        session.addCallback(sessionCallback);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    emailEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    Log.e(Tag,"loginResult == null");
                    return;
                }
//                loadingProgressBar.setVisibility(View.GONE);

                //로그인 실패
                if (loginResult.getError() != null) {
                    showpopup();
                    emailEditText.setText("");
                    passwordEditText.setText("");
                    Log.e(Tag,"로그인 실패");
                    //로그인 결과 초기화
                    loginResult.setOrigin();
                }

                //로그인 성공
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                //Complete and destroy login activity once successful
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(emailEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        emailEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
//        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    loginViewModel.login(emailEditText.getText().toString(),
//                            passwordEditText.getText().toString());
//                }
//                return false;
//            }
//        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(emailEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

        kakao_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);
            }
        });

        email_sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), JoinActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUiWithUser() {
        LoggedInUser loggedInUser = LoggedInUser.getLoggedInUser();
        String welcome = getString(R.string.welcome) + loggedInUser.getnickname();
        // TODO : initiate successful logged in experience
        Log.e(Tag,welcome);
    }

    private void showpopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내")
                .setMessage("로그인에 실패했어요. 아이디와 비밀번호를 다시 확인해 주세요.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}