package top.kagurayayoi.phidbapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

// 通用难度定数表模型

public class GeneralEntity extends BaseEntity{

    public final static String[] columnName = new String[]{"id", "Name", "EZ", "HD", "IN", "AT", "Legacy"};
    private String Name;
    private Object EZ, HD, IN, AT, Legacy;

    @JsonProperty(index = 2)
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @JsonProperty(index = 3)
    public Object getEZ() {
        return EZ;
    }

    public void setEZ(Object EZ) {
        this.EZ = EZ;
    }

    @JsonProperty(index = 4)
    public Object getHD() {
        return HD;
    }

    public void setHD(Object HD) {
        this.HD = HD;
    }

    @JsonProperty(index = 5)
    public Object getIN() {
        return IN;
    }

    public void setIN(Object IN) {
        this.IN = IN;
    }

    @JsonProperty(index = 6)
    public Object getAT() {
        return AT;
    }

    public void setAT(Object AT) {
        this.AT = AT;
    }

    @JsonProperty(index = 7)
    public Object getLegacy() {
        return Legacy;
    }

    public void setLegacy(Object legacy) {
        Legacy = legacy;
    }
}
