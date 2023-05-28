package com.example.coroutineexample


sealed class State

    object Error: State()
    object Progress: State()
    class ResultFactorial(
        val factorial:String
    ): State()