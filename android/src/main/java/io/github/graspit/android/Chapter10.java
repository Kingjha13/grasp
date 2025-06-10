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

public class Chapter10 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter); // Reusing same layout as Chapter11

        ListView chapterListView = findViewById(R.id.chapter_list_view);
        List<String> chapters = Arrays.asList(
            "1.Chemical Reactions and Equations",
            "2.Acids, Bases and Salts",
            "3.Metals and Non-metals",
            "4.Carbon and Its Compounds",
            "5.Periodic Classification of Elements",
            "6.Life Processes",
            "7.Control and Coordination",
            "8.How do Organisms Reproduce?",
            "9.Heredity and Evolution",
            "10.Light - Reflection and Refraction",
            "11.The Human Eye and the Colourful World",
            "12.Electricity",
            "13.Magnetic Effects of Electric Current",
            "14.Sources of Energy",
            "15.Our Environment",
            "16.Management of Natural Resources"
        );

        chapterListView.setAdapter(new SimpleChapterAdapter(this, chapters));
    }

    private static class SimpleChapterAdapter extends BaseAdapter {

        private final Context context;
        private final LayoutInflater inflater;
        private final List<String> chapters;

        private final Map<String, String> chapterMap = Map.ofEntries(
            Map.entry("Chemical Reactions and Equations", "s10a"),
            Map.entry("Acids, Bases and Salts", "s10b"),
            Map.entry("Metals and Non-metals", "s10c"),
            Map.entry("Carbon and Its Compounds", "s10d"),
            Map.entry("Periodic Classification of Elements", "s10e"),
            Map.entry("Life Processes", "s10f"),
            Map.entry("Control and Coordination", "s10g"),
            Map.entry("How do Organisms Reproduce?", "s10h"),
            Map.entry("Heredity and Evolution", "s10i"),
            Map.entry("Light - Reflection and Refraction", "s10j"),
            Map.entry("The Human Eye and the Colourful World", "s10k"),
            Map.entry("Electricity", "s10l"),
            Map.entry("Magnetic Effects of Electric Current", "s10m"),
            Map.entry("Sources of Energy", "s10n"),
            Map.entry("Our Environment", "s10o"),
            Map.entry("Management of Natural Resources", "s10p")
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

