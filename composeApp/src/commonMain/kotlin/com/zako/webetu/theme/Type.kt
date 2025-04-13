package com.zako.webetu.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.Font
import webetu.composeapp.generated.resources.IBMPlexSansArabic
import webetu.composeapp.generated.resources.Res

@Composable
fun provideAppTypography(): Typography {
    val font = FontFamily(Font(Res.font.IBMPlexSansArabic))
    val baseline = Typography()

    return Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = font),
        displayMedium = baseline.displayMedium.copy(fontFamily = font),
        displaySmall = baseline.displaySmall.copy(fontFamily = font),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = font),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = font),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = font),
        titleLarge = baseline.titleLarge.copy(fontFamily = font),
        titleMedium = baseline.titleMedium.copy(fontFamily = font),
        titleSmall = baseline.titleSmall.copy(fontFamily = font),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = font),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = font),
        bodySmall = baseline.bodySmall.copy(fontFamily = font),
        labelLarge = baseline.labelLarge.copy(fontFamily = font),
        labelMedium = baseline.labelMedium.copy(fontFamily = font),
        labelSmall = baseline.labelSmall.copy(fontFamily = font),
    )
}
