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

public class Chapter7 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter);
        ListView chapterListView = findViewById(R.id.chapter_list_view);
        List<String> chapters = Arrays.asList(
            "1.Nutrition in Plants",
            "2.Nutrition in Animals",
            "3.Fibre to Fabric",
            "4.Heat",
            "5.Acids, Bases and Salts",
            "6.Physical and Chemical Changes",
            "7.Weather, Climate and Adaptations of Animals to Climate",
            "8.Winds, Storms and Cyclones",
            "9.Soil",
            "10.Respiration in Organisms",
            "11.Transportation in Animals and Plants",
            "12.Reproduction in Plants",
            "13.Motion and Time",
            "14.Electric Current and its Effects",
            "15.Light",
            "16.Water: A Precious Resource",
            "17.Forests: Our Lifeline",
            "18.Wastewater Story"
        );

        chapterListView.setAdapter(new SimpleChapterAdapter(this, chapters));
    }

    private static class SimpleChapterAdapter extends BaseAdapter {

        private final Context context;
        private final LayoutInflater inflater;
        private final List<String> chapters;

        private final Map<String, String> chapterMap = Map.ofEntries(
            Map.entry("Nutrition in Plants", "s7a"),
            Map.entry("Nutrition in Animals", "s7b"),
            Map.entry("Fibre to Fabric", "s7c"),
            Map.entry("Heat", "s7d"),
            Map.entry("Acids, Bases and Salts", "s7e"),
            Map.entry("Physical and Chemical Changes", "s7f"),
            Map.entry("Weather, Climate and Adaptations of Animals to Climate", "s7g"),
            Map.entry("Winds, Storms and Cyclones", "s7h"),
            Map.entry("Soil", "s7i"),
            Map.entry("Respiration in Organisms", "s7j"),
            Map.entry("Transportation in Animals and Plants", "s7k"),
            Map.entry("Reproduction in Plants", "s7l"),
            Map.entry("Motion and Time", "s7m"),
            Map.entry("Electric Current and its Effects", "s7n"),
            Map.entry("Light", "s7o"),
            Map.entry("Water: A Precious Resource", "s7p"),
            Map.entry("Forests: Our Lifeline", "s7q"),
            Map.entry("Wastewater Story", "s7r")
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
