package com.ipi.featureselection;

import com.ipi.domain.Correlation;
import com.ipi.domain.Feature;
import com.ipi.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;


public final class FeatureSelection {

    /*
     * @param:  caracteristicas obtidas da matriz de co-ocorrência
     * @param:  numero de caracteristicas a serem eliminadas
     * @return:  indices das caracteristicas a serem eliminadas
     */
    public static List<Integer> featureSelection(List<Feature> features, int d) {
        List<Correlation> correlations = calculateCorrelationToPair(features);
        List<Correlation> copy = new ArrayList<>(correlations);
        List<Integer> toRemove = new ArrayList<>();
        for (int i = 0;  i < d; i ++ ) {
            List<Double> avarageOfCorrelation = calculateAvarageCorrelation(copy, (4.0 - i));
            OptionalDouble max = avarageOfCorrelation.stream().mapToDouble(Double::doubleValue).max();
            int remove = avarageOfCorrelation.indexOf(max.getAsDouble());
            copy.removeAll(copy.stream().filter(f -> f.getI() == remove).collect(Collectors.toList()));
            toRemove.add(remove);
        }
        return toRemove;
    }


    public static List<Correlation> calculateCorrelationToPair(List<Feature> features) {
        List<Correlation> r = new ArrayList<>();
        List<Double> avarages = new ArrayList<>();
        calculateAvarages(features, avarages);
        getCorrelation(r, avarages, features);
        return r;
    }

    private static List<Double> calculateAvarageCorrelation(List<Correlation> correlations, Double delta) {
        List<Double> avarages = new ArrayList<>();
        correlations.forEach( f -> f.setValue(Math.abs(f.getValue())));
        for (int i = 0; i < delta; i++) {
            final int jLine = i;
            List<Correlation> valuesToSum = correlations.stream().filter(f -> (f.getI() == jLine))
                    .collect(Collectors.toList());
            Double value = Math.pow(delta, -1.0) *
                    valuesToSum.stream().mapToDouble(Correlation::getValue).sum();
            avarages.add(value);
        }
        return avarages;
    }

    private static void getCorrelation(List<Correlation> r, List<Double> averages, List<Feature> features) {
        int n = features.size();
        for (int[] pair : Constants.PAIRS) {
            int i = pair[0];
            int j = pair[1];
            Double xiXj = 0.0;
            Double xiSquare = 0.0;
            Double xjSquare = 0.0;
            for (Feature feature : features) {
                xiXj += feature.getFeatureByIndex(i) * feature.getFeatureByIndex(j);
                xiSquare += Math.pow(feature.getFeatureByIndex(i), 2.0);
                xjSquare += Math.pow(feature.getFeatureByIndex(j), 2.0);
            }
            Double numerator = (xiXj - n * averages.get(i) * averages.get(j));
            Double denominator = Math.sqrt((xiSquare - n * Math.pow(averages.get(i), 2.0)) *
                    (xjSquare - n * Math.pow(averages.get(j), 2.0)));
            Double result = numerator / denominator;
            r.add(new Correlation(i, j, result));
        }

    }

    private static void calculateAvarages(List<Feature> features, List<Double> avarages) {
        Double sumContrastAvarage = 0.0;
        Double sumCorrelationAvarage = 0.0;
        Double sumEnergyAvarage = 0.0;
        Double sumHomogeneityAvarage = 0.0;
        for (Feature feature : features) {
            sumContrastAvarage += feature.getContrast();
            sumCorrelationAvarage += feature.getCorrelation();
            sumEnergyAvarage += feature.getEnergy();
            sumHomogeneityAvarage += feature.getHomogeneity();
        }
        avarages.add(sumContrastAvarage / features.size());
        avarages.add(sumCorrelationAvarage / features.size());
        avarages.add(sumEnergyAvarage / features.size());
        avarages.add(sumHomogeneityAvarage / features.size());

    }

}
