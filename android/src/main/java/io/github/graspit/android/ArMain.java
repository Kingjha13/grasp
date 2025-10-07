//package io.github.graspit.android;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.Gravity;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
//
//import io.github.graspit.R;
//
//public class ArMain extends Activity {
//    private WoGlb woGlb;
//
//    String[] classNames = {
//        "Tutorials","Class 6","Class 7","Class 8","Class 9","Class 10","Class 11","Class 12"
//    };
//    int[] boxBackgrounds = {
//        R.drawable.border_pink, R.drawable.bc1, R.drawable.bc2,
//        R.drawable.bc1, R.drawable.bc2, R.drawable.border_pink, R.drawable.bc2, R.drawable.bc1
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.content_main);
//
//        LinearLayout mainLayout = findViewById(R.id.main_layout);
//
//        for (int i = 0; i < classNames.length; i++) {
//            String className = classNames[i];
//            LinearLayout boxLayout = createBoxLayout();
//            boxLayout.setBackgroundResource(boxBackgrounds[i % boxBackgrounds.length]);
//
//            Button classButton = createClassButton(className);
//            classButton.setOnClickListener(view -> handleClassSelection(className));
//
//            boxLayout.addView(classButton);
//            mainLayout.addView(boxLayout);
//        }
//    }
//
//    private LinearLayout createBoxLayout() {
//        LinearLayout boxLayout = new LinearLayout(this);
//        boxLayout.setOrientation(LinearLayout.VERTICAL);
//        boxLayout.setPadding(dpToPx(48), dpToPx(48), dpToPx(48), dpToPx(48));
//
//        LinearLayout.LayoutParams boxParams = new LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.MATCH_PARENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        boxParams.setMargins(0, dpToPx(8), 0, 0);
//        boxLayout.setLayoutParams(boxParams);
//
//        return boxLayout;
//    }
//
//    private Button createClassButton(String className) {
//        Button button = new Button(this);
//        button.setText(className);
//        button.setTextSize(18);
//        button.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
//        button.setBackgroundResource(R.drawable.button_gradient);
//        button.setGravity(Gravity.CENTER);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.MATCH_PARENT,
//            dpToPx(60)
//        );
//        params.setMarginStart(dpToPx(80));
//        button.setLayoutParams(params);
//
//        return button;
//    }
//
//    private void handleClassSelection(String className) {
//        switch (className) {
//            case "Tutorials":
//                setContentView(R.layout.you);
//                YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
//                String youtubeUrl = "https://youtu.be/URUJD5NEXC8?si=YUPTXe_a9fQ-x5rz";
//                YouTubePlayerHelper.loadVideo(this, youTubePlayerView, youtubeUrl);
//
//                ImageButton fullscreenButton = findViewById(R.id.fullscreen_button);
//                fullscreenButton.setOnClickListener(v -> toggleFullScreen());
//
//                ImageButton backBtn = findViewById(R.id.back_button);
//                backBtn.setOnClickListener(v -> recreate());
//                break;
//
//            case "Class 11":
//                startActivity(new Intent(this, ArS11.class)
//                    .putExtra("class_name", className));
//                break;
//
//            case "Class 12":
//                startActivity(new Intent(this, ArS12.class)
//                    .putExtra("class_name", className));
//                break;
//
//            case "Class 6":
//            case "Class 7":
//            case "Class 8":
//            case "Class 9":
//            case "Class 10":
//                // Route all classes 6 to 10 to a single activity
//                startActivity(new Intent(this, ArSubjects.class)
//                    .putExtra("class_name", className));
//                break;
//
//            default:
//                startActivity(new Intent(this, ArSubjects.class)
//                    .putExtra("class_name", className));
//                break;
//        }
//    }
//
//
//    private void toggleFullScreen() {
//    }
//
//    private int dpToPx(int dp) {
//        float density = getResources().getDisplayMetrics().density;
//        return Math.round(dp * density);
//    }
//}




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

public class ArMain extends Activity {

    String[] classNames = {
        "Class 6","Class 7","Class 8","Class 9","Class 10","Class 11","Class 12"
    };
    int[] boxBackgrounds = {
        R.drawable.border_pink, R.drawable.bc1, R.drawable.bc2,
        R.drawable.bc1, R.drawable.bc2, R.drawable.border_pink, R.drawable.bc2, R.drawable.bc1
    };

    private boolean isFullscreen = false;

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
        int color;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = getResources().getColor(android.R.color.holo_orange_light, getTheme());
        } else {
            color = getResources().getColor(android.R.color.holo_orange_light);
        }
        button.setTextColor(color);
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

            int classNum = extractClassNumber(className);
            Intent intent;

            if (classNum >= 6 && classNum <= 10) {
                intent = new Intent(this, ArSubjects.class);
            } else if (classNum == 11) {
                intent = new Intent(this, ArS11.class);
            } else if (classNum == 12) {
                intent = new Intent(this, ArS12.class);
            } else {
                intent = new Intent(this, ArSubjects.class);
            }
            intent.putExtra(ArSubjects.EXTRA_CLASS_NUMBER, classNum);
            startActivity(intent);
    }

    private int extractClassNumber(String className) {
        try {
            return Integer.parseInt(className.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 6; // Default to 6 if no number found
        }
    }

    private String extractYoutubeId(String youtubeUrl) {
        // Extract video ID from YouTube URL, basic implementation:
        // e.g. https://youtu.be/URUJD5NEXC8?si=...
        String pattern = "youtu(?:\\.be|be\\.com)/(?:.*v(?:/|=)|(?:.*/)?)([a-zA-Z0-9-_]+)";
        java.util.regex.Pattern compiledPattern = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher matcher = compiledPattern.matcher(youtubeUrl);
        if (matcher.find()) {
            return matcher.group(1);
        }
        // fallback: return entire URL (may fail)
        return youtubeUrl;
    }

    private void toggleFullScreen() {
        isFullscreen = !isFullscreen;
        if (isFullscreen) {
            getWindow().getDecorView().setSystemUiVisibility(
                android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
                    | android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(android.view.View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
