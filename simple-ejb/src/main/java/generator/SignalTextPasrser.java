/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.*;

/**
 *
 * @author maks
 */
public class SignalTextPasrser {

    private double[][] convertToArray(String[] lines, int fromIndex) {
        if (lines == null || fromIndex < 0) {
            throw new IllegalArgumentException("Lines array can't be null and fromIndex can't be less than zero");
        }

        ArrayList<double[]> sig = new ArrayList<double[]>();
        Pattern patt = Pattern.compile("^\\s*(\\S+)\\s+(\\S+)\\s+(.*)$");
        Matcher match;
        for (int i = fromIndex; i < lines.length; i++) {
            try {
                match = patt.matcher(lines[i]); // ////
                match.find();
                sig.add(new double[]{Double.parseDouble(match.group(1)),
                    Double.parseDouble(match.group(2))});
            } catch (NumberFormatException e) {
                break;
            } catch (IllegalStateException e) {
                break;
            }
        }
        double[][] signal = new double[sig.size()][2];
        return sig.toArray(signal);
    }

    private String coordToString(double x, double y) {
        NumberFormat formatter = new DecimalFormat("0.000000E000", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        String xStr = formatter.format(x).toLowerCase();
        int indexOfPlusMinus = xStr.indexOf('e') + 1;
        if (xStr.charAt(indexOfPlusMinus) != '-') {
            xStr = xStr.substring(0, indexOfPlusMinus) + "+" + xStr.substring(indexOfPlusMinus);
        }
        String yStr = formatter.format(y).toLowerCase();
        indexOfPlusMinus = yStr.indexOf('e') + 1;
        if (yStr.charAt(indexOfPlusMinus) != '-') {
            yStr = yStr.substring(0, indexOfPlusMinus) + "+" + yStr.substring(indexOfPlusMinus);
        }
        return xStr + "\t" + yStr + "\t---";
    }

    public String[] toTextLines(double [][] signal) {
        ArrayList<String> lines = toTextLinesList(signal);
        return lines.toArray(new String[lines.size()]);
    }

    public ArrayList<String> toTextLinesList(double [][] signal) {
        ArrayList<String> lines = new ArrayList<String>();
        for (int i = 0; i < signal.length; i++) {
            lines.add(coordToString(signal[i][0], signal[i][1]));
        }
        return lines;
    }
}
