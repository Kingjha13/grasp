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

public class Chapter8 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter);
        ListView chapterListView = findViewById(R.id.chapter_list_view);
        List<String> chapters = Arrays.asList(
            "1.Crop Production and Management",
            "2.Microorganisms: Friend and Foe",
            "3.Synthetic Fibres and Plastics",
            "4.Materials: Metals and Non-Metals",
            "5.Coal and Petroleum",
            "6.Combustion and Flame",
            "7.Conservation of Plants and Animals",
            "8.Cell — Structure and Functions",
            "9.Reproduction in Animals",
            "10.Reaching the Age of Adolescence",
            "11.Force and Pressure",
            "12.Friction",
            "13.Sound",
            "14.Chemical Effects of Electric Current",
            "15.Some Natural Phenomena",
            "16.Light",
            "17.Stars and The Solar System",
            "18.Pollution of Air and Water"
        );

        chapterListView.setAdapter(new SimpleChapterAdapter(this, chapters));
    }

    private static class SimpleChapterAdapter extends BaseAdapter {

        private final Context context;
        private final LayoutInflater inflater;
        private final List<String> chapters;

        private final Map<String, String> chapterMap = Map.ofEntries(
            Map.entry("Crop Production and Management", "s8a"),
            Map.entry("Microorganisms: Friend and Foe", "s8b"),
            Map.entry("Synthetic Fibres and Plastics", "s8c"),
            Map.entry("Materials: Metals and Non-Metals", "s8d"),
            Map.entry("Coal and Petroleum", "s8e"),
            Map.entry("Combustion and Flame", "s8f"),
            Map.entry("Conservation of Plants and Animals", "s8g"),
            Map.entry("Cell — Structure and Functions", "s8h"),
            Map.entry("Reproduction in Animals", "s8i"),
            Map.entry("Reaching the Age of Adolescence", "s8j"),
            Map.entry("Force and Pressure", "s8k"),
            Map.entry("Friction", "s8l"),
            Map.entry("Sound", "s8m"),
            Map.entry("Chemical Effects of Electric Current", "s8n"),
            Map.entry("Some Natural Phenomena", "s8o"),
            Map.entry("Light", "s8p"),
            Map.entry("Stars and The Solar System", "s8q"),
            Map.entry("Pollution of Air and Water", "s8r")
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
