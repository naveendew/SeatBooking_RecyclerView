package com.autoinfini.redbusui;

public abstract class AbstractItem {

    public static final int TYPE_CENTER = 0;
    public static final int TYPE_EDGE = 1;
    public static final int TYPE_EMPTY = 2;
    public static final int TYPE_BOOKED = 3;

    private String label;


    public AbstractItem(String label) {
        this.label = label;
    }


    public String getLabel() {
        return label;
    }

    abstract public int getType();




}
