package com.ipi.domain;

import com.ipi.glcm.Glcm;

import java.util.ArrayList;
import java.util.List;

public class GlcmData {

    private List<List<GlcmData>> training;

    private List<List<Glcm>> test;

    private List<List<GlcmFeatures>> safe;

    private List<List<GlcmFeatures>> warning;

    private List<List<GlcmFeatures>> danger;

    public List<List<GlcmFeatures>> getSafe() {
        return safe;
    }

    public void setSafe(List<List<GlcmFeatures>> safe) {
        this.safe = safe;
    }

    public List<List<GlcmFeatures>> getWarning() {
        return warning;
    }

    public void setWarning(List<List<GlcmFeatures>> warning) {
        this.warning = warning;
    }

    public List<List<GlcmFeatures>> getDanger() {
        return danger;
    }

    public void setDanger(List<List<GlcmFeatures>> danger) {
        this.danger = danger;
    }

    public List<List<GlcmData>> getTraining() {
        return training;
    }

    public void setTraining(List<List<GlcmData>> training) {
        this.training = training;
    }

    public List<List<Glcm>> getTest() {
        return test;
    }

    public void setTest(List<List<Glcm>> test) {
        this.test = test;
    }

    public void setGenericList(List<List<GlcmFeatures>> pair, int i) {
        switch (i) {
            case 0:
                this.safe = pair;
                break;
            case 1:
                this.warning = pair;
                break;
            case 2:
                this.danger = pair;
                break;
        }
    }

    public List<List<List<GlcmFeatures>>> getAsList() {
        List<List<List<GlcmFeatures>>> images = new ArrayList<>();
        images.add(safe);
        images.add(warning);
        images.add(danger);
        return images;

    }
}
