package com.evansowino.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.evansowino.interactivestory.R;
import com.evansowino.interactivestory.model.Page;
import com.evansowino.interactivestory.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private String name;
    private Stack<Integer> mPageStack = new Stack<Integer>();
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
        name = intent.getStringExtra(getString(R.string.user_name));
        if (name == null || name.isEmpty()) {
            name = "Friend";
        }
        Log.d(TAG, name);

        mStory = new Story();
        loadPage(0);
    }

    @Override
    public void onBackPressed() {
        mPageStack.pop();
        if (mPageStack.isEmpty()) {
            super.onBackPressed();
        } else {
            // in a further page in the story
            loadPage(mPageStack.pop());
        }
    }

    private void loadPage(int pageNumber) {
        mPageStack.push(pageNumber);

        final Page page = mStory.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        mStoryImageView.setImageDrawable(image);

        if (page.isFinalPage()) {
            mChoice1Button.setVisibility(View.INVISIBLE);
            mChoice2Button.setText(R.string.play_again_button_text);
            resetButtons();

        } else {
            loadButtons(page);
        }

        String pageText = getString(page.getTextId());
        // Will not add name if placeholder not included
        pageText = String.format(pageText, name);
        mStoryTextView.setText(pageText);



    }

    private void resetButtons() {
        mChoice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadButtons(final Page page) {
        mChoice1Button.setText(page.getChoice1().getTextId());
        mChoice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });

        mChoice2Button.setText(page.getChoice2().getTextId());
        mChoice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }
}
