package com.github.nestorm001.color

import android.os.Bundle
import android.util.Log
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
                string.printColor()
                color.setBackgroundColor(string.toColor())
            }
        }
    }

    private fun String.toColor(): Int {
        return this.hashCode() or 0xFF000000.toInt()
    }

    private fun String.printColor() {
        Log.d("wtf", "color of $this is ${Integer.toHexString(toColor())}")
    }
}
