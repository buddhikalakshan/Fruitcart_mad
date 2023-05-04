package com.example.signup.DataBase

object UserProfile {


    object DeliveryProfile {
        // Table contents are grouped together in an anonymous object.
        object UserDetails : BaseColumns {
            const val TABLE_NAME = "UserDetails"
            const val COLUMN_1 = "UserName"
            const val COLUMN_2 = "email"
            const val COLUMN_3 = "password"
            const val COLUMN_4 ="DateOfBirth"
        }
}