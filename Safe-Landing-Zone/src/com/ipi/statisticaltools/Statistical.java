package com.ipi.statisticaltools;

import java.util.ArrayList;
import java.util.List;

public final class Statistical {

    /*
     * @param: matriz glcm da imagem
     * void: normaliza os dados recebidos
     */
    public static void normalize(List<List<Double>> glcm) {
        Double sum = 0.0;
        for (List<Double> values : glcm) {
            sum += values.stream().mapToDouble(Double::doubleValue).sum();
        }
        for (int i = 0; i < glcm.size(); i++) {
            for (int j = 0; j < glcm.size(); j++) {
                glcm.get(i).set(j, glcm.get(i).get(j) / sum);
            }
        }
    }

    /*
     * @param: probabilidades marginais
     * @return: retorna o desvio padrão entre
     */
    public static List<Double> getStandardDeviations(List<List<Double>> marginalProbabilities) {
        List<Double> deviations = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            deviations.add(Math.pow(getVariance(marginalProbabilities.get(i)), 0.5));
        }
        return deviations;
    }

    /*
     * @param: lista das probabilidades marginais
     * @return: variância
     */
    public static Double getVariance(List<Double> marginalProbability) {
        return getHope(marginalProbability, 2.0)
                - Math.pow(getHope(marginalProbability, 1.0), 2.0);
    }

    /*
     * @param: probabilidades marginais
     * @param: momento da esperança desejada
     * @return: esperança no momento @momentum das probabilidades  @marginalProbability
     */
    public static Double getHope(List<Double> marginalProbability, Double momentum) {
        Double hope = 0.0;
        for (int i = 0; i < marginalProbability.size(); i++) {
            hope += Math.pow(i, momentum) * marginalProbability.get(i);
        }
        return hope;
    }

    /*
     * @param:  valores de uma variável aleatória bidimensional
     * @return: probabilidades marginais de x e de y
     */
    public static List<List<Double>> getMarginalProbabilities(List<List<Double>> values) {
        List<List<Double>> marginalProbabilities = new ArrayList<>();
        List<Double> xProbability = new ArrayList<>();
        List<Double> yProbability = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            Double result = values.get(i).stream().mapToDouble(Double::doubleValue).sum();
            xProbability.add(result);
        }

        for (int j = 0; j < values.get(0).size(); j++) {
            Double result = 0.0;
            for (List<Double> value : values) {
                result += value.get(j);
            }
            yProbability.add(result);
        }
        marginalProbabilities.add(xProbability);
        marginalProbabilities.add(yProbability);
        return marginalProbabilities;
    }

}
