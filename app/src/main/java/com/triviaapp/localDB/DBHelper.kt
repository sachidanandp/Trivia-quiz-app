package com.triviaapp.localDB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.triviaapp.model.QuizHistory
import java.util.*

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        private const val DATABASE_NAME = "Quiz.db"
        const val TABLE_NAME = "quizHistory"
        const val COLUMN_ID = "id"
        const val NAME = "name"
        const val QUESTION1 = "question1"
        const val QUESTION2 = "question2"
        const val ANSWER1 = "answer1"
        const val ANSWER2 = "answer2"
        const val DATE_TIME = "dateTime"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // TODO Auto-generated method stub
        db.execSQL(
            "create table $TABLE_NAME ($COLUMN_ID integer primary key AUTOINCREMENT, $NAME verchar, $QUESTION1 verchar, $ANSWER1 verchar, $QUESTION2 verchar, $ANSWER2 verchar," +
                    " $DATE_TIME)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertQuiz(model: QuizHistory) {
        val db: SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, model.name)
        contentValues.put(QUESTION1, model.question1)
        contentValues.put(QUESTION2, model.question2)
        contentValues.put(ANSWER1, model.answer1)
        contentValues.put(ANSWER2, model.answer2)
        contentValues.put(DATE_TIME, model.dateTime)
        db.insert(TABLE_NAME, null, contentValues)
    }

    fun getData(id: Int): Cursor? {
        val db: SQLiteDatabase = this.readableDatabase
        return db.rawQuery("select * from $TABLE_NAME where id=$id", null)
    }


    fun deleteContact(id: Int?): Int? {
        val db: SQLiteDatabase = this.writableDatabase
        return db.delete(
            TABLE_NAME,
            "id = ? ", arrayOf(Integer.toString(id!!))
        )
    }

    fun getAllRecentCallList(): ArrayList<QuizHistory>? {
        val arrayList: ArrayList<QuizHistory> = ArrayList()

        //hp = new HashMap();
        val db: SQLiteDatabase = this.readableDatabase
        val res = db.rawQuery("select * from $TABLE_NAME ORDER BY id desc", null)
        res.moveToFirst()
        while (!res.isAfterLast) {
            val model = QuizHistory()
            model.name = res.getString(res.getColumnIndex(NAME))
            model.question1 = res.getString(res.getColumnIndex(QUESTION1))
            model.answer1 = res.getString(res.getColumnIndex(ANSWER1))
            model.question2 = res.getString(res.getColumnIndex(QUESTION2))
            model.answer2 = res.getString(res.getColumnIndex(ANSWER2))
            model.dateTime = res.getString(res.getColumnIndex(DATE_TIME))

            arrayList.add(model)
            res.moveToNext()
        }
        res.close()
        return arrayList
    }

}