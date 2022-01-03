package cn.kaicity.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cn.kaicity.common.bean.InputBean
import cn.kaicity.common.repository.ApkObf
import cn.kaicity.common.repository.DexObf
import cn.kaicity.common.widget.LogView
import cn.kaicity.common.widget.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.log

@Preview
@Composable
fun AppPreview() {
    AppMain()
}


@Composable
fun AppMain() {
    var log by remember { mutableStateOf("") }


    val logCallback = { it: String ->
        log = "$log\n$it"
    }

    Row(modifier = Modifier.fillMaxWidth().background(Color.White)) {
        MainView(modifier = Modifier.weight(0.5F)) {
            if (it.input.endsWith(".dex")) {
                log = "Start Obfuscator Dex"
                obfDex(it, logCallback)
            } else if (it.input.endsWith(".apk")) {
                log = "Start Obfuscator Apk"
                obfApk(it, logCallback)
            } else {
                log = "Only support Dex or Apk"
            }

        }
        LogView(modifier = Modifier.weight(0.5F), log)
    }
}


private fun obfDex(inputBean: InputBean, logCallback: (log: String) -> Unit) {
    GlobalScope.launch(Dispatchers.IO) {
        try {

            DexObf.run(inputBean, logCallback)
        } catch (e: Throwable) {
            e.printStackTrace()
            logCallback.invoke("Obfuscator Dex Fail")
            logCallback.invoke(e.message ?: "")
        }
    }
}

private fun obfApk(inputBean: InputBean, logCallback: (log: String) -> Unit) {
    GlobalScope.launch(Dispatchers.IO) {
        ApkObf.run(inputBean, logCallback)
    }
}
