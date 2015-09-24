package com.nevinchen.nofragment.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.nevinchen.nofragment.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nevin on 9/24/15.
 */
public class MainView extends LinearLayout {

    private static final String TAG = "nevin";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.embed)
    LinearLayout embed;


    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        setupUI();



    }

    private void setupUI() {
        toolbar.setNavigationIcon(R.drawable.ic_launcher);
        toolbar.setTitle("Home");
    }

    @OnClick(R.id.show_info)
    public void showInfo() {
        InfoView.AddMe((Activity) getContext());
    }

    @OnClick(R.id.show_embed)
    public void showEmbed() {
        // re-use our custom view! Can you do that in fragment? no!
        InfoView v = InfoView.AddMe(embed);
        Log.d(TAG, "showEmbed " + v.getId());
        v.hideAppBar();
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // clean up resource here
    }
}
