package com.gtoz.uxsocialmedia;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by GtoZ on 10/30/2016.
 */

public class QuestionFragment extends Fragment {
    private TextView txtQuestionNumber, txtQuestion, txtSkipSurvey;
    private ImageButton answer1, answer2, answer3, answer4, answer5, answer6;
    private ProgressBar surveyProgressBar;
    private String[] questions;
    private int[][] answers;

    private final int NUM_OF_QUESTIONS = 5;
    private int[] results = new int[NUM_OF_QUESTIONS];
    int question = 0;
    private int progressStatus = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        // Initialize objects in fragment
        txtQuestionNumber = (TextView) view.findViewById(R.id.txtQuestionNumber);
        txtQuestion = (TextView) view.findViewById(R.id.txtQuestion);
        answer1 = (ImageButton) view.findViewById(R.id.answer1);
        answer2 = (ImageButton) view.findViewById(R.id.answer2);
        answer3 = (ImageButton) view.findViewById(R.id.answer3);
        answer4 = (ImageButton) view.findViewById(R.id.answer4);
        answer5 = (ImageButton) view.findViewById(R.id.answer5);
        answer6 = (ImageButton) view.findViewById(R.id.answer6);
        surveyProgressBar = (ProgressBar) view.findViewById(R.id.surveyProgressBar);
        txtSkipSurvey = (TextView) view.findViewById(R.id.txtSkipSurvey);

        // Get array of questions
        questions = getQuestions();

        // Get array of answers (image resources)
        answers = getAnswers();

        // Set up question
        setNextQuestion();
        surveyProgressBar.setProgress(progressStatus);

        // Button click listeners
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store results and set up next question
                results[question] = 1;
                question++;
                setNextQuestion();
                progressStatus += (100 / NUM_OF_QUESTIONS);
                surveyProgressBar.setProgress(progressStatus);
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store results and set up next question
                results[question] = 2;
                question++;
                setNextQuestion();
                progressStatus += (100 / NUM_OF_QUESTIONS);
                surveyProgressBar.setProgress(progressStatus);
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store results and set up next question
                results[question] = 3;
                question++;
                setNextQuestion();
                progressStatus += (100 / NUM_OF_QUESTIONS);
                surveyProgressBar.setProgress(progressStatus);
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store results and set up next question
                results[question] = 4;
                question++;
                setNextQuestion();
                progressStatus += (100 / NUM_OF_QUESTIONS);
                surveyProgressBar.setProgress(progressStatus);
            }
        });

        answer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store results and set up next question
                results[question] = 5;
                question++;
                setNextQuestion();
                progressStatus += (100 / NUM_OF_QUESTIONS);
                surveyProgressBar.setProgress(progressStatus);
            }
        });

        answer6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store results and set up next question
                results[question] = 6;
                question++;
                setNextQuestion();
                progressStatus += (100 / NUM_OF_QUESTIONS);
                surveyProgressBar.setProgress(progressStatus);
            }
        });

        txtSkipSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Alert User to quit
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Skip survey?");
                alert.setMessage("You may complete this survey later in settings.");
                //alert.setCancelable(false);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Set login fragment
                        LoginFragment fragment = new LoginFragment();
                        setFragment(fragment);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        return view;
    }

    // Method called to upgrade fragment
    public void setFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.surveyContent, fragment).addToBackStack(null).commit();
        fm.executePendingTransactions();
    }

    // Set up next question of survey
    public void setNextQuestion() {
        // Last question
        if(question == NUM_OF_QUESTIONS) {
            // Store results array





            // Set login fragment
            LoginFragment fragment = new LoginFragment();
            setFragment(fragment);
        }
        else {
            // Set question #
            txtQuestionNumber.setText("Question " + (question + 1));
            // Set question
            txtQuestion.setText(questions[question]);
            // Set answer images
            answer1.setImageResource(answers[question][0]);
            answer2.setImageResource(answers[question][1]);
            answer3.setImageResource(answers[question][2]);
            answer4.setImageResource(answers[question][3]);
            answer5.setImageResource(answers[question][4]);
            answer6.setImageResource(answers[question][5]);
        }
    }

    // Return an array of questions for survey
    public String[] getQuestions() {
        String[] questions = new String[NUM_OF_QUESTIONS];



        // Hard coded - Add code to get questions dynamically
        questions[0] = "Choose one of the following: ";
        questions[1] = "Where would you rather be? ";
        questions[2] = "Which interests you the most? ";
        questions[3] = "Choose one of the following: ";
        questions[4] = "Which one looks best? ";





        return questions;
    }

    // Return an array of the responses for each question in survey
    public int[][] getAnswers() {
        int[][] answers = new int[NUM_OF_QUESTIONS][6];



        // Hard coded - Add code to get answers dynamically
        answers[0][0] = R.drawable.one;
        answers[0][1] = R.drawable.two;
        answers[0][2] = R.drawable.three;
        answers[0][3] = R.drawable.four;
        answers[0][4] = R.drawable.five;
        answers[0][5] = R.drawable.six;
        answers[1][0] = R.drawable.seven;
        answers[1][1] = R.drawable.eight;
        answers[1][2] = R.drawable.nine;
        answers[1][3] = R.drawable.ten;
        answers[1][4] = R.drawable.one;
        answers[1][5] = R.drawable.two;
        answers[2][0] = R.drawable.three;
        answers[2][1] = R.drawable.four;
        answers[2][2] = R.drawable.five;
        answers[2][3] = R.drawable.six;
        answers[2][4] = R.drawable.seven;
        answers[2][5] = R.drawable.eight;
        answers[3][0] = R.drawable.nine;
        answers[3][1] = R.drawable.ten;
        answers[3][2] = R.drawable.one;
        answers[3][3] = R.drawable.two;
        answers[3][4] = R.drawable.three;
        answers[3][5] = R.drawable.four;
        answers[4][0] = R.drawable.five;
        answers[4][1] = R.drawable.six;
        answers[4][2] = R.drawable.seven;
        answers[4][3] = R.drawable.eight;
        answers[4][4] = R.drawable.nine;
        answers[4][5] = R.drawable.ten;






        return answers;
    }
}
