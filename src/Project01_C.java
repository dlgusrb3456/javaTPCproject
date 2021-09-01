import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class Project01_C {
    public static void main(String args[]){
        //file 속 데이터 받아오기 json인 경우.
        String src = "info.jaon";
        //IO->Stream(스트림);
        //현재 이 클래스가 만들어진 곳에서 src(파일이름)과 연결되어 있는 객체를 io로 받아오기.
        InputStream is = Project01_C.class.getResourceAsStream(src); // 파일 연결 완료.
        if(is==null){
            throw new NullPointerException("Cannot find src file");
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONObject object= new JSONObject(tokener);
        JSONArray students = object.getJSONArray("students");
        for(int i=0;i<students.length();i++){
            JSONObject studentone = (JSONObject)students.get(i);
            System.out.print(studentone.get("name")+"\t");
            System.out.print(studentone.get("adress")+"\t");
            System.out.println(studentone.get("phone")+"\t");
        }
    }
}
