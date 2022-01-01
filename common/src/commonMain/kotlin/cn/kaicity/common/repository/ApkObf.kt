package cn.kaicity.common.repository

import cn.kaicity.common.bean.InputBean
import net.lingala.zip4j.ZipFile
import java.io.File

object ApkObf {

    suspend fun run(inputBean: InputBean, logCallback: (log: String) -> Unit) {
        val workDir = File(File(inputBean.output).parentFile, "temp")
        try {
            val originApk = File(inputBean.input)
            if (!originApk.exists()) {
                logCallback.invoke("Apk not Exists")
                return
            }

            val targetApk = File(inputBean.output)
            if (targetApk.exists()) {
                targetApk.delete()
            }

            logCallback.invoke("Start Unzip Apk")

            unzip(originApk.absolutePath, workDir.absolutePath)

            val dexList = workDir.listFiles { name ->
                name.extension == "dex"
            }

            val dexOut = File(workDir,"dexDump")
            dexOut.mkdir()

            dexList?.forEach {
                logCallback.invoke("Start Obfuscator ${it.name}")
                val dexInputBean =
                    InputBean(it.absolutePath, dexOut.absolutePath+"/" + it.name, inputBean.depth, inputBean.rule)
                DexObf.run(dexInputBean, logCallback)
            }


            originApk.copyTo(File(inputBean.output))

            logCallback.invoke("Start Package Apk")

            zip(dexOut.absolutePath, inputBean.output)

        } catch (e: Exception) {
            logCallback.invoke("Error!!!")
            logCallback.invoke(e.message.toString())
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

    //todo
    private fun zip(path: String, apk: String) {
        val dexList = File(path).listFiles { name ->
            name.extension == "dex"
        }?.toList()

        ZipFile(apk).use {
            it.addFiles(dexList)
        }

    }

    //todo
    private fun unzip(apk: String, path: String) {
        ZipFile(apk).use {
            it.extractAll(path)
        }

    }



}
