package com.sanjaydevtech.cps.provider

import android.net.Uri
import android.provider.BaseColumns

object ProviderContract {
    const val AUTHORITY = "com.sanjaydevtech.cps"
    val AUTHORITY_URI = Uri.parse("content://$AUTHORITY")!!

    object DomainEntry : BaseColumns {
        val TABLE_NAME = "DeviceUser"
        val DOMAIN_URI = Uri.withAppendedPath(AUTHORITY_URI, TABLE_NAME)
        val TITLE = "title"
    }

}