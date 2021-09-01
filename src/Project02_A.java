import kr.infleran.Downloadbroker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Project02_A {
    public static void main(String args[]) throws IOException {
//        String url="https://m.search.naver.com/search.naver?sm=mtb_hty.top&where=m&oquery=네이버+금융&tqi=heuzRsprvSssssM%2BADCssssstnd-359882&query=중국+환율";
//       Document doc =  Jsoup.connect(url).get();
//       Elements el = doc.getElementsByAttributeValue("class","nb_txt _sub_price");
//
//        System.out.println(el.get(1).text());
        //        Document doc = null;
        String url = "https://sum.su.or.kr:8888/bible/today/Ajax/Bible/BodyMatter?qt_ty=QT1";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("년 월 일 입력 0000-00-00식");
        Document doc=null;
        try{
            String bible = br.readLine();
            url+="&Base_de="+bible;
            doc = Jsoup.connect(url).get();
            Elements element = doc.select("div .bible_text");
            Elements elements2 = doc.select(".body_list > li");
            //  Elements element2 = doc.select("div #newsEndContents");
            String title = element.first().text();

            //  String text = element2.text();
            System.out.println(title);

            for(Element el:elements2){
                System.out.println(el.select(".num").text() + " "+el.select(".info").text());
            }


            //리소스 다운로드.
            Element tag = doc.select("source").first();
            String dPath = tag.attr("src").trim();
            System.out.println(dPath); //https://meditation.su.or.kr/meditation_mp3/2021/20210830.mp3
            String filename = dPath.substring(dPath.lastIndexOf("/")+1);


            Element tag2 = doc.select(".img>img").first();
            String dPath2 = "https://sum.su.or.kr:8888"+tag2.attr("src").trim();
            //System.out.println(dPath2); //https://sum.su.or.kr:8888/attach/X07/4b96bed65d514efebf53766d53742700.jpg
            String filename2 = dPath2.substring(dPath2.lastIndexOf("/")+1);


            Runnable r = new Downloadbroker(dPath,filename);
            Thread dLoad = new Thread(r);
            dLoad.start();
            for(int i=0;i<10;i++){
                try{
                    Thread.sleep(1000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(""+ (i+1));
            }
            System.out.println();
            System.out.println("=====================");
        }catch(IOException e){
            e.printStackTrace();
        }


        //System.out.println(text);
    }
}
