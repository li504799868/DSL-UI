package com.lzp.dslui

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.lzp.dslui.dsl.Column
import com.lzp.dslui.dsl.toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(

            Column(orientation = android.widget.LinearLayout.HORIZONTAL) {

                ImageView(
                    id = R.id.img,
                    width = 150,
                    height = 150,
                    src = R.drawable.ic_launcher_foreground,
                    backgroundColor = ContextCompat.getColor(
                        this@MainActivity,
                        R.color.colorAccent
                    ),
                    onClickListener = View.OnClickListener { toast("img click") }
                )

                Column(
                    orientation = android.widget.LinearLayout.VERTICAL,
                    leftMargin = 30,
                    layoutGravity = Gravity.CENTER_VERTICAL
                ) {

                    TextView(text = "这是一个标题")
                    TextView(text = "这是一个描述")
                }

            }
        )

    }
}