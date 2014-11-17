package com.jarl.tareas;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jarl.tareas.data.DBHelper;
import com.jarl.tareas.data.TareasContract.TableName;

public class Informacion extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista);
		
		getActionBar();
				
		final EditText title = (EditText) findViewById(R.id.nombre);
		final EditText content = (EditText) findViewById(R.id.joel);
		
		final Button btn = (Button) findViewById(R.id.btn_query);
		final Button update = (Button) findViewById(R.id.update);
		final Button delete = (Button) findViewById(R.id.delete);
		
		Intent intent = getIntent();
		final String memberID = intent.getStringExtra("iden");

		//Convierto de String a Long ._ID
		long member_id = Long.parseLong(memberID);
		
		//Nuevo codigo...
		final DBHelper helper = new DBHelper(this);
		final SQLiteDatabase db = helper.getWritableDatabase();
		
		String[] columns = new String[]{ TableName._ID, TableName.TITULO, TableName.CONTENIDO };
		
		Cursor cursor = db.query(TableName.TABLE_NAME, columns, TableName._ID + "=" + memberID , null, null, null, null);
		
		if(cursor != null){
			cursor.moveToFirst();
			//String tb = cursor.getString(1);
			String n = cursor.getString(1);
			String ct = cursor.getString(2);
			
			title.setText(n);
			content.setText(ct);
		}
		//db.close();
		
		update.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View view){
				Toast.makeText(getApplicationContext(), "LISTO", Toast.LENGTH_LONG).show();
				String t = title.getText().toString();
				String cont = content.getText().toString();
				
				ContentValues values = new ContentValues();
				values.put(TableName.TITULO, t);
				values.put(TableName.CONTENIDO, cont);
				
				db.update(TableName.TABLE_NAME, values, TableName._ID + "=" + memberID, null);
				db.close();
				Intent home = new Intent(Informacion.this, MainActivity.class);
				startActivity(home);
			}
		});
		
		delete.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View view){
				db.delete(TableName.TABLE_NAME, TableName._ID + "=" + memberID, null);
				Intent home = new Intent(Informacion.this, MainActivity.class);
				startActivity(home);
				db.close();
			}
		});
	}
}


















