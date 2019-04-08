package com.ipi;

import com.ipi.domain.GlcmData;
import com.ipi.domain.KnnData;
import com.ipi.domain.KnnPoint;
import com.ipi.inputmodule.ReaderModule;
import com.ipi.solve.Solve;
import com.ipi.tests.ConfusionMatrix;
import com.ipi.tests.FMeasure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            GlcmData glcmData = ReaderModule.read();
            KnnData knnData = Solve.getKnnData(glcmData);
            List<KnnPoint> training = new ArrayList<>();
            List<KnnPoint> test = new ArrayList<>();
            for (List<List<KnnPoint>> pair : knnData.getAsList()) {
                training.addAll(pair.get(0));
                test.addAll(pair.get(1));
            }
            // O primeiro teste não deve influir no segundo
            ConfusionMatrix.doTheTest(new ArrayList<>(training), new ArrayList<>(test));
            FMeasure.doTheTest(new ArrayList<>(training), new ArrayList<>(test));

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }


}
