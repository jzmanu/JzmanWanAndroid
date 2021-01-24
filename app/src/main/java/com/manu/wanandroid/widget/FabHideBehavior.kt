package com.manu.wanandroid.widget

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.manu.wanandroid.app.MApplication
import com.manu.wanandroid.utils.L
import com.manu.wanandroid.utils.Util

/**
 * @Desc: hide fab
 * @Author: jzman
 * @Date: 2021/1/23 16:47.
 * https://github.com/jzmanu/JzmanWanAndroid
 */
class FabHideBehavior @JvmOverloads constructor(
        context: Context? = null, attrs: AttributeSet? = null
) : FloatingActionButton.Behavior(context, attrs) {
    private val tag = "FabHideBehavior"
    private var isAnimateStart = false;

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        L.i(tag, "axes:$axes , type:$type")
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
        L.i(tag, "dxConsumed: $dxConsumed, dyConsumed: $dyConsumed, dxUnconsumed: $dxUnconsumed, dyUnconsumed: $dyUnconsumed, type: $type)")

        if (dyConsumed > 3 && !isAnimateStart) {
            // hide
            animate(child, Util.dpToPx(MApplication.getApp().resources, 106f), 0.2f, 0.1f)
        } else if (dyConsumed < -3 && !isAnimateStart) {
            // show
            animate(child, 0f, 1f, 1f)
        }
    }

    private fun animate(v: View, translationY: Float, scale: Float, alpha: Float) {
        v.animate()
                .translationY(translationY)
                .scaleX(scale)
                .scaleY(scale)
                .alpha(alpha)
                .setDuration(300)
                .setInterpolator(DecelerateInterpolator())
                .setListener(object : BaseAnimatorListener() {
                    override fun onAnimationStart(animation: Animator?) {
                        isAnimateStart = true
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        isAnimateStart = false
                    }
                })
                .start()
    }
}