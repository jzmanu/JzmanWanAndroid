package com.manu.wanandroid.widget

import android.animation.Animator

/**
 * @Desc: BaseAnimatorListener
 * @Author: jzman
 * @Date: 2021/1/24 0:19.
 * https://github.com/jzmanu/JzmanWanAndroid
 */
abstract class BaseAnimatorListener : Animator.AnimatorListener {

    override fun onAnimationCancel(animation: Animator?) {
    }

    override fun onAnimationRepeat(animation: Animator?) {
    }
}