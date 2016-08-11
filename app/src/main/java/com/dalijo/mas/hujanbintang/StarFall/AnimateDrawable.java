package com.dalijo.mas.hujanbintang.StarFall;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;

/**
 * Created by hangga on 11/08/16.
 * Inspired from https://raw.githubusercontent.com/HsuEn/SnowFall/master/src/tw/widget/AnimateDrawable.java
 */
public class AnimateDrawable extends ProxyDrawable {

    private Animation mAnimation;
    private Transformation mTransformation = new Transformation();

    public AnimateDrawable(Drawable target, Animation animation) {
        super(target);
        mAnimation = animation;
    }

    @Override
    public void draw(Canvas canvas) {
        Drawable dr = getProxy();
        if (dr != null) {
            int sc = canvas.save();
            Animation anim = mAnimation;
            if (anim != null) {
                anim.getTransformation(
                        AnimationUtils.currentAnimationTimeMillis(),
                        mTransformation);
                canvas.concat(mTransformation.getMatrix());
            }
            dr.draw(canvas);
            canvas.restoreToCount(sc);
        }
    }
}
