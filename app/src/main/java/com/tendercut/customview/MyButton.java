package com.tendercut.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.tendercut.R;

/**
 * Created by Ajo philip PC on 22-11-2016.
 */

public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
        init(null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyButton);
            String fontName = a.getString(R.styleable.MyButton_button_font_name);
            if (fontName != null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }

}
