package me.cupertank

import io.kinference.ndarray.arrays.FloatNDArray
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
suspend fun main() {
    println("POS TAGGER MODEL")
    val posTagger = PosTagger.create()
    val inputs = PosTagger.getInputsFromResources()

    println("Start predicting")
    val outputs = posTagger.model.predict(inputs)

    for ((name, tensor) in outputs) {
        println("Name = $name")
        val ndArray = (tensor.data as FloatNDArray)
        val array = ndArray.array.toArray()
        println("Array size = ${array.size}")
        println("Tensort shape = ${ndArray.shape.joinToString(prefix = "[", postfix = "]")}")
    }

    println("\nSUM MODEL")
    val sumModel = SumModel.create()

    val left = FloatNDArray(intArrayOf(2, 2)) { it.toFloat() }
    val right = FloatNDArray(intArrayOf(2, 2)) { it.toFloat() }

    val output = sumModel.predict(left, right)
    val outputArray = output.array.toArray()
    println("Output summing:")
    println("[")
    for (row in 0 until output.shape[0]) {
        print("\t[")
        for (col in 0 until output.shape[1]) {
            print("${outputArray[row * output.shape[1] + col]}, ")
        }
        print("]\n")
    }
    println("]")
}
