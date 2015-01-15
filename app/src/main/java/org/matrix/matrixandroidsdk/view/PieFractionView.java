/* 
 * Copyright 2014 OpenMarket Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.matrix.matrixandroidsdk.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import org.matrix.matrixandroidsdk.R;

/**
 * View that displays a disc representing a percentage.
 */
public class PieFractionView extends View {

    private static final int START_ANGLE = -90;

    // The fraction between 0 and 100
    private int fraction = 0;

    private RectF rectF;
    private Paint paint;

    private int powerColor;
    private int restColor;

    public PieFractionView(Context context, AttributeSet attrs) {
        super(context, attrs);

        int[] attrArray = new int[] {android.R.attr.layout_width, android.R.attr.layout_height};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, attrArray);
        int width = typedArray.getDimensionPixelSize(0, 0);
        int height = typedArray.getDimensionPixelSize(1, 0);
        rectF = new RectF(0, 0, width, height);
        paint = new Paint();

        powerColor = getResources().getColor(R.color.pie_fraction_fill);
        restColor = getResources().getColor(R.color.pie_fraction_rest);
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int angle = fraction * 360 / 100;

        // Draw the power part
        paint.setColor(powerColor);
        canvas.drawArc(rectF, START_ANGLE, angle, true, paint);

        // Draw the rest
        paint.setColor(restColor);
        canvas.drawArc(rectF, START_ANGLE + angle, 360 - angle, true, paint);
    }
}
