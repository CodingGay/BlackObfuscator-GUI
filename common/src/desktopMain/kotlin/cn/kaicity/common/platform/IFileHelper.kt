package cn.kaicity.common.platform

import androidx.compose.ui.awt.ComposeWindow
import java.awt.FileDialog

actual fun chooseFile(callback:(String)->Unit) {
    val fileDialog = FileDialog(ComposeWindow(), "InputDex", FileDialog.LOAD)
    fileDialog.isVisible = true
    if (fileDialog.directory.isNullOrEmpty() || fileDialog.file.isNullOrEmpty()) {
        callback("")
        return
    }
    return callback(fileDialog.directory + fileDialog.file)
}

actual fun saveFile(callback:(String)->Unit) {
    val fileDialog = FileDialog(ComposeWindow(), "OutputDex", FileDialog.SAVE)
    fileDialog.isVisible = true
    if (fileDialog.directory.isNullOrEmpty() || fileDialog.file.isNullOrEmpty()) {
        callback("")
        return
    }
    return callback(fileDialog.directory + fileDialog.file)
}

