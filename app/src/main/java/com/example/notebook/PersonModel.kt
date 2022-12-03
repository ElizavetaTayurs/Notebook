package com.example.notebook

import java.util.*

data class PersonModel (
    var id: Int = getAutoId(),
    var name: String = "",
    var surname: String = "",
    var birthday: String = "",
    var phone: String = ""
) {
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}