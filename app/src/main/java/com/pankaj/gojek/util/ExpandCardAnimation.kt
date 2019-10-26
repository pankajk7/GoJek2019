package com.pankaj.gojek.util

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout
import android.widget.RelativeLayout

class ExpandCardAnimation(private val mView: View, duration: Int, private val mType: Int) :
    Animation() {

    private var mEndHeight: Int = 0
    private var mRelLayoutParams: RelativeLayout.LayoutParams? = null
    private var mLinLayoutParams: LinearLayout.LayoutParams? = null
    var layoutIsLinear: Int = 0

    var height: Int
        get() = mView.height
        set(height) {
            mEndHeight = height
        }

    init {
        setDuration(duration.toLong())
        mEndHeight = mView.height
        if (mView.layoutParams is LinearLayout.LayoutParams) {
            layoutIsLinear = 1
            mLinLayoutParams = mView.layoutParams as LinearLayout.LayoutParams
        } else if (mView.layoutParams is RelativeLayout.LayoutParams) {
            layoutIsLinear = 0
            mRelLayoutParams = mView.layoutParams as RelativeLayout.LayoutParams
        } else {
            // OTHER LAYOUTS
        }
        if (mType == EXPAND) {
            mEndHeight = mView.measuredHeight
            mView.layoutParams.height = 0
        } else {
            mView.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
        }
        mView.visible()
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {

        super.applyTransformation(interpolatedTime, t)
        if (interpolatedTime != 1.0f) {
            if (mType == EXPAND) {
                if (layoutIsLinear == 0)
                    mRelLayoutParams?.height = (mEndHeight * interpolatedTime).toInt()
                else
                    mLinLayoutParams?.height = (mEndHeight * interpolatedTime).toInt()
            } else {
                if (layoutIsLinear == 0)
                    mRelLayoutParams?.height = (mEndHeight * (1 - interpolatedTime)).toInt()
                else
                    mLinLayoutParams?.height = (mEndHeight * (1 - interpolatedTime)).toInt()
            }
            mView.requestLayout()
        } else {
            if (mType == EXPAND) {
                if (layoutIsLinear == 0)
                    mRelLayoutParams?.height = LinearLayout.LayoutParams.WRAP_CONTENT
                else
                    mLinLayoutParams?.height = LinearLayout.LayoutParams.WRAP_CONTENT

                mView.requestLayout()
            } else {
                mView.gone()
            }
        }
    }

    override fun willChangeBounds(): Boolean {
        return true
    }

    companion object {

        val COLLAPSE = 1
        val EXPAND = 0
    }
}
