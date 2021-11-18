package com.zavazapp.zzhorizontalscroll;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;


public class NoTransform implements Transformation {


    @Override
    public Bitmap transform(Bitmap source) {
        return source;
    }

    @Override
    public String key() {
        return "rounded_corners";
    }
}
