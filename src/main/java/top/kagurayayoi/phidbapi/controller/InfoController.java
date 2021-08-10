package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kagurayayoi.phidbapi.entities.AjaxResult;
import top.kagurayayoi.database.SQLiteHelper;
import top.kagurayayoi.phidbapi.conf.Setup;
import top.kagurayayoi.phidbapi.entities.ExceptionResult;
import top.kagurayayoi.phidbapi.entities.Info;
import java.sql.ResultSet;

// Info Controller

@RestController
public class InfoController {

    private AjaxResult result;
    private SQLiteHelper helper;
    private Info info;

    @GetMapping({"/api/", "/api/info", "/api/Info"})
    @ResponseBody
    public ResponseEntity<AjaxResult> Info() {
        this.Init();
        try {
            helper.connection();
            ResultSet rs = helper.selectAll("main.Info", null);
            while (rs.next()){
                info.setId(rs.getInt(Info.columnName[0]));
                info.setDatabase_Version(rs.getString(Info.columnName[1]));
                info.setAuthor(rs.getString(Info.columnName[2]));
                info.setPhigros_Version(rs.getString(Info.columnName[3]));
                info.setTotal(rs.getInt(Info.columnName[4]));
                info.setReference(rs.getString(Info.columnName[5]));
                info.setReference_Author(rs.getString(Info.columnName[6]));
            }
            result.setResultObj(info);
            helper.close();
            return ResponseEntity.ok().body(result);
        }catch (Exception ex) {
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
        info = new Info();
    }
}