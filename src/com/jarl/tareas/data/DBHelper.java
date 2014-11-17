package com.jarl.tareas.data;

import com.jarl.tareas.data.TareasContract.TableName;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;

	public DBHelper(Context context){
		super(context, TareasContract.NAME_DATABASE, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TableName.TABLE_NAME + "("
				+ TableName._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ TableName.TITULO + " STRING,"
				+ TableName.CONTENIDO + " STRING"
				+ ")"
		);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TableName.TABLE_NAME);
		db.execSQL("CREATE TABLE " + TableName.TABLE_NAME + "("
				+ TableName._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ TableName.TITULO + " STRING,"
				+ TableName.CONTENIDO + " STRING"
				+ ")"
			);
	}
}
