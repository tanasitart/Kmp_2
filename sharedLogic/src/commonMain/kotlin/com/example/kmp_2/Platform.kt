package com.example.kmp_2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform