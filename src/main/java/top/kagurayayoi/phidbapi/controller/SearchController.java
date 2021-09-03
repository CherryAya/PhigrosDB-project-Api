package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kagurayayoi.database.SQLiteHelper;
import top.kagurayayoi.logger.Logger;
import top.kagurayayoi.phidbapi.conf.Setup;
import top.kagurayayoi.phidbapi.entities.AjaxResult;
import top.kagurayayoi.phidbapi.entities.ExceptionResult;
import top.kagurayayoi.phidbapi.entities.SearchSong;
import java.sql.ResultSet;
import java.util.Objects;

@RestController
public class SearchController {

    // 成员变量
    private AjaxResult result;
    private SQLiteHelper helper;
    private SearchSong song;

    @GetMapping({"/api/search"})
    @ResponseBody
    public ResponseEntity<AjaxResult> Search(@RequestParam(name = "name") String Song) {
        this.Init();
        result.setLocation("/api/search");
        if (Objects.equals(Song, "")) {
            result.setMessage("Song is null");
            result.setCode(HttpStatus.NOT_FOUND);
            Logger.Info(this.getClass(), "Controller:Chapter", "Request Call");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        try {
            helper.connection();
            ResultSet rs = helper.selectAll("main.Overview", "Name like '" + Song + "'");
            while (rs.next()) {
                song.setChapter(rs.getString("Chapter"));
                song.setName(rs.getString("Name"));
                song.setAdd_version(rs.getString("Version"));
                song.setAuthor(rs.getString("Author"));
                song.setIllustration(rs.getString("Illustration"));
            }
            rs = helper.selectAll("main.'" + song.getChapter() + "'", "Name like '" + song.getName() + "'");
            while (rs.next()) {
                song.setEZ(rs.getObject("EZ"));
                song.setHD(rs.getObject("HD"));
                song.setIN(rs.getObject("IN"));
                song.setAT(rs.getObject("AT"));
                song.setLegacy(rs.getObject("Legacy"));
            }
            result.setResultObj(song);
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

    private void Init() {
        result = new AjaxResult();
        helper = new SQLiteHelper(Setup.getDatabasePath());
        song = new SearchSong();
    }
}
