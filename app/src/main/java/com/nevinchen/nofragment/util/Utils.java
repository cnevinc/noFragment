package com.nevinchen.nofragment.util;

import android.os.Build;
import android.transition.TransitionManager;
import android.view.ViewGroup;

/**
 * Created by nevin on 11/19/15.
 */
public class Utils {

    public  static void changeScense(ViewGroup parent){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            TransitionManager.beginDelayedTransition(parent);
    }
}
