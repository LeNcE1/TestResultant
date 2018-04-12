package com.lence.testresultant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stock {

@SerializedName("name")
@Expose
private String name;
@SerializedName("price")
@Expose
private Price price;
@SerializedName("percent_change")
@Expose
private Double percentChange;
@SerializedName("volume")
@Expose
private Integer volume;
@SerializedName("symbol")
@Expose
private String symbol;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Price getPrice() {
return price;
}

public void setPrice(Price price) {
this.price = price;
}

public Double getPercentChange() {
return percentChange;
}

public void setPercentChange(Double percentChange) {
this.percentChange = percentChange;
}

public Integer getVolume() {
return volume;
}

public void setVolume(Integer volume) {
this.volume = volume;
}

public String getSymbol() {
return symbol;
}

public void setSymbol(String symbol) {
this.symbol = symbol;
}

}
