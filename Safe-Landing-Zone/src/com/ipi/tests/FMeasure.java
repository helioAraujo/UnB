package com.ipi.tests;

import com.ipi.classifier.Knn;
import com.ipi.domain.KnnPoint;
import com.ipi.domain.enumerations.LabelEnum;

import java.util.ArrayList;
import java.util.List;

public class FMeasure {

    public static void doTheTest(List<KnnPoint> training, List<KnnPoint> tests) {
        training.forEach( f -> {
            if (!f.getType().equals(LabelEnum.SA)) {
                f.setType(LabelEnum.NS);
            }
        });
        int safe = 0;
        int falseSafe = 0;
        int falseNotSafe = 0;
        for (KnnPoint test : tests) {
            LabelEnum result = Knn.classify(training, test);
            if (result.equals(LabelEnum.SA)) {
                safe ++;
            }

            if (!result.equals(test.getType())) {
                training.add(test);
                if (result.equals(LabelEnum.SA)) {
                    falseSafe ++;
                } else {
                    falseNotSafe ++;
                }
            }
        }
        Double precision = ((double) (safe - falseSafe) ) / ((double)(tests.size() - falseSafe - falseNotSafe));
        Double recall = ((double) (safe -falseSafe)) / ((double)(safe));
        Double fmeasure = 2 * (precision * recall) / (precision + recall);
        System.out.println("FMeasure: " + fmeasure);
    }


}
