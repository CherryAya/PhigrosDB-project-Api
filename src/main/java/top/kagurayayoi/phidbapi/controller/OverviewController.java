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
import top.kagurayayoi.phidbapi.entities.ExceptionResult;
import top.kagurayayoi.phidbapi.entities.Overview;
import java.sql.ResultSet;
import java.util.ArrayList;

// Overview Controller

@RestController
public class OverviewController {

    private AjaxResult result;
    private SQLiteHelper helper;
    private ArrayList<Overview> list;

    @GetMapping({"/api/Overview", "/api/overview"})
    @ResponseBody
    public ResponseEntity<AjaxResult> Overview() {
        this.Init();
        result.setLocation("/api/overview");
        try {
            helper.connection();
            ResultSet rs = helper.selectAll("main.Overview", null);
            while (rs.next()){
                Overview overview = new Overview();
                overview.setId(rs.getInt(Overview.columnName[0]));
                overview.setChapter(rs.getString(Overview.columnName[1]));
                overview.setName(rs.getString(Overview.columnName[2]));
                overview.setVersion(rs.getString(Overview.columnName[3]));
                overview.setAuthor(rs.getString(Overview.columnName[4]));
                list.add(overview);
            }
            result.setResultObj(list);
            helper.close();
            Logger.Info(this.getClass(), "Controller:Overview", "Request Call");
            return ResponseEntity.ok().body(result);
        }catch (Exception ex){
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            result.setMessage(ex.getMessage());
            result.setResultObj(new ExceptionResult().setStackTrace(ex.getStackTrace()));
            Logger.Exception(this.getClass(), "Controller:Overview", result.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }

    private void Init() {
        result = new AjaxResult();
        helper = new SQLiteHelper(Setup.getDatabasePath());
        list = new ArrayList<>();
    }
}
