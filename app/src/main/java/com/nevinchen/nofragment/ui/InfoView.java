package com.nevinchen.nofragment.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nevinchen.nofragment.MainActivity;
import com.nevinchen.nofragment.R;
import com.nevinchen.nofragment.data.User;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfoView extends LinearLayout {

    private static final String TAG = "test";

    @Bind(R.id.app_bar)
    AppBarLayout appBar;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.avatar)
    ImageView avatar;

    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.time)
    TextView time;


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

    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // clean up resource here
    }


    /*
    *  Add {@link com.nevinchen.nofragment.ui.InfoView} to your activity's content view
    *
    * */
    public static InfoView AddMe(MainActivity host, Object data) {
        InfoView v = (InfoView) host.getLayoutInflater().inflate(R.layout.info, null);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        host.addContentView(v, p);
        v.bind(data);
        return v;
    }


    /*
    *  remove {@link com.nevinchen.nofragment.ui.InfoView} from parent
    *
    * */
    public void removeFromParent() {
        ((ViewGroup) getParent()).removeView(InfoView.this);
        for (int i = MainActivity.history.size() - 1; i >= 0; i--) {
            Pair<Class, Object> page = MainActivity.history.get(i);
            if (page.first == InfoView.class) {
                MainActivity.history.remove(i);
                break;
            }
        }
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

    public void hideAppBar() {
        appBar.setVisibility(View.GONE);
    }


    private void bind(Object data) {
        User user = (User) data;
        Picasso.with(getContext()).load(user.avatar).placeholder(R.drawable.ic_launcher).into(avatar);
        name.setText(user.name);
        time.setText(user.time);


    }

    public void saveState(Object data) {
        MainActivity.history.add(new Pair(InfoView.class, data));
    }



}
