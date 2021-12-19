package cn.kaicity.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cn.kaicity.common.repository.DexObf
import cn.kaicity.common.widget.LogView
import cn.kaicity.common.widget.MainView

@Preview
@Composable
fun AppPreview() {
    AppMain()
}


@Composable
fun AppMain() {
    var log by remember { mutableStateOf("") }


    Row(modifier = Modifier.fillMaxWidth()) {
        MainView(modifier = Modifier.weight(0.5F)) {
            log = "Black Obfuscator Start\n"

            DexObf.run(it) { l ->
                log = log + "\n" + l
            }
        }
        LogView(modifier = Modifier.weight(0.5F), log)
    }
}
