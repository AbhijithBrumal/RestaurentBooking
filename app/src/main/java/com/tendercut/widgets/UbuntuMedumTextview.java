package com.tendercut.widgets;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Abhijith on 21-11-2016.
 */

public class UbuntuMedumTextView extends TextView {

    private static LruCache<String, Typeface> sTypefaceCache =
            new LruCache<String, Typeface>(12);


    public UbuntuMedumTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public UbuntuMedumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        try {


            Typeface typeface = sTypefaceCache.get("ubuntu_m");

            if (typeface == null) {
                typeface = Typeface.createFromAsset(context.getAssets(),
                        String.format("fonts/%s.ttf", "ubuntu_m"));

                // Cache the Typeface object
                sTypefaceCache.put("ubuntu_m", typeface);
            }
            setTypeface(typeface);

            // Note: This flag is required for proper typeface rendering
            setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);

        } finally {

        }
    }

    public UbuntuMedumTextView(Context context) {
        super(context);

    }

    private void init() {

    }
}
