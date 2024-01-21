package com.example.smartnote.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseOpenHelper(
    context: Context?,
    name: String? = DATABASE_NAME,
    version: Int = DATABASE_VERSION
) : SQLiteOpenHelper(context, name, null, version) {

    companion object{
        const val DATABASE_NAME:String = "SmartNote.db"
        const val DATABASE_VERSION:Int = 1
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SmartNoteDatabaseContract.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}