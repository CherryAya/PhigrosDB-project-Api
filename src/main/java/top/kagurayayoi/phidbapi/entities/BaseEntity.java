package top.kagurayayoi.phidbapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseEntity {

    private int id;

    @JsonProperty(index = 1)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
