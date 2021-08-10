package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kagurayayoi.phidbapi.conf.Setup;
import top.kagurayayoi.database.SQLiteHelper;
import top.kagurayayoi.phidbapi.entities.AjaxResult;
import top.kagurayayoi.phidbapi.entities.Difficulty;
import top.kagurayayoi.phidbapi.entities.ExceptionResult;

import java.sql.ResultSet;
import java.util.ArrayList;

@RestController
public class DifficultyController {
    @GetMapping({"/Difficulty", "/difficulty"})
    @ResponseBody
    public ResponseEntity<AjaxResult> Difficulty(){
        AjaxResult result = new AjaxResult();
        SQLiteHelper helper = new SQLiteHelper(Setup.database_path);
        ArrayList<Difficulty> list = new ArrayList<>();
        try {
            helper.connection();
            ResultSet rs = helper.select("main.Difficulty", Difficulty.columnName, null);
            while (rs.next()){
                Difficulty difficulty = new Difficulty();
                difficulty.setId(rs.getInt(Difficulty.columnName[0]));
                difficulty.setGrade(rs.getString(Difficulty.columnName[1]));
                difficulty.setLv(rs.getString(Difficulty.columnName[2]));
                difficulty.setFullName(rs.getString(Difficulty.columnName[3]));
                list.add(difficulty);
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