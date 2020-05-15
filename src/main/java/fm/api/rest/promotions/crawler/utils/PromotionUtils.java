package fm.api.rest.promotions.crawler.utils;
/*   */

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Quy created on 3/11/2020
 */
@Service("promoUtils")
public class PromotionUtils {
  private static final Logger logger = LogManager.getLogger(PromotionUtils.class);

  private IPromotionCrawlerDAO iPromotionCrawlerDAO;

  @Autowired
  public PromotionUtils(@Qualifier("promotionCrawlerDao") IPromotionCrawlerDAO iPromotionCrawlerDAO) {
    Assert.notNull(iPromotionCrawlerDAO);
    this.iPromotionCrawlerDAO = iPromotionCrawlerDAO;
  }


  /**
   * This service to get type date from string
   *
   * @param text
   * @return
   */
  public String getDateVIBData(String text) {
    if (text != null) {
      String datePattern = "([0-9]+[/||-][0-9]+[/||-][0-9]{4}[.]{0,1})";
      Pattern r = Pattern.compile(datePattern);
      Matcher m = r.matcher(text);
      StringBuilder sb = new StringBuilder();
      while (m.find()) {
        if (sb.toString().equals("")) {
          sb.append(m.group(0));
        } else {
          sb.append("-");
          sb.append(m.group(0));
        }
      }
      if (!sb.toString().equals("")) {
        return sb.toString();
      }
    }
    return "";
  }

  /**
   * This service to get type date from string
   *
   * @param text
   * @return
   */
  public String getDateSCBData(String text) {
    if (text != null) {
      String datePattern = "([0-9]+[/||-][0-9]+[/||-][0-9]{4})";
      Pattern r = Pattern.compile(datePattern);
      Matcher m = r.matcher(text);
      StringBuilder sb = new StringBuilder();
      while (m.find()) {
        if (sb.toString().equals("")) {
          sb.append(m.group(0));
        }
      }
      if (!sb.toString().equals("")) {
        return sb.toString();
      }
    }
    return "";
  }

  /**
   * This service is to get provision data from string
   *
   * @param text
   * @return
   */
  public String getProvision(String text) {
    if (text != null) {
      String moneyPattern1 = "(\\b([0-9]+[,||.][0-9]*)\\S\\W)";
      String moneyPattern2 = "(([0-9]+)[%])";
      Pattern p1 = Pattern.compile(moneyPattern1);
      Matcher m = p1.matcher(text);
      Pattern p2 = Pattern.compile(moneyPattern2);
      Matcher m2 = p2.matcher(text);
      if (m2.find()) {
        return m2.group();
      } else if (m.find()) {
        return m.group();
      }
    }
    return null;
  }

  /**
   * This service is to get period installment from string
   *
   * @param text
   * @return
   */
  public String getPeriod(String text) {
    if (text.contains("Kỳ hạn áp dụng:")) {
      int beginPosition = text.indexOf("Kỳ hạn áp dụng:") + 15;
      int endPosstion = text.indexOf("tháng");

      String period = text.substring(beginPosition, endPosstion).trim();

      return period;
    }
    return null;
  }

  /**
   * This service is to get url from file properties
   *
   * @param fileName
   * @param bankName
   * @return
   */
  public List<String> getBankPromotionLinks(String fileName, String bankName) {
    List<String> listPromotionLinks = new ArrayList<>();
    try {
      Properties prop = new Properties();
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
      if (inputStream != null) {
        prop.load(inputStream);
      } else {
        System.out.println("There is no config file");
      }
      Enumeration keyProp = prop.keys();
      while (keyProp.hasMoreElements()) {
        String keyVal = (String) keyProp.nextElement();
        if (keyVal.contains(bankName)) {
          listPromotionLinks.add((String) prop.get(keyVal).toString().trim());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return listPromotionLinks;
  }

  /**
   * This service is to export all data into file excel
   *
   * @param listProvision
   * @param bankName
   * @param header
   */
  public void exportProvisionExcelFile(Map<String, List<PromotionCrawlerModel>> listProvision, String bankName, String[] header) {
    try {
      String SAMPLE_XLSX_FILE_PATH = bankName + ".xlsx";
      Workbook workbook = new XSSFWorkbook();
      for (String category : listProvision.keySet()) {
        CreationHelper creationHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet(category);
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < header.length; i++) {
          Cell cell = headerRow.createCell(i);
          cell.setCellValue(header[i]);
          cell.setCellStyle(headerCellStyle);
        }
        int rowNum = 1;
        for (PromotionCrawlerModel item : listProvision.get(category)) {
          Row row = sheet.createRow(rowNum++);
          row.createCell(0).setCellValue(item.getBankId());
          row.createCell(1).setCellValue(item.getTitle());
          row.createCell(2).setCellValue(item.getContent());
          row.createCell(3).setCellValue(item.getDiscount());
          row.createCell(4).setCellValue(item.getCategoryId());
          row.createCell(5).setCellValue(item.getStartDate());
          row.createCell(6).setCellValue(item.getEndDate());
          row.createCell(7).setCellValue(item.getHtmlText());
          row.createCell(8).setCellValue(item.getLinkDetail());
          if (header.length >= 13) {
            row.createCell(9).setCellValue(item.getImgURL());
            row.createCell(10).setCellValue(item.getCardType());
            row.createCell(11).setCellValue(item.getCondition());
            row.createCell(12).setCellValue(item.getLocation());
          }

        }
        for (int i = 0; i < header.length; i++) {
          sheet.autoSizeColumn(i);
        }
        System.out.println("Export here");
        FileOutputStream fileOut = null;
        fileOut = new FileOutputStream(SAMPLE_XLSX_FILE_PATH);
        workbook.write(fileOut);
        fileOut.close();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  /**
   * This service is to check existed data
   *
   * @param model
   * @param list
   * @return
   */
  public boolean checkInfoExit(PromotionCrawlerModel model, List<PromotionPresenter> list) {
    if (!list.isEmpty()) {
      for (PromotionPresenter item : list) {
        if (model.getTitle().equals(item.getTitle())) {
          if (model.getContent().equals(item.getContent())) {
            if (model.getDiscount() != null && item.getDiscount() != null) {
              if (!model.getDiscount().equals(item.getDiscount()) || !model.getEndDate().equals(item.getEndDate())) {
                //do update
                logger.info("Update VIB Promotion - Promotion link  :  " + model.getLinkDetail());
                return true;
              }
            }
            if (model.getInstallmentPeriod() != null && item.getInstallmentPeriod() != null) {
              if (!model.getInstallmentPeriod().equals(item.getInstallmentPeriod()) || !model.getEndDate().equals(item.getEndDate())) {
                logger.info("Update VIB Promotion - Promotion link  :  " + model.getLinkDetail());
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }

  /**
   * This service is to get bank promotion  data from Database.
   *
   * @param bankId
   * @param cateId
   * @return
   */
  public List<PromotionPresenter> initBankData(int bankId, int cateId) {
    return iPromotionCrawlerDAO.getPrmoTionByBankId(bankId, cateId);
  }

  /**
   * This service is to add list promotion from bank into Map because there are has the same Key value from map.
   *
   * @param promotionMap      : current Map value
   * @param listBankPromotion
   * @return
   */
  public Map<Integer, List<PromotionCrawlerModel>> addPromotionDataIntoMap(Map<Integer, List<PromotionCrawlerModel>> promotionMap,
                                                                           List<PromotionCrawlerModel> listBankPromotion,
                                                                           int cateId) {
    if (!listBankPromotion.isEmpty()) {
      if (promotionMap.get(cateId) != null) {
        promotionMap.get(cateId).addAll(listBankPromotion);
//        for (PromotionCrawlerModel model : listBankPromotion) {
//          promotionMap.get(cateId).add(model);
//        }
        return promotionMap;
      } else {
        promotionMap.put(cateId, listBankPromotion);
      }
    }
    return promotionMap;
  }

}
