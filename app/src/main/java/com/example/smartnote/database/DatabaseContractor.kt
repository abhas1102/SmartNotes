package com.example.smartnote.database

import android.provider.BaseColumns
import android.provider.BaseColumns._ID

class SmartNoteDatabaseContract {

    companion object SmartNoteEntry:BaseColumns {
        const val TABLE_NAME = "smart_note"
        const val NOTE_TITLE = "note_title"
        const val NOTE_DESCRIPTION = "note_description"

        const val SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +  _ID +" INTEGER PRIMARY KEY, " +
                NOTE_TITLE + "TEXT NOT NULL, " + NOTE_DESCRIPTION + "TEXT NOT NULL, " +")"

    }

}