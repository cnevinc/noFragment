package com.nevinchen.nofragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import com.nevinchen.nofragment.data.User;
import com.nevinchen.nofragment.ui.InfoView;
import com.nevinchen.nofragment.ui.MainView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "test";

    // Used to save view history
    public ArrayList<Pair<Class, Object>> history = new ArrayList<>();
    private MainView mainView;

    public void updateMainView(User user){
        mainView.bind(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mainView = MainView.AddMe(this,null);
        setContentView(mainView);


        if (savedInstanceState != null) {
            history = (ArrayList<Pair<Class, Object>>)savedInstanceState.getSerializable("history");
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putSerializable("history", history);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


}
