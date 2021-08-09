package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kagurayayoi.phidbapi.conf.Setup;
import top.kagurayayoi.phidbapi.database.SQLiteHelper;
import top.kagurayayoi.phidbapi.entities.AjaxResult;
import top.kagurayayoi.phidbapi.entities.ChapterList;
import top.kagurayayoi.phidbapi.entities.ExceptionResult;
import java.sql.ResultSet;
import java.util.ArrayList;

@RestController
public class ChapterListController {
    @GetMapping("/ChapterList")
    @ResponseBody
    public ResponseEntity<AjaxResult> ChapterList(){
        AjaxResult result = new AjaxResult();
        SQLiteHelper helper = new SQLiteHelper(Setup.database_path);
        ArrayList<ChapterList> list = new ArrayList<>();
        try {
            helper.connection();
            ResultSet rs = helper.select("main.ChapterList", ChapterList.columnName, null);
            while (rs.next()){
                ChapterList chapterList = new ChapterList();
                chapterList.setId(rs.getInt(ChapterList.columnName[0]));
                chapterList.setName(rs.getString(ChapterList.columnName[1]));
                chapterList.setTitle(rs.getString(ChapterList.columnName[2]));
                chapterList.setTotal(rs.getString(ChapterList.columnName[3]));
                list.add(chapterList);
            }
            result.setResultObj(list);
            helper.close();
            return ResponseEntity.ok().body(result);
        }catch (Exception ex){
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            result.setMessage(ex.getMessage());
            result.setResultObj(new ExceptionResult().setStackTrace(ex.getStackTrace()));
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(result);
        }
    }
}
