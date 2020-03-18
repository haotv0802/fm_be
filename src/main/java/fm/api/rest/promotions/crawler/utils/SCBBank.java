package fm.api.rest.promotions.crawler.utils;
/* Quy created on 3/11/2020  */
import fm.api.rest.promotions.crawler.PromotionCrawlerDAO;
import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;


public class SCBBank {
    private static final Logger LOGGER = LogManager.getLogger(PromotionCrawlerDAO.class);
    private final String mainLink="https://www.scb.com.vn/";
    private PromotionUtils utils= new PromotionUtils();
    private Set<String> listDetailPromoLinks= new HashSet<>();

    public boolean getListPromotionInfo() {
        Map<String, List<PromotionCrawlerModel>> listPromotion= new TreeMap<>();
        List<String> listLinks = utils.getBankPromotionLinks("./properties/SCB.properties","SCB");
        if(!listLinks.isEmpty()){
            try {
                for(String link : listLinks){
                    List<PromotionCrawlerModel> bankProvisionModels = new ArrayList<>();
                    Document promoDoc = Jsoup.connect(link).get();
                    int pageLimit = getLimitPagePromotionCate(promoDoc.getElementsByClass("page-num"));
//                    for(int i=0;i < pageLimit ; i++){
//                        String pageLinkPromo = StringUtils.substring(link,0,link.length()-1)+i;
//                        promoDoc =Jsoup.connect(pageLinkPromo).get();
//                        Elements promoDivEls = promoDoc.select(".small-plus-item > a");
//                        for(Element promoDivEl : promoDivEls){
//                            String linkDetail = promoDivEl.attr("href").toString();
//                            System.out.println(linkDetail);
//                            if(listDetailPromoLinks.add(linkDetail)){
//                                bankProvisionModels.add(getPromotionFromLink(linkDetail,link.split("/")[6]));
//                            }else{
//                                System.out.println("ERROR : The link promo is already existed in List");
//                            }
//
//                        }
//                    }
                    getPromotionFromLink("https://www.scb.com.vn/vie/uu-dai-chu-the/renaissance-riverside-hotel-saigon","AA");
                    listPromotion.put(link.split("/")[6],bankProvisionModels);
                    for (PromotionCrawlerModel model : bankProvisionModels){
                        System.out.println(model.getHtmlText());
                        System.out.println(model.getTitle());
                        System.out.println(model.getContent());
                        System.out.println(model.getDiscount());
                        System.out.println(model.getStartDate());
                        System.out.println(model.getEndDate());
                    }
//                    System.out.println("done");
//                    String[] headers = {"Bank","TiTle","Contain","Discount","Category","Start Beign","Date Expire", "Html","Link","imgURL","cardType","condition","location"};
//                    utils.exportProvisionExcelFile(listPromotion,"SCBBank",headers);
                }
                listDetailPromoLinks.clear();
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
    @Test
    public void test(){
        getPromotionFromLink("https://www.scb.com.vn/vie/uu-dai-chu-the/renaissance-riverside-hotel-saigon","AA");
    }

    public PromotionCrawlerModel getPromotionFromLink(String link, String categoryName) {
        try {
            String startDate="" ;
            String endDate ="" ;
            Document docPromoDetailInfo = Jsoup.connect(link).get();
            byte[] utf8Bytes = docPromoDetailInfo.outerHtml().getBytes("UTF-8");
            String roundTrip = new String(utf8Bytes, "UTF-8");
            docPromoDetailInfo.outputSettings().charset(Charset.forName("UTF-8"));
            System.out.println(roundTrip);
            Elements elPromoDetailInfo = docPromoDetailInfo.getElementsByClass("content-1");
            String title = getTitle(docPromoDetailInfo.select(".sale-detail-wrap"),".title-d-1");
            String content = getDetail(elPromoDetailInfo,"p","Ưu Đãi");
            String date = getDetail(elPromoDetailInfo,"p","Thời gian");
            String cardType = getDetail(elPromoDetailInfo,"p","Áp dụng");
            String condition = getDetail(elPromoDetailInfo,"p","Điều kiện điều khoản");
            String location = getDetail(elPromoDetailInfo,"p","Địa Chỉ");
            String htmlText = getDetail(elPromoDetailInfo,"content-1","HTML");
            String img = getImg(elPromoDetailInfo,"p > img");
            if(date!=null){
                startDate = date.split("đến")[0];
                endDate = date.split("đến")[1];
            }
            PromotionCrawlerModel model = new PromotionCrawlerModel(title, content,utils.getProvision(content),startDate,endDate,categoryName,"SCB",htmlText, link, img,cardType,condition,location);
            return model;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDetail(Elements container, String selector, String tagName) {
        Elements promoDetailEls = container.select(selector);
        if(tagName.equals("HTML")){
            System.out.println("HTLM");
            return container.outerHtml();
        }
        for(Element promoDetail:promoDetailEls){
//            System.out.println("Detail 1");
            if(promoDetail.text().contains(tagName)){
                System.out.println("Detail 2");
                if(promoDetail.text().length() <= tagName.length() + 3){
                    System.out.println("Detail 3");
                    if(promoDetail.nextElementSibling().select("li").size() >0){
                        System.out.println("Detail 4");
                        StringBuilder strb = new StringBuilder();
                        for(Element el : promoDetail.nextElementSibling().select("li")){
                            System.out.println("Detail 5");
                            strb.append(el.text());
                            strb.append("-");
                        }
                        System.out.println("Detail 6 : " + strb.toString());
                        return strb.toString();
                    }else{
                        return promoDetail.nextElementSibling().text();
                    }
                }else{
                    return promoDetail.text();
                }
            }
        }
        return null;
    }

    public List<String> getLocations(Elements container) {
        return null;
    }

    public int getLimitPagePromotionCate(Elements elements){
        Elements numberPage = elements.select("li");
        return numberPage.size();
    }

    public String getTitle (Elements container, String selector){
        Element promoDetailEl = container.select(selector).first();
        if(promoDetailEl !=null){
            return promoDetailEl.text();
        }
        return null;
    }

    public String getImg (Elements container,String selector){
        Element promoDetailEl = container.select(selector).first();
        if(promoDetailEl !=null){
            return promoDetailEl.attr("src");
        }
        return null;
    }
}
