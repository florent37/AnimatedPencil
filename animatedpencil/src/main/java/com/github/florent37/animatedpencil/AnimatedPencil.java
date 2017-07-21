package com.github.florent37.animatedpencil;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by florentchampigny on 21/07/2017.
 */

public class AnimatedPencil extends FrameLayout {
    private AppCompatImageView imageView;
    @ColorInt
    private int color = Color.BLACK;
    private Drawable drawable;

    public AnimatedPencil(Context context) {
        super(context);
        init(context, null);
    }

    public AnimatedPencil(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AnimatedPencil(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimatedPencil);
        if (typedArray != null) {
            color = typedArray.getColor(R.styleable.AnimatedPencil_pencil_color, color);
            typedArray.recycle();
        }
        imageView = new AppCompatImageView(getContext());
        addView(imageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        drawable = AppCompatDrawableManager.get().getDrawable(getContext(), R.drawable.awsb_ic_edit_animated_24);
        drawable = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(drawable, color);
        imageView.setImageDrawable(drawable);
    }

    public void setColor(int color) {
        this.color = color;
        DrawableCompat.setTint(drawable, color);
        imageView.setImageDrawable(drawable);
    }

    @Override
    public boolean performClick() {
        animateIcon();
        return super.performClick();
    }

    private void animateIcon() {
        if (drawable instanceof AnimatedVectorDrawable) {
            final AnimatedVectorDrawable d = (AnimatedVectorDrawable) drawable;
            d.start();
        } else if (drawable instanceof AnimatedVectorDrawableCompat) {
            final AnimatedVectorDrawableCompat d = (AnimatedVectorDrawableCompat) drawable;
            d.start();
        }
    }
}
