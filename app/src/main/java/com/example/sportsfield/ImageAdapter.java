package com.example.sportsfield;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> implements Filterable {
	RecyclerView recyclerView;
	public static Context context;



	//검색처리하려면 기존 아이템을 담고있는 items 변수하나
	//필터링되지않은 unFilterdList 변수 생성
	static ArrayList<ImageData> items = new ArrayList<ImageData>();
	ArrayList<ImageData> unFilteredList = new ArrayList<ImageData>();

	AdapterView.OnItemClickListener listener;

	//기존에 있는거와 동일한이름인데 하나 새로 정의함.
	//이벤트를 처리하는게 리스트뷰같은 경우에는 setOnItemClickListener()라고 미리정의된게 있는데
	//리싸이클러뷰는 그게 없으니까 리스트뷰처럼 사용할 수 있도록 이렇게 리스너관련된걸 따로 추가해준거다.


	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		listener.onItemClick(parent, view, position, id);
	}

	public void addItem(ImageData item) {
		items.add(item);
	}

	public void setItems(ArrayList<ImageData> items) {
		this.items = items;
	}

	public ImageData getItem(int position) {
		return items.get(position);
	}

	public void setItem(int position, ImageData item) {
		items.set(position, item);
	}



	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater.from(parent.getContext()));
		View itemView = inflater.inflate(R.layout.homesport_item, parent, false);
		return new ViewHolder(itemView);

	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

		final ImageData item = items.get(position);
		holder.setItem(item);

		//아이템 클릭시 recyclerViewSelect 액티비티로 이동 하면서 값들을 넘겨준다
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RecyclerViewSelect.class);
				intent.putExtra("StoreName", item.getStore_name());
				intent.putExtra("AddressName",item.getAddress());
				intent.putExtra("FloorName", item.getFloor());
				intent.putExtra("Latitude",item.getLatitude());
				intent.putExtra("Longitude", item.getLongitude());

				v.getContext().startActivity(intent);
			}
		});



	}

	public ImageAdapter(Context context, ArrayList<ImageData> list) {
		super();
		this.context = context;
		this.items = list;
		this.unFilteredList = list;
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	@Override
	public Filter getFilter() {
		return new Filter() {
			//performFiltering 함수에서 필터된 결과를 UI갱신 역할 수행
			@Override
			public void publishResults(CharSequence constraint, FilterResults results) {
				items = (ArrayList<ImageData>) results.values;

				notifyDataSetChanged();

			}

			//필터링 수행하는 함수, 필터링된 결과 리스트를 FliterResults에 담아 리턴
			@Override
			public FilterResults performFiltering(CharSequence constraint) {
				String charString = constraint.toString();
				if (charString.isEmpty()) {
					Log.d("1", "k");
					items = unFilteredList;
				} else {
					Log.d("2", "k");
					ArrayList<ImageData> filteringList = new ArrayList<>();
					for (ImageData row : unFilteredList) {
						if (row.getStore_name().contains(charString.toLowerCase()) ||
								row.getAddress().contains(constraint)) {
							filteringList.add(row);
						}
					}
					items = filteringList;


				}
				FilterResults filterResults = new FilterResults();
				filterResults.values = items;
				return filterResults;

			}

		};
	}


	static class ViewHolder extends RecyclerView.ViewHolder {
		TextView textView;
		TextView textView2;
		TextView textView3;



		public ViewHolder(final View itemView) {
			super(itemView);

			textView = itemView.findViewById(R.id.store_name);
			textView2 = itemView.findViewById(R.id.address);
			textView3 = itemView.findViewById(R.id.floor);

		}

		public void setItem(ImageData item) {
			textView.setText(item.getStore_name());
			textView2.setText(item.getAddress());
			textView3.setText(item.getFloor());

		}
	}



}




