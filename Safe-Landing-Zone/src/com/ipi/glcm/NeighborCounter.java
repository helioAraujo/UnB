package com.ipi.glcm;

import com.ipi.domain.GlcmFeatures;
import com.ipi.domain.GlcmMatrix;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public final class NeighborCounter {

    /*
     * @param:  imagem
     * @param:  objeto para gerar a matriz de co-ocorrência
     * Gera matrizes de co-ocorrência nos ângulos definidos
     */
    public static void countOccurrences(BufferedImage image, GlcmFeatures matrix) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                List<Integer> current = makeList(i, j);
                countNeighborhood(image, matrix, current);
            }
        }
    }

    /*
     * @param:  imagem
     * @param:  objeto para gerar a matriz de co-ocorrência
     * @param:  posição atual
     * Conta as transições que ocorrerram entre os níveis de cinza nos diferentes ângulos da imagem
     */
    private static void countNeighborhood(BufferedImage image, GlcmFeatures matrix, List<Integer> current) {
        zeroDegreeCounter(image, current, matrix.getZeroDegree());
        fortyFiveDegreeCounter(image, current, matrix.getFortyFiveDegree());
        ninetyDegreeCounter(image, current, matrix.getNinetyDegree());
        oneHundredThirtyFiveCounter(image, current, matrix.getOneHundredThirtyFiveDegre());
    }

    /*
     * @param:  imagem
     * @param:  posição atual
     * @param:  objeto para gerar a matriz de co-ocorrência
     * Conta a vizinhança a zero graus
     */
    private static void zeroDegreeCounter(BufferedImage image, List<Integer> current, GlcmMatrix glcmMatrix) {
        if ((current.get(1) + glcmMatrix.getD()) < image.getWidth()) {
            int i = (new Color(image.getRGB(current.get(0), current.get(1))).getBlue());
            int j = (new Color(image.getRGB(current.get(0), (current.get(1) + glcmMatrix.getD()))).getBlue());
            glcmMatrix.incrementElement(i, j);
        }
    }

    /*
     * @param:  imagem
     * @param:  objeto para gerar a matriz de co-ocorrência
     * @param:  posição atual
     * Conta a vizinhança a quarenta e cinco graus
     */
    private static void fortyFiveDegreeCounter(BufferedImage image, List<Integer> current,
            GlcmMatrix glcmMatrix) {
        if (current.get(0) - glcmMatrix.getD() >=  0 && image.getWidth()  - current.get(1) > glcmMatrix.getD()) {
            int i = (new Color(image.getRGB(current.get(0), current.get(1))).getBlue());
            int j = (new Color(image.getRGB((current.get(0) - glcmMatrix.getD()), (current.get(1) + glcmMatrix.getD()))).getBlue());
            glcmMatrix.incrementElement(i, j);
        }
    }

    /*
     * @param:  imagem
     * @param:  objeto para gerar a matriz de co-ocorrência
     * @param:  posição atual
     * Conta a vizinhança a noventa graus
     */
    private static void ninetyDegreeCounter(BufferedImage image, List<Integer> current,
            GlcmMatrix glcmMatrix) {
        if (current.get(0) + glcmMatrix.getD() < image.getHeight()) {
            int i = (new Color(image.getRGB(current.get(0), current.get(1))).getBlue());
            int j = (new Color(image.getRGB(current.get(0) + glcmMatrix.getD(), current.get(1))).getBlue());
            glcmMatrix.incrementElement(i, j);
        }
    }

    /*
     * @param:  imagem
     * @param:  objeto para gerar a matriz de co-ocorrência
     * @param:  posição atual
     * Conta a vizinhança a cento e trinta e cinco graus
     */
    private static void oneHundredThirtyFiveCounter(BufferedImage image, List<Integer> current,
            GlcmMatrix glcmMatrix) {
        if (current.get(0) + glcmMatrix.getD() < image.getHeight() && current.get(1) + glcmMatrix.getD() < image.getWidth()) {
            int i = (new Color(image.getRGB(current.get(0), current.get(1))).getBlue());
            int j = (new Color(image.getRGB((current.get(0) + glcmMatrix.getD()), (current.get(1) + glcmMatrix.getD()))).getBlue());
            glcmMatrix.incrementElement(i, j);
        }
    }

    /*
     * @param:  i
     * @param:  j
     * @return: uma lista com os dois objetos recebidos
     */
    private static List<Integer> makeList(int i, int j) {
        List<Integer> positions = new ArrayList<>();
        positions.add(i);
        positions.add(j);
        return positions;
    }

}
