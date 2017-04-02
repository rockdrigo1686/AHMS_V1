package com.ahms.ui.utils;

import com.ahms.boundary.entity_boundary.ErrorTraceBoundary;
import com.ahms.model.entity.ErrorTrace;
import java.awt.Component;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author jorge
 */
public class GeneralFunctions {

    private static final ErrorTraceBoundary ERROR_TRACE_BOUNDARY = new ErrorTraceBoundary();

    public static int getDaysBetweenDates(Calendar date1, Calendar date2) {
        int days = 0;
       SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            DateTime dt1 = new DateTime(date1);
            DateTime dt2 = new DateTime(date2);
            
             days = Days.daysBetween(dt1,dt2).getDays();
            
        } catch (Exception e) {
            e.printStackTrace();
            //appendTrace(e.getStackTrace());
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
        ERROR_TRACE_BOUNDARY.insert(newTrace);
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
    
    public static void resetProperties(Object obj) {
        
        for (Field object : obj.getClass().getDeclaredFields()) {
            try {
                object.setAccessible(true);
                object.set(obj, null);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(GeneralFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static boolean validateDecimals(String expression){
        try {
            Pattern p = Pattern.compile("(\\d+).\\d{2}");
            Matcher m = p.matcher(expression);
            return m.matches();
        } catch (Exception e) {
            appendTrace(e.getStackTrace());
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean validateNumeric(String expression){
        try {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(expression);
            return m.matches();
        } catch (Exception e) {
            appendTrace(e.getStackTrace());
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean validateAlpha(String expression){
        try {
            Pattern p = Pattern.compile("^[\\p{L} .'-]+$");
            Matcher m = p.matcher(expression);
            return m.matches();
        } catch (Exception e) {
            appendTrace(e.getStackTrace());
            e.printStackTrace();
        }
        return false;
    }
}
