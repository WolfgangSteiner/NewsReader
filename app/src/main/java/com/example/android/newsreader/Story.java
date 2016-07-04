package com.example.android.newsreader;

/**
 * Created by wolfgang on 04.07.16.
 */
public class Story
{
    private String mTitle;
    private String mAuthor;
    private String mTrailText;

    public Story(String aTitle, String aTrailText) {
        this.mTitle = aTitle;
        this.mTrailText = aTrailText;
    }

    public String getTitle() {
        return mTitle;
    }
    public String getAuthor() {
        return mAuthor;
    }
    public String getTrailText() { return mTrailText; }
}
