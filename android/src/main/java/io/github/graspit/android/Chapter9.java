package io.github.graspit.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.github.graspit.R;

public class Chapter9 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter); // Reuse the same layout with a ListView (id: chapter_list_view)

        ListView chapterListView = findViewById(R.id.chapter_list_view);

        // Class 9 Science chapters
        List<String> chapters = Arrays.asList(
            "1.Matter in Our Surroundings",
            "2.Is Matter Around Us Pure",
            "3.Atoms and Molecules",
            "4.Structure of the Atom",
            "5.The Fundamental Unit of Life",
            "6.Tissues",
            "7.Diversity in Living Organisms",
            "8.Motion",
            "9.Force and Laws of Motion",
            "10.Gravitation",
            "11.Work and Energy",
            "12.Sound",
            "13.Why Do We Fall Ill?",
            "14.Natural Resources",
            "15.Improvement in Food Resources"
        );

        chapterListView.setAdapter(new SimpleChapterAdapter(this, chapters));
    }

    private static class SimpleChapterAdapter extends BaseAdapter {

        private final Context context;
        private final LayoutInflater inflater;
        private final List<String> chapters;

        private final Map<String, String> chapterMap = Map.ofEntries(
            Map.entry("Matter in Our Surroundings", "s9a"),
            Map.entry("Is Matter Around Us Pure", "s9b"),
            Map.entry("Atoms and Molecules", "s9c"),
            Map.entry("Structure of the Atom", "s9d"),
            Map.entry("The Fundamental Unit of Life", "s9e"),
            Map.entry("Tissues", "s9f"),
            Map.entry("Diversity in Living Organisms", "s9g"),
            Map.entry("Motion", "s9h"),
            Map.entry("Force and Laws of Motion", "s9i"),
            Map.entry("Gravitation", "s9j"),
            Map.entry("Work and Energy", "s9k"),
            Map.entry("Sound", "s9l"),
            Map.entry("Why Do We Fall Ill?", "s9m"),
            Map.entry("Natural Resources", "s9n"),
            Map.entry("Improvement in Food Resources", "s9o")
        );

        public SimpleChapterAdapter(Context context, List<String> chapters) {
            this.context = context;
            this.chapters = chapters;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return chapters.size();
        }

        @Override
        public Object getItem(int position) {
            return chapters.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            TextView textView = convertView.findViewById(android.R.id.text1);
            String chapter = chapters.get(position);
            textView.setText(chapter);
            textView.setTextColor(Color.DKGRAY);

            textView.setOnClickListener(v -> {
                String clean = chapter.replaceAll("^\\d+\\.", "").trim();
                String unit = chapterMap.get(clean);
                if (unit != null) {
                    load3DModel((Activity) context, unit);
                }
            });

            return convertView;
        }

        private void load3DModel(Activity activity, String unitParam) {
            FrameLayout frameLayout = new FrameLayout(activity);
            WoGlb woGlb = new WoGlb(activity);
            frameLayout.addView(woGlb);
            woGlb.loadModel("All.html?" + unitParam);

            ImageButton backButton = new ImageButton(activity);
            backButton.setImageResource(android.R.drawable.ic_media_previous);
            backButton.setBackgroundColor(Color.TRANSPARENT);

            FrameLayout.LayoutParams buttonParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            );
            buttonParams.setMargins(dpToPx(activity, 16), dpToPx(activity, 16), 0, 0);
            backButton.setLayoutParams(buttonParams);
            backButton.setOnClickListener(v -> activity.recreate());

            frameLayout.addView(backButton);
            activity.setContentView(frameLayout);
        }

        private int dpToPx(Context context, int dp) {
            float density = context.getResources().getDisplayMetrics().density;
            return Math.round(dp * density);
        }
    }
}
