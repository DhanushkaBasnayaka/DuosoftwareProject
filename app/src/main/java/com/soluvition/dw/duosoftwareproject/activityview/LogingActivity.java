package com.soluvition.dw.duosoftwareproject.activityview;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import com.soluvition.dw.duosoftwareproject.DuosoConstant;
import com.soluvition.dw.duosoftwareproject.R;
import com.soluvition.dw.duosoftwareproject.helper.DuosoftHelper;
import com.soluvition.dw.duosoftwareproject.service.DuosoftServiceManager;
import com.soluvition.dw.duosoftwareproject.service.events.FailedEvent;
import com.soluvition.dw.duosoftwareproject.service.events.LoginEvent;
import com.soluvition.dw.duosoftwareproject.service.events.LoginUserEvent;
import com.soluvition.dw.duosoftwareproject.service.requestes.LoginRequest;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;

import android.support.annotation.NonNull;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.soluvition.dw.duosoftwareproject.helper.DuosoftHelper.isValidEmail;

public class LogingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;
    @BindView(R.id.input_layout_password)
    TextInputLayout inputLayoutPassword;

    @BindView(R.id.rl_supervisor)
    RelativeLayout mRelativeLayoutSupervisor;

    @Inject
    public DuosoftServiceManager mDuosoftServiceManager;
    private LoginRequest mLoginRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        intview();
    }

    private void intview() {

        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
    }

    @OnClick(R.id.btn_signup)
    public void loginWithEmail(View view) {
        submitForm();
    }

    private void submitForm() {


        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }


        reqvestLoginApi();
    }

    private void reqvestLoginApi() {

        mLoginRequest = new LoginRequest();
        mLoginRequest.userName = DuosoConstant.userName;
        mLoginRequest.password = DuosoConstant.password;
        mLoginRequest.clientID = DuosoConstant.clientID;
        mLoginRequest.console = DuosoConstant.console;
        mLoginRequest.scope = DuosoConstant.scope;
        if (DuosoftHelper.isNetworkAvailable(this)) {
            mDuosoftServiceManager.login(mLoginRequest);
        } else {
            DuosoftHelper.showSnackbar(mRelativeLayoutSupervisor, DuosoConstant.ERR_INTERNET_CONNECTION_FAILED, this);
        }

    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {

            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;

        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {

            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);

            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }


    @Subscribe
    public void onEvent(LoginUserEvent event) {
        if (event.result.state.equalsIgnoreCase("login")) {

            DuosoftHelper.saveToPreferences(this, event.result.token, true);
            startActivity(new Intent(LogingActivity.this, HomeActivity.class));
            finish();
        }
    }

    @Subscribe
    public void onEvent(FailedEvent event) {

        if (DuosoftHelper.isNetworkAvailable(this)) {
            DuosoftHelper.showSnackbar(mRelativeLayoutSupervisor, event.message, this);
        } else {
            DuosoftHelper.showSnackbar(mRelativeLayoutSupervisor, DuosoConstant.ERR_INTERNET_CONNECTION_FAILED, this);
        }
    }


}
