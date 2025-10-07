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
import java.util.*;

import io.github.graspit.R;

public class ArClass6to10 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter);

        String classLevel = getIntent().getStringExtra("class_name");
        ListView chapterListView = findViewById(R.id.chapter_list_view);
        List<String> chapters = getChaptersByClass(classLevel);

        ChapterAdapter adapter = new ChapterAdapter(this, chapters, classLevel);
        chapterListView.setAdapter(adapter);
    }

    private List<String> getChaptersByClass(String classLevel) {
        switch (classLevel) {
            case "6":
                return Arrays.asList(
                    "Food: Where Does It Come From?",
                    "Components of Food",
                    "Fibre to Fabric",
                    "Sorting Materials into Groups",
                    "Separation of Substances",
                    "Changes Around Us",
                    "Getting to Know Plants",
                    "Body Movements",
                    "The Living Organisms and Their Surroundings",
                    "Motion and Measurement of Distances",
                    "Light, Shadows and Reflections",
                    "Electricity and Circuits",
                    "Fun with Magnets",
                    "Water",
                    "Air Around Us",
                    "Garbage In, Garbage Out"
                );
            case "7":
                return Arrays.asList(
                    "Nutrition in Plants",
                    "Nutrition in Animals",
                    "Fibre to Fabric",
                    "Heat",
                    "Acids, Bases and Salts",
                    "Physical and Chemical Changes",
                    "Weather, Climate and Adaptations of Animals to Climate",
                    "Winds, Storms and Cyclones",
                    "Soil",
                    "Respiration in Organisms",
                    "Transportation in Animals and Plants",
                    "Reproduction in Plants",
                    "Motion and Time",
                    "Electric Current and its Effects",
                    "Light",
                    "Water: A Precious Resource",
                    "Forests: Our Lifeline",
                    "Wastewater Story"
                );
            case "8":
                return Arrays.asList(
                    "Crop Production and Management",
                    "Microorganisms: Friend and Foe",
                    "Synthetic Fibres and Plastics",
                    "Materials: Metals and Non-Metals",
                    "Coal and Petroleum",
                    "Combustion and Flame",
                    "Conservation of Plants and Animals",
                    "Cell — Structure and Functions",
                    "Reproduction in Animals",
                    "Reaching the Age of Adolescence",
                    "Force and Pressure",
                    "Friction",
                    "Sound",
                    "Chemical Effects of Electric Current",
                    "Some Natural Phenomena",
                    "Light",
                    "Stars and The Solar System",
                    "Pollution of Air and Water"
                );
            case "9":
                return Arrays.asList(
                    "Matter in Our Surroundings",
                    "Is Matter Around Us Pure",
                    "Atoms and Molecules",
                    "Structure of the Atom",
                    "The Fundamental Unit of Life",
                    "Tissues",
                    "Diversity in Living Organisms",
                    "Motion",
                    "Force and Laws of Motion",
                    "Gravitation",
                    "Work and Energy",
                    "Sound",
                    "Why Do We Fall Ill?",
                    "Natural Resources",
                    "Improvement in Food Resources"
                );
            case "10":
                return Arrays.asList(
                    "Chemical Reactions and Equations",
                    "Acids, Bases and Salts",
                    "Metals and Non-metals",
                    "Carbon and Its Compounds",
                    "Periodic Classification of Elements",
                    "Life Processes",
                    "Control and Coordination",
                    "How do Organisms Reproduce?",
                    "Heredity and Evolution",
                    "Light - Reflection and Refraction",
                    "The Human Eye and the Colourful World",
                    "Electricity",
                    "Magnetic Effects of Electric Current",
                    "Sources of Energy",
                    "Our Environment",
                    "Management of Natural Resources"
                );
        }
        return new ArrayList<>();
    }

    public class ChapterAdapter extends BaseAdapter {

        private final Context context;
        private final LayoutInflater inflater;
        private final List<String> chapters;
        private final String classLevel;

        private final Map<String, String> keywordToUnit = new HashMap<>();

        public ChapterAdapter(Context context, List<String> chapters, String classLevel) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.chapters = chapters;
            this.classLevel = classLevel;
            initializeKeywordMap();
        }

        private void initializeKeywordMap() {
            Map<String, String> map = keywordToUnit;

            // Class 6
            map.put("Food: Where Does It Come From?", "s6a");
            map.put("Components of Food", "s6b");
            map.put("Fibre to Fabric", "s6c");
            map.put("Sorting Materials into Groups", "s6d");
            map.put("Separation of Substances", "s6e");
            map.put("Changes Around Us", "s6f");
            map.put("Getting to Know Plants", "s6g");
            map.put("Body Movements", "s6h");
            map.put("The Living Organisms and Their Surroundings", "s6i");
            map.put("Motion and Measurement of Distances", "s6j");
            map.put("Light, Shadows and Reflections", "s6k");
            map.put("Electricity and Circuits", "s6l");
            map.put("Fun with Magnets", "s6m");
            map.put("Water", "s6n");
            map.put("Air Around Us", "s6o");
            map.put("Garbage In, Garbage Out", "s6p");

            // Class 7
            map.put("Nutrition in Plants", "s7a");
            map.put("Nutrition in Animals", "s7b");
            map.put("Heat", "s7d");
            map.put("Acids, Bases and Salts", "s7e");
            map.put("Physical and Chemical Changes", "s7f");
            map.put("Weather, Climate and Adaptations of Animals to Climate", "s7g");
            map.put("Winds, Storms and Cyclones", "s7h");
            map.put("Soil", "s7i");
            map.put("Respiration in Organisms", "s7j");
            map.put("Transportation in Animals and Plants", "s7k");
            map.put("Reproduction in Plants", "s7l");
            map.put("Motion and Time", "s7m");
            map.put("Electric Current and its Effects", "s7n");
            map.put("Light", "s7o");
            map.put("Water: A Precious Resource", "s7p");
            map.put("Forests: Our Lifeline", "s7q");
            map.put("Wastewater Story", "s7r");

            // Class 8
            map.put("Crop Production and Management", "s8a");
            map.put("Microorganisms: Friend and Foe", "s8b");
            map.put("Synthetic Fibres and Plastics", "s8c");
            map.put("Materials: Metals and Non-Metals", "s8d");
            map.put("Coal and Petroleum", "s8e");
            map.put("Combustion and Flame", "s8f");
            map.put("Conservation of Plants and Animals", "s8g");
            map.put("Cell — Structure and Functions", "s8h");
            map.put("Reproduction in Animals", "s8i");
            map.put("Reaching the Age of Adolescence", "s8j");
            map.put("Force and Pressure", "s8k");
            map.put("Friction", "s8l");
            map.put("Sound", "s8m");
            map.put("Chemical Effects of Electric Current", "s8n");
            map.put("Some Natural Phenomena", "s8o");
            map.put("Light", "s8p");
            map.put("Stars and The Solar System", "s8q");
            map.put("Pollution of Air and Water", "s8r");

            // Class 9
            map.put("Matter in Our Surroundings", "s9a");
            map.put("Is Matter Around Us Pure", "s9b");
            map.put("Atoms and Molecules", "s9c");
            map.put("Structure of the Atom", "s9d");
            map.put("The Fundamental Unit of Life", "s9e");
            map.put("Tissues", "s9f");
            map.put("Diversity in Living Organisms", "s9g");
            map.put("Motion", "s9h");
            map.put("Force and Laws of Motion", "s9i");
            map.put("Gravitation", "s9j");
            map.put("Work and Energy", "s9k");
            map.put("Sound", "s9l");
            map.put("Why Do We Fall Ill?", "s9m");
            map.put("Natural Resources", "s9n");
            map.put("Improvement in Food Resources", "s9o");

            // Class 10
            map.put("Chemical Reactions and Equations", "s10a");
            map.put("Acids, Bases and Salts", "s10b");
            map.put("Metals and Non-metals", "s10c");
            map.put("Carbon and Its Compounds", "s10d");
            map.put("Periodic Classification of Elements", "s10e");
            map.put("Life Processes", "s10f");
            map.put("Control and Coordination", "s10g");
            map.put("How do Organisms Reproduce?", "s10h");
            map.put("Heredity and Evolution", "s10i");
            map.put("Light - Reflection and Refraction", "s10j");
            map.put("The Human Eye and the Colourful World", "s10k");
            map.put("Electricity", "s10l");
            map.put("Magnetic Effects of Electric Current", "s10m");
            map.put("Sources of Energy", "s10n");
            map.put("Our Environment", "s10o");
            map.put("Management of Natural Resources", "s10p");
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
            if (convertView == null)
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            TextView textView = convertView.findViewById(android.R.id.text1);
            textView.setTextColor(Color.DKGRAY);
            String chapter = chapters.get(position);
            textView.setText(chapter);
            textView.setOnClickListener(v -> {
                String unit = keywordToUnit.get(chapter);
                if (unit != null)
                    load3DModel((Activity) context, unit);
            });

            return convertView;
        }

        private void load3DModel(Activity activity, String unitParam) {
            FrameLayout frameLayout = new FrameLayout(activity);
            ArModel arModel = new ArModel(activity);
            arModel.setUnitParam(unitParam);
            arModel.loadModel("Ar.html?unit=" + unitParam.toLowerCase());
            frameLayout.addView(arModel);

            ImageButton backButton = new ImageButton(activity);
            backButton.setImageResource(android.R.drawable.ic_media_previous);
            backButton.setBackgroundColor(Color.TRANSPARENT);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
            int margin = dpToPx(activity, 16);
            params.setMargins(margin, margin, 0, 0);
            backButton.setLayoutParams(params);
            backButton.setOnClickListener(v -> activity.recreate());

            frameLayout.addView(backButton);
            activity.setContentView(frameLayout);
        }

        private int dpToPx(Context context, int dp) {
            float density = context.getResources().getDisplayMetrics().density;
            return Math.round((float) dp * density);
        }
    }
}
