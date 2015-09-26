package com.nevinchen.nofragment.ui.presentor;

import android.app.Activity;
import android.view.ViewGroup;

/**
 * Created by nevin on 9/26/15.
 */
public interface Container {

    void bind(Object data);

    boolean onBackPressed();

}
