package com.zavazapp.zzhorizontalscroll;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

public class Utils {

    public static Point getScreenDimen(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Point p = new Point();
        p.set(displayMetrics.widthPixels, displayMetrics.heightPixels);
        return p;
    }

}
