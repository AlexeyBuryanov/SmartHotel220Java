package com.alexeyburyanov.smarthotel.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.alexeyburyanov.smarthotel.R;

/**
 * Created by Allen on 12/22/2015.
 * Modified by Alexey Buryanov 28.03.2018
 */
public class ThermometerProgress extends View {

    private Paint progressPaint;
    private Paint fillPaint;
    private final int BAR_HEIGHT = 5; // радиус малого полукруга слева от бара
    private final int SMALL_CIRLCE_RADIUS = BAR_HEIGHT; // 5dp
    private final int CIRCLE_RADIUS = 12; // 15dp
    private final int STROKE_WIDTH = 1;
    private int heightWithoutPadding;
    private int widthWithoutPaddding;
    private float barWidth; // в пикселях
    private float pxBarHeight;
    private float pxBarLenght;
    private float pxCirleRadius;
    private int progress;
    private float textSize;

    public ThermometerProgress(Context context) {
        super(context);
        init(context, null);
    }
    public ThermometerProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    public ThermometerProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        float deviceDensity = getResources().getDisplayMetrics().density;
        pxBarHeight = BAR_HEIGHT * deviceDensity;
        pxCirleRadius = CIRCLE_RADIUS * deviceDensity;
        textSize = 10 * deviceDensity;

        progressPaint = new Paint();
        progressPaint.setStrokeWidth(2);
        progressPaint.setColor(getResources().getColor(R.color.colorPrimary));

        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(getResources().getColor(R.color.blue_black_light));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        heightWithoutPadding = getHeight() - getPaddingBottom();
        widthWithoutPaddding = getWidth() - getPaddingRight();
        barWidth = widthWithoutPaddding - pxBarHeight - pxCirleRadius - (float) (Math.sqrt(Math.pow(pxCirleRadius, 2) - Math.pow(pxBarHeight, 2)));

        // Рисует половину в конце
        // если прогресс > 0, заполняет небольшую половину круга слева
        if (progress > 0) {
            progressPaint.setStyle(Paint.Style.FILL);
            progressPaint.setColor(getResources().getColor(R.color.blue_black_light));
        }
        @SuppressLint("DrawAllocation")
        RectF segment = new RectF();
        @SuppressLint("DrawAllocation")
        Path path = new Path();
        segment.set(getPaddingLeft(), getHeight() / 2 - pxBarHeight, getPaddingLeft() + 2 * pxBarHeight, getHeight() / 2 + pxBarHeight);
        path.addArc(segment, 90, 180);
        canvas.drawPath(path, progressPaint);

        // Рисует две строки равные основному бару
        @SuppressLint("DrawAllocation")
        Paint linePaint = new Paint();
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(getResources().getColor(R.color.colorPrimary));
        linePaint.setStrokeWidth(2);
        float endlineX = (float) (widthWithoutPaddding - pxCirleRadius - 2 * Math.sqrt(2) / 3 * pxCirleRadius);
        float startlineX = getPaddingLeft() + pxBarHeight; // радиус малого круга
        float abouveLineY = getHeight() / 2 - pxBarHeight;
        float belowLineY = getHeight() / 2 + pxBarHeight;
        pxBarLenght = endlineX - startlineX;
        canvas.drawLine(startlineX, abouveLineY, endlineX, abouveLineY, linePaint);
        canvas.drawLine(startlineX, belowLineY, endlineX, belowLineY, linePaint);

        // Рисует большой круг справа
        segment.set(widthWithoutPaddding - 2 * pxCirleRadius, getHeight() / 2 - pxCirleRadius, widthWithoutPaddding, getHeight() / 2 + pxCirleRadius);
        path.rewind();
        float angle = (float) Math.toDegrees(Math.atan(1 / (2 * Math.sqrt(2)))); // Почему? Потому, что так я сказал
        path.addArc(segment, 180 + angle, 360 - 2 * angle);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setColor(getResources().getColor(R.color.colorPrimary));
        canvas.drawPath(path, progressPaint);

        // Рисует цвет заполнения прогресса
        float totalLength = getWidth() - getPaddingRight() - getPaddingLeft();
        int firstThresol = (int) (7000 * pxBarHeight / totalLength);
        int secondThresol = (int) (7000 * (pxBarHeight + pxBarLenght) / totalLength);
        int thirdThresol = (int) (7000 * (totalLength - pxCirleRadius) / totalLength);
        float lengthofProgress = totalLength * progress / 7000;
        if (progress > firstThresol) {
            if (progress <= secondThresol) {
                // Заполняет бар цветом
                canvas.drawRect(startlineX, abouveLineY, getPaddingLeft() + lengthofProgress, belowLineY, fillPaint);
            } else {
                // Заполняет всё в баре цветом, а также круг справа
                canvas.drawRect(startlineX, abouveLineY, getPaddingLeft() + pxBarHeight + pxBarLenght, belowLineY, fillPaint); // заполнить панель
                path.rewind();
                float angle2 = 0;
                if (progress <= thirdThresol) {
                    angle2 = (float) Math.toDegrees(Math.acos((totalLength - lengthofProgress - pxCirleRadius) / pxCirleRadius));
                    path.addArc(segment, 180 - angle2, 2 * angle2);
                } else {
                    angle2 = (float) Math.toDegrees(Math.acos((lengthofProgress - totalLength + pxCirleRadius) / pxCirleRadius));
                    path.addArc(segment, angle2, 360 - 2 * angle2);
                }
                canvas.drawPath(path, fillPaint);
            }
        }

        // Рисует текст отображающийся в баре
        @SuppressLint("DrawAllocation")
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(textSize);
        textPaint.setColor(getResources().getColor(R.color.black));
        textPaint.setTextAlign(Paint.Align.CENTER);
        float textHeight = textPaint.descent() - textPaint.ascent();
        float textOffset = (textHeight / 2) - textPaint.descent();
        canvas.drawText(progress + " K", segment.centerX(), segment.centerY() + textOffset, textPaint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }
}