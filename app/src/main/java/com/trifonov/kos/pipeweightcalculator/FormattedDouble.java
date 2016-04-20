package com.trifonov.kos.pipeweightcalculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Formatted double string
 * Created by kos on 15.04.16.
 */
public class FormattedDouble {
    private double aDouble;

    public void parseDouble(String string) {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        Number number;
        try {
            number = format.parse(string);
            aDouble = number.doubleValue();
        } catch (ParseException e) {
            aDouble = 0.0;
            e.printStackTrace();
        }
    }

    public int getInt() {
        return ((Double) aDouble).intValue();
    }

    public double getDouble() {
        return aDouble;
    }

    public String toString() {
        return new DecimalFormat("#.##").format(aDouble);
    }
}
