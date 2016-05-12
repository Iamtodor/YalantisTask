package com.todor.yalantistask.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

public class StrokeTransform implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {
        int w = source.getWidth();
        int h = source.getHeight();

        int radius = Math.min(h / 2, w / 2);
        Bitmap output = Bitmap.createBitmap(w + 8, h + 8, Bitmap.Config.ARGB_8888);
        Paint p = new Paint();
        p.setAntiAlias(true);
        Canvas c = new Canvas(output);
        c.drawBitmap(source, 4, 4, p);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.WHITE);
        p.setStrokeWidth(2);
        c.drawCircle((w / 2) + 4, (h / 2) + 4, radius, p);
        source.recycle();
        return output;
    }

    @Override
    public String key() {
        return "stroke";
    }

}
