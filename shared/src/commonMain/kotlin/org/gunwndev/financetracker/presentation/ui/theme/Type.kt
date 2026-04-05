package org.gunwndev.financetracker.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import financetrackerwithai.shared.generated.resources.Res
import financetrackerwithai.shared.generated.resources.vt323
import org.jetbrains.compose.resources.Font

@Composable
fun getTypography(): Typography {
    val manropeFontFamily = FontFamily(
        Font(
            resource = Res.font.vt323,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
    )

    return Typography(
        displayLarge = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Normal, fontSize = 57.sp),
        displayMedium = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Normal, fontSize = 45.sp),
        displaySmall = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Normal, fontSize = 36.sp),
        headlineLarge = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Normal, fontSize = 32.sp),
        headlineMedium = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Normal, fontSize = 28.sp),
        headlineSmall = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Normal, fontSize = 24.sp),
        titleLarge = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Normal, fontSize = 22.sp),
        titleMedium = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Medium, fontSize = 16.sp),
        titleSmall = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp),
        bodyLarge = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp),
        bodyMedium = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp),
        bodySmall = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp),
        labelLarge = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp),
        labelMedium = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Medium, fontSize = 12.sp),
        labelSmall = TextStyle(fontFamily = manropeFontFamily, fontWeight = FontWeight.Medium, fontSize = 11.sp)
    )
}

fun Typography.withDefaultFontFamily(fontFamily: FontFamily): Typography {
    return this.copy(
        displayLarge = this.displayLarge.copy(fontFamily = fontFamily),
        displayMedium = this.displayMedium.copy(fontFamily = fontFamily),
        displaySmall = this.displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = this.headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = this.headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = this.headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = this.titleLarge.copy(fontFamily = fontFamily),
        titleMedium = this.titleMedium.copy(fontFamily = fontFamily),
        titleSmall = this.titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = this.bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = this.bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = this.bodySmall.copy(fontFamily = fontFamily),
        labelLarge = this.labelLarge.copy(fontFamily = fontFamily),
        labelMedium = this.labelMedium.copy(fontFamily = fontFamily),
        labelSmall = this.labelSmall.copy(fontFamily = fontFamily)
    )
}
