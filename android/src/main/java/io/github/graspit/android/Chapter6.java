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

public class Chapter6 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter);
        ListView chapterListView = findViewById(R.id.chapter_list_view);
        List<String> chapters = Arrays.asList(
            "1.Food: Where Does It Come From?",
            "2.Components of Food",
            "3.Fibre to Fabric",
            "4.Sorting Materials into Groups",
            "5.Separation of Substances",
            "6.Changes Around Us",
            "7.Getting to Know Plants",
            "8.Body Movements",
            "9.The Living Organisms and Their Surroundings",
            "10.Motion and Measurement of Distances",
            "11.Light, Shadows and Reflections",
            "12.Electricity and Circuits",
            "13.Fun with Magnets",
            "14.Water",
            "15.Air Around Us",
            "16.Garbage In, Garbage Out"
        );

        chapterListView.setAdapter(new SimpleChapterAdapter(this, chapters));
    }

    private static class SimpleChapterAdapter extends BaseAdapter {

        private final Context context;
        private final LayoutInflater inflater;
        private final List<String> chapters;

        private final Map<String, String> chapterMap = Map.ofEntries(
            Map.entry("Food: Where Does It Come From?", "s6a"),
            Map.entry("Components of Food", "s6b"),
            Map.entry("Fibre to Fabric", "s6c"),
            Map.entry("Sorting Materials into Groups", "s6d"),
            Map.entry("Separation of Substances", "s6e"),
            Map.entry("Changes Around Us", "s6f"),
            Map.entry("Getting to Know Plants", "s6g"),
            Map.entry("Body Movements", "s6h"),
            Map.entry("The Living Organisms and Their Surroundings", "s6i"),
            Map.entry("Motion and Measurement of Distances", "s6j"),
            Map.entry("Light, Shadows and Reflections", "s6k"),
            Map.entry("Electricity and Circuits", "s6l"),
            Map.entry("Fun with Magnets", "s6m"),
            Map.entry("Water", "s6n"),
            Map.entry("Air Around Us", "s6o"),
            Map.entry("Garbage In, Garbage Out", "s6p")
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
