package cn.kaicity.common.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * 日志
 *
 * @param modifier
 */
@Composable
fun LogView(modifier: Modifier,text:String) {
    Box(
        modifier = modifier.then(
            Modifier.padding(12.dp).verticalScroll(rememberScrollState()).background(color = Color(0xffeef0f4)).requiredHeightIn(300.dp, 900.dp)
                .fillMaxWidth()
        )
    ) {
        Text(
            text=text, color = Color(0xff555666),
            modifier = Modifier.padding(8.dp)
        )
    }
}
