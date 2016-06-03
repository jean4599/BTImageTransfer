package com.example.android.bluetoothchat;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.GridView;

public class ShowGridView extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_grid_view);

        //get grid view reference
        GridView grid = (GridView)findViewById(R.id.image_grid);
        grid.setAdapter(new GridViewAdapter(getApplicationContext()));
    }

    public void showInfo(String size, String uri, String time){
//        FragmentManager fm = getFragmentManager();
//        ImageInfoDialogFragment dialogFragment = new ImageInfoDialogFragment ();
//        dialogFragment.newInstace(size);
//        dialogFragment.show(fm,"f");
        FragmentTransaction ft =  getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ImageInfoDialogFragment infoDialogFragment = new ImageInfoDialogFragment();
        infoDialogFragment.newInstace(size,uri,time);
        infoDialogFragment.show(ft, "fragment_edit_name");
    }

}
