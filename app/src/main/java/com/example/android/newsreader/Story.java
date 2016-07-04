package com.example.android.newsreader;

/**
 * Created by wolfgang on 04.07.16.
 */
public class Story
{
    private String mTitle;
    private String mAuthor;
    private String mTrailText;
    private String mUrl;

    public Story(String aTitle, String aTrailText, String aUrl) {
        this.mTitle = aTitle;
        this.mTrailText = aTrailText;
        this.mUrl = aUrl;
    }

    public String getTitle() {
        return mTitle;
    }
    public String getAuthor() {
        return mAuthor;
    }
    public String getTrailText() { return mTrailText; }
    public String getUrl() { return mUrl; }
}
