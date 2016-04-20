package com.trifonov.kos.pipeweightcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DigitsActivity extends AppCompatActivity {
    static final String STATE_RESULT = "com.trifonov.kos.pipeweightcalculator.RESULT";

    private String dot;
    private String zero;
    private String clear;
    private Boolean isSetDot;
    private Boolean isNewDigit;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digits);
        dot = getString(R.string.dot);
        zero = getString(R.string.zero);
        clear = getString(R.string.clear);
        isNewDigit = true;
        resultTextView = (TextView) findViewById(R.id.textView24);
        Intent intent = getIntent();
        String s = intent.getStringExtra(MainActivity.EXTRA_NUMBER);
        resultTextView.setText(s);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        resultTextView.setText(savedInstanceState.getString(STATE_RESULT));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_RESULT, resultTextView.getText().toString());
    }

    public void onDigitClick(View view) {
        String s = ((TextView) view).getText().toString();
        String str = resultTextView.getText().toString();
        if (isNewDigit) {
            str = "";
            isSetDot = false;
            isNewDigit = false;
        }
        if (s.equals(clear)) {
            resultTextView.setText(zero);
            isNewDigit = true;
            return;
        }
        if (s.equals(dot)) {
            if (isSetDot)
                return;
            if (str.equals(""))
                str = str.concat(zero);
            isSetDot = true;
        }
        str = str.concat(s);
        resultTextView.setText(str);
    }

    public void onEnterClick(View view) {
        String s = resultTextView.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_NUMBER, s);
        setResult(RESULT_OK, intent);
        finish();
    }
}
