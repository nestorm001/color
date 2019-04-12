package com.github.nestorm001.color

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input.addTextChangedListener {
            it?.run {
                val string = this.toString()
                string.printColor(ColorMode.HSV)
                val hsvColor = string.toColor(ColorMode.HSV)
                hsv.setBackgroundColor(hsvColor)
                hsv.text = getString(R.string.hsv, hsvColor.toHex())
                hsv.setTextColor(invertColor(hsvColor))

                string.printColor(ColorMode.RGB)
                val rgbColor = string.toColor(ColorMode.RGB)
                rgb.setBackgroundColor(string.toColor(ColorMode.RGB))
                rgb.text = getString(R.string.rgb, rgbColor.toHex())
                rgb.setTextColor(invertColor(rgbColor))
            }
        }
    }
}
