package cn.kaicity.common.repository

import cn.kaicity.common.bean.InputBean
import cn.kaicity.common.platform.IObfuscator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import java.io.File

object DexObf {

    private var obfuscatorImpl: IObfuscator? = null

    suspend fun run(inputBean: InputBean, logCallback: (log: String) -> Unit) {
        val params = arrayListOf<String>()
        params.add("-i")
        params.add(inputBean.input)

        params.add("-o")
        params.add(inputBean.output)

        params.add("-d")
        params.add(inputBean.depth)

        params.add("-a")

        val filterFile = File(File(inputBean.output).parentFile, "filter.txt")
        filterFile.writeText(inputBean.rule)
        params.add(filterFile.absolutePath)


        flow {
            obfuscatorImpl = IObfuscator(this)
            obfuscatorImpl?.doObfuscator(params.toTypedArray())
        }.catch {
            logCallback.invoke("Error")
        }.onCompletion {
            filterFile.delete()
            logCallback.invoke("Finish")
        }.flowOn(Dispatchers.IO)
            .collect {
                logCallback.invoke(it)
            }
    }

}
