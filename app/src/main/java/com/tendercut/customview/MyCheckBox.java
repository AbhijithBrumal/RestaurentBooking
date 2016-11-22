package com.tendercut.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.tendercut.R;

/**
 * Created by Ajo philip PC on 22-11-2016.
 */

public class MyCheckBox extends AppCompatCheckBox {
    public MyCheckBox(Context context) {
        super(context);
        init(null);
    }

    public MyCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs!=null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyCheckBox);
            String fontName = a.getString(R.styleable.MyCheckBox_checkbox_font_name);
            if (fontName!=null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/"+fontName);
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }
}
