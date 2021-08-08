package top.kagurayayoi.phidbapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class AjaxResult {

    private int Code = HttpStatus.OK.value();
    private String Message = "操作成功";
    private Object resultObj = null;

    // static method
    public static AjaxResult thisObj(){
        return new AjaxResult();
    }

    // Constructor
    // Success
    public AjaxResult() { }
    // Error
    public AjaxResult(String Message, HttpStatus Code) {
        this.Code = Code.value();
        this.Message = Message;
    }

    // HttpStatusCode Getter/Setter
    public AjaxResult setCode(HttpStatus Code) {
        this.Code = Code.value();
        return this;
    }

    @JsonProperty(index = 1)
    public int getCode() {
        return Code;
    }

    // Message Getter/Setter
    public AjaxResult setMessage(String Message) {
        this.Message = Message;
        return this;
    }

    @JsonProperty(index = 2)
    public String getMessage() {
        return Message;
    }

    // ResultObject Getter/Setter
    public AjaxResult setResultObj(Object resultObj) {
        this.resultObj = resultObj;
        return this;
    }

    @JsonProperty(index = 3)
    public Object getResultObj() {
        return resultObj;
    }


}

