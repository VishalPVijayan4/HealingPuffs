package com.buildndeploy.healingpuffs.data.local.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TriggerConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromTriggers(triggers: List<String>): String = gson.toJson(triggers)

    @TypeConverter
    fun toTriggers(json: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, listType)
    }
}
