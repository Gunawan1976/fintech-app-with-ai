package org.gunwndev.financetracker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform