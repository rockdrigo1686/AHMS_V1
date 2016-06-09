package com.ahms.ui.utils;

import com.ahms.boundary.entity_boundary.ErrorTraceBoundary;
import com.ahms.model.entity.ErrorTrace;
import java.awt.Component;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author jorge
 */
public class GeneralFunctions {

    private static final ErrorTraceBoundary errorTraceBoundary = new ErrorTraceBoundary();

    public static long getDaysBetweenDates(Calendar date1, Calendar date2) {
        long days = 0;
        try {
            long diffTimeInMillis = date2.getTimeInMillis() - date1.getTimeInMillis();
            days = diffTimeInMillis / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            appendTrace(e.getStackTrace());
        }
        return days;
    }

    public static void sendMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message);
    }

    public static String formatAmount(BigDecimal amount) {
        String formatted;
        DecimalFormat df = new DecimalFormat("$ ##,##0.00");
        try {
            formatted = df.format(amount.doubleValue());
        } catch (Exception e) {
            appendTrace(e.getStackTrace());
            formatted = amount.toString();
        }
        return formatted;
    }

    public static String formatDate(Date date) {
        String formatted;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            formatted = sdf.format(date);
        } catch (Exception e) {
            appendTrace(e.getStackTrace());
            formatted = date.toString();
        }
        return formatted;
    }

    public static void appendTrace(StackTraceElement[] stackTraceArray) {
        ErrorTrace newTrace = new ErrorTrace();
        newTrace.setErrTrace(getCompleteTrace(stackTraceArray));
        newTrace.setErrDate(new Date());
        errorTraceBoundary.insert(newTrace);
    }

    private static String getCompleteTrace(StackTraceElement[] stackTraceArray) {
        StringBuilder sbTrace = new StringBuilder();
        for (StackTraceElement elem : stackTraceArray) {
            sbTrace.append(elem.toString()).append("\n");
        }
        return sbTrace.toString();
    }

    public static boolean compareDates(Calendar calIni, Calendar calEnd, Boolean dif) {
        calIni.set(Calendar.HOUR_OF_DAY, 0);
        calIni.set(Calendar.MINUTE, 0);
        calIni.set(Calendar.SECOND, 0);
        calIni.set(Calendar.MILLISECOND, 0);
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);       
        if (dif) {
            return calIni.compareTo(calEnd) < 0;
        } else {
            return calIni.compareTo(calEnd) < 1;
        }
    }
}
