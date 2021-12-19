package cn.kaicity.common.platform

import androidx.compose.ui.awt.ComposeWindow
import java.awt.FileDialog

actual fun chooseFile(): String {
    val fileDialog = FileDialog(ComposeWindow(), "InputDex", FileDialog.LOAD)
    fileDialog.isVisible = true
    if (fileDialog.directory.isNullOrEmpty() || fileDialog.file.isNullOrEmpty()) {
        return ""
    }
    return fileDialog.directory + fileDialog.file
}

actual fun saveFile(): String {
    val fileDialog = FileDialog(ComposeWindow(), "OutputDex", FileDialog.SAVE)
    fileDialog.isVisible = true
    if (fileDialog.directory.isNullOrEmpty() || fileDialog.file.isNullOrEmpty()) {
        return ""
    }
    return fileDialog.directory + fileDialog.file
}

