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
import top.kagurayayoi.phidbapi.entities.Difficulty;
import top.kagurayayoi.phidbapi.entities.ExceptionResult;
import java.sql.ResultSet;
import java.util.ArrayList;

// Difficulty Controller

@RestController
public class DifficultyController {

    private AjaxResult result;
    private SQLiteHelper helper;
    private ArrayList<Difficulty> list;

    @GetMapping({"/api/Difficulty", "/api/difficulty"})
    @ResponseBody
    public ResponseEntity<AjaxResult> Difficulty() {
        this.Init();
        result.setLocation("/api/difficulty");
        try {
            helper.connection();
            ResultSet rs = helper.selectAll("main.Difficulty", null);
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
            Logger.Info(this.getClass(), "Controller:Difficulty", "Request Call");
            return ResponseEntity.ok().body(result);
        }catch (Exception ex){
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            result.setMessage(ex.getMessage());
            result.setResultObj(new ExceptionResult().setStackTrace(ex.getStackTrace()));
            Logger.Exception(this.getClass(), "Controller:Difficulty", result.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }

    private void Init() {
        result = new AjaxResult();
        helper = new SQLiteHelper(Setup.getDatabasePath());
        list = new ArrayList<>();
    }
}
