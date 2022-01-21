package com.emirhan.basecomposeapplication.data.remote.dto

data class Attack(
    val convertedEnergyCost: Int,
    val cost: List<String>,
    val damage: String,
    val name: String,
    val text: String
)