package io.github.graspit.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import java.util.HashMap;

import io.github.graspit.R;

public class ChapterActivity extends Activity {

    public static final String EXTRA_CLASS_NUMBER = "class_number";

    public static void start(Context context, int classNumber) {
        Intent intent = new Intent(context, ChapterActivity.class);
        intent.putExtra(EXTRA_CLASS_NUMBER, classNumber);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter);

        int classNumber = getIntent().getIntExtra(EXTRA_CLASS_NUMBER, 6);

        ListView chapterListView = findViewById(R.id.chapter_list_view);
        List<String> chapters = getChaptersForClass(classNumber);
        Map<String, String> chapterMap = getChapterMapForClass(classNumber);

        chapterListView.setAdapter(new SimpleChapterAdapter(this, chapters, chapterMap));
    }


    private List<String> getChaptersForClass(int classNumber) {
        switch (classNumber) {
            case 6:
                return Arrays.asList(
                    "1.Food: Where Does It Come From?",
                    "2.Components of Food",
                    "3.Fibre to Fabric",
                    "4.Sorting Materials Into Groups",
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
            case 7:
                return Arrays.asList(
                    "1.Nutrition in Plants",
                    "2.Nutrition in Animals",
                    "3.Fibre to Fabric",
                    "4.Heat",
                    "5.Acids, Bases and Salts",
                    "6.Physical and Chemical Changes",
                    "7.Weather, Climate and Adaptations of Animals",
                    "8.Winds, Storms and Cyclones",
                    "9.Soil",
                    "10.Respiration in Organisms",
                    "11.Transportation in Animals and Plants",
                    "12.Reproduction in Plants",
                    "13.Motion and Time",
                    "14.Electric Current and Its Effects",
                    "15.Light",
                    "16.Water: A Precious Resource",
                    "17.Forests: Our Lifeline",
                    "18.Wastewater Story"
                );
            case 8:
                return Arrays.asList(
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
            case 9:
                return Arrays.asList(
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
            case 10:
                return Arrays.asList(
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
            default:
                return Arrays.asList("No chapters available");
        }
    }

    private Map<String, String> getChapterMapForClass(int classNumber) {
        Map<String, String> map = new HashMap<>();
        switch (classNumber) {
            case 6:
                map.put("Food: Where Does It Come From?", "s6a");
                map.put("Components of Food", "s6b");
                break;
            case 7:
                map.put("Nutrition in Plants", "s7a");
                map.put("Nutrition in Animals", "s7b");
                break;
            case 8:
                map.put("Crop Production and Management", "s8a");
                map.put("Microorganisms: Friend and Foe", "s8b");
                break;
            case 9:
                map.put("Matter in Our Surroundings", "s9a");
                map.put("Is Matter Around Us Pure", "s9b");
                break;
            case 10:
                map.put("Chemical Reactions and Equations", "s10a");
                map.put("Acids, Bases and Salts", "s10b");
                break;
        }
        return map;
    }

    private static class SimpleChapterAdapter extends BaseAdapter {
        private final Context context;
        private final LayoutInflater inflater;
        private final List<String> chapters;
        private final Map<String, String> chapterMap;

        public SimpleChapterAdapter(Context context, List<String> chapters, Map<String, String> chapterMap) {
            this.context = context;
            this.chapters = chapters;
            this.chapterMap = chapterMap;
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
