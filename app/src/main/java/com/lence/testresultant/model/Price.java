package com.lence.testresultant.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Price {

@SerializedName("currency")
@Expose
private String currency;
@SerializedName("amount")
@Expose
private Double amount;

public String getCurrency() {
return currency;
}

public void setCurrency(String currency) {
this.currency = currency;
}

public Double getAmount() {
return amount;
}

public void setAmount(Double amount) {
this.amount = amount;
}

}

