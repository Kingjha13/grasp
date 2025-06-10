package io.github.graspit.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chapter extends Activity {

    private List<String> chapters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String subject = getIntent().getStringExtra("subject");

        switch (subject) {
            case "Biology":
                chapters.addAll(Arrays.asList(
                    "Getting to Know Plants",
                    "Body Movements",
                    "The Living Organisms and Their Surroundings",
                    "Garbage In, Garbage Out"
                ));
                break;
            case "Chemistry":
                chapters.addAll(Arrays.asList(
                    "Food: Where Does It Come From?",
                    "Components of Food",
                    "Fibre to Fabric",
                    "Sorting Materials into Groups",
                    "Separation of Substances",
                    "Changes Around Us",
                    "Water",
                    "Air Around Us"
                ));
                break;
            case "Physics":
                chapters.addAll(Arrays.asList(
                    "Motion and Measurement of Distances",
                    "Light, Shadows and Reflection",
                    "Electricity and Circuits",
                    "Fun with Magnets",
                    "Rain, Thunder and Lightning"
                ));
                break;
        }

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(Color.WHITE);
        rootLayout.setPadding(16, 16, 16, 16);

        TextView titleView = new TextView(this);
        titleView.setText("Chapters");
        titleView.setTextSize(24f);
        titleView.setTextColor(Color.BLACK);
        titleView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
        titleParams.setMargins(0, 20, 0, 20);
        titleView.setLayoutParams(titleParams);

        ListView chapterListView = new ListView(this);
        chapterListView.setDividerHeight(1);
        chapterListView.setDivider(getResources().getDrawable(android.R.color.darker_gray, null));
        LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            0, 1f);
        chapterListView.setLayoutParams(listParams);

        ChapterAdapter adapter = new ChapterAdapter(this, chapters);
        chapterListView.setAdapter(adapter);

        rootLayout.addView(titleView);
        rootLayout.addView(chapterListView);

        setContentView(rootLayout);
    }

    private static class ChapterAdapter extends BaseAdapter {
        private Context context;
        private List<String> chapters;

        public ChapterAdapter(Context context, List<String> chapters) {
            this.context = context;
            this.chapters = chapters;
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
            TextView chapterName;

            if (convertView == null) {
                chapterName = new TextView(context);
                chapterName.setPadding(20, 20, 20, 20);
                chapterName.setTextSize(18f);
                chapterName.setTextColor(Color.parseColor("#333333"));
            } else {
                chapterName = (TextView) convertView;
            }

            chapterName.setText(chapters.get(position));

            chapterName.setOnClickListener(v -> {
                Intent intent = new Intent(context, ModelActivity.class);
                context.startActivity(intent);
            });

            return chapterName;
        }
    }
}
