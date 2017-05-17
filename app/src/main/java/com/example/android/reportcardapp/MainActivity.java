package com.example.android.reportcardapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final private String LOG_TAG = "ReportCard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<SubjectMark> result = new ArrayList<SubjectMark>();
        result.add(new SubjectMark("English", 75));
        result.add(new SubjectMark("Biology", 80));
        result.add(new SubjectMark("Physics", 92));
        result.add(new SubjectMark("Chemistry", 95));
        result.add(new SubjectMark("Mathematics", 98));
        result.add(new SubjectMark("History", 70));
        result.add(new SubjectMark("Geography99", 83));
        result.add(new SubjectMark("Spa@nish", 68));
        result.add(new SubjectMark("Computers", 999));

        ReportCard reportCard = new ReportCard("billy9 harper", "Mary@@ Thomson123!!", 7, result,
                "Have a nice summer\nSchool begins on June 18th\nSee you soon!");

        String output = reportCard.toString();
        Log.v(LOG_TAG, output);

        TextView textView = (TextView) findViewById(R.id.text_output);
        textView.setText(output);
    }
}
