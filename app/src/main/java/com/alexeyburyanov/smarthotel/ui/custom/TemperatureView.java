package com.alexeyburyanov.smarthotel.ui.custom;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Keep;
import android.util.AttributeSet;
import android.view.View;

import com.alexeyburyanov.smarthotel.utils.ViewUtils;

/**
 * Created by bergmannm 2016.
 * Modified by Alexey Buryanov 28.03.2018
 * */
public class TemperatureView extends View {

    // private final float LINE_CIRCLE_SCALE;
    // private final int MAX_TEMPER;
    // private final int MIN_TEMPER;
    // private final float STROKE_WIDTH_SCALE;
    private ObjectAnimator anim;
    private int f0h;
    private Paint mBgPaint;
    private float mCenterX;
    private float mCenterY;
    private LinearGradient mLinearGradient;
    private int mMainColor;
    private RadialGradient mRadialGradientBig;
    private RadialGradient mRadialGradientSmall;
    private float mRadius;
    private float mSmallRadius;
    // private float mStokeWidth;
    private int mTemper;
    private int mTemperColor;
    // private LinearGradient mTemperGradient;
    private Paint mTemperPaint;
    private Path mTemperPath;
    private float mTemperTop;
    private int mTextColor;
    private Paint mTextPaint;
    private float mTextSize;
    // private float mTextTop;
    private int f1w;
    private float y0;
    private float yScale;

    public TemperatureView(Context context) {
        super(context);
        init();
    }
    public TemperatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public TemperatureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // float STROKE_WIDTH_SCALE = 0.125f;
        // float LINE_CIRCLE_SCALE = 0.8f;
        // float MAX_TEMPER = 80;
        // float MIN_TEMPER = -30;
        mTemper = 0;
        mTextSize = 32.0f;
        mMainColor = Color.GRAY;
        mTextColor = Color.BLACK;
        mTemperColor = Color.RED;

        mBgPaint = new Paint(1);
        mBgPaint.setColor(mMainColor);
        mBgPaint.setStrokeJoin(Paint.Join.ROUND);
        mTextPaint = new Paint(1);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(mTextColor);
        mTemperPaint = new Paint(1);
        mTemperPaint.setColor(mTemperColor);
        mTemperPaint.setStyle(Paint.Style.FILL);
        mTemperPaint.setStrokeCap(Paint.Cap.ROUND);
        mTemperPath = new Path();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        f1w = w;
        f0h = h;
        float actualWidth = (float) ((int) (((float) h) / 4.0f));
        float mStokeWidth = 0.125f * actualWidth;
        mRadius = (actualWidth / 2.0f) - mStokeWidth;
        mSmallRadius = mRadius * 0.6f;
        mTextSize = ((float) h) / 13.0f;
        mTextPaint.setTextSize(mTextSize);
        float mTextTop = (((float) (h - getPaddingBottom())) - ViewUtils.getTextHeight(mTextPaint)) - (((float) h) / 19.0f);
        y0 = (mTextTop - mRadius) - mSmallRadius;
        mTemperTop = ((float) getPaddingTop()) + (mSmallRadius * 0.8f);
        yScale = (y0 - mTemperTop) / 110.0f;
        mCenterX = (float) (w / 2);
        mCenterY = mTextTop - mRadius;
        setPath();
        initGradient();
    }

    private void initGradient() {
        int c = mMainColor;
        float f = mCenterX;
        float f2 = mTemperTop;
        float f3 = mSmallRadius * 0.8f;
        int[] iArr = new int[3];
        iArr[2] = c;
        mRadialGradientSmall = new RadialGradient(f, f2, f3, iArr, new float[]{0.0f, 0.08f, 1.0f}, Shader.TileMode.CLAMP);
        f = mCenterX;
        f2 = mCenterY;
        f3 = mRadius;
        iArr = new int[3];
        iArr[2] = c;
        mRadialGradientBig = new RadialGradient(f, f2, f3, iArr, new float[]{0.2f, 0.3f, 1.0f}, Shader.TileMode.CLAMP);
        f = mCenterX - (mSmallRadius * 0.8f);
        f3 = (mSmallRadius * 0.8f) + mCenterX;
        int[] iArr2 = new int[3];
        iArr2[0] = c;
        iArr2[2] = c;
        mLinearGradient = new LinearGradient(f, 0.0f, f3, 0.0f, iArr2, null, Shader.TileMode.CLAMP);
        f = 0.0f;
        f3 = 0.0f;
        float[] fArr = null;
        LinearGradient mTemperGradient = new LinearGradient(f, mRadius + mCenterY, f3, mTemperTop, new int[]{-16698673, -14288878, -65536}, fArr, Shader.TileMode.CLAMP);
        mTemperPaint.setShader(mTemperGradient);
    }

    private void setPath() {
        float left = mCenterX - (mSmallRadius * 0.8f);
        float right = mCenterX + (mSmallRadius * 0.8f);
        mTemperPath.moveTo(left, y0 - (mRadius * 0.268f));
        mTemperPath.lineTo(left, mTemperTop);
        float radius = mSmallRadius * 0.8f;
        mTemperPath.addArc(new RectF(left, mTemperTop - radius, right, mTemperTop + radius), 180.0f, 180.0f);
        mTemperPath.lineTo(right, y0 - (mRadius * 0.268f));
        mTemperPath.addArc(new RectF(mCenterX - mRadius, mCenterY - mRadius, mCenterX + mRadius, mCenterY + mRadius), -90.0f + ((float) 30.0D), (float) (360.0d - (2.0d * 30.0d)));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mTemper + "\u2103", mCenterX, (float) (f0h - getPaddingBottom()), mTextPaint);
        drawTemper(canvas);
        drawBg(canvas);
    }

    private void drawTemper(Canvas canvas) {
        mTemperPaint.setStrokeWidth(1.0f);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mTemperPaint);
        mTemperPaint.setStrokeWidth((mSmallRadius * 0.8f) * 2.0f);
        canvas.drawLine(mCenterX, mCenterY, mCenterX, (mCenterY - mSmallRadius) - (((float) (mTemper + 30)) * yScale), mTemperPaint);
    }

    private void drawBg(Canvas canvas) {
        mBgPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mTemperPath, mBgPaint);
        float step = (y0 - mTemperTop) / 5.0f;
        float startX = mCenterX + (mSmallRadius * 0.8f);
        float stopX = mCenterX - (mSmallRadius * 0.3f);
        for (int i = 1; i < 5; i++) {
            float y = y0 - (((float) i) * step);
            canvas.drawLine(startX, y, stopX, y, mBgPaint);
        }
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setAlpha(220);
        int sc = canvas.saveLayer(0.0f, 0.0f, (float) f1w, (float) f0h, mBgPaint, Canvas.ALL_SAVE_FLAG);
        mBgPaint.setShader(mRadialGradientBig);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mBgPaint);
        mBgPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        mBgPaint.setShader(mLinearGradient);
        canvas.drawRect(mCenterX - (mSmallRadius * 0.8f), mTemperTop, mCenterX + (mSmallRadius * 0.8f), y0 - (0.2f * mRadius), mBgPaint);
        canvas.restoreToCount(sc);
        mBgPaint.setXfermode(null);
        mBgPaint.setShader(mRadialGradientSmall);
        canvas.drawArc(new RectF(mCenterX - (mSmallRadius * 0.8f), mTemperTop - (mSmallRadius * 0.8f), mCenterX + (mSmallRadius * 0.8f), mTemperTop + (mSmallRadius * 0.8f)), 180.0f, 180.0f, false, mBgPaint);
        mBgPaint.setShader(null);
        mBgPaint.setAlpha(255);
    }

    public void startAnim(int temper, long duration) {
        if (anim != null) {
            anim.cancel();
        }
        temper = Math.max(-30, Math.min(80, temper));
        if (Math.abs(mTemper - temper) > 5) {
            anim = ObjectAnimator.ofInt(this, "temper", mTemper, temper).setDuration(duration);
            anim.start();
        } else {
            setTemper(temper);
        }
    }

    @Keep
    protected void setTemper(int temper) {
        if (mTemper != temper) {
            mTemper = temper;
            invalidate();
        }
    }
}
