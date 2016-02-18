/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.utils;

import java.util.Calendar;

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
}
