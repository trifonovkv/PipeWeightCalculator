package com.trifonov.kos.pipeweightcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class WallThickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_thick);
    }

    public void onThicknessClick(View view) {
        String s = ((TextView) view).getText().toString();
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_NUMBER, s);
        setResult(RESULT_OK, intent);
        finish();
    }
}
