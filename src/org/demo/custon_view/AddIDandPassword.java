package org.demo.custon_view;



import java.io.Externalizable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.demo.custon_view.SecondActivity;

public class AddIDandPassword extends Activity{
	public final static String EXTRA_MESSAGE1 = "com.example.myfirstapp.MESSAGE1";
	public final static String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE2";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addid);
		getIntent();
		
	}
	public void sendIDandPassword(View view){
		Intent intent = new Intent(this, SecondActivity.class);
		EditText eT1 = (EditText) findViewById(R.id.editText2);
		EditText eT2 = (EditText) findViewById(R.id.editText1);
		String id = eT1.getText().toString();
		String pass = eT2.getText().toString();
		
//		sp=getSharedPreferences("SPnew", MODE_PRIVATE);
//		Editor editor = sp.edit();
//        
//        editor.putString("usrname"+num_of_item,id );
//        editor.putString("password"+num_of_item, password);
//        editor.commit();
		
		intent.putExtra(EXTRA_MESSAGE1, id);
		intent.putExtra(EXTRA_MESSAGE2, pass);
		startActivity(intent);
	}
}
