package com.example.sportsfield;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder>{
	ArrayList<DocumentData> items = new ArrayList<>();



	public void addItem(DocumentData item) {
		items.add(item);
	}

	public void setItems(ArrayList<DocumentData> items) {
		this.items = items;
	}

	public DocumentData getItem(int position) {
		return items.get(position);
	}

	public void setItem(int position, DocumentData item) {
		items.set(position, item);
	}



	@NonNull
	@Override
	public DocumentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View itemView = inflater.inflate(R.layout.document_item,parent,false);

		return new ViewHolder(itemView);
	}


	@Override
	public void onBindViewHolder(@NonNull DocumentAdapter.ViewHolder holder, int position) {
		DocumentData item = items.get(position);
		holder.setItem(item);

	}


	@Override
	public int getItemCount() {
		return items.size();
	}

	//안되면지우기
	public void addItem(String member_id, String store_name, String address, String date, String time) {
	}


	public class ViewHolder extends RecyclerView.ViewHolder {
			TextView textView;
			TextView textView2;
			TextView textView3;
			TextView textView4;
			TextView textView5;

		public ViewHolder(View itemView) {
			super(itemView);

			textView = itemView.findViewById(R.id.userID);
			textView2 = itemView.findViewById(R.id.d_Store_name);
			textView3 = itemView.findViewById(R.id.d_Address);
			textView4 = itemView.findViewById(R.id.d_Date);
			textView5 = itemView.findViewById(R.id.d_Time);

		}




			public void setItem(DocumentData item) {
			textView.setText(item.getUserID());
			textView2.setText(item.getD_Store_name());
			textView3.setText(item.getD_Address());
			textView4.setText(item.getD_Date());
			textView5.setText(item.getD_Time());
		}

	}
}
