package com.gorkem.githubrepo.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        const val DATE_FORMAT = "YYYY-MM-dd"
        @JvmStatic
        fun createDateString(year: Int, month: Int, day: Int): String {
            val cal = createDate(year, month, day)
            return getDateFormatString(cal)
        }

        @JvmStatic
        fun createDate(year: Int, month: Int, day: Int): Calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        }

        @JvmStatic
        private fun getDateFormatString(cal: Calendar): String {
            val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale("EN"))
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            dateFormat.calendar = cal
            return dateFormat.format(cal.time)
        }

        @JvmStatic
        fun addDay(cal: Calendar, day: Int): String {
            cal.add(Calendar.DATE, day)
            return getDateFormatString(cal)
        }
    }
}