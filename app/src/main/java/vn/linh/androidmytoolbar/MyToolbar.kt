package vn.linh.androidmytoolbar

import android.content.Context
import android.content.res.ColorStateList
import android.support.constraint.ConstraintLayout
import android.support.v4.widget.ImageViewCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.layout_azui_toolbar.view.*

class MyToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    var title: String? = null
        set(value) {
            tv_title.text = value
        }
    private var hideLeftAction: Boolean = false
        set(value) {
            img_left_action.visibility = if (value) View.GONE else View.VISIBLE
            field = value
        }
    private var leftActionIcon: Int = -1
        set(value) {
            if (value != -1) {
                img_left_action.setImageResource(value)
            }
            field = value
        }
    private var leftIconTint: Int = -1
        set(value) {
            if (value != -1) {
                ImageViewCompat.setImageTintList(img_left_action, ColorStateList.valueOf(value))
            }
            field = value
        }

    private var rightIconTint: Int = -1
        set(value) {
            if (value != -1) {
                ImageViewCompat.setImageTintList(img_right_action, ColorStateList.valueOf(value))
            }
            field = value
        }

    private var hideRightAction: Boolean = false
        set(value) {
            img_right_action.visibility = if (value) View.GONE else View.VISIBLE
            field = value
        }
    private var rightActionIcon: Int = -1
        set(value) {
            if (value != -1) {
                img_right_action.setImageResource(value)
            }
            field = value
        }
    var onBackClickListener: View.OnClickListener? = null
    var onRightActionClickListener: View.OnClickListener? = null

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.layout_azui_toolbar, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyToolbar)
        try {
            title = typedArray.getString(R.styleable.MyToolbar_atb_title)
            leftActionIcon = typedArray.getResourceId(
                R.styleable.MyToolbar_atb_left_action_icon, -1)
            hideLeftAction = typedArray.getBoolean(
                R.styleable.MyToolbar_atb_hide_left_action, leftActionIcon == -1)

            rightActionIcon = typedArray.getResourceId(
                R.styleable.MyToolbar_atb_right_action_icon, -1)
            hideRightAction = typedArray.getBoolean(
                R.styleable.MyToolbar_atb_hide_right_action, rightActionIcon == -1)
            leftIconTint = typedArray.getColor(R.styleable.MyToolbar_atb_left_icon_tint, -1)
            rightIconTint = typedArray.getColor(R.styleable.MyToolbar_atb_right_icon_tint, -1)
        } finally {
            typedArray.recycle()
        }
        handleEvent()
    }

    private fun handleEvent() {
        img_left_action.setOnClickListener { view ->
            onBackClickListener?.onClick(view)
        }

        img_right_action.setOnClickListener {
            onRightActionClickListener?.onClick(it)
        }
    }

    fun showLeftAction() {
        hideLeftAction = false
    }

    fun hideLeftAction() {
        hideLeftAction = true
    }

    fun showRightAction() {
        hideRightAction = false
    }

    fun hideRightAction() {
        hideRightAction = true
    }

    companion object {
        private val TITLE_GRAVIY_START = 0
        private val TITLE_GRAVIY_CENTER = 1
        private val TITLE_GRAVIY_END = 2
    }
}