package com.ipi.domain;

public class Correlation {

    private int i;

    private int j;

    private Double value;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Correlation() {
    }

    public Correlation(int i, int j, Double value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }
}
