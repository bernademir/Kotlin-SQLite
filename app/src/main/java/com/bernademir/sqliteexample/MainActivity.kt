package com.bernademir.sqliteexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            //db olusturma
            val database = this.openOrCreateDatabase("exampledb", MODE_PRIVATE, null)

            //tablo olusturma
            database.execSQL("CREATE TABLE IF NOT EXISTS exampletable (id INTEGER PRIMARY KEY, column1 VARCHAR, column2 INT)")

            //veri ekleme
            database.execSQL("INSERT INTO exampletable (column1, column2) VALUES ('value1', 10)")
            database.execSQL("INSERT INTO exampletable (column1, column2) VALUES ('value2', 20)")
            database.execSQL("INSERT INTO exampletable (column1, column2) VALUES ('value3', 30)")
            database.execSQL("INSERT INTO exampletable (column1, column2) VALUES ('value4', 40)")

            //veri silme
            database.execSQL("DELETE FROM exampletable WHERE id = 2")

            //veri guncelleme
            database.execSQL("UPDATE exampletable SET column1 = 'updated value' WHERE id = 4")

            //veri okuma
            val cursor = database.rawQuery("SELECT * FROM exampletable", null)

            val idColumnIndex = cursor.getColumnIndex("id")
            val column1ColumnIndex = cursor.getColumnIndex("column1")
            val column2ColumnIndex = cursor.getColumnIndex("column2")

            while (cursor.moveToNext()){
                println("id : ${cursor.getInt(idColumnIndex)}")
                println("column1 : ${cursor.getString(column1ColumnIndex)}")
                println("column2 : ${cursor.getString(column2ColumnIndex)}")
            }
            cursor.close()

        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}