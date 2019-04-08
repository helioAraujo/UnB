package com.ipi.inputmodule;

import com.ipi.domain.GlcmData;
import com.ipi.domain.GlcmFeatures;
import com.ipi.domain.enumerations.LabelEnum;
import com.ipi.glcm.Glcm;
import com.ipi.imagemanager.ImageManager;
import com.ipi.util.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ReaderModule {

    private static final String DEFAULT_INPUT_PATH = "src/com/ipi/data/images/";

    private static final String DEFAULT_FILE_TYPE = ".png";


    /*
     *Lê todas as imagens que estão na pasta/nome path e obtêm suas matrizes glcm
     * bem como as informações dela derivadas
     */
    public static List<GlcmFeatures> readAll(int begin, int end, String path, LabelEnum labelEnum) throws IOException {
        List<GlcmFeatures> images = new ArrayList<>();
        for (int i = begin; i <= end; i++) {
            System.out.println(i);
            String inputImage = path;
            if (i < 10) {
                inputImage = new String(inputImage.concat("0"));
            }
            inputImage = new String(inputImage.concat(i + "").concat(DEFAULT_FILE_TYPE));
            BufferedImage image = read(inputImage);
            image = ImageManager.toGrayScale(image);
            GlcmFeatures glcmFeatures = Glcm.getGlcm(image, 100);
            glcmFeatures.setType(labelEnum);
            images.add(glcmFeatures);
        }
        return images;
    }

    // Lê uma imagem no caminho path
    public static BufferedImage read(String path) throws IOException {
        BufferedImage imagem = ImageIO.read(new File(DEFAULT_INPUT_PATH + path));
        return imagem;
    }


    public static GlcmData read() throws IOException{
        GlcmData glcmData = new GlcmData();
        glcmData.setGenericList(readAsphalt(), 0);
        glcmData.setGenericList(readGrass(), 1);
        glcmData.setGenericList(readDanger(), 2);

        return glcmData;
    }

    private static List<List<GlcmFeatures>> readAsphalt() throws IOException{
        List<GlcmFeatures> training;
        List<GlcmFeatures> test;
        List<List<GlcmFeatures>> images = new ArrayList<>();
        training = readAll(1, 25, Constants.PATHS[0], LabelEnum.SA);
        test = readAll(26, 50, Constants.PATHS[0], LabelEnum.SA);
        images.add(training);
        images.add(test);
        return images;
    }

    private static List<List<GlcmFeatures>> readGrass() throws IOException{
        List<GlcmFeatures> training;
        List<GlcmFeatures> test;
        List<List<GlcmFeatures>> images = new ArrayList<>();
        training = readAll(1, 25, Constants.PATHS[1], LabelEnum.WA);
        test = readAll(26, 50, Constants.PATHS[2], LabelEnum.WA);
        images.add(training);
        images.add(test);
        return images;
    }

    private static List<List<GlcmFeatures>> readDanger() throws IOException{
        List<GlcmFeatures> training;
        List<GlcmFeatures> test;
        List<List<GlcmFeatures>> images = new ArrayList<>();
        training = readAll(1, 25, Constants.PATHS[3], LabelEnum.DA);
        test = readAll(26, 50, Constants.PATHS[3], LabelEnum.DA);
        images.add(training);
        images.add(test);
        return images;
    }


}
