package io.github.graspit.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.ViewGroup;

public class SubjectActivity extends Activity {

    public static final String EXTRA_CLASS_NUMBER = "class_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int classNumber = getIntent().getIntExtra(EXTRA_CLASS_NUMBER, 6); // Default to class 6

        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);

        Button scienceButton = new Button(this);
        scienceButton.setText("Science Class " + classNumber);
        scienceButton.setTextColor(Color.WHITE);
        scienceButton.setBackgroundColor(Color.BLUE);
        scienceButton.setPadding(30, 30, 30, 30);
        layout.addView(scienceButton);

        setContentView(layout);

        scienceButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChapterActivity.class);
            intent.putExtra(ChapterActivity.EXTRA_CLASS_NUMBER, classNumber);
            intent.putExtra("subject", "Science");
            startActivity(intent);
        });
    }
}
