package com.ipi.domain.enumerations;

public enum LabelEnum {

    /**
     * Safe
     */
    SA,

    /**
     * Warning
     */
    WA,

    /**
     * Danger
     */
    DA,

    /**
     * Not Safe
     *
     */
    NS;


    public String getDescribe() {
        switch (this) {
            case SA:
                return "Safe";
            case WA:
                return "Warning";
            case DA:
                return "Danger";
            case NS:
                return "Not Safe";
            default:
                return "";
        }
    }

    public static LabelEnum getInstance(int l) {
        switch (l) {
            case 0:
                return LabelEnum.SA;
            case 1:
                return LabelEnum.WA;
            case 2:
                return LabelEnum.DA;
            case 3:
                return LabelEnum.NS;
            default:
                return null;
        }
    }

    public int getIntValue() {
        switch (this) {
            case SA:
                return 0;
            case WA:
                return 1;
            case DA:
                return 2;
            case NS:
                return 3;
            default:
                return -1;
        }
    }


}
