package fm.api.rest.promotions.crawler.utils;
/* Quy created on 3/11/2020  */
import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class VIBBank implements IPromotionCrawlerService {
    private final String mainLink="https://www.vib.com.vn/";
    private final PromotionUtils utils = new PromotionUtils();
    private Set<String> listDetailPromoLink = new HashSet<>();
    @Override
    public boolean getListPromotionInfo() {
        List<String> listLinks = utils.getBankPromotionLinks("./properties/VIB.properties","VIB");
        if(!listLinks.isEmpty()){
            try {
                Map<String,List<PromotionCrawlerModel>> listPromotion= new TreeMap<>();
                for(String item : listLinks){
                    List<PromotionCrawlerModel> listModel = new ArrayList<>();
                    Document pagePromoByCate = Jsoup.connect(item).get();
                    Element promotionContainer = pagePromoByCate.getElementById("promotionList");
                    Elements promotionbox = promotionContainer.select(".vib-v2-world-box");
                    for (Element el : promotionbox){
                        String linkPromoDetail = mainLink + el.select("a").attr("href");
                        if(listDetailPromoLink.add(linkPromoDetail)){
                            listModel.add(getPromotionFromLink(linkPromoDetail,item.split("&name=")[1]));
                        }else{
                            System.out.println("ERROR : The link is already in List");
                        }
                    }
                    String cateID = item.split("\\?cate=")[1].split("&name=")[0];
                    listPromotion.put(cateID,listModel);

                }
                String[] headers = {"Bank","TiTle","Contain","Discount","Category","Start Beign","Date Expire", "Html","Link"};
                utils.exportProvisionExcelFile(listPromotion,"VIBBank",headers);
                listDetailPromoLink.clear();
                return true;
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public PromotionCrawlerModel getPromotionFromLink(String link, String categoryName) {
        try {
            Document promotionPage = Jsoup.connect(link).get();
            Elements promotionPageEls = promotionPage.getElementsByClass("vib-v2-wrapper_new");
            String title = getDetail(promotionPageEls,".vib-v2-title-promotion-detail","Title");
            String endDate = getDetail(promotionPageEls,".vib-v2-date-promotion","End Date");
            String content = getDetail(promotionPageEls,".vib-v2-left-body-world-detail","Content");
            List<String> location = getLocations(promotionPageEls);
            String htmlText  = getDetail(promotionPageEls,".vib-v2-left-body-world-detail","HTML");
            PromotionCrawlerModel model = new PromotionCrawlerModel(title,content,utils.getProvision(content),"",endDate,categoryName,"VIBBank",htmlText,link,"IMG","CARD TYPE","CONDITION","LOCATION");
            return model;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getDetail(Elements container, String selector, String tagName) {
        try {
            for(Element item : container){
                String result= item.select(selector).text();
                if(!result.equals("")){
                    if (tagName.equals("End Date")){
                        return utils.getDate(result);
                    }else if(tagName.equals("HTML")){
                        return item.select(selector).outerHtml();
                    }
                    else{
                        return result;
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getLocations(Elements container) {
        List<String> locationLists = new ArrayList<>();
        for (Element item : container){
            Elements locationContainer = item.select(".vib-v2-box-place-map");
            for(Element locaationEl : locationContainer){
                String location = locaationEl.text();
                locationLists.add(location);
            }
        }
        return locationLists;
    }
}
