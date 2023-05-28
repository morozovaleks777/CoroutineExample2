package com.example.coroutineexample

import java.lang.Error

open class State()

    class Error:State()
    class Progress: State()
    class Result(
        val factorial:String
    ):State()