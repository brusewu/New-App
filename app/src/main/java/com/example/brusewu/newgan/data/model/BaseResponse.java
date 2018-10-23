package com.example.brusewu.newgan.data.model;

import java.util.List;

public class BaseResponse<T> {

    private List<T> results;

    public List<T> getResults(){
        return results;
    }
}
