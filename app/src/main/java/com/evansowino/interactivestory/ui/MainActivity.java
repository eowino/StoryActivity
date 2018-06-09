package com.evansowino.interactivestory.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.evansowino.interactivestory.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText mNameField;
    private Button mStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameField = findViewById(R.id.nameEditText);
        mStartBtn = findViewById(R.id.startButton);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mNameField.getText().toString();
                startStory(name);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNameField.setText("");
    }

    private void startStory(String userName) {
        Intent intent = new Intent(this, StoryActivity.class);
        String key = getResources().getString(R.string.user_name);
        intent.putExtra(key , userName);
        startActivity(intent);
    }
}
