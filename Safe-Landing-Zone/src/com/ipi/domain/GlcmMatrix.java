package com.ipi.domain;

import java.util.ArrayList;
import java.util.List;

public class GlcmMatrix {

    private int d;

    private int angle;

    private List<List<Double>> elements;

    private Double length;

    private Feature feature;

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public List<List<Double>> getElements() {
        return elements;
    }

    public void setElements(List<List<Double>> elements) {
        this.elements = elements;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public GlcmMatrix(int d, int angle, Double length) {
        this.d = d;
        this.angle = angle;
        this.length = length;
    }

    public void initializeElements() {
        if (this.length > 0) {
            this.elements = new ArrayList<>();
            for (int i = 0; i <= this.length; i++){
                List<Double> jValues = new ArrayList<>();
                for (int j = 0; j <= this.length; j++) {
                    jValues.add(0.0);
                }
                this.elements.add(jValues);
            }
        }
    }

    public void incrementElement(int i, int j) {
        this.elements.get(i).set(j, this.elements.get(i).get(j) + 1.0);
    }

}
