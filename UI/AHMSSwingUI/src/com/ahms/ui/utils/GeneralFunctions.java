/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.utils;

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
    public static long getDaysBetweenDates (Calendar date1, Calendar date2) {
        long days = 0;
        try {
            long diffTimeInMillis = date2.getTimeInMillis() - date1.getTimeInMillis();
            days = diffTimeInMillis / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }
    
    public static void sendMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message);
    }
    
    public static String formatAmount(BigDecimal amount){
        String formatted = "";
        DecimalFormat df = new DecimalFormat("$ ##,##0.00");
        try {
            formatted = df.format(amount.doubleValue());
        } catch (Exception e) {
            e.printStackTrace();
            formatted = amount.toString();
        }
        return formatted;
    }
    
    public static String formatDate(Date date){
        String formatted = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            formatted = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            formatted = date.toString();
        }
        return formatted;
    }
}
