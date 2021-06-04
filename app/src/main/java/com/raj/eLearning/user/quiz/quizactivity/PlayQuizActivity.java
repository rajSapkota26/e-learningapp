package com.raj.eLearning.user.quiz.quizactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.raj.eLearning.R;
import com.raj.eLearning.model.Question;
import com.raj.eLearning.user.quiz.quizadapter.LoadQuestion;

import java.util.List;
import java.util.Random;

public class PlayQuizActivity extends AppCompatActivity {
    private TextView option1, option2, option3, option4, questionCounter, tv_question, tv_timer;
    List<Question> questions;
    int index = 0;
    Question question;
    CountDownTimer timer;
    int correctAnswers = 0;
    private LoadQuestion loadQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quiz);
        init();
    }

    public void init() {
        loadQuestion = new LoadQuestion(this);
        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        option4 = findViewById(R.id.option_4);
        questionCounter = findViewById(R.id.questionCounter);
        tv_question = findViewById(R.id.question);
        tv_timer = findViewById(R.id.timer);
        final String catId = getIntent().getStringExtra("catId");
        questions = loadQuestion.getQuestion(catId);
        Random random = new Random();
        final int rand = random.nextInt(12);
        resetTimer();
        setNextQuestion();


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.option_1:
            case R.id.option_2:
            case R.id.option_3:
            case R.id.option_4:
                if (timer != null) {
                    timer.cancel();
                    TextView selected = (TextView) view;
                    checkAnswer(selected);
                }

                break;
            case R.id.nextBtn:

                reset();
                if (index < questions.size()) {
                    index++;
                    setNextQuestion();
                } else {
                    finishQuiz();
                }
                break;
        }
    }

    void showAnswer() {
        if (question.getAnswer().equals(option1.getText().toString()))
            option1.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if (question.getAnswer().equals(option2.getText().toString()))
            option2.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if (question.getAnswer().equals(option3.getText().toString()))
            option3.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if (question.getAnswer().equals(option4.getText().toString()))
            option4.setBackground(getResources().getDrawable(R.drawable.option_right));
    }

    void reset() {
        option1.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        option2.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        option3.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        option4.setBackground(getResources().getDrawable(R.drawable.option_unselected));
    }

    void checkAnswer(TextView textView) {
        String selectedAnswer = textView.getText().toString();
        if (selectedAnswer.equals(question.getAnswer())) {
            correctAnswers++;
            textView.setBackground(getResources().getDrawable(R.drawable.option_right));
        } else {
            showAnswer();
            textView.setBackground(getResources().getDrawable(R.drawable.option_wrong));
        }
    }

    void setNextQuestion() {
        if (timer != null)
            timer.cancel();

        timer.start();
        if (index < questions.size()) {
            questionCounter.setText(String.format("%d/%d", (index + 1), questions.size()));
            question = questions.get(index);
            tv_question.setText(question.getQuestion());
            option1.setText(question.getOption1());
            option2.setText(question.getOption2());
            option3.setText(question.getOption3());
            option4.setText(question.getOption4());
        }
    }

    void resetTimer() {
        timer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_timer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                finishQuiz();
            }
        };
    }



    public void finishQuiz() {
        Intent intent = new Intent(PlayQuizActivity.this, QuizResultActivity.class);
        intent.putExtra("correct", correctAnswers);
        intent.putExtra("total", questions.size());
        startActivity(intent);
        finish();
    }

    public void quitQuiz(View view) {
        startActivity(new Intent(PlayQuizActivity.this, QuizActivity.class));
        finish();
    }
}