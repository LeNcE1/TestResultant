package com.lence.testresultant;

import com.lence.testresultant.model.StocksModel;

public interface Mvp {
    void refreshList(StocksModel body);
    void showMessage(String message);
}
