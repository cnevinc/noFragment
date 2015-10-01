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
import butterknife.OnClick;

public class InfoView extends LinearLayout {

    private static final String TAG = "test";

    @Bind(R.id.app_bar)
    AppBarLayout appBar;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    User user;

    @OnClick(R.id.card)
    public void updateMain(){
        ((MainActivity)this.getContext()).updateMainView(user);
        this.removeFromParent();
    }

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
    public static InfoView AddMe(MainActivity host, Object user , boolean restore) {
        if (user instanceof  User){
            InfoView v = (InfoView) host.getLayoutInflater().inflate(R.layout.info, null);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            host.addContentView(v, p);
            v.bind((User)user);
            if (!restore){
                v.saveState(user);

            }
            return v;
        }else{
            throw new IllegalArgumentException("InfoView only allow User type data ");
        }

    }


    /*
    *  remove {@link com.nevinchen.nofragment.ui.InfoView} from parent
    *
    * */
    public void removeFromParent() {
        ((ViewGroup) getParent()).removeView(InfoView.this);
        MainActivity host =(MainActivity)getContext();
        for (int i = host.history.size() - 1; i >= 0; i--) {
            Pair<Class, Object> page = host.history.get(i);
            if (page.first == InfoView.class) {
                host.history.remove(i);
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


    private void bind(User user) {
        this.user = user ;
        Picasso.with(getContext()).load(user.avatar).placeholder(R.drawable.ic_launcher).into(avatar);
        name.setText(user.name);
        time.setText(user.time);

    }

    private void saveState(Object data) {
        MainActivity host =(MainActivity)getContext();
        host.history.add(new Pair(InfoView.class, data));
    }



}
