import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.infleran.BookDTO;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class Project01_A {
    public static  void main(String[] args){
        //Object -> JSON(String)

        BookDTO dto = new BookDTO("자바",21000,"에이콘",670);
        Gson g = new Gson();
        String json= g.toJson(dto);
       // System.out.println(json);
       BookDTO dto1= g.fromJson(json,BookDTO.class);
      // System.out.println(dto1);

       //
        List<BookDTO> list = new ArrayList<BookDTO>();
        list.add(new BookDTO("자바",21000,"에이콘",670));
        list.add(new BookDTO("자바1",21000,"에이콘1",670));
        list.add(new BookDTO("자바2",21000,"에이콘2",670));
        list.add(new BookDTO("자바3",21000,"에이콘3",670));
        String listJson = g.toJson(list);
       // System.out.println(listJson);


        //Json(String) -> Object(List<BookDTO>)
        //g.fromJson(listJson,List<BookDTO>.class); 안됨.
        List<BookDTO> list2 =  g.fromJson(listJson,new TypeToken<List<BookDTO>>(){}.getType());
        for(BookDTO bk : list2){
            System.out.println(bk);
        }
    }
}
