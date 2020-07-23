# DSL-UI
Kotlin实现DSL语法，模仿Flutter，JetPack Compose，简单代码布局demo。

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
