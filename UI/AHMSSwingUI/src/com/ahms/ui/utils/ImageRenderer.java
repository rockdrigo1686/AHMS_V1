package com.ahms.ui.utils;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer {
    
    private final JLabel lb = new JLabel();   
    private ImageIcon icon = null;
    public ImageRenderer(String status) {
        String path = "/com/ahms/ui/resources/img/";
        switch(status.toLowerCase()){
            case "disponible": path+= "bedG.png"; break;
            case "ocupado":    path+= "bedR.png"; break;
            case "reservado":  path+= "bedB.png"; break;
        }
        icon = new ImageIcon(getClass().getResource(path));
    }
    
    public ImageRenderer(){}
    
    @Override  
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //lb.setSize(50,50);
        lb.setIcon(icon);
        /*int width = 0;
        switch(column){
            case 0: width = 50; break;
            case 1: width = 20; break;
            case 2: width = 200; break;
            case 3: width = 20; break;
            case 4: width = 20; break;
            case 5: width = 20; break;
            case 6: width = 20; break;
            case 7: width = 20; break;
        }
        table.getColumnModel().getColumn(column).setWidth(width);
        */return lb;
     }  
    
}
