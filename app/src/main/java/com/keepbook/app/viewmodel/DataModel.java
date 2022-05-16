package com.keepbook.app.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.keepbook.app.model.vo.BillVO;

import java.util.Map;

public class DataModel extends ViewModel {
    private MutableLiveData<Map<String,BillVO>> data = new MutableLiveData<>();
    private MutableLiveData<Integer> pos = new MutableLiveData<>();

    public MutableLiveData<Integer> getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos.setValue(pos);
    }

    public MutableLiveData<Map<String,BillVO>> getData() {
        return data;
    }

    public void setData(Map<String,BillVO>data) {
        this.data.setValue(data);;
    }

}
