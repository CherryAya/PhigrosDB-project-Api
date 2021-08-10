package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kagurayayoi.database.SQLiteHelper;
import top.kagurayayoi.phidbapi.conf.Setup;
import top.kagurayayoi.phidbapi.entities.AjaxResult;
import top.kagurayayoi.phidbapi.entities.ExceptionResult;
import top.kagurayayoi.phidbapi.entities.Grade;
import java.sql.ResultSet;
import java.util.ArrayList;

@RestController
public class GradeController {

    private AjaxResult result;
    private SQLiteHelper helper;
    private ArrayList<Grade> list;

    @GetMapping({"/api/Grade", "/api/grade"})
    @ResponseBody
    public ResponseEntity<AjaxResult> Grade(){
        this.Init();
        result.setLocation("/api/grade");
        try {
            helper.connection();
            ResultSet rs = helper.select("main.Grade", Grade.columnName, null);
            while (rs.next()){
                Grade grade = new Grade();
                grade.setId(rs.getInt(Grade.columnName[0]));
                grade.setSymbol(rs.getString(Grade.columnName[1]));
                grade.setFraction(rs.getString(Grade.columnName[2]));
                list.add(grade);
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

    private void Init(){
        result = new AjaxResult();
        helper = new SQLiteHelper(Setup.database_path);
        list = new ArrayList<>();
    }
}
