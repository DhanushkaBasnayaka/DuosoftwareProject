package com.soluvition.dw.duosoftwareproject.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.soluvition.dw.duosoftwareproject.DuosoConstant;
import com.soluvition.dw.duosoftwareproject.R;
import com.soluvition.dw.duosoftwareproject.model.User;
import com.soluvition.dw.duosoftwareproject.service.responses.LoginResponse;


/**
 * Created by Dhanushka on 17/06/2017.
 */

public class DuosoftHelper {


    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(DuosoConstant.PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean getUserLoggedInState(Context context) {
        final SharedPreferences prefs = getPreferences(context);

        return prefs.getBoolean(DuosoConstant.PREF_KEY_USER_LOGGED_IN_STATE, false);
    }

    public static String getAccessToken(Context context) {
        final SharedPreferences prefs = getPreferences(context);

        return prefs.getString(DuosoConstant.PREF_KEY_ACCESS_TOKEN, "");
    }

    public static void saveToPreferences(Context context, String token, boolean userLoggedInState) {
        final SharedPreferences preferences = context.getSharedPreferences(
                DuosoConstant.PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        try {
            editor.putString(
                    DuosoConstant.PREF_KEY_ACCESS_TOKEN, token
            );


            editor.putBoolean(
                    DuosoConstant.PREF_KEY_USER_LOGGED_IN_STATE, userLoggedInState
            );

            editor.apply();
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    public static void removePreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(DuosoConstant.PREFERENCES, Context.MODE_PRIVATE);

        preferences.edit().remove(DuosoConstant.PREF_KEY_ACCESS_TOKEN).commit();


        preferences.edit().putBoolean(
                DuosoConstant.PREF_KEY_USER_LOGGED_IN_STATE, false
        ).commit();

    }

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void showSnackbar(RelativeLayout relativeLayout, String message, Context context) {
        Snackbar snackbar = Snackbar
                .make(relativeLayout, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(context.getResources().getColor(R.color.colorDarkGray));
        snackbar.show();
    }

    public static void showSnackbarLinear(LinearLayout relativeLayout, String message, Context context) {
        Snackbar snackbar = Snackbar
                .make(relativeLayout, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(context.getResources().getColor(R.color.colorDarkGray));
        snackbar.show();
    }

    public final static boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
