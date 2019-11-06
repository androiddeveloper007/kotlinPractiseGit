
package com.eostek.kotlinpractice.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

public class GifView extends View {

    /**
     * 默认为1秒
     */
    private static final int DEFAULT_MOVIE_DURATION = 1000;

    private Movie mMovie;

    private int xOffset = 0;

    private int yOffset = 0;

    private long mMovieStart;

    private boolean mVisible = true;

    private int mCurrentAnimationTime = 0;;

    public GifView(Context context) {
        this(context, null);
    }

    public GifView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("NewApi")
    public GifView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    public void setGifPath(String gifPath) {
        mMovie = Movie.decodeFile(gifPath);
        requestLayout();
    }

    /**
     * 设置gif图资源
     * 
     * @param movieResId
     */
    public void setGifResource(int movieResId) {
        mMovie = Movie.decodeStream(getResources().openRawResource(movieResId));
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(viewWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mMovie != null) {
            updateAnimationTime();
            drawMovieFrame(canvas);
            postInvalidateDelayed(10);
        }
    }

    @SuppressLint("NewApi")
    private void invalidateView() {
        if (mVisible) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                postInvalidateOnAnimation();
            } else {
                invalidate();
            }
        }
    }

    private void updateAnimationTime() {
        long now = SystemClock.currentThreadTimeMillis();
        // 如果第一帧，记录起始时间
        if (mMovieStart == 0) {
            mMovieStart = now;
        }
        // 取出动画的时长
        int dur = mMovie.duration();
        if (dur == 0) {
            dur = DEFAULT_MOVIE_DURATION;
        }
        // 算出需要显示第几帧
        mCurrentAnimationTime = (int) ((now - mMovieStart) % dur);
    }

    private void drawMovieFrame(Canvas canvas) {
        // 设置要显示的帧，绘制即可
        mMovie.setTime(mCurrentAnimationTime);
        canvas.save();//Canvas.MATRIX_SAVE_FLAG
        canvas.drawColor(Color.BLACK);
        float scaleWidth = (getMeasuredWidth() + 0.0f) / mMovie.width();
        float scaleHeight = (getMeasuredHeight() + 0.0f) / mMovie.height();
        float scale = Math.min(scaleWidth, scaleHeight);
        if (scaleWidth < scaleHeight) {
            xOffset = 0;
            yOffset = (int) ((getMeasuredHeight() - mMovie.height() * scale) / 2/scale);
        } else {
            xOffset = (int) ((getMeasuredWidth() - mMovie.width() * scale) / 2/scale);
            yOffset = 0;
        }
        canvas.scale(scale, scale);
        mMovie.draw(canvas, xOffset, yOffset);
        canvas.restore();
    }
}
