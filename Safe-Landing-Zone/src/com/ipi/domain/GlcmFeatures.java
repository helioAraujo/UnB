package com.ipi.domain;

import com.ipi.domain.enumerations.LabelEnum;

import java.util.ArrayList;
import java.util.List;

public class GlcmFeatures {

    private GlcmMatrix zeroDegree;

    private GlcmMatrix fortyFiveDegree;

    private GlcmMatrix ninetyDegree;

    private GlcmMatrix oneHundredThirtyFiveDegre;

    private LabelEnum type;

    Double length;

    int d;

    public GlcmMatrix getZeroDegree() {
        return zeroDegree;
    }

    public void setZeroDegree(GlcmMatrix zeroDegree) {
        this.zeroDegree = zeroDegree;
    }

    public GlcmMatrix getFortyFiveDegree() {
        return fortyFiveDegree;
    }

    public void setFortyFiveDegree(GlcmMatrix fortyFiveDegree) {
        this.fortyFiveDegree = fortyFiveDegree;
    }

    public GlcmMatrix getNinetyDegree() {
        return ninetyDegree;
    }

    public void setNinetyDegree(GlcmMatrix ninetyDegree) {
        this.ninetyDegree = ninetyDegree;
    }

    public GlcmMatrix getOneHundredThirtyFiveDegre() {
        return oneHundredThirtyFiveDegre;
    }

    public void setOneHundredThirtyFiveDegre(GlcmMatrix oneHundredThirtyFiveDegre) {
        this.oneHundredThirtyFiveDegre = oneHundredThirtyFiveDegre;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public LabelEnum getType() {
        return type;
    }

    public void setType(LabelEnum type) {
        this.type = type;
    }

    public GlcmFeatures(int d, Double length) {
        this.length = length;
        this.d = d;
        this.zeroDegree = new GlcmMatrix(d, 0, length);
        this.fortyFiveDegree = new GlcmMatrix(d, 45, length);
        this.ninetyDegree = new GlcmMatrix(d, 90, length);
        this.oneHundredThirtyFiveDegre = new GlcmMatrix(d, 135, length);

        this.zeroDegree.initializeElements();
        this.fortyFiveDegree.initializeElements();
        this.ninetyDegree.initializeElements();
        this.oneHundredThirtyFiveDegre.initializeElements();

    }

    public GlcmMatrix getGlcmMatrixByAngle(int angle) {
        switch (angle) {
            case 0:
                return this.getZeroDegree();
            case 45:
                return this.getFortyFiveDegree();
            case 90:
                return this.getNinetyDegree();
            case 135:
                return this.getOneHundredThirtyFiveDegre();
            default:
                return null;
        }
    }

    public List<Feature> getGlcmMatrixByAngle(List<Feature> features) {
        List<Feature> featureList = new ArrayList<>();
        featureList.add(zeroDegree.getFeature());
        featureList.add(fortyFiveDegree.getFeature());
        featureList.add(ninetyDegree.getFeature());
        featureList.add(oneHundredThirtyFiveDegre.getFeature());
        return featureList;
    }

    @Override
    public String toString() {
        return "GlcmFeatures{" +
                "zeroDegree=" + zeroDegree +
                ", fortyFiveDegree=" + fortyFiveDegree +
                ", ninetyDegree=" + ninetyDegree +
                ", oneHundredThirtyFiveDegre=" + oneHundredThirtyFiveDegre +
                ", length=" + length +
                ", d=" + d +
                '}';
    }
}
