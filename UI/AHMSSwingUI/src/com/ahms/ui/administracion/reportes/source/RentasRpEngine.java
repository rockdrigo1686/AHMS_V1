package com.ahms.ui.administracion.reportes.source;

import com.ahms.model.entity.CashOut;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.UIConstants;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RentasRpEngine {
    private ArrayList<ArrayList<String>> source = null;
    private CashOut shift = null;
    
    public RentasRpEngine(ArrayList<ArrayList<String>> _source, CashOut _shift){
        this.source = _source;
        this.shift = _shift;
    }
    
    public void generate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_hh_mm_ss");
        SimpleDateFormat sdfTitle = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date today = new Date();
        try {
            Document documento = new Document();
            documento.setPageSize(PageSize.A4);            
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(UIConstants.REPORTE_OUT_FILE + "RentasRp_" + sdf.format(today) + ".pdf"));
            writer.setPdfVersion(PdfWriter.VERSION_1_7);                        
            documento.open();
            
            Font fontTitle = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, BaseColor.WHITE);
            Font fontRem2 = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);
            Font fontNormal = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL, BaseColor.DARK_GRAY);
            
            //IMAGEN
            Image img = Image.getInstance(UIConstants.LOGO_W);
            img.scaleToFit(580f, 76.5f);
            img.setAlignment(Image.ALIGN_CENTER);
            documento.add(img);
            
            //Cabecera			
            PdfPTable titleTable = new PdfPTable(1);
            titleTable.setWidthPercentage(100);
            PdfPCell titCell = new PdfPCell(new Phrase("REPORTE DE RENTAS", fontTitle));
            tittleCellStyle(titCell);
            titleTable.addCell(titCell);
            documento.add(titleTable);		

            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setWidthPercentage(100);
            PdfPCell fechaCell = new PdfPCell(new Phrase("Fecha: " + sdfTitle.format(today), fontRem2));
            headerCellStyle(fechaCell);
            headerTable.addCell(fechaCell);
            documento.add(headerTable);
            
            //DATOS
            int rowNumber = 0;
            BigDecimal total = BigDecimal.ZERO;
            BigDecimal totalSinIva = BigDecimal.ZERO;
            for(ArrayList<String> iSource : source){
                if(rowNumber <= 0){
                    PdfPTable colsTable = new PdfPTable(7);
                    colsTable.setWidthPercentage(100);
                    for(int i=0;i<7;i++){
                        PdfPCell cuartoCell = new PdfPCell(new Phrase(iSource.get(i).toUpperCase(), fontRem2));
                        headerCellStyle(cuartoCell);
                        colsTable.addCell(cuartoCell);
                    }
                    documento.add(colsTable);
                } else {
                    PdfPTable rowTable = new PdfPTable(5);
                    rowTable.setWidthPercentage(100);
                    for(int i=0;i<7;i++){
                        PdfPCell cell;
                        if(i > 3 && i < 6 ){
                            BigDecimal monto = new BigDecimal(iSource.get(i)).setScale(2);
                            cell = new PdfPCell(new Phrase("$ " + monto.toString(), fontNormal));                        
                        } else {
                            cell = new PdfPCell(new Phrase(iSource.get(i).toUpperCase(), fontNormal));                        
                        }
                        cellStyle(cell);
                        rowTable.addCell(cell);
                    }
                    documento.add(rowTable);
                    total = total.add(new BigDecimal(iSource.get(5)));
                    totalSinIva = totalSinIva.add(new BigDecimal(iSource.get(4)));                            
                }
                rowNumber++;
            }
            PdfPTable totalSinIvaTable = new PdfPTable(2);
            totalSinIvaTable.setWidthPercentage(100);
            PdfPCell totalSinIvaCellName = new PdfPCell(new Phrase("TOTAL SIN IVA:" , fontRem2));
            totalSinIvaTable.addCell(totalSinIvaCellName);
            PdfPCell totalSinIvaCellValue = new PdfPCell(new Phrase("$ " + totalSinIva.toString() + fontRem2));
            totalSinIvaTable.addCell(totalSinIvaCellValue);
            
            PdfPTable totalTable = new PdfPTable(2);
            totalTable.setWidthPercentage(100);
            PdfPCell totalCellName = new PdfPCell(new Phrase("TOTAL:" , fontRem2));
            totalTable.addCell(totalCellName);
            PdfPCell totalCellValue = new PdfPCell(new Phrase("$ " + total.toString() + fontRem2));
            totalTable.addCell(totalCellValue);            
            
            //Create file
            documento.close();
            GeneralFunctions.sendMessage(null, "Reporte de ocupacion generado correctamente.");
        } catch (DocumentException | IOException e) {
            GeneralFunctions.sendMessage(null, "Ocurrio un error al generar el reporte.\nContacte con su servicio técnico.\nError: " + e.getMessage());
        }
    }
    
    
    private void headerCellStyle(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingBottom(3f);		
        cell.setBackgroundColor(new BaseColor(224, 224, 224));
        cell.setBorderColor(new BaseColor(0, 0, 0));
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
    }
    
    public void tittleCellStyle(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingTop(3f);
        cell.setPaddingBottom(3f);		
        cell.setBackgroundColor(new BaseColor(224, 224, 224));
        cell.setBorderColor(new BaseColor(0, 0, 0));
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
    }
    
    public static void cellStyle(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingBottom(3f);		
        cell.setBorderColor(new BaseColor(0, 0, 0));
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
    }
}
