package com.example.android.newsreader;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wolfgang on 03.07.16.
 */
public class StoryAdapter extends ArrayAdapter<Story>
{
    private int mColorId;

    public StoryAdapter(Activity aContext, ArrayList<Story> aBookList)
    {
        super(aContext, 0, aBookList);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View bookItemView = convertView;

        if(bookItemView == null)
        {
            bookItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.story_list_item, parent, false);
        }

        final Story currenStory = getItem(position);

        TextView titleTextView = (TextView) bookItemView.findViewById(R.id.title_field);
        titleTextView.setText(currenStory.getTitle());

        TextView authorTextView = (TextView) bookItemView.findViewById(R.id.trail_text_field);
        authorTextView.setText(currenStory.getTrailText());

        return bookItemView;
    }
}
