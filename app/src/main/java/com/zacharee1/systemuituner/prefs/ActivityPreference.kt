package com.zacharee1.systemuituner.prefs

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.zacharee1.systemuituner.R
import com.zacharee1.systemuituner.util.ColorPreference
import com.zacharee1.systemuituner.util.IColorPreference
import com.zacharee1.systemuituner.util.ISecurePreference
import com.zacharee1.systemuituner.util.SecurePreference

class ActivityPreference(context: Context, attrs: AttributeSet) : Preference(context, attrs), IColorPreference by ColorPreference(context, attrs), ISecurePreference by SecurePreference(context, attrs) {
    private val activityIntent: Intent?

    init {
        layoutResource = R.layout.custom_preference

        val array = context.theme.obtainStyledAttributes(attrs, R.styleable.ActivityPreference, 0, 0)

        activityIntent = run {
            val c = array.getString(R.styleable.ActivityPreference_activity_class)
            if (c != null) try {
                Intent(context, context.classLoader.loadClass(c))
            } catch (e: Exception) {
                null
            } else null
        }
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)

        bindVH(holder)
    }

    override fun onClick() {
        super.onClick()

        if (activityIntent != null) context.startActivity(activityIntent)
    }
}