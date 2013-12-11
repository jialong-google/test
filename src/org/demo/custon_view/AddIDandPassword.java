package org.demo.custon_view;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.demo.custon_view.SecondActivity;

public class AddIDandPassword extends Activity{
	public final static String EXTRA_MESSAGE1 = "com.example.myfirstapp.MESSAGE1";
	public final static String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE2";
	public final static String XiuGai1="com.example.myfirstapp.XiuGai1";
	public final static String XiuGai2="com.example.myfirstapp.XiuGai2";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.addid);
		Intent intent=getIntent();
		EditText et1=(EditText) findViewById(R.id.editText2);
		EditText et2=(EditText) findViewById(R.id.editText1);
		et1.setText(intent.getStringExtra(XiuGai1));
		et2.setText(intent.getStringExtra(XiuGai2));
	}
	public void sendIDandPassword(View view){
		Intent intent = new Intent(this, SecondActivity.class);
		EditText eT1 = (EditText) findViewById(R.id.editText2);
		EditText eT2 = (EditText) findViewById(R.id.editText1);
		String id = eT1.getText().toString();
		String pass = eT2.getText().toString();
		intent.putExtra(EXTRA_MESSAGE1, id);
		intent.putExtra(EXTRA_MESSAGE2, pass);
		startActivity(intent);
	}
}
