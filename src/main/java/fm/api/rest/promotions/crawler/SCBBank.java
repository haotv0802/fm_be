package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerService;
import fm.api.rest.promotions.crawler.utils.PromotionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;


public class SCBBank implements IPromotionCrawlerService {
    private final String mainLink="https://www.scb.com.vn/";
    private PromotionUtils utils= new PromotionUtils();
    private Set<String> listDetailPromoLinks= new HashSet<>();

    @Override
    public void getListPromotionInfo() {
        Map<String, List<PromotionCrawlerModel>> listPromotion= new TreeMap<>();
        List<String> listLinks = utils.getBankPromotionLinks("./config/SCB.properties","SCB");
        if(!listLinks.isEmpty()){
            try {
                for(String link : listLinks){
                    List<PromotionCrawlerModel> bankProvisionModels = new ArrayList<>();
                    Document promoDoc = Jsoup.connect(link).get();

                    int pageLimit = getLimitPagePromotionCate(promoDoc.getElementsByClass("page-num"));
                    for(int i=0;i < pageLimit ; i++){
                        String pageLinkPromo = StringUtils.substring(link,0,link.length()-1)+i;
                        promoDoc =Jsoup.connect(pageLinkPromo).get();
                        Elements promoDivEls = promoDoc.select(".small-plus-item > a");
                        for(Element promoDivEl : promoDivEls){
                            String linkDetail = promoDivEl.attr("href").toString();
                            if(listDetailPromoLinks.add(linkDetail)){
                                bankProvisionModels.add(getPromotionFromLink(linkDetail,link.split("/")[6]));
                            }else{
                                System.out.println("ERROR : The link promo is already existed in List");
                            }

                        }
                    }
                    listPromotion.put(link.split("/")[6],bankProvisionModels);
                    System.out.println("done");
                    String[] headers = {"Bank","TiTle","Contain","Discount","Category","Start Beign","Date Expire", "Html","Link","imgURL","cardType","condition","location"};
                    utils.ExportProvisionExcelFile(listPromotion,"SCBBank",headers);
                    listDetailPromoLinks.clear();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PromotionCrawlerModel getPromotionFromLink(String link, String categoryName) {
        try {
            System.out.println(link);
            String startDate="" ;
            String endDate ="" ;
            Document docPromoDetailInfo = Jsoup.connect("https://www.scb.com.vn/vie/uu-dai-chu-the/booking-giam-10").get();
            Elements elPromoDetailInfo = docPromoDetailInfo.getElementsByClass("content-1");
            String title = getTitle(docPromoDetailInfo.select(".sale-detail-wrap"),".title-d-1");
            String content = getDetail(elPromoDetailInfo,"p","Ưu đãi");
            String date = getDetail(elPromoDetailInfo,"p","Thời gian");
            String cardType = getDetail(elPromoDetailInfo,"p","Áp dụng");
            String condition = getDetail(elPromoDetailInfo,"p","Điều kiện điều khoản");
            String location = getDetail(elPromoDetailInfo,"p","Địa điểm");
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

    @Override
    public String getDetail(Elements container, String selector, String tagName) {
        Elements promoDetailEls = container.select(selector);
        if(tagName.equals("HTML")){
            return container.outerHtml();
        }
        for(Element promoDetail:promoDetailEls){
            if(promoDetail.text().contains(tagName)){
                if(promoDetail.text().length() <= tagName.length() + 3){
                    if(promoDetail.nextElementSibling().select("li").size() >0){
                        StringBuilder strb = new StringBuilder();
                        for(Element el : promoDetail.nextElementSibling().select("li")){
                            strb.append(el.text());
                            strb.append("-");
                        }
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

    @Override
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
