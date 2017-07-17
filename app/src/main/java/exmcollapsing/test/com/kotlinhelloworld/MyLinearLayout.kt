package exmcollapsing.test.com.kotlinhelloworld

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * Created by Boosal_Android on 2016/7/28.
 * 解决activity设置了状态栏透明，又设置了android:windowSoftInputMode="adjustResize"的时候输入框被输入法挡住的情况
 * 这个作为layout的根布局(root),并设置 android:fitsSystemWindows="true"
 */
class MyLinearLayout : LinearLayout {

    val insets = IntArray(4)

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun fitSystemWindows(insets: Rect): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Intentionally do not modify the bottom inset. For some reason,
            // if the bottom inset is modified, window resizing stops working.
            // TODO: Figure out why.

            this.insets[0] = insets.left
            this.insets[1] = insets.top
            this.insets[2] = insets.right

            insets.left = 0
            insets.top = 0
            insets.right = 0
        }

        return super.fitSystemWindows(insets)
    }
}
