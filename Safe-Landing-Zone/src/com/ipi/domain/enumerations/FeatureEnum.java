package com.ipi.domain.enumerations;

public enum FeatureEnum {

    /**
     * Contraste
     */
    CT,

    /**
     * Correlação
     */
    CR,

    /**
     * Homogeneidade
     */
    HO,

    /**
     * Energia
     *
     */
    EN;


    public String getDescribe() {
        switch (this) {
            case CT:
                return "Contraste";
            case CR:
                return "Correlação";
            case HO:
                return "Homogeneidade";
            case EN:
                return "Energia";
            default:
                return "";
        }
    }

    public static FeatureEnum getInstance(int l) {
        switch (l) {
            case 0:
                return FeatureEnum.CT;
            case 1:
                return FeatureEnum.CR;
            case 2:
                return FeatureEnum.HO;
            case 3:
                return FeatureEnum.EN;
            default:
                return null;
        }
    }


}
