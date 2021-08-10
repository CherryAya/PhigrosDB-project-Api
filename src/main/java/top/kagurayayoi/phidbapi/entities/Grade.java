package top.kagurayayoi.phidbapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Grade extends BaseEntity{

    public final static String[] columnName = new String[]{"id", "Symbol", "Fraction"};
    private String Symbol;
    private String Fraction;

    @JsonProperty(index = 2)
    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    @JsonProperty(index = 3)
    public String getFraction() {
        return Fraction;
    }

    public void setFraction(String fraction) {
        Fraction = fraction;
    }
}
