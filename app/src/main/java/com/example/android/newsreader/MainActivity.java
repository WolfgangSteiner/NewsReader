package com.example.android.newsreader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnRestTaskCompleted
{
    ArrayList<Story> mStoryList;
    StoryAdapter mStoryAdapter;
    ListView mListView;
    TextView mNoItemsView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStoryList = new ArrayList<Story>();

        mListView = (ListView) findViewById(R.id.list_view);
        mStoryAdapter = new StoryAdapter(this, mStoryList);
        mListView.setAdapter(mStoryAdapter);

        mNoItemsView = (TextView) findViewById(R.id.no_items_view);
    }

    public void onStartSearch(View aView)
    {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
        {
            Uri myUri = buildUri("science");
            new RestTask(this).execute(myUri);
        }
        else
        {
            Toast.makeText(this, R.string.network_not_reachable, Toast.LENGTH_SHORT).show();
        }
    }

    public void onRestTaskCompleted(String aResultString) {
        mStoryList.clear();
        parseJson(aResultString);
        mStoryAdapter.notifyDataSetChanged();

        if (mStoryList.isEmpty())
        {
            mNoItemsView.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        }
        else
        {
            mNoItemsView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
        }
    }

    private Uri.Builder buildUriBuilder() {
        Uri.Builder builder = new Uri.Builder();

        builder.scheme("http")
                .authority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter("show-fields", "thumbnail,trailText")
                .appendQueryParameter("api-key", "test");

        return builder;
    }

    private Uri buildUri(String aSearchString) {
        Uri.Builder builder = buildUriBuilder();
        builder.appendQueryParameter("section", aSearchString);
        return builder.build();
    }

    private void parseJson(String jsonString) {
        try
        {
            JSONObject jsonObj = new JSONObject(jsonString);
            JSONObject responseObj = jsonObj.getJSONObject("response");
            JSONArray resultArray = responseObj.getJSONArray("results");

            for (int i = 0; i < resultArray.length(); i++)
            {
                JSONObject storyInfo = resultArray.getJSONObject(i);
                String title = storyInfo.getString("webTitle");

                JSONObject fieldsObj = storyInfo.getJSONObject("fields");
                String trailText = fieldsObj.getString("trailText");

                mStoryList.add(new Story(title, trailText));
            }
        }
        catch (JSONException e)
        {
            Toast.makeText(this, R.string.error_parsing_message, Toast.LENGTH_SHORT).show();
        }
    }
}
