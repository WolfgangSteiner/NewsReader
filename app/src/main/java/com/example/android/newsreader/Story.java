package com.example.android.newsreader;

/**
 * Created by wolfgang on 04.07.16.
 */
public class Story
{
    private String mTitle;
    private String mAuthor;

    public Story(String aTitle, String aAuthor) {
        this.mTitle = aTitle;
        this.mAuthor = aAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
