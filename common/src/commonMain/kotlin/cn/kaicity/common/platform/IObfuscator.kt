package cn.kaicity.common.platform

import kotlinx.coroutines.flow.FlowCollector


expect class IObfuscator(flow: FlowCollector<String>) {
    suspend fun doObfuscator(args: Array<String>)
}
