package com.londonappbrewery.quizzler;

public class TrueFalse {

    private int mQuestionId;
    private Boolean mAnswer;

    public TrueFalse(int questionResoreId,Boolean trueOrFalse){
        mAnswer=trueOrFalse;
        mQuestionId=questionResoreId;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public Boolean getAnswer() {
        return mAnswer;
    }

    public void setAnswer(Boolean answer) {
        mAnswer = answer;
    }
}
