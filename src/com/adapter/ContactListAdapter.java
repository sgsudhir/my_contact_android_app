package com.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.contactapp.R;

public class ContactListAdapter extends BaseAdapter {
	ArrayList<String> name;
	ArrayList<String> phone;
	ArrayList<String> rel;
	ArrayList<String> mail;
	LayoutInflater layoutInflater;
	public ContactListAdapter(ArrayList<String> name, ArrayList<String> phone, ArrayList<String> rel, ArrayList<String> mail, Context context) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.phone=phone;
		this.rel=rel;
		this.mail=mail;
		this.layoutInflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.name.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("ViewHolder") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row;
		TextView name,mail,phone,rel;
		
		row=layoutInflater.inflate(R.layout.list_view_row, parent,false);
		
		name=(TextView)row.findViewById(R.id.row_name);
		phone=(TextView)row.findViewById(R.id.row_phone);
		rel=(TextView)row.findViewById(R.id.row_relationship);
		mail=(TextView)row.findViewById(R.id.row_mail);
		
		name.setText(this.name.get(position));
		phone.setText(this.phone.get(position));
		rel.setText(this.rel.get(position));
		mail.setText(this.mail.get(position));
		
		return row;
	}

}
