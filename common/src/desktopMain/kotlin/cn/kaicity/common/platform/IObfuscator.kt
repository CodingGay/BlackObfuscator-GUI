package cn.kaicity.common.platform

import kotlinx.coroutines.flow.FlowCollector
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset


actual class IObfuscator actual constructor(private var flow: FlowCollector<String>) {

    actual suspend fun doObfuscator(args: Array<String>) {
        val os = System.getProperty("os.name").lowercase()

        val param = args.joinToString(" ")

        val rawPath = "./dex-tools/black-obfuscator"

        val shell = if (os.contains("windows")) {
            "$rawPath.bat d2j-black-obfuscator $param \n"
        } else {
            "$rawPath.sh d2j-black-obfuscator $param \n"
        }

        val process = Runtime.getRuntime().exec(shell)

        val output = BufferedReader(InputStreamReader(process.inputStream, Charset.forName("GBK")))
        output.useLines { sequence ->
            sequence.forEach {
                flow.emit(it)
            }
        }

        val error = BufferedReader(InputStreamReader(process.errorStream, Charset.forName("GBK")))
        error.useLines { sequence ->
            sequence.forEach {
                flow.emit(it)
            }
        }

        close()
    }

    suspend fun close() {
    }
}

