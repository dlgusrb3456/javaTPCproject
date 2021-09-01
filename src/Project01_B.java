import org.json.JSONArray;
import org.json.JSONObject;

public class Project01_B {
    public static void main(String[] args){
        JSONArray studentarr = new JSONArray();
        JSONObject students = new JSONObject();
        students.put("name","Carl");
        students.put("phone","010-3361-4263");
        students.put("adress","seoul");
       // System.out.println(students);
        studentarr.put(students);
        students = new JSONObject();
        students.put("name","dlgusrb");
        students.put("phone","010-3361-6571");
        students.put("adress","asdf");
        studentarr.put(students);
      //  System.out.println(studentarr);

        JSONObject object = new JSONObject();
        object.put("students",studentarr);
        System.out.println(object.toString(2));

        // Object 를 다시 Jsonarray 로 바꿔 사용하기.
    }
}
