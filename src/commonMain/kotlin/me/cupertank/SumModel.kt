package me.cupertank

import io.kinference.core.KIEngine
import io.kinference.core.data.tensor.asTensor
import io.kinference.ndarray.arrays.FloatNDArray
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class SumModel(modelBytes: ByteArray) {
    private val model = KIEngine.loadModel(modelBytes)

    fun predict(left: FloatNDArray, right: FloatNDArray): FloatNDArray {
        val output = model.predict(listOf(left.asTensor("x"), right.asTensor("y")))

        return (output["sum"]!!.data as FloatNDArray)
    }

    companion object {
        suspend fun create(): SumModel {
            return SumModel(ResourceLoader.bytes(Path("/add/model.onnx")))
        }
    }
}
