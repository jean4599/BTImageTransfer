package com.example.android.bluetoothchat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Shun-Huai.Yao on 6/2/2016.
 */
public class ImageInfoDialogFragment extends DialogFragment {

    private ImageView imageView;
    private TextView imageSize;
    private TextView imageUri;
    private TextView imageLastTime;


    public ImageInfoDialogFragment(){

    }

    public void newInstace(String size, String uri,String time) {

        imageView = (ImageView)getView().findViewById(R.id.selectedimage);
        imageView.setImageURI(Uri.parse(uri));

        imageSize = (TextView)getView().findViewById(R.id.imageSize);
        imageSize.setText("Size: " + size);

        imageUri = (TextView)getView().findViewById(R.id.imageUri);
        imageUri.setText("URI: "+ uri);

        imageLastTime = (TextView)getView().findViewById(R.id.imageTime);
        imageLastTime.setText("Last Time: "+ time);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //System.out.println("tag = " + getTag()); // tag which is from acitivity which started this fragment

        View view = inflater.inflate(R.layout.imageinfo_fragment, container,false);
        getDialog().setTitle("Info");

        return view;
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        int title = getArguments().getInt("Info");
//
//        return new AlertDialog.Builder(getActivity())
//                .setIcon(R.drawable.alert_dialog_icon)
//                .setTitle(title)
//                .setPositiveButton(R.string.alert_dialog_ok,
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                ((FragmentAlertDialog) getActivity()).doPositiveClick();
//                            }
//                        }
//                )
//                .create();
//    }
}