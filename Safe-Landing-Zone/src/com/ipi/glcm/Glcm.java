package com.ipi.glcm;

import com.ipi.domain.GlcmFeatures;
import com.ipi.imagemanager.ImageManager;
import com.ipi.statisticaltools.Statistical;

import java.awt.image.BufferedImage;
import java.util.List;

public final class Glcm {

    /*
     * @param: imagem
     * @param: tamanho da janela
     * @return: características da imagem derivadas a partir da glcm
     */
    public static GlcmFeatures getGlcm(BufferedImage image, int d) {
        Double length = ImageManager.takeMaximumGrayLevel(image);
        GlcmFeatures features = makeGlcm(image, d,  length);
        normalizeGlcmFeatures(features);
        getStatisticalData(features);
        return features;
    }

    /*
     * @param:  dados relacionados a matriz de co-ocorrencia
     * @param: maior valor de cinza ou tamanho da matriz glcm da imagem
     * @return: Glcm da imagem
     */
    private static GlcmFeatures makeGlcm(BufferedImage image, int d, Double length) {
        GlcmFeatures features = new GlcmFeatures( d, length);
        NeighborCounter.countOccurrences(image, features);
        return features;
    }

    /*
     * @param:  dados relacionados a matriz de co-ocorrencia
     * @return: normaliza os dados para todos os ângulos
     */
    private static void normalizeGlcmFeatures(GlcmFeatures glcmFeatures) {
        Statistical.normalize(glcmFeatures.getZeroDegree().getElements());
        Statistical.normalize(glcmFeatures.getFortyFiveDegree().getElements());
        Statistical.normalize(glcmFeatures.getNinetyDegree().getElements());
        Statistical.normalize(glcmFeatures.getOneHundredThirtyFiveDegre().getElements());
    }

    /*
     * @param:  dados relacionados a matriz de co-ocorrencia
     * @return: deriva as informações estatísticas da matriz de co-ocorrência
     */
    private static void getStatisticalData(GlcmFeatures glcmFeatures) {
        FeatureExtraction.getStatisticalData(glcmFeatures.getZeroDegree());
        FeatureExtraction.getStatisticalData(glcmFeatures.getFortyFiveDegree());
        FeatureExtraction.getStatisticalData(glcmFeatures.getNinetyDegree());
        FeatureExtraction.getStatisticalData(glcmFeatures.getOneHundredThirtyFiveDegre());

    }





}