package com.ipi.domain;

import com.ipi.domain.enumerations.LabelEnum;

import java.util.List;

public class KnnPoint {

    List<Point> points;

    LabelEnum type;
    
    Double distance;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public LabelEnum getType() {
        return type;
    }

    public void setType(LabelEnum type) {
        this.type = type;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
    
    public void calculateDistance(KnnPoint knnPoint) {
        Double sum = 0.0;
        for (int i = 0; i < knnPoint.getPoints().size(); i++) {
            sum += Math.pow((knnPoint.getPoints().get(i).getValue() - points.get(i).getValue()), 2.0);
        }
        this.distance = Math.sqrt(sum);
    }
}
