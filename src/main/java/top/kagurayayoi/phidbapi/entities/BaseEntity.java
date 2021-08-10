package top.kagurayayoi.phidbapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

// 实体类基类

public class BaseEntity {

    private Integer id;

    @JsonProperty(index = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
