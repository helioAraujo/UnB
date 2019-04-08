package com.ipi.solve;

import com.ipi.domain.Feature;
import com.ipi.domain.GlcmData;
import com.ipi.domain.GlcmFeatures;
import com.ipi.domain.KnnData;
import com.ipi.domain.KnnPoint;
import com.ipi.domain.Point;
import com.ipi.domain.enumerations.FeatureEnum;
import com.ipi.featureselection.FeatureSelection;
import com.ipi.util.Constants;

import java.util.ArrayList;
import java.util.List;

public final class Solve {

    /*
     * Listas de listas representam aqui um par de dados no qual o elemento de indice zero contém informações
     * do conjunto de treino e o elemento de índice contém informações do conjunto de teste
     */


    /*
     * @param: glcmData dados de todas as imagens lidas, tanto pra teste como pra treino
     * @return KnnData os mesmos dados recebidos só que convertidos para um objeto do tipo KnnData
     */
    public static KnnData getKnnData(GlcmData glcmData) {
        KnnData knnData = new KnnData();
        for (int i = 0; i < glcmData.getAsList().size(); i++) {
            knnData.setGenericList(convertAPair(glcmData.getAsList().get(i)), i);
        }
        return knnData;
    }

    /*
     * @param: Lista que contém as características das imagens recebidas para treino e para testador
     *         glcmFeatures.get(0) -> Lista com dados de treino
     *         glcmFeatures.get(1) -> Lista com dados de teste
     * @return Extraí as informações mais relevantes e gera listas de treino(no índice 0) e de teste( no índice 1) no
     *         fomato KnnPoint
     */
    private static List<List<KnnPoint>> convertAPair(List<List<GlcmFeatures>> glcmFeatures) {
        List<List<Integer>> toIgnores = new ArrayList<>();
        for (int i = 0; i < Constants.ANGLES.length; i++) {
            List<Feature> features = extractedFeatures(glcmFeatures, Constants.ANGLES[i]);
            List<Integer> toIgnore = FeatureSelection.featureSelection(features, 1);
            toIgnores.add(toIgnore);
        }
        List<List<KnnPoint>> knnPoints = new ArrayList<>();
        for (List<GlcmFeatures> glcmFeature : glcmFeatures) {
            knnPoints.add(convertToListKnnPoint(glcmFeature, toIgnores));
        }
        return knnPoints;
    }

    /*
     * @param: Lista de caracaterísticas
     * @param: Lista de características altamente correlacionadas, que devem ser ignoradas, agrupadas por angulo
     * @return: Lista de pontos para classificação
     */
    private static List<KnnPoint> convertToListKnnPoint(List<GlcmFeatures> glcmFeatures, List<List<Integer>> toIgnores) {
        List<KnnPoint> knnPoints = new ArrayList<>();
        for (GlcmFeatures glcmFeature : glcmFeatures) {
            knnPoints.add(convertToKnnPoint(glcmFeature, toIgnores));
        }
        return knnPoints;
    }

    /*
     * @param: Características derivadas da imagem original
     * @param: Características altamente correlacionadas, que devem ser ignoradas
     * @return: Lista de pontos para classificação
     */
    private static KnnPoint convertToKnnPoint(GlcmFeatures glcmFeatures, List<List<Integer>> toIgnores) {
        KnnPoint knnPoint = new KnnPoint();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < Constants.ANGLES.length; i++) {
            List<Integer> toIgnore = toIgnores.get(i);
            for (int j = 0; j < glcmFeatures.getZeroDegree().getFeature().getAsList().size(); j++) {
                if (toIgnore.indexOf(j) < 0) {
                    Point point = new Point();
                    point.setValue(glcmFeatures.getZeroDegree().getFeature().getAsList().get(j));
                    point.setType(FeatureEnum.getInstance(j));
                    points.add(point);
                }
            }
        }
        knnPoint.setPoints(points);
        knnPoint.setType(glcmFeatures.getType());
        return knnPoint;
    }

    /*
     * @param: Lista com características do conjunto de treino e de teste
     * @param: Angulo analisado
     * @return: Lista com as características extraídas
     */
    private static List<Feature> extractedFeatures(List<List<GlcmFeatures>> pair, int angle) {
        List<Feature> features = new ArrayList<>();
        for (List<GlcmFeatures> glcmFeatures : pair) {
            for (GlcmFeatures glcmFeature : glcmFeatures) {
                features.add(glcmFeature.getGlcmMatrixByAngle(angle).getFeature());
            }
        }
        return features;
    }


}