package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.MemoryFile;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button mTrueButton, mFalseButton;
    TextView mQuestionTextView;
    int mIndex=0, mQuestion, mScore;
    TextView mScoreTextView;
    ProgressBar mProgressBar;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };


    final int PROGRESS_BAR_INCREMENT=(int)Math.ceil(100/mQuestionBank.length)+1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // this is used when screen rotation is on
        if(savedInstanceState!=null){
            mScore=savedInstanceState.getInt("ScoreKey");
            mIndex=savedInstanceState.getInt("IndexKey");
        }
        else{
            mScore=0;
            mIndex=0;
        }


        mTrueButton=(Button) findViewById(R.id.true_button);
        mFalseButton= (Button) findViewById(R.id.false_button);
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);

        TrueFalse firstQuestion= mQuestionBank[mIndex];
        mQuestion = firstQuestion.getQuestionId();   //mquestion =  mQuestionBank[INDEX].getQuestionId();
        mQuestionTextView.setText(mQuestion);

        mScoreTextView=(TextView) findViewById(R.id.score);
        mProgressBar=(ProgressBar) findViewById(R.id.progress_bar);
        mScoreTextView.setText(String.valueOf(mScore)+"/"+mQuestionBank.length);
        //two way of making listner
        //type1
        View.OnClickListener mTrueListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("@string/app_name","Button true pressed");
                //Toast.makeText(getApplicationContext(),"True Pressed!", Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                updateQuestion();
            }
        };
        mTrueButton.setOnClickListener(mTrueListner);

        //type 2 listner

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("@string/app_name","False button presed");
                //Toast myToast= Toast.makeText(getApplicationContext(),"False Pressed!", Toast.LENGTH_SHORT);
               // myToast.show();
                checkAnswer(false);
                updateQuestion();
            }
        });
    }

    private void updateQuestion(){
        mIndex=(mIndex+1)%mQuestionBank.length;
        if (mIndex==0){
            AlertDialog.Builder alert= new AlertDialog.Builder(this);
            alert.setTitle("GAme Finnish!!");
            alert.setCancelable(false);
            alert.setMessage("your score :"+mScore+"/"+mQuestionBank.length);
            alert.setPositiveButton("Close App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        mQuestion = mQuestionBank[mIndex].getQuestionId();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText(String.valueOf(mScore)+"/"+mQuestionBank.length);
    }

    private void checkAnswer(boolean userAnswe){
        boolean correctAnswer=mQuestionBank[mIndex].getAnswer();
        if (userAnswe==correctAnswer){
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            Log.d("@string/app_name","correct");
            mScore++;
        }
        else{
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
            Log.d("@string/app_name","wrong");
        }
    }
//when screen rotation is on need to save the states.

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey",mScore);  //key-value pair
        outState.putInt("IndexKey",mIndex);
    }
}
