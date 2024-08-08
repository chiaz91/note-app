package com.example.note.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

open class SettingMenu(
    @DrawableRes open val icon: Int,
    @StringRes open val name: Int,
)

class ExternalSettingMenu(
    @DrawableRes override val icon: Int,
    @StringRes override val name: Int,
    val url: String,
): SettingMenu(icon, name)