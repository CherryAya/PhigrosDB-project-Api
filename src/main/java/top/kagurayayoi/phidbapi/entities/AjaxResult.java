package top.kagurayayoi.phidbapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class AjaxResult {

    private Integer Code = HttpStatus.OK.value();
    private String Location = "/";
    private String Message = "操作成功 | Succeed";
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
    public Integer getCode() {
        return Code;
    }

    // Location Getter/Setter
    public AjaxResult setLocation(String location) {
        this.Location = location;
        return this;
    }

    @JsonProperty(index = 2)
    public String getLocation() {
        return this.Location;
    }

    // Message Getter/Setter
    public AjaxResult setMessage(String Message) {
        this.Message = Message;
        return this;
    }

    @JsonProperty(index = 3)
    public String getMessage() {
        return Message;
    }

    // ResultObject Getter/Setter
    public AjaxResult setResultObj(Object resultObj) {
        this.resultObj = resultObj;
        return this;
    }

    @JsonProperty(index = 4)
    public Object getResultObj() {
        return resultObj;
    }

}

