package com.trifonov.kos.pipeweightcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_NUMBER = "com.trifonov.kos.pipeweightcalculator.NUMBER";

    static final String STATE_PACKAGE = "com.trifonov.kos.pipeweightcalculator.PACKAGE";
    static final String STATE_DIAMETER = "com.trifonov.kos.pipeweightcalculator.DIAMETER";
    static final String STATE_WALL_THICK = "com.trifonov.kos.pipeweightcalculator.WALL_THICK";
    static final String STATE_LENGTH_MIN = "com.trifonov.kos.pipeweightcalculator.LENGTH_MIN";
    static final String STATE_LENGTH_MAX = "com.trifonov.kos.pipeweightcalculator.LENGTH_MAX";
    static final String STATE_PIECES = "com.trifonov.kos.pipeweightcalculator.PIECES";

    static final int REQUEST_CODE_DIAMETER = 1;
    static final int REQUEST_CODE_WALL_THICK = 2;
    static final int REQUEST_CODE_LENGTH_MIN = 3;
    static final int REQUEST_CODE_LENGTH_MAX = 4;
    static final int REQUEST_CODE_PIECES = 5;

    private TextView weightView;
    private TextView diameterView;
    private TextView wallThickView;
    private TextView lengthMinView;
    private TextView lengthMaxView;
    private TextView piecesView;
    private PackagePipes packagePipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weightView = (TextView) findViewById(R.id.textView);
        diameterView = (TextView) findViewById(R.id.textView3);
        wallThickView = (TextView) findViewById(R.id.textView5);
        lengthMinView = (TextView) findViewById(R.id.textView7);
        lengthMaxView = (TextView) findViewById(R.id.textView9);
        piecesView = (TextView) findViewById(R.id.textView11);
        weightView.setMovementMethod(new ScrollingMovementMethod());
        packagePipes = new PackagePipes();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        packagePipes = (PackagePipes) savedInstanceState.getSerializable(STATE_PACKAGE);
        diameterView.setText(savedInstanceState.getString(STATE_DIAMETER));
        wallThickView.setText(savedInstanceState.getString(STATE_WALL_THICK));
        lengthMinView.setText(savedInstanceState.getString(STATE_LENGTH_MIN));
        lengthMaxView.setText(savedInstanceState.getString(STATE_LENGTH_MAX));
        piecesView.setText(savedInstanceState.getString(STATE_PIECES));
        String weight = String.format(Locale.getDefault(), "%,d", (int) packagePipes.getWeight());
        weightView.setText(weight);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(STATE_PACKAGE, packagePipes);
        outState.putString(STATE_DIAMETER, diameterView.getText().toString());
        outState.putString(STATE_WALL_THICK, wallThickView.getText().toString());
        outState.putString(STATE_LENGTH_MIN, lengthMinView.getText().toString());
        outState.putString(STATE_LENGTH_MAX, lengthMaxView.getText().toString());
        outState.putString(STATE_PIECES, piecesView.getText().toString());
    }

    public void selectDiameter(View view) {
        Intent intent = new Intent(this, DiametersActivity.class);
        startActivityForResult(intent, REQUEST_CODE_DIAMETER);
    }

    public void selectThickness(View view) {
        Intent intent = new Intent(this, WallThickActivity.class);
        startActivityForResult(intent, REQUEST_CODE_WALL_THICK);
    }

    public void enterNumber(View view) {
        int id = view.getId();
        String s = ((TextView) view).getText().toString();
        Intent intent = new Intent(this, DigitsActivity.class);
        intent.putExtra(EXTRA_NUMBER, s);
        if (id == lengthMinView.getId())
            startActivityForResult(intent, REQUEST_CODE_LENGTH_MIN);
        if (id == lengthMaxView.getId())
            startActivityForResult(intent, REQUEST_CODE_LENGTH_MAX);
        if (id == piecesView.getId())
            startActivityForResult(intent, REQUEST_CODE_PIECES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        FormattedDouble formattedDouble = new FormattedDouble();
        formattedDouble.parseDouble(data.getStringExtra(EXTRA_NUMBER));
        switch (requestCode) {
            case REQUEST_CODE_DIAMETER:
                diameterView.setText(formattedDouble.toString());
                packagePipes.setDiameter(formattedDouble.getDouble());
                break;
            case REQUEST_CODE_WALL_THICK:
                wallThickView.setText(formattedDouble.toString());
                packagePipes.setWallThick(formattedDouble.getDouble());
                break;
            case REQUEST_CODE_LENGTH_MIN:
                lengthMinView.setText(formattedDouble.toString());
                packagePipes.setLengthMin(formattedDouble.getDouble());
                break;
            case REQUEST_CODE_LENGTH_MAX:
                lengthMaxView.setText(formattedDouble.toString());
                packagePipes.setLengthMax(formattedDouble.getDouble());
                break;
            case REQUEST_CODE_PIECES:
                Integer pieces = formattedDouble.getInt();
                piecesView.setText(String.format(Locale.getDefault(), "%d", pieces));
                packagePipes.setCount(pieces);
                break;
        }
        String string = String.format(Locale.getDefault(), "%,d", (int) packagePipes.getWeight());
        weightView.setText(string);
    }
}
