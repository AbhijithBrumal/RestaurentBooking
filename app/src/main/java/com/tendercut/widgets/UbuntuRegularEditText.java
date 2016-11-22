package com.tendercut.widgets;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Abhijith on 21-11-2016.
 */

public class UbuntuRegularEditText extends EditText{

    private static LruCache<String, Typeface> sTypefaceCache =
            new LruCache<String, Typeface>(12);

    public UbuntuRegularEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public UbuntuRegularEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        try {


            Typeface typeface = sTypefaceCache.get("ubuntu_r");

            if (typeface == null) {
                typeface = Typeface.createFromAsset(context.getAssets(),
                        String.format("fonts/%s.ttf", "ubuntu_r"));

                // Cache the Typeface object
                sTypefaceCache.put("ubuntu_r", typeface);
            }
            setTypeface(typeface);

            // Note: This flag is required for proper typeface rendering
            setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);

        } finally {

        }
    }

    public UbuntuRegularEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/ubuntu_r.ttf");
        setTypeface(tf);
    }
}
