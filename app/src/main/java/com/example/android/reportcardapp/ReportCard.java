package com.example.android.reportcardapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Report Card class
 * This class prepares student report card.
 * It takes Student Name, Teacher Name, Student Year, Subjects, Marks, and
 * Special Message as inputs to prepare the report.
 * The class also validates and sanitizes data where appropriate.
 *
 * @author  Aditi Bhattacharya
 * @version 1.0
 * @since   2017-05-17
 */

public class ReportCard {

    /** Constant variables */

    static final private String SCHOOL_NAME = "GMGS School";
    static final private int MIN_YEARS = 1;
    static final private int MAX_YEARS = 13;

    static final private String GRADE_EXCELLENT = "Excellent";
    static final private String GRADE_VERY_GOOD = "Very Good";
    static final private String GRADE_GOOD = "Good, above average";
    static final private String GRADE_AVERAGE = "Average, improvement needed";
    static final private String GRADE_MIN_PASS = "Minimum pass, close fail";
    static final private String GRADE_FAIL = "Fail / No Pass";


    /** Class attributes */

    private String mStudentName;
    private String mTeacherName;
    private int mStudentYear;
    private char mFinalGrade;
    private String mFinalGradeDesc;
    private String mMessage;

    /** ArrayList to store Subjects and Marks */
    private ArrayList<SubjectMark> mSubjectMarks;



    /**
     * Default Constructor for ReportCard class
     */
    public ReportCard (String studentName, String teacherName, int studentYear,
                       ArrayList<SubjectMark> subjectMarks, String message) {
        setStudentName(studentName);
        setTeacherName(teacherName);
        setStudentName(studentYear);
        mSubjectMarks = subjectMarks;
        setMessage(message);
    }

    /**
     * This method is to set Student Name
     * @param studentName
     */
    public void setStudentName(String studentName) {
        mStudentName = formatName(studentName);
    }

    /**
     * This method is to get Student Name
     * @return Student Name
     */
    public String getStudentName() {
        return mStudentName;
    }

    /**
     * This method is to set Teacher Name
     * @param teacherName
     */
    public void setTeacherName(String teacherName) {
        mTeacherName = formatName(teacherName);
    }

    /**
     * This method is to get Teacher Name
     * @return Teacher Name
     */
    public String getTeacherName() {
        return mTeacherName;
    }

    /**
     * This method is to set Student Year
     * @param studentYear
     */
    public void setStudentName(int studentYear) {
        mStudentYear = validateStudentYear(studentYear);
    }

    /**
     * This method is to get Student Year
     * @return Student Year
     */
    public int getStudentYear() {
        return mStudentYear;
    }

    /**
     * This method is to set message for student
     * @param message
     */
    public void setMessage(String message) {
        mMessage = message.trim();
    }

    /**
     * This method is to get message for student
     * @return message
     */
    public String getMessage() {
        return mMessage;
    }

    /**
     * This method sanitizes input names (Student Name or Teacher Name) and converts to title case
     * @param name
     * @return formatted name
     */
    public String formatName(String name) {
        String formattedName = "";
        formattedName = toTitleCase(sanitizeName(name));
        return formattedName;
    }

    /**
     * This method sanitizes input name by removing digits or characters other than ' and -
     * @param name
     * @return sanitized name
     */
    public String sanitizeName(String name) {
        String sanitizedName = "";

        String patternName = "[a-zA-z]+([ '-][a-zA-Z]+)*";

        Pattern pattern = Pattern.compile(patternName);
        Matcher matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            sanitizedName = name.replaceAll("[^a-zA-Z-\' ]+", "");
        } else {
            sanitizedName = name;
        }

        return sanitizedName;
    }

    /**
     * This method converts input name to title case
     * @param name
     * @return capitalized name
     */

    public String toTitleCase(String name) {
        String capitalizedName = "";
        boolean isSpace = true;
        int nameLength = 0;

        StringBuilder stringBuilder = new StringBuilder(name);
        nameLength = stringBuilder.length();

        for (int i = 0; i < nameLength; i++) {
            char c = stringBuilder.charAt(i);
            if (isSpace) {
                if (!Character.isWhitespace(c)) {
                    stringBuilder.setCharAt(i, Character.toUpperCase(c));
                    isSpace = false;
                }
            } else if (Character.isWhitespace(c)) {
                isSpace = true;
            } else {
                stringBuilder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        capitalizedName = stringBuilder.toString();
        return capitalizedName;
    }

    /**
     * This method validates input student year by checking if the value is between 1 and 13
     * @param year
     * @return validated year
     */
    public int validateStudentYear (int year) {
        int validatedYear = 0;

        if ((year < MIN_YEARS) || (year > MAX_YEARS)) {
            validatedYear = 1;
        } else {
            validatedYear = year;
        }

        return validatedYear;
    }

    /**
     * This method is to add the subjects and associated marks for the report card
     * @param subject
     * @param marks
     */
    public void setSubjectMarks(String subject, int marks) {
        SubjectMark subjectMark = new SubjectMark(subject, marks);
    }

    /**
     * This method prepares the string to be displayed as output
     * @return
     */
    public String prepareOutput() {
        String strOutput = "";

        // Prepare string output for Subject & Marks list
        StringBuilder displaySubjectMarks = new StringBuilder();
        for (SubjectMark sm : mSubjectMarks) {
            displaySubjectMarks.append(sm.getSubject() + " - " + sm.getMarks() + "\n");
        }

        // Get Final Grade
        prepareFinalGrade();

        // Prepare combined output
        strOutput = "School = " + SCHOOL_NAME + "\n" +
                    "Student Name = " + mStudentName + "\n" +
                    "Teacher Name = " + mTeacherName + "\n" +
                    "Student Year = " + mStudentYear + "\n\n" +
                    "MARKS BY SUBJECT" + "\n" +
                    "---------------------------------" + "\n" +
                    displaySubjectMarks.toString() + "\n\n" +
                    "Final Grade = " + mFinalGrade + " (" + mFinalGradeDesc + ")\n\n\n" +
                    "Message " + "\n" +
                    "---------------------------------" + "\n" +
                    mMessage
                ;

        return strOutput;
    }

    /**
     * This method computes Final Grade of the Student based on input marks
     */
    public void prepareFinalGrade() {
        int totalMarks = 0;
        int averageMarks = 0;
        int listSize = mSubjectMarks.size();

        for (SubjectMark sm : mSubjectMarks) {
            totalMarks += sm.getMarks();
        }

        averageMarks = (totalMarks / listSize);
        Log.v("RC", "" + totalMarks + "/" + listSize + "/" + averageMarks);
        if (averageMarks >= 90) {
            mFinalGrade = 'A';
            mFinalGradeDesc = GRADE_EXCELLENT;
        } else if (averageMarks < 90 && averageMarks >= 80) {
            mFinalGrade = 'B';
            mFinalGradeDesc = GRADE_VERY_GOOD;
        } else if (averageMarks < 80 && averageMarks >= 60) {
            mFinalGrade = 'C';
            mFinalGradeDesc = GRADE_GOOD;
        } else if (averageMarks < 60 && averageMarks >= 50) {
            mFinalGrade = 'D';
            mFinalGradeDesc = GRADE_AVERAGE;
        } else if (averageMarks < 50 && averageMarks >= 40) {
            mFinalGrade = 'E';
            mFinalGradeDesc = GRADE_MIN_PASS;
        } else if (averageMarks < 40) {
            mFinalGrade = 'F';
            mFinalGradeDesc = GRADE_FAIL;
        }
    }


    /**
     * This method returns String representation of the current Class object
     * @return output
     */
    @Override
    public String toString() {
        return prepareOutput();
    }

}

/**
 * Object for Subjects and corresponding marks
 * Class attributes - Subject Name, Marks
 * Methods - getter methods, setter methods, validation methods
 */
class SubjectMark {
    static final private int MIN_SCORE = 1;
    static final private int MAX_SCORE = 100;
    private String mSubject;
    private int mMarks;

    // Default constructor for SubjectMarks
    public SubjectMark (String subject, int marks) {
        setSubject(subject);
        setMarks(marks);
    }

    /**
     * Setter method for subject
     * @param subject
     */
    public void setSubject(String subject) {
        mSubject = formatSubject(subject);
    }

    /**
     * Getter method for subject
     * @return
     */
    public String getSubject() {
        return mSubject;
    }

    /**
     * Setter method for marks
     * @param marks
     */
    public void setMarks(int marks) {
        mMarks = validateMarks(marks);
    }

    /**
     * Getter method for marks
     * @return
     */
    public int getMarks() {
        return mMarks;
    }

    /**
     * This method sanitizes and formats user input subject
     * @param subject
     * @return formatted subject
     */
    private String formatSubject(String subject) {
        String formattedSubject = "";

        // String can contain letters and space, must start with a letter, and each word separated by space
        String patternSubject = "^[a-zA-Z]+( [a-zA-z]+)*$";

        Pattern pattern = Pattern.compile(patternSubject);
        Matcher matcher = pattern.matcher(subject);

        if (!matcher.matches()) {
            formattedSubject = subject.replaceAll("[^a-zA-Z ]+", "");
        } else {
            formattedSubject = subject;
        }

        return formattedSubject;
    }

    /**
     * This method validates if input marks is between 1 and 100, else it defaults the value to 0
     * @param marks
     * @return validated marks
     */
    public int validateMarks(int marks) {
        int validatedMarks = 0;

        if ((marks < MIN_SCORE) || (marks > MAX_SCORE)) {
            validatedMarks = 0;
        } else {
            validatedMarks = marks;
        }

        return validatedMarks;
    }

}