package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kagurayayoi.logger.Logger;
import top.kagurayayoi.phidbapi.entities.AjaxResult;

// 该类用于测试
// Test Controller

@RestController
public class TestController {

    private AjaxResult result = new AjaxResult();

    @GetMapping({"/api/test"})
    @ResponseBody
    public ResponseEntity<AjaxResult> Info() {
        this.Init();
        result.setLocation("/api/test");

        result.setMessage("该Api用于开发时测试");

        Logger.Debug(this.getClass(), "Controller:Test", "Request Call");
        return ResponseEntity.ok().body(result);
    }

    private void Init() {
        result = new AjaxResult();
    }
}
