package com.contactapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.network.HTTPServiceHandler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnClickListener{
	ViewFlipper vf;
	Button add_contact,back,clear,save;
	EditText name,phone,mail,rel;
	Animation inAnim,outAnim;
	public String _name,_phone,_mail,_rel;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        vf=(ViewFlipper)findViewById(R.id.vf);
        add_contact=(Button)findViewById(R.id.addContact);
        back=(Button)findViewById(R.id.back);
        clear=(Button)findViewById(R.id.clear);
        save=(Button)findViewById(R.id.save);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        mail=(EditText)findViewById(R.id.mail);
        rel=(EditText)findViewById(R.id.relation);
        
        inAnim=AnimationUtils.loadAnimation(this, R.anim.in);
        outAnim=AnimationUtils.loadAnimation(this, R.anim.out);
        
        vf.setInAnimation(inAnim);
        vf.setOutAnimation(outAnim);
        
        add_contact.setOnClickListener(this);
        back.setOnClickListener(this);
        clear.setOnClickListener(this);
        save.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.addContact:
			vf.showNext();
			break;
		case R.id.back:
			vf.showPrevious();
			break;
		case R.id.clear:
			name.setText("");
			phone.setText("");
			mail.setText("");
			rel.setText("");
			break;
		case R.id.save:
			_name=null;
			_phone=null;
			_mail=null;
			_rel=null;
			_name=name.getText().toString();
			_phone=phone.getText().toString();
			_mail=mail.getText().toString();
			_rel=rel.getText().toString();
			new Store().execute();
			break;

		default:
			break;
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()){
		case R.id.show_contacts:
			Intent i=new Intent(MainActivity.this, ContactList.class);
			startActivity(i);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class Store extends AsyncTask<Void, Void, Void>{
		private boolean status=false;
		ProgressDialog dialog;
		public Store(){
			dialog=new ProgressDialog(MainActivity.this);
			dialog.setMessage("Please Wait While Storing Data");
		}
		@Override
		protected Void doInBackground(Void... params1) {
			// TODO Auto-generated method stub
			
		    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		    params.add(new BasicNameValuePair("name", _name));
		    params.add(new BasicNameValuePair("phone_number", _phone));
		    params.add(new BasicNameValuePair("email", _mail));
		    params.add(new BasicNameValuePair("relationship", _rel));
		    
		    HTTPServiceHandler serviceClient = new HTTPServiceHandler();
		    String json = serviceClient.makeServiceCall("https://quiet-waters-9559.herokuapp.com/api/v1/contacts",HTTPServiceHandler.POST, params);
			Log.e("Response", json);
			
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.hide();
			if(status==true){
				
			}else {
				
			}
		}
	}
}
