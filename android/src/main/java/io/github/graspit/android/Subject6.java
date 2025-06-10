package io.github.graspit.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.ViewGroup;

public class Subject6 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        Button scienceButton = new Button(this);
        scienceButton.setText("Science");
        scienceButton.setTextColor(Color.YELLOW);
        scienceButton.setBackgroundColor(Color.MAGENTA);
        scienceButton.setGravity(Gravity.CENTER);
        layout.addView(scienceButton);
        layout.setGravity(Gravity.CENTER);
        setContentView(layout);

        scienceButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Chapter6.class);
            intent.putExtra("subject", "Science");
            startActivity(intent);
        });
    }
}

