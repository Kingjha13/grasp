package io.github.graspit.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.graspit.R;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ArC11 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter);

        String subject = getIntent().getStringExtra("subject");
        ListView chapterListView = findViewById(R.id.chapter_list_view);
        List<String> chapters = new ArrayList<>();

        switch (subject) {
            case "Biology":
                chapters.addAll(Arrays.asList(
                    "1.Diversity of Living Organisms",
                    "2.Structural Organisation in Animals and Plants",
                    "3.Cell Structure and Function",
                    "4.Plant Physiology",
                    "5.Human Physiology"
                ));
                break;
            case "Chemistry":
                chapters.addAll(Arrays.asList(
                    "1.Some Basic Concepts of Chemistry",
                    "2.Structure of Atom",
                    "3.Classification of Elements and Periodicity in Properties",
                    "4.Chemical Bonding and Molecular Structure",
                    "5.State Of Matter",
                    "6.Thermodynamics",
                    "7.Equilibrium",
                    "8.Redox Reactions",
                    "9.Hydrogen",
                    "10.The s-Block Element",
                    "11.Some p-Block Elements",
                    "12.Organic Chemistry – Some Basic Principles",
                    "13.Hydrocarbons",
                    "14.Environmental Chemistry"
                ));
                break;
            case "Physics":
                chapters.addAll(Arrays.asList(
                    "1.Physical World",
                    "2.Units and Measurements",
                    "3.Motion in a Straight Line",
                    "4.Motion in a Plane",
                    "5.Laws of Motion",
                    "6.Work, Energy and Power",
                    "7.System of Particles and Rotational Motion",
                    "8.Gravitation",
                    "9.Mechanical Properties of Solids",
                    "10.Mechanical Properties of Fluids",
                    "11.Thermal Properties of Matter",
                    "12.Thermodynamics",
                    "13.Kinetic Theory",
                    "14.Oscillations",
                    "15.Waves"
                ));
                break;
        }

        ChapterAdapter adapter = new ChapterAdapter(this, chapters);
        chapterListView.setAdapter(adapter);
    }

    public class ChapterAdapter extends BaseAdapter {

        private final Context context;
        private final LayoutInflater inflater;
        private final List<String> baseChapters;
        private String expandedChapter = null;

        private final java.util.Map<String, String[]> subChapterMap = new java.util.HashMap<>();
        {
            subChapterMap.put("Diversity of Living Organisms", new String[]{
                "Chapter 1: The Living World",
                "Chapter 2: Biological Classification",
                "Chapter 3: Plant Kingdom",
                "Chapter 4: Animal Kingdom"
            });
            subChapterMap.put("Structural Organisation in Animals and Plants", new String[]{
                "Chapter 5: Morphology of Flowering Plants",
                "Chapter 6: Anatomy of Flowering Plants",
                "Chapter 7: Structural Organisation in Animals"
            });
            subChapterMap.put("Cell Structure and Function", new String[]{
                "Chapter 8: Cell - The Unit of Life",
                "Chapter 9: Biomolecules",
                "Chapter 10: Cell Cycle and Cell Division"
            });
            subChapterMap.put("Plant Physiology", new String[]{
                "Chapter 11: Transport in Plants",
                "Chapter 12: Mineral Nutrition",
                "Chapter 13: Photosynthesis",
                "Chapter 14: Respiration in Plants",
                "Chapter 15: Plant Growth and Development"
            });
            subChapterMap.put("Human Physiology", new String[]{
                "Chapter 16: Digestion and Absorption",
                "Chapter 17: Breathing and Exchange of Gases",
                "Chapter 18: Body Fluids and Circulation",
                "Chapter 19: Excretory Products and Their Elimination",
                "Chapter 20: Locomotion and Movement",
                "Chapter 21: Neural Control and Coordination",
                "Chapter 22: Chemical Coordination and Integration"
            });
        }

        public ChapterAdapter(Context context, List<String> baseChapters) {
            this.context = context;
            this.baseChapters = baseChapters;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if (expandedChapter == null) return baseChapters.size();

            String[] subChapters = subChapterMap.get(expandedChapter);
            return baseChapters.size() + (subChapters != null ? subChapters.length : 0);
        }

        @Override
        public Object getItem(int position) {
            return null;
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
            textView.setTextColor(Color.DKGRAY);
            List<Object> visibleItems = new ArrayList<>();
            for (String chapter : baseChapters) {
                visibleItems.add(chapter);
                String clean = chapter.replaceAll("^\\d+\\.", "").trim();
                if (clean.equals(expandedChapter)) {
                    String[] subs = subChapterMap.get(clean);
                    if (subs != null) visibleItems.addAll(Arrays.asList(subs));
                }
            }

            Object item = visibleItems.get(position);
            if (item instanceof String && ((String) item).startsWith("Chapter")) {
                String sub = (String) item;
                textView.setText("    - " + sub);
                textView.setOnClickListener(v -> load3DModel((Activity) context, getUnitParam(sub)));
            } else {
                String chapter = (String) item;
                String cleanChapter = chapter.replaceAll("^\\d+\\.", "").trim();
                boolean isExpandable = subChapterMap.containsKey(cleanChapter);
                String arrow = isExpandable ? (cleanChapter.equals(expandedChapter) ? "▼ " : "► ") : "";
                textView.setText(arrow + chapter);

                textView.setOnClickListener(v -> {
                    if (isExpandable) {
                        expandedChapter = cleanChapter.equals(expandedChapter) ? null : cleanChapter;
                        notifyDataSetChanged();
                    } else {
                        load3DModel((Activity) context, getUnitParam(cleanChapter));
                    }
                });
            }

            return convertView;
        }

        private final Map<String, String> keywordToUnit = Map.ofEntries(
            // Biology
            Map.entry("Chapter 1: The Living World", "Bc11a"),
            Map.entry("Chapter 2: Biological Classification", "Bc11b"),
            Map.entry("Chapter 3: Plant Kingdom", "Bc11c"),
            Map.entry("Chapter 4: Animal Kingdom", "Bc11d"),
            Map.entry("Chapter 5: Morphology of Flowering Plants", "Bc11e"),
            Map.entry("Chapter 6: Anatomy of Flowering Plants", "Bc11f"),
            Map.entry("Chapter 7: Structural Organisation in Animals", "Bc11g"),
            Map.entry("Chapter 8: Cell - The Unit of Life", "Bc11h"),
            Map.entry("Chapter 9: Biomolecules", "Bc11i"),
            Map.entry("Chapter 10: Cell Cycle and Cell Division", "Bc11j"),
            Map.entry("Chapter 11: Transport in Plants", "Bc11k"),
            Map.entry("Chapter 12: Mineral Nutrition", "Bc11l"),
            Map.entry("Chapter 13: Photosynthesis", "Bc11m"),
            Map.entry("Chapter 14: Respiration in Plants", "Bc11n"),
            Map.entry("Chapter 15: Plant Growth and Development", "Bc11o"),
            Map.entry("Chapter 16: Digestion and Absorption", "Bc16"),
            Map.entry("Chapter 17: Breathing and Exchange of Gases", "Bc17"),
            Map.entry("Chapter 18: Body Fluids and Circulation", "Bc18"),
            Map.entry("Chapter 19: Excretory Products and Their Elimination", "Bc19"),
            Map.entry("Chapter 20: Locomotion and Movement", "Bc20"),
            Map.entry("Chapter 21: Neural Control and Coordination", "Bc21"),
            Map.entry("Chapter 22: Chemical Coordination and Integration", "Bc22"),

            // Chemistry
            Map.entry("Some Basic Concepts of Chemistry", "c11a"),
            Map.entry("Structure of Atom", "c11b"),
            Map.entry("Classification of Elements and Periodicity in Properties", "c11c"),
            Map.entry("Chemical Bonding and Molecular Structure", "c11d"),
            Map.entry("State Of Matter", "c11e"),
            Map.entry("Thermodynamics", "c11f"),
            Map.entry("Equilibrium", "c11g"),
            Map.entry("Redox Reactions", "c11n"),
            Map.entry("Hydrogen", "c11h"),
            Map.entry("The s-Block Element", "c11i"),
            Map.entry("Some p-Block Elements", "c11j"),
            Map.entry("Organic Chemistry – Some Basic Principles", "c11k"),
            Map.entry("Hydrocarbons", "c11l"),
            Map.entry("Environmental Chemistry", "c11m"),

            // Physics
            Map.entry("Physical World", "p11a"),
            Map.entry("Units and Measurements", "p11v"),
            Map.entry("Motion in a Straight Line", "p11c"),
            Map.entry("Motion in a Plane", "p11v"),
            Map.entry("Laws of Motion", "p11e"),
            Map.entry("Work, Energy and Power", "p11f"),
            Map.entry("System of Particles and Rotational Motion", "p11g"),
            Map.entry("Gravitation", "p11h"),
            Map.entry("Mechanical Properties of Solids", "p11i"),
            Map.entry("Mechanical Properties of Fluids", "p11j"),
            Map.entry("Thermal Properties of Matter", "p11k"),
            Map.entry("Chapter 12: Thermodynamics", "p11l"),
            Map.entry("Kinetic Theory", "p11m"),
            Map.entry("Oscillations", "p11n"),
            Map.entry("Waves", "p11o")
        );
        private String getUnitParam(String subChapter) {
            for (Map.Entry<String, String> entry : keywordToUnit.entrySet()) {
                if (subChapter.equalsIgnoreCase(entry.getKey())) {
                    return entry.getValue();
                }
            }
            return null;
        }


        private void load3DModel(Activity activity, String unitParam) {
            FrameLayout frameLayout = new FrameLayout(activity);
            ArModel arModel = new ArModel(activity);
            arModel.setUnitParam(unitParam);
//            arModel.loadModel("Ar.html");
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
