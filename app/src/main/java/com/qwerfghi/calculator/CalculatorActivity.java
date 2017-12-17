package com.qwerfghi.calculator;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.Expression;

public class CalculatorActivity extends AppCompatActivity {

    private Button mZeroButton, mOneButton, mTwoButton, mThreeButton, mFourButton, mFiveButton,
            mSixButton, mSevenButton, mEightButton, mNineButton, mLeftBracketButton,
            mRightBracketButton, mExponentiationButton, mDotButton, mFactorialButton, mSqrtButton,
            mPlusButton, mMinusButton, mMultiplicationButton, mDivisionButton, mSinButton,
            mCosButton, mTagButton, mCtgButton, mLnButton, mEqualButton, mEraseButton, mResetButton;

    private EditText mExpressionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        getLinksOnViewComponents();
        setOnClickListeners();
    }

    private void getLinksOnViewComponents() {
        mExpressionEditText = findViewById(R.id.expression);

        mZeroButton = findViewById(R.id.button_zero);
        mOneButton = findViewById(R.id.button_one);
        mTwoButton = findViewById(R.id.button_two);
        mThreeButton = findViewById(R.id.button_three);
        mFourButton = findViewById(R.id.button_four);
        mFiveButton = findViewById(R.id.button_five);
        mSixButton = findViewById(R.id.button_six);
        mSevenButton = findViewById(R.id.button_seven);
        mEightButton = findViewById(R.id.button_eight);
        mNineButton = findViewById(R.id.button_nine);
        mLeftBracketButton = findViewById(R.id.button_left_bracket);
        mRightBracketButton = findViewById(R.id.button_right_bracket);
        mExponentiationButton = findViewById(R.id.button_exponentiation);
        mDotButton = findViewById(R.id.button_dot);
        mFactorialButton = findViewById(R.id.button_factorial);
        mSqrtButton = findViewById(R.id.button_sqrt);
        mPlusButton = findViewById(R.id.button_plus);
        mMinusButton = findViewById(R.id.button_minus);
        mMultiplicationButton = findViewById(R.id.button_multiplication);
        mDivisionButton = findViewById(R.id.button_division);
        mSinButton = findViewById(R.id.button_sin);
        mCosButton = findViewById(R.id.button_cos);
        mTagButton = findViewById(R.id.button_tag);
        mCtgButton = findViewById(R.id.button_ctg);
        mLnButton = findViewById(R.id.button_ln);
        mEqualButton = findViewById(R.id.button_equal);
        mEraseButton = findViewById(R.id.button_erase);
        mResetButton = findViewById(R.id.button_reset);
    }

    private void setOnClickListeners() {
        mExpressionEditText.setOnClickListener(this::expressionTextClick);
        mZeroButton.setOnClickListener(this::simpleButtonClick);
        mOneButton.setOnClickListener(this::simpleButtonClick);
        mTwoButton.setOnClickListener(this::simpleButtonClick);
        mThreeButton.setOnClickListener(this::simpleButtonClick);
        mFourButton.setOnClickListener(this::simpleButtonClick);
        mFiveButton.setOnClickListener(this::simpleButtonClick);
        mSixButton.setOnClickListener(this::simpleButtonClick);
        mSevenButton.setOnClickListener(this::simpleButtonClick);
        mEightButton.setOnClickListener(this::simpleButtonClick);
        mNineButton.setOnClickListener(this::simpleButtonClick);
        mLeftBracketButton.setOnClickListener(this::simpleButtonClick);
        mRightBracketButton.setOnClickListener(this::simpleButtonClick);
        mExponentiationButton.setOnClickListener(this::simpleButtonClick);
        mDotButton.setOnClickListener(this::simpleButtonClick);
        mFactorialButton.setOnClickListener(this::simpleButtonClick);
        mPlusButton.setOnClickListener(this::simpleButtonClick);
        mMinusButton.setOnClickListener(this::simpleButtonClick);
        mMultiplicationButton.setOnClickListener(this::simpleButtonClick);
        mDivisionButton.setOnClickListener(this::simpleButtonClick);

        mSqrtButton.setOnClickListener(this::functionalButtonClick);
        mSinButton.setOnClickListener(this::functionalButtonClick);
        mCosButton.setOnClickListener(this::functionalButtonClick);
        mCosButton.setOnClickListener(this::functionalButtonClick);
        mTagButton.setOnClickListener(this::functionalButtonClick);
        mCtgButton.setOnClickListener(this::functionalButtonClick);
        mLnButton.setOnClickListener(this::functionalButtonClick);

        mEqualButton.setOnClickListener(this::equalButtonClick);
        mEraseButton.setOnClickListener(this::eraseButtonClick);
        mResetButton.setOnClickListener(this::resetButtonClick);
    }

    private String calculate(String expressionString) {
        double result;
        int intResult;

        if (expressionString.isEmpty()) {
            return String.valueOf(0);
        }
        Expression expression = new Expression(expressionString);
        result = expression.calculate();

        if (result % 1 == 0) {
            intResult = (int) result;
            return String.valueOf(intResult);
        } else {
            return String.valueOf(result);
        }
    }

    private void simpleButtonClick(View view) {
        Button pressedButton = findViewById(view.getId());
        String pressedButtonValue = pressedButton.getText().toString().toLowerCase();
        int start = mExpressionEditText.getSelectionStart();
        if ((isEmpty() && !isDotButton(pressedButton)) || isNaN()) {
            mExpressionEditText.setText(pressedButtonValue);
            mExpressionEditText.setSelection(mExpressionEditText.getText().length());
        } else {
            mExpressionEditText.getText().insert(start, pressedButtonValue);
        }
    }

    private void functionalButtonClick(View view) {
        Button pressedButton = findViewById(view.getId());
        String pressedButtonValue = pressedButton.getText().toString().toLowerCase();
        int start = mExpressionEditText.getSelectionStart();
        if (isEmpty() || isNaN()) {
            mExpressionEditText.setText(pressedButtonValue + "()");
            mExpressionEditText.setSelection(mExpressionEditText.getText().length() - 1);
        } else {
            mExpressionEditText.getText().insert(start, pressedButtonValue + "()");
            mExpressionEditText.setSelection(start + pressedButtonValue.length() + 1);
        }
    }

    private void eraseButtonClick(View view) {
        int start = mExpressionEditText.getSelectionStart();
        int end = mExpressionEditText.getSelectionEnd();
        if (end != 0) {
            mExpressionEditText.getText().delete((start == end) ? start - 1 : start, end);
        }
    }

    private void equalButtonClick(View view) {
        mExpressionEditText.setText(calculate(mExpressionEditText.getText().toString()));
        mExpressionEditText.setSelection(mExpressionEditText.getText().length());
    }

    private void resetButtonClick(View view) {
        mExpressionEditText.setText("0");
        mExpressionEditText.setSelection(mExpressionEditText.getText().length());
    }

    private void expressionTextClick(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private boolean isDotButton(Button pressedButton) {
        return pressedButton.getText().equals(".");
    }

    private boolean isNaN() {
        return mExpressionEditText.getText().toString().equals("NaN");
    }

    private boolean isEmpty() {
        return mExpressionEditText.getText().toString().equals("0");
    }
}