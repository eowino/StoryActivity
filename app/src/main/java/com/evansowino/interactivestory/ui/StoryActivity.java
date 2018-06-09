package com.evansowino.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.evansowino.interactivestory.R;
import com.evansowino.interactivestory.model.Page;
import com.evansowino.interactivestory.model.Story;

public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private Story mStory;
    private ImageView mStoryImageView;
    private TextView mStoryTextView;
    private Button mChoice1Button;
    private Button mChoice2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        mStoryImageView = findViewById(R.id.storyImageView);
        mStoryTextView = findViewById(R.id.storyTextView);
        mChoice1Button = findViewById(R.id.choice1Btn);
        mChoice2Button = findViewById(R.id.choice2Btn);

        Intent intent = getIntent();
        String name = intent.getStringExtra(getString(R.string.user_name));
        if (name == null || name.isEmpty()) {
            name = "Friend";
        }
        Log.d(TAG, name);

        mStory = new Story();
        loadPage(0);
    }

    private void loadPage(int pageNumber) {
        Page page = mStory.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        mStoryImageView.setImageDrawable(image);
    }
}
