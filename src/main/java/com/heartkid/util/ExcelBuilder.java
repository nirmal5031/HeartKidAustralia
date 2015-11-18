package com.heartkid.util;
 
import java.util.List;
import java.util.Map;
 


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractExcelView;
 


import com.heartkid.model.entity.RegisterDtoEntity;
/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */
@Service
public class ExcelBuilder extends AbstractExcelView {
 
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	System.out.println("INSIDE ECEL BUILD ___++++++++++::::::::::::::::::");
        // get data model which is passed by the Spring container
       // List<Book> listBooks = (List<Book>) model.get("listBooks");
         List<RegisterDtoEntity> listHeartkidUser = (List<RegisterDtoEntity>)model.get("listheartkidusers");
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("HeartKid User Report");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        HSSFRow header = sheet.createRow(0);
         
        header.createCell(0).setCellValue("Reference Number");
        header.getCell(0).setCellStyle(style);
        
        header.createCell(1).setCellValue("User Type");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("Title");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("First Name");
        header.getCell(3).setCellStyle(style);
         
      
         
        header.createCell(4).setCellValue("Last Name");
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue("DoB");
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue("Post Code");
        header.getCell(6).setCellStyle(style);
        
        
        header.createCell(7).setCellValue("State");
        header.getCell(7).setCellStyle(style);
        
        
        header.createCell(8).setCellValue("eMail");
        header.getCell(8).setCellStyle(style);
        
        
        header.createCell(9).setCellValue("Contact Agree");
        header.getCell(9).setCellStyle(style);
        
        
        header.createCell(10).setCellValue("Contact via Phone");
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue("Contact via email");
        header.getCell(11).setCellStyle(style);
        
        
        header.createCell(12).setCellValue("Phone");
        header.getCell(12).setCellStyle(style);
        
        header.createCell(13).setCellValue("ethnicity");
        header.getCell(13).setCellStyle(style);
        
        
        header.createCell(14).setCellValue("Country");
        header.getCell(14).setCellStyle(style);
        
        
        header.createCell(15).setCellValue("Language");
        header.getCell(15).setCellStyle(style);
        
        header.createCell(16).setCellValue("Condition Called");
        header.getCell(16).setCellStyle(style);
        
        header.createCell(17).setCellValue("Heart Condition");
        header.getCell(17).setCellStyle(style);
        
        header.createCell(18).setCellValue("Surgery Held");
        header.getCell(18).setCellStyle(style);
        
        header.createCell(19).setCellValue("Surgery Held");
        header.getCell(19).setCellStyle(style);
        
        header.createCell(20).setCellValue("Surgery Delay count");
        header.getCell(20).setCellStyle(style);
        
        header.createCell(21).setCellValue("Travel Distance");
        header.getCell(21).setCellStyle(style);
        
        header.createCell(22).setCellValue("Heart Doctor Visit");
        header.getCell(22).setCellStyle(style);
        
        header.createCell(23).setCellValue("Local Doctor Visit");
        header.getCell(23).setCellStyle(style);
        
        header.createCell(24).setCellValue("Emergency visit");
        header.getCell(24).setCellStyle(style);
        
        header.createCell(25).setCellValue("Care Age");
        header.getCell(25).setCellStyle(style);
        
         
        // create data rows
        int rowCount = 1;
         
        for (RegisterDtoEntity searchreport : listHeartkidUser) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(searchreport.getReferencenumber());
            aRow.createCell(1).setCellValue(searchreport.getUsertype());
            aRow.createCell(2).setCellValue(searchreport.getTitle());
            aRow.createCell(3).setCellValue(searchreport.getFirstname());
            aRow.createCell(4).setCellValue(searchreport.getLastname());
            aRow.createCell(5).setCellValue(searchreport.getBirthdate());
            aRow.createCell(6).setCellValue(searchreport.getPostcode());
            aRow.createCell(7).setCellValue(searchreport.getState());
            aRow.createCell(8).setCellValue(searchreport.getEmail());
            aRow.createCell(9).setCellValue(searchreport.getConctagree());
           aRow.createCell(10).setCellValue(searchreport.getContctviaphone());
           aRow.createCell(11).setCellValue(searchreport.getContctviaemail());
           aRow.createCell(12).setCellValue(searchreport.getPhone());
           aRow.createCell(13).setCellValue(searchreport.getEthnicity());
           aRow.createCell(14).setCellValue(searchreport.getCountrybirth());
           aRow.createCell(15).setCellValue(searchreport.getLanguage());
           aRow.createCell(16).setCellValue(searchreport.getConditioncalld());
           aRow.createCell(17).setCellValue(searchreport.getHeartconds());
           aRow.createCell(18).setCellValue(searchreport.getSurgeryheld());
           aRow.createCell(19).setCellValue(searchreport.getSurgerydelay());
           aRow.createCell(20).setCellValue(searchreport.getSurgerydelaycount());
           aRow.createCell(21).setCellValue(searchreport.getTraveldistdoc());
           aRow.createCell(22).setCellValue(searchreport.getHeartdoc());
           aRow.createCell(23).setCellValue(searchreport.getLocaldoctorvisit());
           aRow.createCell(24).setCellValue(searchreport.getEmergdeptvisit());
           aRow.createCell(25).setCellValue(searchreport.getCareage16());
        
       }
   }

}
