package com.example.coroutineexample

import java.lang.Error

class State(
    val isError: Boolean = false,
    val isInProgress: Boolean = false,
    val factorial:String = ""
)