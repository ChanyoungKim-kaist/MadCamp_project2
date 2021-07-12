package com.example.loginui_kakao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginui_kakao.data.KakaoData;
import com.example.loginui_kakao.data.KakaoResponse;
import com.example.loginui_kakao.data.LoginData;
import com.example.loginui_kakao.data.LoginResponse;
import com.example.loginui_kakao.network.RetrofitClient;
import com.example.loginui_kakao.network.ServiceApi;
import com.google.gson.annotations.SerializedName;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ISessionCallback mSessionCallback;
    private EditText mEmailView;
    private EditText mPasswordView;
    private TextView mEmailLoginButton;
    private TextView mJoinButton;
    private ProgressBar mProgressView;
    private ServiceApi service;
    private static int SPLASH_SCREEN=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);*/


        mSessionCallback = new ISessionCallback() {
            @Override
            public void onSessionOpened() { //로그인 요청
                UserManagement.getInstance().me(new MeV2ResponseCallback() {
                    @Override
                    public void onFailure(ErrorResult errorResult) { //로그인 실패
                        Toast.makeText(MainActivity.this, "로그인 도중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        Toast.makeText(MainActivity.this, "세션이 닫혔습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(MeV2Response result) { //로그인 성공
                        long userId = result.getId();
                        String userName = result.getKakaoAccount().getProfile().getNickname();
                        String userEmail = result.getKakaoAccount().getEmail();
                        kakaoLogin(new KakaoData(userName, userEmail, userId));
                        Toast.makeText(MainActivity.this, "환영합니다!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {

            }
        };
        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();

        mEmailView = (EditText) findViewById(R.id.username_input);
        mPasswordView = (EditText) findViewById(R.id.pass);
        mEmailLoginButton = (TextView) findViewById(R.id.login);
        mJoinButton = (TextView) findViewById(R.id.signup);
        mProgressView = (ProgressBar) findViewById(R.id.login_progress);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        mEmailLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
    }

    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mEmailView.setError("비밀번호를 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            mEmailView.setError("이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            //Toast.makeText(MainActivity.this, "입", Toast.LENGTH_SHORT).show();
            startLogin(new LoginData(email, password));
            //showProgress(true);
        }
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();

                if (result.getOk()) {
                    Toast.makeText(MainActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                    String token = result.getToken();
                    Intent loginintent = new Intent(getApplicationContext(), SuccessActivity.class);
                    loginintent.putExtra("token", token);
                    startActivity(loginintent);
                }
                else
                    Toast.makeText(MainActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
                //showProgress(false);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
                t.printStackTrace();
                //showProgress(false);
            }
        });
    }

    private void kakaoLogin(KakaoData data) {
        service.userKakao(data).enqueue(new Callback<KakaoResponse>() {
            @Override
            public void onResponse(Call<KakaoResponse> call, Response<KakaoResponse> response) {
                KakaoResponse result = response.body();
                if (result.getOk()) {
                    Toast.makeText(MainActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                    String token = result.getToken();
                    Intent loginintent = new Intent(getApplicationContext(), SuccessActivity.class);
                    loginintent.putExtra("token", token);
                    startActivity(loginintent);
                }
                else
                    Toast.makeText(MainActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<KakaoResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(mSessionCallback);
    }
}
