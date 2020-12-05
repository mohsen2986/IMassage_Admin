package com.example.imassage_admin.ui.utils

import android.animation.AnimatorInflater
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.imassage_admin.R

class ShowStatus {


    fun showProgress(lottie: LottieAnimationView, button: Button) {
        button.text = ""

        /*
        button.isClickable = false
        button.isPressed = false*/
        button.isEnabled = false

        lottie.setAnimation(R.raw.progress_bar)
        lottie.visibility = View.VISIBLE
        lottie.repeatCount = LottieDrawable.INFINITE
        lottie.playAnimation()

        val fadeInLottie =
            AnimatorInflater.loadAnimator(lottie.context, R.animator.fade_in).apply {
                setTarget(lottie)
            }

        Handler(Looper.getMainLooper()).postDelayed({
            fadeInLottie.start()
        }, 300)
    }

    fun showSuccess(lottie: LottieAnimationView){
        lottie.visibility = View.VISIBLE
        lottie.setAnimation(R.raw.success)
        lottie.repeatCount = 0
        lottie.playAnimation()
    }

    fun showFail(lottie: LottieAnimationView){
        lottie.visibility = View.VISIBLE
        lottie.setAnimation(R.raw.fail)
        lottie.repeatCount = 0
        lottie.playAnimation()
    }

    fun showButton(lottie: LottieAnimationView,button: Button,buttonText:String){
        lottie.pauseAnimation()
        lottie.visibility = View.GONE
        button.text = buttonText
        button.isEnabled = true

        val fadeOutLottie =
            AnimatorInflater.loadAnimator(lottie.context, R.animator.fade_out).apply {
                setTarget(lottie)
            }

        Handler(Looper.getMainLooper()).postDelayed({
            fadeOutLottie.start()
        },300)
    }

}