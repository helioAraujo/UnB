package com.ipi.tests;

import com.ipi.classifier.Knn;
import com.ipi.domain.KnnPoint;
import com.ipi.domain.enumerations.LabelEnum;

import java.util.ArrayList;
import java.util.List;

public final class ConfusionMatrix {

    public static void doTheTest(List<KnnPoint> classifier, List<KnnPoint> tester) {
        List<List<Integer>> confusionMatrix = initialiazeMatrix();

        for (KnnPoint test : tester) {
            LabelEnum result = Knn.classify(classifier, test);
            int i = test.getType().getIntValue();
            int j = result.getIntValue();
            if (i != j) {
                classifier.add(test);
            }
            confusionMatrix.get(i).set(j, confusionMatrix.get(i).get(j) + 1);
        }
        printMatrix(confusionMatrix);
    }

    private static void printMatrix(List<List<Integer>> confusionMatrix) {
        System.out.println("Matriz de Confusão");
        System.out.println("  00 01 02");
        for (int i = 0; i < confusionMatrix.size(); i++) {
            String line = "0" + i + " ";
            for (int j = 0; j < confusionMatrix.size(); j++) {
                if (confusionMatrix.get(i).get(j) < 10) {
                    line = new String(line.concat("0"+ confusionMatrix.get(i).get(j) + " "));
                } else {
                    line = new String(line.concat(confusionMatrix.get(i).get(j) + " "));
                }
            }
            System.out.println(line);
        }
    }

    private static List<List<Integer>> initialiazeMatrix()  {
        List<List<Integer>> confusionMatrix = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Integer> collumn = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                collumn.add(0);
            }
            confusionMatrix.add(collumn);
        }
        return confusionMatrix;
    }

}
