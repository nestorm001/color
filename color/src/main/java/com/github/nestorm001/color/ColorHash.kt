@file:JvmName("ColorHash")

package com.github.nestorm001.color

import android.graphics.Color
import android.util.Log

/**
 * Created on 2019/4/11.
 * By nesto
 */

object ColorMode {
    const val RGB = 1000
    const val HSV = 1001
}

fun String.toHSV(): FloatArray {
    val hashCode = this.hashCode()
    val h = ((0xFFFF0000.toInt() and hashCode) shr 16) % 360
    val s = ((0x00FF00 and hashCode) shr 8) / 255f
    val v = (0x0000FF and hashCode) / 255f
    return floatArrayOf(h.toFloat(), s, v)
}

fun String.toHSVColor(): Int {
    return Color.HSVToColor(toHSV())
}

fun String.toRGB(): Int {
    return this.hashCode() or 0xFF000000.toInt()
}

fun String.toColor(colorMode: Int = ColorMode.RGB): Int {
    return when (colorMode) {
        ColorMode.HSV -> toHSVColor()
        else -> toRGB()
    }
}

fun String.printColor(colorMode: Int = ColorMode.RGB) {
    Log.d("wtf", "color of $this is ${Integer.toHexString(toRGB())}")
}
