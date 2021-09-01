import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.awt.image.DataBufferDouble;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.Date;

public class Project01_E {
    public static void map_service(String point_x, String point_y, String address) {
        String URL_STATICMAP="https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
        try{
            String pos = URLEncoder.encode(point_x+" "+point_y,"UTF-8");
            String url = URL_STATICMAP;
            url += "center="+point_x+","+point_y;
            url+="&level=16&w=700&h=500";
            url += "&markers=type:t|size:mid|pos:"+pos+"|label"+URLEncoder.encode(address,"UTF-8");
            URL u = new URL(url);
            HttpURLConnection con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID","uck69eevv9");
            con.setRequestProperty("X-NCP-APIGW-API-KEY","VtgWSggn3uwrmrBtilS48cBULAAs98c9eGtbaLuG");
            int responsecode = con.getResponseCode();
            BufferedReader br;
            if(responsecode==200){
                InputStream is = con.getInputStream();
                int read=0;
                byte[] bytes = new byte[1024];
                String tempname = Long.valueOf(new Date().getTime()).toString();
                File f = new File(tempname+".jpg");
                f.createNewFile();
                OutputStream ou = new FileOutputStream(f);
                while((read=is.read(bytes))!= -1){
                    ou.write(bytes,0,read);
                }
                is.close();
            }else{
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while((inputLine=br.readLine())!=null){
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            }

        }catch(Exception e){
            System.out.println(e);
        }


    }

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

            String x=""; String y="";  String z="";
            while((line = br.readLine())!=null){
                response.append(line);
            }
            br.close();

            JSONTokener tokener = new JSONTokener(response.toString());
            JSONObject object = new JSONObject(tokener);
            System.out.println(object.toString(2));

            JSONArray arr =  object.getJSONArray("addresses");


            for(int i=0;i<arr.length();i++){
                JSONObject obj = (JSONObject) arr.get(0);
                System.out.print("address: "+obj.get("roadAddress"));
                System.out.print("지번: "+obj.get("jibunAddress"));
                System.out.print("경도: "+obj.get("x"));
                System.out.println("위도: "+obj.get("y"));
                x=(String) obj.get("x");
                y=(String) obj.get("y");
                z=(String) obj.get("roadAddress");
            }

            map_service(x,y,z);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
