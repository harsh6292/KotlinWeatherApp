package com.example.harsh.weatherapp.domain.commands

interface Command<out T> {
    fun execute(): T
}