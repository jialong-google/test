package org.demo.custon_view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;




import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;


//这是经过验证后的第二个窗口
public class SecondActivity extends ListActivity{//BaseActivity {
    /** Called when the activity is first created. */
   // @Override
    //public void onCreate(Bundle savedInstanceState) {
//    	super.onCreate(savedInstanceState);
//		setContentView(R.layout.main);
       //	TextView tv=new TextView(this);
       //	tv.setText("Second");
       //	setContentView(tv);
       // 	setContentView(R.layout.);
   // }
	
	
	private static int num_of_item=0;
	//private static Context ctx1;   
	//private static SharedPreferences sp1;
//	private static Context ctx;
	public static SharedPreferences sp;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		
//		ctx = SecondActivity.this;       
//        sp = ctx.getSharedPreferences("SPnew", SecondActivity.MODE_PRIVATE);
	    sp = getSharedPreferences("SPnew", MODE_PRIVATE);
		//初始化两个读取字符串
        String name_id="none";
        String string_password="none";
        
        
		
		ListView list=(ListView) getListView();
		
		ArrayList<HashMap<String,String>> mylist=new ArrayList<HashMap<String,String>>();
		
		//for(int i=0;i<3;i++)
		//{
		//	HashMap<String,String> map =new HashMap<String,String>();
		//	map.put("ItemTitle","Title");
		//	map.put("ItemText","Content");
		//	mylist.add(map);
		//}
		SimpleAdapter mSchedule=new SimpleAdapter(this,mylist,R.layout.listitem,new String[]{"ItemTitle","ItemText"},new int[]{R.id.ItemTitle,R.id.ItemText});
		
		
		list.setAdapter(mSchedule);
		//显示过去存储过的信息
		for(int i=0;i<num_of_item;i++){
        	
        	name_id =sp.getString("usrname"+i, "none");
        	string_password=sp.getString("password"+i, "none");
        	System.out.println("usrname"+i+": "+name_id);
        	System.out.println("password"+i+": "+string_password);
        	if(name_id!="none" || string_password!="none"){
        		HashMap<String,String> map =new HashMap<String,String>();
    			map.put("ItemTitle",name_id);
    			map.put("ItemText",string_password);
    			mylist.add(map);
        	}
        }
		//若得到了intent使之添加信息
		Intent intent=getIntent();
		
		String id=intent.getStringExtra(AddIDandPassword.EXTRA_MESSAGE1);
		String password=intent.getStringExtra(AddIDandPassword.EXTRA_MESSAGE2);
		if(id!=null && password!=null){
			HashMap<String,String> map =new HashMap<String,String>();
			map.put("ItemTitle",id);
			map.put("ItemText",password);
			mylist.add(map);
		
		
		//获取SharedPreferences对象
//        ctx = SecondActivity.this;       
//        sp = ctx.getSharedPreferences("SPnew", SecondActivity.MODE_PRIVATE);
//		 sp = getSharedPreferences("SPnew", MODE_PRIVATE);
		//存入数据
//        Editor editor = sp.edit();
//        
//        editor.putString("usrname"+num_of_item,id );
//        editor.putString("password"+num_of_item, password);
//        editor.commit();
        //信息的item个数加一
        num_of_item++;
        Log.d("lijialong","num_of_item: "+num_of_item);
		}
        
        
        
        
		
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1:
            	startActivity(new Intent(SecondActivity.this,AddIDandPassword.class));
                return true;
            case R.id.item2:
            	startActivity(new Intent(SecondActivity.this,GesturePassActivity.class));
                return true;
            default:
            	return false;
                //return super.onOptionsItemSelected(item);
        }
    }
//    @Override
//    public boolean onStop(){}
}


	      
