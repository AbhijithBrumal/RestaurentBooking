package com.tendercut.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Abhijith on 21-11-2016.
 */

public class Utils {

    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity
                    .getCurrentFocus().getWindowToken(), 0);
        }
    }
}
