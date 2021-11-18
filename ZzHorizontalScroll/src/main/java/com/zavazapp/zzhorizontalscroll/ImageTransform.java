package com.zavazapp.zzhorizontalscroll;

public enum ImageTransform {
    CIRCULAR (1),
    SQUARE_CIRCULAR (2),
    SQUARE (3);

    private int transformCode;
    ImageTransform(int i) {
        this.transformCode = i;
    }

    public int getTransformCode(){
        return this.transformCode;
    }
}
