package com.contactapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.adapter.ContactListAdapter;
import com.network.HTTPServiceHandler;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class ContactList extends Activity {
	ListView listView;
	ArrayList<String> name=new ArrayList<String>();
	ArrayList<String> phone=new ArrayList<String>();
	ArrayList<String> rel=new ArrayList<String>();
	ArrayList<String> mail=new ArrayList<String>();
	
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_list);
		ActionBar actionBar=getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setIcon(R.drawable.show_contacts);
		actionBar.setTitle(null);
		
		listView=(ListView)findViewById(R.id.contact_list);
		new Task().execute();
	}
	
	private class Task extends AsyncTask<Void, Void, Void>{
		ProgressDialog dialog;
		public Task(){
			this.dialog=new ProgressDialog(ContactList.this);
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				HTTPServiceHandler serviceClient = new HTTPServiceHandler();
			    String json = serviceClient.makeServiceCall("https://quiet-waters-9559.herokuapp.com/api/v1/contacts",HTTPServiceHandler.GET);
				Log.d("OkOk>>>>>>>>>", json);
				JSONObject obj1=new JSONObject(json.toString());
				JSONArray array=obj1.optJSONArray("contacts");
				JSONObject obj2;
				for (int i = 0; i < 3 ; i++) {
					obj2=array.getJSONObject(i);
					name.add(obj2.getString("name"));
					phone.add(obj2.getString("phone_number"));
					mail.add(obj2.getString("email"));
					rel.add(obj2.getString("relationship"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				listView.setDivider(null);
				listView.setAdapter(new ContactListAdapter(name, phone, rel, mail, ContactList.this));
				dialog.cancel();
			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.setMessage("Fatching Data ...");
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
		}
	}

}
