package com.example.signup.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DBHandler {

    class DBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(SQL_CREATE_ENTRIES)
        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES)
            onCreate(db)
        }
        override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            onUpgrade(db, oldVersion, newVersion)
        }
        companion object {
            // If you change the database schema, you must increment the database version.
            const val DATABASE_VERSION = 1
            const val DATABASE_NAME = "UserDetails.db"
        }

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${DeliveryProfile.DeliveryDetails.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${UserProfile.UserDetails.COLUMN_1} TEXT," +
                    "${UserProfile.UserDetails.COLUMN_2} TEXT," +
                    "${UserProfile.UserDetails.COLUMN_3} TEXT," +
                    "${UserProfile.UserDetails.COLUMN_4} TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${UserProfile.UserDetails.TABLE_NAME}"

// Insert Function
        fun addInfo(UserName: String, email: String, password: String, DateOfBirth: String): Long? {
            // Gets the data repository in write mode
            val db = writableDatabase

            // Create a new map of values, where column names are the keys
            val values = ContentValues().apply {
                put(UserProfile.UserDetails.COLUMN_1, UserName)
                put(UserProfile.UserDetails.COLUMN_2, email)
                put(UserProfile.UserDetails.COLUMN_3, password)
                put(UserProfile.UserDetails.COLUMN_4, DateOfBirth)
            }

            // Insert the new row, returning the primary key value of the new row
            val newRowId = db?.insert(UserProfile.UserDetails.TABLE_NAME, null, values)

            retur newRowId
            }
        //Update fuction

        fun updateInfo(UserName: String, email: String, password: String, DateOfBirth: String){
            val db = writableDatabase

            // New value for one column
            val values = ContentValues().apply {
                put(UserProfile.UserDetails.COLUMN_2, email)
                put(UserProfile.UserDetails.COLUMN_3, password)
                put(UserProfile.UserDetails.COLUMN_4, DateOfBirth)
            }

            // Which row to update, based on the title
            val selection = "${UserProfile.UserDetails.COLUMN_1} LIKE ?"
            val selectionArgs = arrayOf(UserName)
            val count = db.update(
                UserProfile.UserDetails.TABLE_NAME,
                values,
                selection,
                selectionArgs)
            }

        //delete
        fun deleteInfo(PNumber: String){
            val db = writableDatabase

            // Define 'where' part of query.
            val selection = "${UserProfile.UserDetails.COLUMN_2} LIKE ?"
            // Specify arguments in placeholder order.
            val selectionArgs = arrayOf(email)
            // Issue SQL statement.
            val deletedRows = db.delete(UserProfile.UserDetails.TABLE_NAME, selection, selectionArgs)
            }

        //read data
        fun readAllInfo(): MutableList<Long> {
            val Name: String = "buddhika"
            val db = readableDatabase

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            val projection = arrayOf(BaseColumns._ID, UserProfile.UserDetails.COLUMN_1
                , UserProfile.UserDetails.COLUMN_2
                , UserProfile.UserDetails.COLUMN_3
                , UserProfile.UserDetails.COLUMN_4)

            // Filter results WHERE "title" = 'My Title'
            val selection = "${UserProfile.UserDetails.COLUMN_1} = ?"
            val selectionArgs = arrayOf(UserName)

            // How you want the results sorted in the resulting Cursor
            val sortOrder = "${UserProfile.UserDetails.COLUMN_1} DESC"

            val cursor = db.query(
                UserProfile.UserDetails.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
            )

            val UserData = mutableListOf<Long>()
            with(cursor) {
                while (moveToNext()) {
                    val UserName:String = getString(getColumnIndexOrThrow(UserProfile.UserDetails.COLUMN_1))
                    val email = getString(getColumnIndexOrThrow(UserProfile.UserDetails.COLUMN_2))
                    val password = getString(getColumnIndexOrThrow(UserProfile.UserDetails.COLUMN_3))
                    val DateOfBirth = getString(getColumnIndexOrThrow(UserProfile.UserDetails.COLUMN_4))
                    UserData.add(Name)//0
                    UserData.add(Address)//1
                    UserData.add(Email)//2
                    UserData.add(PNumber)//3
                }
            }
            cursor.close()
            return UserData
            }


    }