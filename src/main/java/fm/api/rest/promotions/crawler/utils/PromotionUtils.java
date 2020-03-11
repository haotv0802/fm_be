package fm.api.rest.promotions.crawler.utils;

import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PromotionUtils {
    public String getDate(String text){
        String datePattern = "([0-9]+[/][0-9]+[/][0-9]{4}[.]{0,1})";
        Pattern r = Pattern.compile(datePattern);
        Matcher m = r.matcher(text);
        StringBuilder sb = new StringBuilder();
        while(m.find()){
            if(sb.toString().equals("")){
                sb.append(m.group(0));
            }else{
                sb.append("-");
                sb.append(m.group(0));
            }
        }
        if(!sb.toString().equals("")){
            return sb.toString();
        }
        return null;
    }

    public String getProvision(String text){
        String moneyPattern1 ="(([0-9]+[,][0-9]*)[d||đ||vnd||vnđ])";
        String moneyPattern2 ="(([0-9]+)[%])";
        Pattern p1= Pattern.compile(moneyPattern1);
        Matcher m = p1.matcher(text);
        Pattern p2= Pattern.compile(moneyPattern2);
        Matcher m2 = p2.matcher(text);
        if(m2.find()){
            return m2.group();
        }else if(m.find()){
            return m.group();
        }
        return null;
    }

    public List<String> getBankPromotionLinks(String fileName, String bankName){
        List<String> listPromotionLinks= new ArrayList<>();
        try {
            Properties prop = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if(inputStream != null){
                prop.load(inputStream);
            }else{
                System.out.println("There is no config file");
            }
            Enumeration keyProp = prop.keys();
            while(keyProp.hasMoreElements()){
                String keyVal = (String) keyProp.nextElement();
                if(keyVal.contains(bankName)){
                    System.out.println( prop.get(keyVal));
                    listPromotionLinks.add((String) prop.get(keyVal).toString().trim());
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return listPromotionLinks;
    }

    public boolean checckExistedPromo(Object promoModel,String bankID){
//
        //Get Value from DB
//
        return false;
    }

    public void ExportProvisionExcelFile(Map<String,List<PromotionCrawlerModel>> listProvision, String bankName, String[]header){
        try {
            String SAMPLE_XLSX_FILE_PATH = bankName+".xlsx";
            Workbook workbook = new XSSFWorkbook();
            for(String category : listProvision.keySet()){
                CreationHelper creationHelper = workbook.getCreationHelper();
                Sheet sheet = workbook.createSheet(category+"_"+listProvision.get(category).get(0).getCategory());
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 14);
                headerFont.setColor(IndexedColors.RED.getIndex());
                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFont(headerFont);

                Row headerRow =  sheet.createRow(0);

                for(int i = 0 ; i<header.length;i++){
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(header[i]);
                    cell.setCellStyle(headerCellStyle);
                }
                int rowNum =1;
                for(PromotionCrawlerModel item : listProvision.get(category)){
                    Row row = sheet.createRow(rowNum++);
                    if(header.length>=13) {
                        row.createCell(0).setCellValue(item.getBankId());
                        row.createCell(1).setCellValue(item.getTitle());
                        row.createCell(2).setCellValue(item.getContent());
                        row.createCell(3).setCellValue(item.getDiscount());
                        row.createCell(4).setCellValue(item.getCategory());
                        row.createCell(5).setCellValue(item.getStartDate());
                        row.createCell(6).setCellValue(item.getEndDate());
                        row.createCell(7).setCellValue(item.getHtmlText());
                        row.createCell(8).setCellValue(item.getLinkDetail());
                        row.createCell(9).setCellValue(item.getImgURL());
                        row.createCell(10).setCellValue(item.getCardType());
                        row.createCell(11).setCellValue(item.getCondition());
                        row.createCell(12).setCellValue(item.getLocation());
                    }else{
                        row.createCell(0).setCellValue(item.getBankId());
                        row.createCell(1).setCellValue(item.getTitle());
                        row.createCell(2).setCellValue(item.getContent());
                        row.createCell(3).setCellValue(item.getDiscount());
                        row.createCell(4).setCellValue(item.getCategory());
                        row.createCell(5).setCellValue(item.getStartDate());
                        row.createCell(6).setCellValue(item.getEndDate());
                        row.createCell(7).setCellValue(item.getHtmlText());
                        row.createCell(8).setCellValue(item.getLinkDetail());
                    }

                }
                for(int i = 0; i < header.length; i++) {
                    sheet.autoSizeColumn(i);
                }
                FileOutputStream fileOut = null;
                fileOut = new FileOutputStream(SAMPLE_XLSX_FILE_PATH);
                workbook.write(fileOut);
                fileOut.close();
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
