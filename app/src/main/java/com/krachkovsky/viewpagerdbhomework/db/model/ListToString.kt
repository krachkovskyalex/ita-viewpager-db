package com.krachkovsky.viewpagerdbhomework.db.model

fun fromUserList(list: List<User>): String {
    val result: StringBuilder = StringBuilder()
    for (user in list) {
        result
            .append(user.id)
            .append(". ")
            .append(user.firstName)
            .append(" ")
            .append(user.lastName)
            .append("\n")
    }
    return result.toString()
}