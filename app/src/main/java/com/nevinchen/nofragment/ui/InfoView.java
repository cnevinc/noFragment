package com.nevinchen.nofragment.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nevinchen.nofragment.MainActivity;
import com.nevinchen.nofragment.R;
import com.nevinchen.nofragment.data.User;
import com.nevinchen.nofragment.util.Utils;
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

    @Bind(R.id.et)
    EditText et;

    User user;

    // Demo 3: How custom view talk to other custom view
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
            @Override
            public void onClick(View v) {
                removeFromParent();
            }
        });

        //  clear focus after keyboard hidden
        et.setHint("this is how you deal with EditText");
        et.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    et.clearFocus();
                }
                return false;
            }
        });

    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // clean up resource here
    }



    /*
    *  Add {@link com.nevinchen.nofragment.ui.InfoView} to your activity's content view.
    *
    *  @param host : The content view this view will be added.
    *  @param user : Any data that will be bind to this view
    *  @param isFromHistory : if this view is added from history (ex recovered from configuration change)
    *         when
    *
    * */
    public static InfoView AddMe(MainActivity host, Object user , boolean isFromHistory) {
        if (user instanceof  User){
            InfoView v = (InfoView) host.getLayoutInflater().inflate(R.layout.info, null);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            ViewGroup parent =  ((ViewGroup) host.findViewById(android.R.id.content));

            if (!isFromHistory){
                Utils.changeScense(parent);
                v.saveState(user);
            }

            host.addContentView(v, p);

            v.bind((User)user);


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
        ViewGroup parent = (ViewGroup) getParent();
        Utils.changeScense(parent);

        parent.removeView(InfoView.this);
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
