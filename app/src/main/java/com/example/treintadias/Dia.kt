package com.example.treintadias

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dia(
    @StringRes val days: Int,
    @StringRes val titleDays: Int,
    @DrawableRes val imageDays: Int,
    @StringRes val descriptionDays: Int
)