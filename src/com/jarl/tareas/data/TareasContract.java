package com.jarl.tareas.data;

import android.provider.BaseColumns;

public class TareasContract {

	public static final String NAME_DATABASE = "tareas.db";
	
	private TareasContract(){}
	
	public static class TableName implements BaseColumns{
		
		private TableName(){}
		
		public static final String TABLE_NAME = "tareas";
		
		public static final String TITULO = "titulo";
		public static final String CONTENIDO = "contenido";
	}
}
