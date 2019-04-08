package com.ipi.classifier;

import com.ipi.domain.KnnPoint;
import com.ipi.domain.enumerations.LabelEnum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class Knn {

    // Classifica usando o algoritmo knn com a distância euclidiana
    public static LabelEnum classify(List<KnnPoint> training, KnnPoint test) {
        int k = chooseK(training.size());
        List<KnnPoint> copy = new ArrayList<>(training);
        copy.forEach(f -> f.calculateDistance(test));
        copy = copy.stream()
                .sorted(Comparator.comparing(KnnPoint::getDistance)).collect(Collectors.toList());
        List<KnnPoint> kNN = copy.subList(0, k);
        return classify(kNN);
    }

    // Vê o label mais parecido baseado nos k vizinhos mais próximos
    private static LabelEnum classify(List<KnnPoint> knnPoints) {
        List<Long> values = new ArrayList<>();
        List<LabelEnum> labels = knnPoints.stream().map(KnnPoint::getType).collect(Collectors.toList());
        values.add(labels.stream().filter(f -> f.equals(LabelEnum.SA)).count());
        values.add(labels.stream().filter(f -> f.equals(LabelEnum.WA)).count());
        values.add(labels.stream().filter(f -> f.equals(LabelEnum.DA)).count());
        values.add(labels.stream().filter(f -> f.equals(LabelEnum.NS)).count());
        Long max = values.stream().sorted().collect(Collectors.toList()).get(values.size() - 1);
        return LabelEnum.getInstance(values.indexOf(max));
    }

    // Escolhe o k
    private static int chooseK(int size) {
        int k = (int) Math.log(size);
        if (k % 2 == 0) {
            if (k < size - 2) {
                return k + 1;
            } else {
                return k - 1;
            }
        }
        return k;
    }

}
