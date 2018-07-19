package pl.thor.app.util

import android.animation.Animator
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView

/**
 * Created by Sylwester Tor on 03.04.2015.
 */
object AnimUtils {
    @JvmOverloads
    fun showViewAnimated(view: View, duration: Int? = null, listener: Animator.AnimatorListener? = null) {
        var duration = duration
        if (duration == null)
            duration = 300

        if (view.visibility != View.VISIBLE) {
            view.alpha = 0f
            view.visibility = View.VISIBLE
            view.animate()
                    .alpha(1f)
                    .setDuration(duration.toLong())
                    .setListener(listener)
        }
    }

    fun showViewAnimated(view: View, listener: Animator.AnimatorListener) {
        showViewAnimated(view, null, listener)
    }

    @JvmOverloads
    fun hideViewAnimated(view: View, duration: Int? = 300) {
        if (view.visibility == View.VISIBLE) {
            view.alpha = 1f
            view.visibility = View.VISIBLE
            view.animate()
                    .alpha(0f)
                    .setDuration(duration!!.toLong())
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animator: Animator) {

                        }

                        override fun onAnimationEnd(animator: Animator) {
                            view.visibility = View.GONE
                        }

                        override fun onAnimationCancel(animator: Animator) {

                        }

                        override fun onAnimationRepeat(animator: Animator) {

                        }
                    })
        }
    }

    fun hideViewAnimated(view: View, duration: Int, listener: Animator.AnimatorListener) {
        if (view.visibility == View.VISIBLE) {
            view.alpha = 1f
            view.visibility = View.VISIBLE
            view.animate()
                    .alpha(0f)
                    .setDuration(duration.toLong())
                    .setListener(listener)
        }
    }

    fun hideViewAnimated(view: View, listener: Animator.AnimatorListener) {
        hideViewAnimated(view, 300, listener)
    }

    @JvmOverloads
    fun hideViewAnimatedInvisible(view: View, duration: Int? = 300) {
        if (view.visibility == View.VISIBLE) {
            view.alpha = 1f
            view.visibility = View.VISIBLE
            view.animate()
                    .alpha(0f)
                    .setDuration(duration!!.toLong())
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animator: Animator) {

                        }

                        override fun onAnimationEnd(animator: Animator) {
                            view.visibility = View.INVISIBLE
                        }

                        override fun onAnimationCancel(animator: Animator) {

                        }

                        override fun onAnimationRepeat(animator: Animator) {

                        }
                    })
        }
    }

    fun hideViewAnimatedInvisible(view: View, duration: Int, listener: Animator.AnimatorListener) {
        if (view.visibility == View.VISIBLE) {
            view.alpha = 1f
            view.visibility = View.VISIBLE
            view.animate()
                    .alpha(0f)
                    .setDuration(duration.toLong())
                    .setListener(listener)
        }
    }

    fun hideViewAnimatedInvisible(view: View, listener: Animator.AnimatorListener) {
        hideViewAnimatedInvisible(view, 300, listener)
    }

    /**
     * an animation for resizing the view.
     */
    class ResizeAnimation : Animation {
        private var mView: View? = null
        private var mToHeight: Float = 0.toFloat()
        private var mFromHeight: Float = 0.toFloat()

        private var mToWidth: Float = 0.toFloat()
        private var mFromWidth: Float = 0.toFloat()

        constructor(v: View, fromWidth: Float, fromHeight: Float, toWidth: Float, toHeight: Float, duration: Int) {
            mToHeight = toHeight
            mToWidth = toWidth
            mFromHeight = fromHeight
            mFromWidth = fromWidth
            mView = v
            setDuration(duration.toLong())
        }

        constructor(v: View, fromWidth: Float, fromHeight: Float, toWidth: Float, toHeight: Float) {
            mToHeight = toHeight
            mToWidth = toWidth
            mFromHeight = fromHeight
            mFromWidth = fromWidth
            mView = v
            duration = 300
        }

        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            val height = (mToHeight - mFromHeight) * interpolatedTime + mFromHeight
            val width = (mToWidth - mFromWidth) * interpolatedTime + mFromWidth
            val p = mView!!.layoutParams
            p.height = height.toInt()
            p.width = width.toInt()
            mView!!.requestLayout()
        }
    }

    fun changeImageFade(imageView: ImageView, duration: Int?, loadedImage: Bitmap) {
        imageView.alpha = 1f
        imageView.visibility = View.VISIBLE
        imageView.animate()
                .alpha(0f)
                .setDuration(duration!!.toLong())
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationEnd(animator: Animator) {
                        imageView.setImageBitmap(loadedImage)
                        imageView.animate()
                                .alpha(1f).duration = duration.toLong()
                    }

                    override fun onAnimationCancel(animator: Animator) {}
                    override fun onAnimationRepeat(animator: Animator) {}
                    override fun onAnimationStart(animator: Animator) {}

                })
    }

    fun changeImageResize(imageView: ImageView, newImage: Bitmap) {
        val animation = ResizeAnimation(imageView, imageView.width.toFloat(), imageView.height.toFloat(), newImage.width.toFloat(), newImage.height.toFloat(), 250)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                imageView.setImageBitmap(newImage)
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        imageView.startAnimation(animation)
    }

    fun expandView(v: View) {
        v.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targetHeight = v.measuredHeight

        v.layoutParams.height = 0
        v.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.height = if (interpolatedTime == 1f)
                    ViewGroup.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = (targetHeight / v.context.resources.displayMetrics.density).toInt().toLong()
        v.startAnimation(a)
    }

    fun collapseView(v: View) {
        val initialHeight = v.measuredHeight

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                    v.layoutParams.height = initialHeight
                    v.requestLayout()
                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = (initialHeight / v.context.resources.displayMetrics.density).toInt().toLong()
        v.startAnimation(a)
    }
}

