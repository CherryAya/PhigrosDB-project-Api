package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.kagurayayoi.database.SQLiteHelper;
import top.kagurayayoi.logger.Logger;
import top.kagurayayoi.phidbapi.conf.Setup;
import top.kagurayayoi.phidbapi.entities.AjaxResult;
import top.kagurayayoi.phidbapi.entities.ExceptionResult;
import top.kagurayayoi.phidbapi.entities.GeneralEntity;
import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<AjaxResult> Chapter(@PathVariable("ChapterName") String ChapterName, HttpServletResponse response) {
        this.Init();
        result.setLocation("/api/chapter/" + ChapterName);
        try {
            switch (ChapterName.toLowerCase()) {
                case "one":
                case "two":
                case "three":
                    response.sendRedirect("/api/chapter/legacy");
                    return ResponseEntity.status(301).body(result.setCode(HttpStatus.MOVED_PERMANENTLY).setMessage("Moved"));
                default:
                    break;
            }
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
            Logger.Info(this.getClass(), "Controller:Chapter", "Request Call");
            return ResponseEntity.ok().body(result);
        }catch (Exception ex){
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            result.setMessage(ex.getMessage());
            result.setResultObj(new ExceptionResult().setStackTrace(ex.getStackTrace()));
            Logger.Exception(this.getClass(), "Controller:Chapter", result.getMessage());
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
            Logger.Info(this.getClass(), "Controller:ChapterEx", "Request Call");
            return ResponseEntity.ok().body(result);
        }catch (Exception ex){
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            result.setMessage(ex.getMessage());
            result.setResultObj(new ExceptionResult().setStackTrace(ex.getStackTrace()));
            Logger.Exception(this.getClass(), "Controller:ChapterEx", result.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }

    private void Init() {
        result = new AjaxResult();
        helper = new SQLiteHelper(Setup.getDatabasePath());
        list = new ArrayList<>();
    }
}
