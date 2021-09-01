import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Project01_D {
    public static void main(String args[]){
        String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
        String clientId = "uck69eevv9";
        String clientSecret = "VtgWSggn3uwrmrBtilS48cBULAAs98c9eGtbaLuG";
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("주소를 입력하시오: ");
            String address=io.readLine();
            String addr = URLEncoder.encode(address,"UTF-8");
            String reqUrl = apiURL + addr;

            URL url = new URL(reqUrl);
           HttpURLConnection con= (HttpURLConnection)url.openConnection();
           con.setRequestMethod("GET");
           con.setRequestProperty("X-NCP-APIGW-API-KEY-ID",clientId);
           con.setRequestProperty("X-NCP-APIGW-API-KEY",clientSecret);

           int responseCode = con.getResponseCode();
           BufferedReader br;

           if(responseCode==200){
               br=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));

           }else{
               br=new BufferedReader(new InputStreamReader(con.getErrorStream()));
           }
           String line;
           StringBuffer response = new StringBuffer();

           while((line = br.readLine())!=null){
               response.append(line);
           }
           br.close();

            JSONTokener tokener = new JSONTokener(response.toString());
            JSONObject object = new JSONObject(tokener);
            System.out.println(object.toString(2));

            //출력된 object의 내용을 확인하여 내가 필요로 하는 부분을 array화 하는 작업을 진행한다.

           JSONArray arr =  object.getJSONArray("addresses");

            JSONObject obj = (JSONObject) arr.get(0);
            System.out.print("address: "+obj.get("roadAddress"));
            System.out.print("지번: "+obj.get("jibunAddress"));
            System.out.print("경도: "+obj.get("x"));
            System.out.println("위도: "+obj.get("y"));



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
