package com.trifonov.kos.pipeweightcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DiametersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diameters);
    }

    public void onDiameterClick(View view) {
        String s = ((TextView) view).getText().toString();
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_NUMBER, s);
        setResult(RESULT_OK, intent);
        finish();
    }
}
