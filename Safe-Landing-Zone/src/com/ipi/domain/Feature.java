package com.ipi.domain;

import java.util.ArrayList;
import java.util.List;

public class Feature {


    private Double contrast;

    private Double correlation;

    private Double energy;

    private Double homogeneity;

    public Double getContrast() {
        return contrast;
    }

    public void setContrast(Double contrast) {
        this.contrast = contrast;
    }

    public Double getCorrelation() {
        return correlation;
    }

    public void setCorrelation(Double correlation) {
        this.correlation = correlation;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getHomogeneity() {
        return homogeneity;
    }

    public void setHomogeneity(Double homogeneity) {
        this.homogeneity = homogeneity;
    }

    public Double getFeatureByIndex(int i) {
        switch (i) {
            case 0:
                return contrast;
            case 1:
                return correlation;
            case 2:
                return energy;
            case 3:
                return homogeneity;
            default:
                return null;
        }
    }

    public List<Double> getAsList() {
        List<Double> features = new ArrayList<>();
        features.add(contrast);
        features.add(correlation);
        features.add(energy);
        features.add(homogeneity);
        return features;
    }
}
