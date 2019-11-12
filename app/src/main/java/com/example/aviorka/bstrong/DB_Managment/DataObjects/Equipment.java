package com.example.aviorka.bstrong.DB_Managment.DataObjects;

import java.io.Serializable;

public class Equipment implements Serializable {
    private boolean isSelected;
    private String name;
    private int dbId;
    private int viewId;

    public Equipment(boolean isSelected, String name, int dbId, int viewId){
        setSelected(isSelected);
        setName(name);
        setDbId(dbId);
        setViewId(viewId);
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public int getDbId(){return this.dbId;}
    public void setDbId(int dbId){this.dbId = dbId;}

    public int getViewId(){ return this.viewId;}
    public void setViewId(int viewId){this.viewId = viewId;}


}
