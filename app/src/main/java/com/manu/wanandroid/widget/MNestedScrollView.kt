package com.manu.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.widget.NestedScrollView
import com.manu.wanandroid.utils.L

/**
 * @Desc: 待完善
 * @Author: jzman
 */
class MNestedScrollView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        NestedScrollView(context, attrs, defStyleAttr) {

    private val tag = "MNestedScrollView"
    private var mTopY = 0

    private var mInterceptLastY = 0

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val y = ev.y.toInt()
        L.i(tag,"action:${ev.action},y:$y,mInterceptLastY:$mInterceptLastY, mTopY:$mTopY ，canScroll:${canScrollVertically(-1)}")
        when(ev.action){
            MotionEvent.ACTION_DOWN ->{
            }
            MotionEvent.ACTION_MOVE ->{
                val distance = y - mInterceptLastY
                val isScrollTop = distance > 0 && canScrollVertically(1)
                val isScrollBottom = distance < 0 && canScrollVertically(-1)


                L.i(tag, "isScrollTop:$isScrollTop ,isScrollBottom:$isScrollBottom ，向上:${canScrollVertically(-1)} , 向下:${canScrollVertically(1)}")

                if (y - mInterceptLastY > 0 && mTopY == 0){
                    L.i(tag,"正在下滑")
                }else{
                    L.i(tag,"正在上滑")
                    if (canScrollVertically(-1)){
                        L.i(tag,"已经滑动到底部")
                        return false
                    }
                }
            }
            MotionEvent.ACTION_UP ->{
            }
        }
        mInterceptLastY = y
        return super.onInterceptTouchEvent(ev)
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        L.i(tag, "dxConsumed: $dxConsumed, dyConsumed: $dyConsumed, dxUnconsumed: $dxUnconsumed, dyUnconsumed: $dyUnconsumed)")
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int, consumed: IntArray) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed)
        L.i(tag, "dxConsumed: $dxConsumed, dyConsumed: $dyConsumed, dxUnconsumed: $dxUnconsumed, dyUnconsumed: $dyUnconsumed, type:$type)")
    }

    override fun onScrollChanged(x: Int, y: Int, oldx: Int, oldy: Int) {
        super.onScrollChanged(x, y, oldx, oldy)
        val yyy = getY()
        L.i(tag,"x:$x, y:$y, oldX:$oldx, oldy:$oldy, yyy:$yyy")
        mTopY = y
    }
}