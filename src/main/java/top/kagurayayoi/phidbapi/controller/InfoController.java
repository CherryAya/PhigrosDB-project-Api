package top.kagurayayoi.phidbapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kagurayayoi.phidbapi.entities.AjaxResult;

@RestController
public class InfoController {
    @GetMapping({"/", "/info"})
    public ResponseEntity<AjaxResult> Info() {
        AjaxResult result = new AjaxResult()
        .setCode(HttpStatus.OK)
        .setResultObj("");
        return ResponseEntity.ok().body(result);
    }
}