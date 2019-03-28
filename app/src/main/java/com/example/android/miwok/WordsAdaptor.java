package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class WordsAdaptor extends ArrayAdapter<Words> {
    private int mColorResID;
    MediaPlayer mMediaplayer;
    public WordsAdaptor(Activity context, ArrayList<Words> wordsArrayList, int color) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, wordsArrayList);
        mColorResID= color;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Words currentwords = getItem(position);

        int color = ContextCompat.getColor(getContext(),mColorResID);
        LinearLayout layout = (LinearLayout)listItemView.findViewById(R.id.linear_layout);
        layout.setBackgroundColor(color);


        TextView nameTextView = (TextView) listItemView.findViewById(R.id.mivok_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentwords.getmMivokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentwords.getmDefaultTranslation());
        ImageView iconview = (ImageView) listItemView.findViewById(R.id.iconview);
        if (currentwords.hasimage()) {

            iconview.setImageResource(currentwords.getmImageResourceId());
        } else {
            iconview.setVisibility(View.GONE);
        }
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView

        return listItemView;
    }


}