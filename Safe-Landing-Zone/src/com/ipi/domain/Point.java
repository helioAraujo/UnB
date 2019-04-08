package com.ipi.domain;

import com.ipi.domain.enumerations.FeatureEnum;

public class Point {

    private Double value;

    private FeatureEnum type;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public FeatureEnum getType() {
        return type;
    }

    public void setType(FeatureEnum type) {
        this.type = type;
    }
}
