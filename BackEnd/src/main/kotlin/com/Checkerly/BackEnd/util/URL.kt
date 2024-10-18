package com.Checkerly.BackEnd.util

import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

object URL {
    fun decodeParam(text: String?): String? {
        try {
            return URLDecoder.decode(text, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            return ""
        }
    }

    fun convertDate(textDate: String?, defaultValue: Date?): Date? {
        val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"))
        try {
            return sdf.parse(textDate)
        } catch (e: ParseException) {
            return defaultValue
        }
    }
}
