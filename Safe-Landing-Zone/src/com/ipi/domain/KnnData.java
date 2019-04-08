package com.ipi.domain;


import java.util.ArrayList;
import java.util.List;

public class KnnData {

    private List<KnnPoint> training;

    private List<KnnPoint> test;

    private List<List<KnnPoint>> safe;

    private List<List<KnnPoint>> warning;

    private List<List<KnnPoint>> danger;

    public List<List<KnnPoint>> getSafe() {
        return safe;
    }

    public void setSafe(List<List<KnnPoint>> safe) {
        this.safe = safe;
    }

    public List<List<KnnPoint>> getWarning() {
        return warning;
    }

    public void setWarning(
            List<List<KnnPoint>> warning) {
        this.warning = warning;
    }

    public List<List<KnnPoint>> getDanger() {
        return danger;
    }

    public void setDanger(
            List<List<KnnPoint>> danger) {
        this.danger = danger;
    }

    public List<KnnPoint> getTraining() {
        return training;
    }

    public void setTraining(List<KnnPoint> training) {
        this.training = training;
    }

    public List<KnnPoint> getTest() {
        return test;
    }

    public void setTest(List<KnnPoint> test) {
        this.test = test;
    }

    public void setGenericList(List<List<KnnPoint>> pair, int i) {
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

    public List<List<List<KnnPoint>>> getAsList() {
        List<List<List<KnnPoint>>> points = new ArrayList<>();
        points.add(safe);
        points.add(warning);
        points.add(danger);
        return points;

    }
}
