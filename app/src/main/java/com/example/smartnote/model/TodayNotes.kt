package com.example.smartnote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodayNotes(val title:String, val description:String):Parcelable
