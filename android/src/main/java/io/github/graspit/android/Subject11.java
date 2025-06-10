package io.github.graspit.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import io.github.graspit.R;


public class Subject11 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject);

        Button biologyButton = findViewById(R.id.biology_button);
        Button chemistryButton = findViewById(R.id.chemistry_button);
        Button physicsButton = findViewById(R.id.physics_button);

        biologyButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Chapter11.class);
            intent.putExtra("subject", "Biology");
            startActivity(intent);
        });

        chemistryButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Chapter11.class);
            intent.putExtra("subject", "Chemistry");
            startActivity(intent);
        });

        physicsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Chapter11.class);
            intent.putExtra("subject", "Physics");
            startActivity(intent);
        });
    }
}
