//package io.github.graspit.android;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import io.github.graspit.R;
//import android.widget.BaseAdapter;
//import android.widget.FrameLayout;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.TextView;
//
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//public class ArC12 extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.chapter);
//
//        String subject = getIntent().getStringExtra("subject");
//        ListView chapterListView = findViewById(R.id.chapter_list_view);
//        List<String> chapters = new ArrayList<>();
//
//        switch (subject) {
//            case "Biology":
//                chapters.addAll(Arrays.asList(
//                    "1.Reproduction",
//                    "2.Genetics and Evolution",
//                    "3.Biology and Human Welfare",
//                    "4.Biotechnology and Its Applications",
//                    "5.Ecology and Environment"
//                ));
//                break;
//            case "Chemistry":
//                chapters.addAll(Arrays.asList(
//                    "1.Solid State",
//                    "2.Solutions",
//                    "3.Electrochemistry",
//                    "4.Chemical Kinetics",
//                    "5.Surface Chemistry",
//                    "6.General Principles and Processes of Isolation of Elements",
//                    "7.p-Block Elements",
//                    "8.d- and f-Block Elements",
//                    "9.Coordination Compounds",
//                    "10.Haloalkanes and Haloarenes",
//                    "11.Alcohols, Phenols, and Ethers",
//                    "12.Aldehydes, Ketones, and Carboxylic Acids",
//                    "13.Organic Compounds Containing Nitrogen",
//                    "14.Biomolecules",
//                    "15.Polymers",
//                    "16.Chemistry in Everyday Life"
//                ));
//                break;
//            case "Physics":
//                chapters.addAll(Arrays.asList(
//                    "1.Electrostatics",
//                    "2.Current Electricity",
//                    "3.Magnetic Effects of Current and Magnetism",
//                    "4.Electromagnetic Induction and Alternating Currents",
//                    "5.Electromagnetic Waves",
//                    "6.Optics",
//                    "7.Dual Nature of Matter and Radiation",
//                    "8.Atoms and Nuclei",
//                    "9.Electronic Devices",
//                    "10.Communication Systems"
//                ));
//                break;
//        }
//
//        ChapterAdapter adapter = new ChapterAdapter(this, chapters);
//        chapterListView.setAdapter(adapter);
//    }
//
//    public class ChapterAdapter extends BaseAdapter {
//
//        private final Context context;
//        private final LayoutInflater inflater;
//        private final List<String> baseChapters;
//        private String expandedChapter = null;
//
//        private final java.util.Map<String, String[]> subChapterMap = new java.util.HashMap<>();
//        {
//            subChapterMap.put("Reproduction", new String[]{
//                "Chapter 1: Sexual Reproduction in     Flowering Plants",
//                "Chapter 2: Human Reproduction",
//                "Chapter 3: Reproductive Health"
//            });
//            subChapterMap.put("Genetics and Evolution", new String[]{
//                "Chapter 4: Principles of Inheritance and Variation",
//                "Chapter 5: Molecular Basis of Inheritance",
//                "Chapter 6: Evolution"
//            });
//            subChapterMap.put("Biology and Human Welfare", new String[]{
//                "Chapter 7: Human Health and Disease",
//                "Chapter 8: Strategies for Enhancement in Food Production",
//                "Chapter 9: Microbes in Human Welfare"
//            });
//            subChapterMap.put("Biotechnology and Its Applications", new String[]{
//                "Chapter 10: Biotechnology: Principles and Processes",
//                "Chapter 11: Biotechnology and Its Applications"
//            });
//            subChapterMap.put("Ecology and Environment", new String[]{
//                "Chapter 12: Organisms and Populations",
//                "Chapter 13: Ecosystem",
//                "Chapter 14: Biodiversity and Conservation",
//                "Chapter 15: Environmental Issues"
//            });
//        }
//
//        public ChapterAdapter(Context context, List<String> baseChapters) {
//            this.context = context;
//            this.baseChapters = baseChapters;
//            this.inflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            if (expandedChapter == null) return baseChapters.size();
//
//            String[] subChapters = subChapterMap.get(expandedChapter);
//            return baseChapters.size() + (subChapters != null ? subChapters.length : 0);
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//            }
//            TextView textView = convertView.findViewById(android.R.id.text1);
//            textView.setTextColor(Color.DKGRAY);
//            List<Object> visibleItems = new ArrayList<>();
//            for (String chapter : baseChapters) {
//                visibleItems.add(chapter);
//                String clean = chapter.replaceAll("^\\d+\\.", "").trim();
//                if (clean.equals(expandedChapter)) {
//                    String[] subs = subChapterMap.get(clean);
//                    if (subs != null) visibleItems.addAll(Arrays.asList(subs));
//                }
//            }
//
//            Object item = visibleItems.get(position);
//            if (item instanceof String && ((String) item).startsWith("Chapter")) {
//                String sub = (String) item;
//                textView.setText("    - " + sub);
//                textView.setOnClickListener(v -> load3DModel((Activity) context, getUnitParam(sub)));
//            } else {
//                String chapter = (String) item;
//                String cleanChapter = chapter.replaceAll("^\\d+\\.", "").trim();
//                boolean isExpandable = subChapterMap.containsKey(cleanChapter);
//                String arrow = isExpandable ? (cleanChapter.equals(expandedChapter) ? "▼ " : "► ") : "";
//                textView.setText(arrow + chapter);
//
//                textView.setOnClickListener(v -> {
//                    if (isExpandable) {
//                        expandedChapter = cleanChapter.equals(expandedChapter) ? null : cleanChapter;
//                        notifyDataSetChanged();
//                    } else {
//                        load3DModel((Activity) context, getUnitParam(cleanChapter));
//                    }
//                });
//            }
//
//            return convertView;
//        }
//
//        private final Map<String, String> keywordToUnit = Map.ofEntries(
//            Map.entry("Sexual Reproduction", "unit1"),
//            Map.entry("Human Reproduction", "unit2"),
//            Map.entry("Reproductive Health", "unit3"),
//            Map.entry("Inheritance", "unit4"),
//            Map.entry("Molecular Basis", "unit5"),
//            Map.entry("Evolution", "unit6"),
//            Map.entry("Human Health", "unit7"),
//            Map.entry("Food Production", "unit8"),
//            Map.entry("Microbes", "unit9"),
//            Map.entry("Principles and Processes", "unit10"),
//            Map.entry("Biotechnology and Its Applications", "unit11"),
//            Map.entry("Organisms and Populations", "unit12"),
//            Map.entry("Ecosystem", "unit13"),
//            Map.entry("Biodiversity", "unit14"),
//            Map.entry("Environmental Issues", "unit15"),
//
//            //Yaha se chemistry ka html file h
//
//            Map.entry("Solid State", "C12unit1"),
//            Map.entry("Solutions", "C12unit2"),
//            Map.entry("Electrochemistry", "C12unit3"),
//            Map.entry("Chemical Kinetics", "C12unit4"),
//            Map.entry("Surface Chemistry", "C12unit5"),
//            Map.entry("General Principles and Processes", "C12unit6"),
//            Map.entry("p-Block Elements", "C12unit7"),
//            Map.entry("d- and f-Block Elements", "C12unit8"),
//            Map.entry("Coordination Compounds", "C12unit9"),
//            Map.entry("Haloalkanes", "C12unit10"),
//            Map.entry("Alcohols", "C12unit11"),
//            Map.entry("Aldehydes", "C12unit12"),
//            Map.entry("Nitrogen", "C12unit13"),
//            Map.entry("Biomolecules", "C12unit14"),
//            Map.entry("Polymers", "C12unit15"),
//            Map.entry("Chemistry in Everyday Life", "C12unit16"),
//
//            //yaha se physics
//
//            Map.entry("Electrostatics", "p12unit1"),
//            Map.entry("Current Electricity", "p12unit2"),
//            Map.entry("Magnetic Effects", "p12unit3"),
//            Map.entry("Electromagnetic Induction", "p12unit4"),
//            Map.entry("Electromagnetic Waves", "p12unit5"),
//            Map.entry("Optics", "p12unit6"),
//            Map.entry("Dual Nature", "p12unit7"),
//            Map.entry("Atoms", "p12unit8"),
//            Map.entry("Electronic Devices", "p12unit9"),
//            Map.entry("Communication Systems", "p12unit10")
//        );
//        private String getUnitParam(String subChapter) {
//            for (Map.Entry<String, String> entry : keywordToUnit.entrySet()) {
//                if (subChapter.equalsIgnoreCase(entry.getKey())) {
//                    return entry.getValue();
//                }
//            }
//            return null;
//        }
//
//
//        private void load3DModel(Activity activity, String unitParam) {
//            FrameLayout frameLayout = new FrameLayout(activity);
//            ArModel arModel = new ArModel(activity);
//            arModel.setUnitParam(unitParam);
////            arModel.loadModel("Ar.html");
//            arModel.loadModel("Ar.html?unit=" + unitParam.toLowerCase());
//
//
//            frameLayout.addView(arModel);
//
//            ImageButton backButton = new ImageButton(activity);
//            backButton.setImageResource(android.R.drawable.ic_media_previous);
//            backButton.setBackgroundColor(Color.TRANSPARENT);
//
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
//                FrameLayout.LayoutParams.WRAP_CONTENT,
//                FrameLayout.LayoutParams.WRAP_CONTENT);
//            int margin = dpToPx(activity, 16);
//            params.setMargins(margin, margin, 0, 0);
//            backButton.setLayoutParams(params);
//            backButton.setOnClickListener(v -> activity.recreate());
//
//            frameLayout.addView(backButton);
//            activity.setContentView(frameLayout);
//        }
//        private int dpToPx(Context context, int dp) {
//            float density = context.getResources().getDisplayMetrics().density;
//            return Math.round((float) dp * density);
//        }
//    }
//}



package io.github.graspit.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import io.github.graspit.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArC12 extends Activity {

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
                    "1.Reproduction",
                    "2.Genetics and Evolution",
                    "3.Biology and Human Welfare",
                    "4.Biotechnology and Its Applications",
                    "5.Ecology and Environment"
                ));
                break;
            case "Chemistry":
                chapters.addAll(Arrays.asList(
                    "1.Solid State",
                    "2.Solutions",
                    "3.Electrochemistry",
                    "4.Chemical Kinetics",
                    "5.Surface Chemistry",
                    "6.General Principles and Processes of Isolation of Elements",
                    "7.p-Block Elements",
                    "8.d- and f-Block Elements",
                    "9.Coordination Compounds",
                    "10.Haloalkanes and Haloarenes",
                    "11.Alcohols, Phenols, and Ethers",
                    "12.Aldehydes, Ketones, and Carboxylic Acids",
                    "13.Organic Compounds Containing Nitrogen",
                    "14.Biomolecules",
                    "15.Polymers",
                    "16.Chemistry in Everyday Life"
                ));
                break;
            case "Physics":
                chapters.addAll(Arrays.asList(
                    "1.Electrostatics",
                    "2.Current Electricity",
                    "3.Magnetic Effects of Current and Magnetism",
                    "4.Electromagnetic Induction and Alternating Currents",
                    "5.Electromagnetic Waves",
                    "6.Optics",
                    "7.Dual Nature of Matter and Radiation",
                    "8.Atoms and Nuclei",
                    "9.Electronic Devices",
                    "10.Communication Systems"
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

        private final Map<String, String[]> subChapterMap = new HashMap<String, String[]>() {{
            put("Reproduction", new String[]{
                "Chapter 1: Sexual Reproduction in Flowering Plants",
                "Chapter 2: Human Reproduction",
                "Chapter 3: Reproductive Health"
            });
            put("Genetics and Evolution", new String[]{
                "Chapter 4: Principles of Inheritance and Variation",
                "Chapter 5: Molecular Basis of Inheritance",
                "Chapter 6: Evolution"
            });
            put("Biology and Human Welfare", new String[]{
                "Chapter 7: Human Health and Disease",
                "Chapter 8: Strategies for Enhancement in Food Production",
                "Chapter 9: Microbes in Human Welfare"
            });
            put("Biotechnology and Its Applications", new String[]{
                "Chapter 10: Biotechnology: Principles and Processes",
                "Chapter 11: Biotechnology and Its Applications"
            });
            put("Ecology and Environment", new String[]{
                "Chapter 12: Organisms and Populations",
                "Chapter 13: Ecosystem",
                "Chapter 14: Biodiversity and Conservation",
                "Chapter 15: Environmental Issues"
            });
        }};


        public ChapterAdapter(Context context, List<String> baseChapters) {
            this.context = context;
            this.baseChapters = baseChapters;
            this.inflater = LayoutInflater.from(context);
        }

        private List<Object> buildVisibleItems() {
            List<Object> visibleItems = new ArrayList<>();
            for (String chapter : baseChapters) {
                visibleItems.add(chapter);
                String clean = chapter.replaceAll("^\\d+\\.", "").trim();
                if (clean.equals(expandedChapter)) {
                    String[] subs = subChapterMap.get(clean);
                    if (subs != null) visibleItems.addAll(Arrays.asList(subs));
                }
            }
            return visibleItems;
        }

        @Override
        public int getCount() {
            return buildVisibleItems().size();
        }

        @Override
        public Object getItem(int position) {
            return buildVisibleItems().get(position);
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

            Object item = getItem(position);

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
            Map.entry("Sexual Reproduction", "unit1"),
            Map.entry("Human Reproduction", "unit2"),
            Map.entry("Reproductive Health", "unit3"),
            Map.entry("Inheritance", "unit4"),
            Map.entry("Molecular Basis", "unit5"),
            Map.entry("Evolution", "unit6"),
            Map.entry("Human Health", "unit7"),
            Map.entry("Food Production", "unit8"),
            Map.entry("Microbes", "unit9"),
            Map.entry("Principles and Processes", "unit10"),
            Map.entry("Biotechnology and Its Applications", "unit11"),
            Map.entry("Organisms and Populations", "unit12"),
            Map.entry("Ecosystem", "unit13"),
            Map.entry("Biodiversity", "unit14"),
            Map.entry("Environmental Issues", "unit15"),

            // Chemistry
            Map.entry("Solid State", "C12unit1"),
            Map.entry("Solutions", "C12unit2"),
            Map.entry("Electrochemistry", "C12unit3"),
            Map.entry("Chemical Kinetics", "C12unit4"),
            Map.entry("Surface Chemistry", "C12unit5"),
            Map.entry("General Principles and Processes", "C12unit6"),
            Map.entry("p-Block Elements", "C12unit7"),
            Map.entry("d- and f-Block Elements", "C12unit8"),
            Map.entry("Coordination Compounds", "C12unit9"),
            Map.entry("Haloalkanes", "C12unit10"),
            Map.entry("Alcohols", "C12unit11"),
            Map.entry("Aldehydes", "C12unit12"),
            Map.entry("Nitrogen", "C12unit13"),
            Map.entry("Biomolecules", "C12unit14"),
            Map.entry("Polymers", "C12unit15"),
            Map.entry("Chemistry in Everyday Life", "C12unit16"),

            // Physics
            Map.entry("Electrostatics", "p12unit1"),
            Map.entry("Current Electricity", "p12unit2"),
            Map.entry("Magnetic Effects", "p12unit3"),
            Map.entry("Electromagnetic Induction", "p12unit4"),
            Map.entry("Electromagnetic Waves", "p12unit5"),
            Map.entry("Optics", "p12unit6"),
            Map.entry("Dual Nature", "p12unit7"),
            Map.entry("Atoms", "p12unit8"),
            Map.entry("Electronic Devices", "p12unit9"),
            Map.entry("Communication Systems", "p12unit10")
        );

        private String getUnitParam(String subChapter) {
            for (Map.Entry<String, String> entry : keywordToUnit.entrySet()) {
                if (subChapter.toLowerCase().contains(entry.getKey().toLowerCase())) {
                    return entry.getValue();
                }
            }
            return null;
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
