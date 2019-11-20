package com.example.aviorka.bstrong.DB_Managment.DataObjects;

import java.io.Serializable;
import android.view.View;

public class Equipment implements Serializable {
    private boolean isSelected;
    private String name;
    private int dbId;
    private View view;
    private int viewId;

    public Equipment(boolean isSelected, String name, int dbId, int viewId){
        setSelected(isSelected);
        setName(name);
        setDbId(dbId);
        setViewId(viewId);
    }

    public Equipment(boolean isSelected, String name, int dbId, View view){
        setSelected(isSelected);
        setName(name);
        setDbId(dbId);
        setView(view);
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

    public View getView(){return this.view;}
    public void setView(View view){ this.view = view;}

    /**
     * hashCode returns the viewId since it is unique
     * @return
     */
    @Override
    public int hashCode(){
        return getViewId();
    }

    /**
     * Compare other equipment to this
     * if the other is null or not an instance of Equipment -> return null.
     * Otherwise, cast other to Equipment and compare thr hashcode
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if(other == null || !(other instanceof Equipment)) return false;
        Equipment e = (Equipment)other;
        return hashCode() == e.hashCode();

    }
}
