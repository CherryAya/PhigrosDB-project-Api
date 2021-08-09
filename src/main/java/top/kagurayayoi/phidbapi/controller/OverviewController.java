package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kagurayayoi.phidbapi.conf.Setup;
import top.kagurayayoi.phidbapi.database.SQLiteHelper;
import top.kagurayayoi.phidbapi.entities.AjaxResult;
import top.kagurayayoi.phidbapi.entities.ExceptionResult;
import top.kagurayayoi.phidbapi.entities.Overview;
import java.sql.ResultSet;
import java.util.ArrayList;

@RestController
public class OverviewController {
    @GetMapping("/Overview")
    public ResponseEntity<AjaxResult> Overview(){
        AjaxResult result = new AjaxResult();
        SQLiteHelper helper = new SQLiteHelper(Setup.database_path);
        ArrayList<Overview> list = new ArrayList<>();
        try {
            helper.connection();
            ResultSet rs = helper.select("main.Overview", Overview.columnName, null);
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
