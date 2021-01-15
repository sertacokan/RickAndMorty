package com.example.rickandmortyapp.actions

sealed class StateActions

object LoadingState : StateActions()
data class SuccessAction<T : Any>(val data: T) : StateActions()
data class ErrorAction(val e: Throwable) : StateActions()
object CompleteState : StateActions()
