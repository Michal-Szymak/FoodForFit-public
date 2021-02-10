package com.objavieni.controller;

import java.util.List;

public class Ingredients {
    private List<String> list;
    private String listAsString;
    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "list=" + list +
                ", listAsString='" + listAsString + '\'' +
                '}';
    }
}
