package com.nevinchen.nofragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import com.nevinchen.nofragment.ui.InfoView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "test";

    // ugly global object to save view history
    public static ArrayList<Pair<Class, Object>> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        if (savedInstanceState != null) {

            ArrayList<Pair<Class, Object>> local = new ArrayList<Pair<Class, Object>>();
            local.addAll(history);

            for (int i = 0; i < local.size(); i++) {
                Pair<Class, Object> node = local.get(i);
                if (node.first == InfoView.class) {
                    InfoView info = InfoView.AddMe(this,node.second);
                    //  Log.e(TAG, "bind " + node.first + "----" + node.second);
                }
            }
            local.clear();
        }
    }


}
