package com.jarl.tareas;

import com.jarl.tareas.data.DBHelper;
import com.jarl.tareas.data.TareasContract.TableName;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTareas extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_tareas);
		
		ActionBar actionBar = getActionBar();
		
		final DBHelper helper = new DBHelper(this);
		final SQLiteDatabase db = helper.getWritableDatabase();
		
		final EditText titulo = (EditText) findViewById(R.id.titulo);
		final EditText contenido = (EditText) findViewById(R.id.content);
		final Button enviar = (Button) findViewById(R.id.enviar);
		final Button eliminar = (Button) findViewById(R.id.eliminar);
		
		eliminar.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View view){
				db.delete(TableName.TABLE_NAME, null, null);
				db.close();
				
				Intent main = new Intent(AddTareas.this, MainActivity.class);
				startActivity(main);
				
				Toast.makeText(AddTareas.this, "eliminado", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		enviar.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View view){
				
				String title = titulo.getText().toString();
				String content = contenido.getText().toString();
				
				ContentValues values = new ContentValues();
				values.put(TableName.TITULO, title);
				values.put(TableName.CONTENIDO, content);
				
				db.insert(TableName.TABLE_NAME, null, values);
				db.close();
				
				Intent lista = new Intent(AddTareas.this, MainActivity.class);
				startActivity(lista);
			}
		});
	}

}
