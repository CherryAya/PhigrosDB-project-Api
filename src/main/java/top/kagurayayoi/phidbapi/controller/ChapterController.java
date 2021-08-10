package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.kagurayayoi.database.SQLiteHelper;
import top.kagurayayoi.phidbapi.conf.Setup;
import top.kagurayayoi.phidbapi.entities.AjaxResult;
import top.kagurayayoi.phidbapi.entities.ExceptionResult;
import top.kagurayayoi.phidbapi.entities.GeneralEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

// Chapter & ChapterEx Controller

@RestController
public class ChapterController {

    // 成员变量
    private AjaxResult result;
    private SQLiteHelper helper;
    private ArrayList<GeneralEntity> list;

    @GetMapping({"/api/chapter/{ChapterName}", "/api/Chapter/{ChapterName}"})
    @ResponseBody
    public ResponseEntity<AjaxResult> Chapter(@PathVariable("ChapterName") String ChapterName) {
        this.Init();
        result.setLocation("/api/chapter/chapter-" + ChapterName);
        try {
            helper.connection();
            ResultSet rs = helper.selectAll("main.'Chapter-" + ChapterName+ "'", null);
            while (rs.next()){
                GeneralEntity general = new GeneralEntity();
                general.setId(rs.getInt(GeneralEntity.columnName[0]));
                general.setName(rs.getString(GeneralEntity.columnName[1]));
                general.setEZ(rs.getObject(GeneralEntity.columnName[2]));
                general.setHD(rs.getObject(GeneralEntity.columnName[3]));
                general.setIN(rs.getObject(GeneralEntity.columnName[4]));
                general.setAT(rs.getObject(GeneralEntity.columnName[5]));
                general.setLegacy(rs.getObject(GeneralEntity.columnName[6]));
                list.add(general);
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

    @GetMapping({"/api/chapter/ex/{ChapterName}", "/api/Chapter/Ex/{ChapterName}", "/api/Chapter/ex/{ChapterName}", "/api/chapter/Ex/{ChapterName}"})
    @ResponseBody
    public ResponseEntity<AjaxResult> ChapterEX(@PathVariable("ChapterName") String ChapterName) {
        this.Init();
        result.setLocation("/api/chapter/ex/" + ChapterName);
        try {
            helper.connection();
            ResultSet rs = helper.selectAll("main.'Chapter-Ex-" + ChapterName+ "'", null);
            while (rs.next()){
                GeneralEntity general = new GeneralEntity();
                general.setId(rs.getInt(GeneralEntity.columnName[0]));
                general.setName(rs.getString(GeneralEntity.columnName[1]));
                general.setEZ(rs.getObject(GeneralEntity.columnName[2]));
                general.setHD(rs.getObject(GeneralEntity.columnName[3]));
                general.setIN(rs.getObject(GeneralEntity.columnName[4]));
                general.setAT(rs.getObject(GeneralEntity.columnName[5]));
                general.setLegacy(rs.getObject(GeneralEntity.columnName[6]));
                list.add(general);
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

    private void Init() {
        result = new AjaxResult();
        helper = new SQLiteHelper(Setup.getDatabasePath());
        list = new ArrayList<>();
    }
}
