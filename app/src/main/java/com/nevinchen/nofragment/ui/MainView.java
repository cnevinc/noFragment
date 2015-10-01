package com.nevinchen.nofragment.ui;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nevinchen.nofragment.MainActivity;
import com.nevinchen.nofragment.R;
import com.nevinchen.nofragment.data.User;

import org.threeten.bp.Instant;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  Main View as content view
 */
public class MainView extends LinearLayout {

    private static final String TAG = "test";
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.selected_user)
    TextView selected_user;


    public void bind(User user){
        if (user != null) {
            selected_user.setText("Selected : "+user.name + "@" + user.time );
        }
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public static MainView AddMe(MainActivity host,User user) {
        MainView v = (MainView) host.getLayoutInflater().inflate(R.layout.main, null);
        host.setContentView(v);
        v.bind(user);
        return v;
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
        User user = new User();
        user.name = "Nevin Chen";
        user.avatar = "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/1/005/024/1b0/22cebd0.jpg";
        user.time = Instant.now().toString();
        InfoView.AddMe((MainActivity) getContext(),user , false);
    }


    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // clean up resource here
    }
}
