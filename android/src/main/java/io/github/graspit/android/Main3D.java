package io.github.graspit.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import io.github.graspit.R;

public class Main3D extends Activity {

    private WoGlb woGlb;

    String[] classNames = {
        "Tutorials", "Class 6", "Class 7", "Class 8", "Class 9", "Class 10", "Class 11", "Class 12"
    };

    int[] boxBackgrounds = {
        R.drawable.border_pink, R.drawable.bc1, R.drawable.bc2,
        R.drawable.bc1, R.drawable.bc2, R.drawable.border_pink, R.drawable.bc2, R.drawable.bc1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        LinearLayout mainLayout = findViewById(R.id.main_layout);

        for (int i = 0; i < classNames.length; i++) {
            String className = classNames[i];
            LinearLayout boxLayout = createBoxLayout();
            boxLayout.setBackgroundResource(boxBackgrounds[i % boxBackgrounds.length]);

            Button classButton = createClassButton(className);
            classButton.setOnClickListener(view -> handleClassSelection(className));

            boxLayout.addView(classButton);
            mainLayout.addView(boxLayout);
        }
    }

    private LinearLayout createBoxLayout() {
        LinearLayout boxLayout = new LinearLayout(this);
        boxLayout.setOrientation(LinearLayout.VERTICAL);
        boxLayout.setPadding(dpToPx(48), dpToPx(48), dpToPx(48), dpToPx(48));

        LinearLayout.LayoutParams boxParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        boxParams.setMargins(0, dpToPx(8), 0, 0);
        boxLayout.setLayoutParams(boxParams);

        return boxLayout;
    }

    private Button createClassButton(String className) {
        Button button = new Button(this);
        button.setText(className);
        button.setTextSize(18);
        button.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
        button.setBackgroundResource(R.drawable.button_gradient);
        button.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dpToPx(60)
        );
        params.setMarginStart(dpToPx(80));
        button.setLayoutParams(params);

        return button;
    }

    private void handleClassSelection(String className) {
        if ("Tutorials".equals(className)) {
            setContentView(R.layout.you);
            YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
            String youtubeUrl = "https://youtu.be/URUJD5NEXC8?si=YUPTXe_a9fQ-x5rz";
            YouTubePlayerHelper.loadVideo(this, youTubePlayerView, youtubeUrl);

            ImageButton fullscreenButton = findViewById(R.id.fullscreen_button);
            fullscreenButton.setOnClickListener(v -> toggleFullScreen());

            ImageButton backBtn = findViewById(R.id.back_button);
            backBtn.setOnClickListener(v -> recreate());
            return;
        }

        if (className.startsWith("Class")) {
            int classNumber = Integer.parseInt(className.replaceAll("[^0-9]", ""));

            switch (classNumber) {
                case 11:
                    startActivity(new Intent(this, Subject11.class).putExtra("class_name", className));
                    break;
                case 12:
                    startActivity(new Intent(this, Subject12.class).putExtra("class_name", className));
                    break;
                default:
                    Intent intent = new Intent(this, SubjectActivity.class);
                    intent.putExtra(SubjectActivity.EXTRA_CLASS_NUMBER, classNumber);
                    startActivity(intent);
                    break;
            }
        }
    }

    private void toggleFullScreen() {
        // TODO: Implement full screen logic if needed
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
