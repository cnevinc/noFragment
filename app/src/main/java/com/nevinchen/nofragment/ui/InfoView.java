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
        this.setId((int) (Math.random() * 1000));

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
   *  Add {@link com.nevinchen.nofragment.ui.InfoView} to what ever parent you want
   *
   * */
    public static InfoView AddMe(ViewGroup host, Object data) {
        // since there's only one activity, we can guarantee the inflated activity is the same activity with host
        Activity activity = (Activity) host.getContext();
        InfoView v = (InfoView) activity.getLayoutInflater().inflate(R.layout.info, null);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        host.addView(v, 0);
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

//    @Override
//    public Parcelable onSaveInstanceState() {
//        //begin boilerplate code that allows parent classes to save time
//        Parcelable superState = super.onSaveInstanceState();
//
//        SavedState ss = new SavedState(superState);
//        ss.time = this.mTime ;
//        return ss;
//    }
//
//    @Override
//    public void onRestoreInstanceState(Parcelable state) {
//        //begin boilerplate code so parent classes can restore time
//        if(!(state instanceof SavedState)) {
//            super.onRestoreInstanceState(state);
//            return;
//        }
//
//        SavedState ss = (SavedState)state;
//        super.onRestoreInstanceState(ss.getSuperState());
//        //end
//
//        this.mTime = ss.time;
//    }


    private void bind(Object data) {
        User user = (User) data;
        Picasso.with(getContext()).load(user.avatar).placeholder(R.drawable.ic_launcher).into(avatar);
        name.setText(user.name);
        time.setText(user.time);


    }

    public void saveState(Object data) {
        MainActivity.history.add(new Pair(InfoView.class, data));
    }


//    static class SavedState extends BaseSavedState {
//        String time;
//
//        SavedState(Parcelable superState) {
//            super(superState);
//        }
//
//        private SavedState(Parcel in) {
//            super(in);
//            time = in.readString();
//        }
//
//        @Override
//        public void writeToParcel(Parcel out, int flags) {
//            super.writeToParcel(out, flags);
//            out.writeString(time);
//        }
//
//        public static final Parcelable.Creator<SavedState> CREATOR
//                = new Parcelable.Creator<SavedState>() {
//            public SavedState createFromParcel(Parcel in) {
//                return new SavedState(in);
//            }
//
//            public SavedState[] newArray(int size) {
//                return new SavedState[size];
//            }
//        };
//    }

}
