package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kagurayayoi.phidbapi.entities.AjaxResult;
import top.kagurayayoi.phidbapi.database.SQLiteHelper;
import top.kagurayayoi.phidbapi.conf.Setup;
import top.kagurayayoi.phidbapi.entities.ExceptionResult;
import top.kagurayayoi.phidbapi.entities.Info;

import java.sql.ResultSet;

@RestController
public class InfoController {
    @GetMapping({"/", "/info"})
    public ResponseEntity<AjaxResult> Info() {
        AjaxResult result = new AjaxResult();
        SQLiteHelper helper = new SQLiteHelper(Setup.database_path);
        Info info = new Info();
        try {
            helper.connection();
            ResultSet rs = helper.select("main.Info", Info.columnName, null);
            while (rs.next()){
                info.setId(rs.getInt("id"));
                info.setDatabase_Version(rs.getString("Database_Version"));
                info.setAuthor(rs.getString("Author"));
                info.setPhigros_Version(rs.getString("Phigros_Version"));
                info.setTotal(rs.getInt("Total"));
                info.setReference(rs.getString("Reference"));
                info.setReference_Author(rs.getString("Reference_Author"));
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
}