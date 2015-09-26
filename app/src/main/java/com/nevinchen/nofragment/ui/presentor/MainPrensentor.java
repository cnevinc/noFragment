package com.nevinchen.nofragment.ui.presentor;

import android.app.Activity;
import android.os.Bundle;

import com.nevinchen.nofragment.MainActivity;
import com.nevinchen.nofragment.R;
import com.nevinchen.nofragment.ui.InfoView;

/**
 * Created by nevin on 9/25/15.
 */
public class MainPrensentor {

    MainActivity activity;

    State state;
    Object data;

    public MainPrensentor(MainActivity act ){
        activity = act ;
    }



    public enum State{
        Main, Info
    }

//    public void display(State state){
//        this.state = state;
//        switch (state){
//            case Main:
//                activity.setContentView(R.layout.main);
//                break;
//            case Info:
//                InfoView.AddMe(activity);
//
//        }
//    }
//
//    public void saveState() {
//        switch (state){
//            case Main:
//
//                break;
//            case Info:
//                InfoView.AddMe(activity);
//
//
//        }
//    }

    public void onSaveInstanceState(Bundle savedInstanceState){

    }
}
