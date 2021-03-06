package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kagurayayoi.logger.Logger;
import top.kagurayayoi.phidbapi.conf.Setup;
import top.kagurayayoi.database.SQLiteHelper;
import top.kagurayayoi.phidbapi.entities.AjaxResult;
import top.kagurayayoi.phidbapi.entities.ChapterList;
import top.kagurayayoi.phidbapi.entities.ExceptionResult;
import java.sql.ResultSet;
import java.util.ArrayList;

// ChapterList Controller

@RestController
public class ChapterListController {

    private AjaxResult result;
    private SQLiteHelper helper;
    private ArrayList<ChapterList> list;

    @GetMapping({"/api/ChapterList", "/api/chapterlist", "/api/chapterList", "/api/Chapterlist"})
    @ResponseBody
    public ResponseEntity<AjaxResult> ChapterList() {
        this.Init();
        try {
            helper.connection();
            ResultSet rs = helper.selectAll("main.ChapterList", null);
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
            Logger.Info(this.getClass(), "Controller:ChapterList", "Request Call");
            return ResponseEntity.ok().body(result);
        }catch (Exception ex){
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            result.setMessage(ex.getMessage());
            result.setResultObj(new ExceptionResult().setStackTrace(ex.getStackTrace()));
            Logger.Exception(this.getClass(), "Controller:ChapterList", result.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }

    private void Init() {
        result = new AjaxResult();
        helper = new SQLiteHelper(Setup.getDatabasePath());
        list = new ArrayList<>();
    }
}
