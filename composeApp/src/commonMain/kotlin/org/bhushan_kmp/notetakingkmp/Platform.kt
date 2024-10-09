package org.bhushan_kmp.notetakingkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform