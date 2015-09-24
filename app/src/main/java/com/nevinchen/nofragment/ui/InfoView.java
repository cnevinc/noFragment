package com.nevinchen.nofragment.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nevinchen.nofragment.R;

import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfoView extends LinearLayout {

    private static final String TAG = "nevinInfo";

    @Bind(R.id.app_bar)
    AppBarLayout appBar;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.message)
    TextView message;


    public InfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupBackPressed();
    }
    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        setupUI();
    }

    private void setupUI() {
        toolbar.setTitle("Info");
        toolbar.setBackgroundColor(Color.RED);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                removeFromParent();
            }
        });
        try {

            message.setText("Now:"+ Instant.now());

        } catch (Exception ex) {
            Log.e(this.getClass().getName(), "formatDate with error msg :" + ex);
        }
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // clean up resource here
    }


    /*
    *  Add {@link com.nevinchen.nofragment.ui.InfoView} to your activity's content view
    *
    * */
    public static void AddMe(Activity host) {
        InfoView v = (InfoView) host.getLayoutInflater().inflate(R.layout.info, null);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        host.addContentView(v, p);
    }

    /*
   *  Add {@link com.nevinchen.nofragment.ui.InfoView} to what ever parent you want
   *
   * */
    public static InfoView AddMe(ViewGroup host) {
        // since there's only one activity, we can guarantee the inflated activity is the same activity with host
        Activity activity = (Activity) host.getContext();
        InfoView v = (InfoView) activity.getLayoutInflater().inflate(R.layout.info, null);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        host.addView(v, 0);
        return v;
    }

    /*
    *  remove {@link com.nevinchen.nofragment.ui.InfoView} from parent
    *
    * */
    public void removeFromParent() {
        ((ViewGroup) getParent()).removeView(InfoView.this);
    }


    private void setupBackPressed() {
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.requestFocus();
        this.setOnKeyListener(new OnKeyListener() {
            @Override public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    removeFromParent();
                }
                return true;
            }
        });
    }

    public void hideAppBar(){
        appBar.setVisibility(View.GONE);
    }


}
