package com.codembeded.ngo.models;

public class ModelRead {
    String number;
    boolean check;

    public ModelRead(String number, boolean check) {
        this.number = number;
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }



    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
