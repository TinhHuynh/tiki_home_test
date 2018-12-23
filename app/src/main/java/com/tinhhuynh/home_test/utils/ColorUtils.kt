package com.tinhhuynh.home_test.utils

import android.content.Context
import android.graphics.Color
import java.util.*


class ColorUtils {
    companion object {
        fun random(): Int {
            val rnd = Random()
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            return color
        }

        // random Material Design color
        fun randomMatColor(context: Context, typeColor: String): Int {
            var returnColor = Color.BLACK
            val arrayId = context.resources.getIdentifier("mdcolor_$typeColor",
                    "array",
                    context.applicationContext.packageName)

            if (arrayId != 0) {
                val colors = context.resources.obtainTypedArray(arrayId)
                val index = (Math.random() * colors.length()).toInt()
                returnColor = colors.getColor(index, Color.BLACK)
                colors.recycle()
            }
            return returnColor
        }

    }
}
