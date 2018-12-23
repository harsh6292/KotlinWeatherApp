package com.example.harsh.weatherapp.extensions

import org.jetbrains.anko.db.SelectQueryBuilder

fun SelectQueryBuilder.byId(id: Long) = whereSimple("_id = ?", id.toString())