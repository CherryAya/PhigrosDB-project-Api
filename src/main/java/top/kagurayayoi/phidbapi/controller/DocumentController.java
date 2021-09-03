package top.kagurayayoi.phidbapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.kagurayayoi.logger.Logger;

@Controller
public class DocumentController {

    @RequestMapping("/api/doc")
    public String Document() {
        Logger.Info(this.getClass(),"Api-Document", "Request Call");
        return "/document";
    }

}
