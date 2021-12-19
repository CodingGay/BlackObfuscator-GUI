package cn.kaicity.common.repository

import kotlinx.coroutines.flow.FlowCollector


actual class IObfuscator actual constructor(private var flow: FlowCollector<String>) {

    actual suspend fun doObfuscator(vararg arg: String){
        close()
    }

    suspend fun close(){
    }
}

