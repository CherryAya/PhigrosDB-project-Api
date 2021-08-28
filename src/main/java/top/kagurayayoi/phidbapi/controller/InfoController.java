package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kagurayayoi.logger.Logger;
import top.kagurayayoi.phidbapi.entities.AjaxResult;
import top.kagurayayoi.database.SQLiteHelper;
import top.kagurayayoi.phidbapi.conf.Setup;
import top.kagurayayoi.phidbapi.entities.ExceptionResult;
import top.kagurayayoi.phidbapi.entities.Info;
import top.kagurayayoi.version.PublishType;
import top.kagurayayoi.version.VersionTool;

import java.sql.ResultSet;

// Info Controller

@RestController
public class InfoController {

    private AjaxResult result;
    private SQLiteHelper helper;
    private Info info;

    @GetMapping({"/api", "/api/", "/api/info", "/api/Info"})
    @ResponseBody
    public ResponseEntity<AjaxResult> Info() {
        this.Init();
        result.setLocation("/api/");
        try {
            helper.connection();
            ResultSet rs = helper.selectAll("main.Info", null);
            while (rs.next()){
                info.setId(rs.getInt(Info.columnName[0]));
                info.setApi_Version(Setup.getApiVersion());
                info.setDatabase_Version(rs.getString(Info.columnName[1]));
                if (VersionTool.ControlVersion(1,1,0, PublishType.SNAPSHOT, Setup.getDatabaseVersion()))
                    info.setPublish_Type(rs.getString(Info.columnName[2]));
                else
                    info.setPublish_Type(PublishType.RELEASE.toString());
                info.setAuthor(rs.getString(Info.columnName[3]));
                info.setPhigros_Version(rs.getString(Info.columnName[4]));
                info.setTotal(rs.getInt(Info.columnName[5]));
                info.setReference(rs.getString(Info.columnName[6]));
                info.setReference_Author(rs.getString(Info.columnName[7]));
            }
            result.setResultObj(info);
            helper.close();
            Logger.Info(this.getClass(), "Controller:Info", "Request Call");
            return ResponseEntity.ok().body(result);
        }catch (Exception ex) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            result.setMessage(ex.getMessage());
            result.setResultObj(new ExceptionResult().setStackTrace(ex.getStackTrace()));
            Logger.Exception(this.getClass(), "Controller:Info", result.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }

    private void Init() {
        result = new AjaxResult();
        helper = new SQLiteHelper(Setup.getDatabasePath());
        info = new Info();
    }
}