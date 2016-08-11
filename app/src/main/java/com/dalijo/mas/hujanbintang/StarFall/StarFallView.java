package com.dalijo.mas.hujanbintang.StarFall;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import com.dalijo.mas.hujanbintang.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hangga on 11/08/16.
 * Inspired from : https://github.com/HsuEn/SnowFall/blob/master/src/tw/widget/SnowFallView.java
 */
public class StarFallView extends View {
    private static Random sRandomGen = new Random();

    private int snow_flake_count = 10; // inisiasi awal
    private int duration_level = 5; //durationMillis
    private final List<Drawable> drawables = new ArrayList<Drawable>();
    private int[][] coords;
    private final Context mContext;
    private int size_level = 60;
    private int DrawableId = R.drawable.star;

    Drawable snow_flake;
    public StarFallView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        mContext = context;
    }

    public StarFallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        mContext = context;
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        if (snow_flake == null) snow_flake = mContext.getResources().getDrawable(DrawableId);
        Interpolator interpolator = new LinearInterpolator();
        snow_flake_count = Math.max(width, height) / 40;
        coords = new int[snow_flake_count][];
        drawables.clear();
        for (int i = 0; i < snow_flake_count; i++) {
            Animation animation = new TranslateAnimation(0, height / 10
                    - sRandomGen.nextInt(height / 5), 0, height + 30);
            animation.setDuration(duration_level * height + sRandomGen.nextInt(2 * height));
            animation.setRepeatCount(-1);
            animation.initialize(10, 10, 10, 10);
            animation.setInterpolator(interpolator);

            coords[i] = new int[] { sRandomGen.nextInt(width - 30), -60 };

            int size = size_level * height / 1000 + sRandomGen.nextInt(13);

            snow_flake.setBounds(0, 0, size, size);
            drawables.add(new AnimateDrawable(snow_flake, animation));
            animation.setStartOffset(sRandomGen.nextInt(20 * height));
            animation.startNow();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < snow_flake_count; i++) {
            Drawable drawable = drawables.get(i);
            canvas.save();
            canvas.translate(coords[i][0], coords[i][1]);
            drawable.draw(canvas);
            canvas.restore();
        }
        invalidate();
    }
}
