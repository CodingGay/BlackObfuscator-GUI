package cn.kaicity.common.repository

import cn.kaicity.common.bean.InputBean
import net.lingala.zip4j.ZipFile
import java.io.File

object ApkObf {

    suspend fun run(inputBean: InputBean, logCallback: (log: String) -> Unit) {
        val workDir = File(File(inputBean.output).parentFile, "temp")
        val targetApk = File(inputBean.output)
        try {
            val originApk = File(inputBean.input)
            if (!originApk.exists()) {
                logCallback.invoke("Apk not Exists")
                return
            }

            if (targetApk.exists()) {
                targetApk.delete()
            }

            logCallback.invoke("Start Unzip Apk")

            unzip(originApk.absolutePath, workDir.absolutePath)

            val dexList = workDir.listFiles { name ->
                name.extension == "dex"
            }
            dexList?.forEach {
                logCallback.invoke("Start Obfuscator ${it.name}")
                val dexInputBean =
                    InputBean(it.absolutePath, workDir.absolutePath + "/" + it.name, inputBean.depth, inputBean.rule)
                DexObf.run(dexInputBean, logCallback)
            }

            logCallback.invoke("Start Package Apk")

            zip(workDir.absolutePath, originApk, targetApk)

            logCallback.invoke("Please Sign APK!!!")

        } catch (e: Exception) {
            e.printStackTrace()
            logCallback.invoke("Error!!!")
            logCallback.invoke(e.message.toString())
            targetApk.delete()
        } finally {
            removeFile(workDir)
        }

    }

    private fun removeFile(file: File) {
        if (file.isFile) {
            file.delete()
        } else if (file.isDirectory) {
            file.listFiles()?.forEach(::removeFile)
            file.delete()
        }
    }

    private fun zip(path: String, origin: File, targetApk: File) {
        val dexList = File(path).listFiles { name ->
            name.extension == "dex"
        }?.toList()

        origin.copyTo(targetApk)
        ZipFile(targetApk).use {
            it.addFiles(dexList)
        }

    }


    private fun unzip(apk: String, path: String) {
        ZipFile(apk).use {
            it.extractAll(path)
        }
    }

}
