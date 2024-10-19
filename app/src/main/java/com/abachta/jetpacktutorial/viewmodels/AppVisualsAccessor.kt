package com.abachta.jetpacktutorial.viewmodels

import com.abachta.jetpacktutorial.settings.AppTheme
import com.abachta.jetpacktutorial.settings.DynamicColorsOption

interface AppVisualsAccessor {

    var theme: AppTheme

    var dynamicColors: DynamicColorsOption
}