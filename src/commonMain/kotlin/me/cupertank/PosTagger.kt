package me.cupertank

import io.kinference.core.KIEngine
import io.kinference.core.KIONNXData
import io.kinference.data.ONNXDataType
import io.kinference.model.Model
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class PosTagger(modelBytes: ByteArray) {
    val model = KIEngine.loadModel(modelBytes)

    companion object {
        suspend fun create() = PosTagger(ResourceLoader.bytes(Path("pos_tagger/model.onnx")))

        suspend fun getInputsFromResources(): List<KIONNXData<*>> =
            List(13) {
                val bytes = ResourceLoader.bytes(Path("pos_tagger/input_$it.pb"))
                KIEngine.loadData(bytes, ONNXDataType.ONNX_TENSOR)
            }
    }
}

