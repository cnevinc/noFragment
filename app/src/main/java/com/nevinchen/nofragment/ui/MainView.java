package com.nevinchen.nofragment.ui;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nevinchen.nofragment.MainActivity;
import com.nevinchen.nofragment.R;
import com.nevinchen.nofragment.data.User;

import org.threeten.bp.Instant;

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
        this.setId((int) (Math.random() * 1000));
        Log.e("nevin", "mainview2 construct:" + this.getId());

    }

    public MainView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setId((int) (Math.random() * 1000));
        Log.e("nevin", "mainview3 construct:" + this.getId());
    }

    public static MainView AddMe(MainActivity host) {
        MainView v = (MainView) host.getLayoutInflater().inflate(R.layout.main, null);
//        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        host.setContentView(v);
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
        user.name = "nevin chen";
        user.avatar = "https://lh4.googleusercontent.com/HeuT6rAOKGSMMgwYH6LkTaoF0rH-sL7APoBc_lug5W7wGwwxWsqMhuNVUvZyhW8p--ocoxUeJEwBncE=w1290-h544";
        user.time = Instant.now().toString();
        InfoView v = InfoView.AddMe((MainActivity) getContext(),user);
        v.saveState(user);
    }

    @OnClick(R.id.show_embed)
    public void showEmbed() {
        User user = new User();
        user.name = "nevin chen";
        user.avatar = "https://lh4.googleusercontent.com/HeuT6rAOKGSMMgwYH6LkTaoF0rH-sL7APoBc_lug5W7wGwwxWsqMhuNVUvZyhW8p--ocoxUeJEwBncE=w1290-h544";
        user.time = Instant.now().toString();

        // re-use our custom view! Can you do that in fragment? no!
        InfoView v = InfoView.AddMe(embed,user);
        Log.d(TAG, "showEmbed " + v.getId());
        v.hideAppBar();
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // clean up resource here
    }
}
