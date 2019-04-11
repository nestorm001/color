@file:JvmName("ColorHash")

package com.github.nestorm001.color

import android.graphics.Color
import android.util.Log
import androidx.annotation.IntDef

/**
 * Created on 2019/4/11.
 * By nesto
 */

object ColorMode {
    @Target(AnnotationTarget.VALUE_PARAMETER)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(RGB, HSV)
    annotation class Mode

    const val RGB = 1000
    const val HSV = 1001
}

private fun String.toHSV(): FloatArray {
    val hashCode = this.hashCode()
    val h = ((0xFFFF0000.toInt() and hashCode) shr 16) % 360
    val s = ((0x00FF00 and hashCode) shr 8) / 255f
    val v = (0x0000FF and hashCode) / 255f
    return floatArrayOf(h.toFloat(), s, v)
}

private fun String.toHSVColor(): Int {
    return Color.HSVToColor(toHSV())
}

private fun String.toRGB(): Int {
    return this.hashCode() or 0xFF000000.toInt()
}

fun String.toColor(@ColorMode.Mode colorMode: Int = ColorMode.RGB): Int {
    return when (colorMode) {
        ColorMode.HSV -> toHSVColor()
        else -> toRGB()
    }
}

fun String.printColor(@ColorMode.Mode colorMode: Int = ColorMode.RGB) {
    when (colorMode) {
        ColorMode.HSV -> print("HSV", toHSVColor())
        else -> print("HSV", toRGB())
    }
}

private fun String.print(mode: String, color: Int) {
    if (!BuildConfig.DEBUG) return
    Log.d("wtf", "$mode color of $this is ${Integer.toHexString(color)}")
}
