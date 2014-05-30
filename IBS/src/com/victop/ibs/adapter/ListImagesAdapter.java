package com.victop.ibs.adapter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.victop.ibs.activity.ImgFileListActivity;
import com.victop.ibs.activity.ImgShowActivity;
import com.victop.ibs.activity.R;
import com.victop.ibs.bean.Entity;
import com.victop.ibs.util.Constants;
import com.victop.ibs.util.Container;
import com.victop.ibs.util.ImgCallBack;
import com.victop.ibs.util.Util;
import com.victop.ibs.view.SwipeListView;

public class ListImagesAdapter extends BaseAdapter {

	Context context;
	List<Entity> list;
	public Bitmap bitmaps[];
	Util until;
	float DownX;
	float UpX;
	// private List<Map<String, String>> mData; // �洢��editTexֵ
	private Integer index = -1;
	SwipeListView mSwipeListView;

	public ListImagesAdapter(Context context, List<Entity> list,
			SwipeListView mSwipeListView) {
		this.context = context;
		this.list = list;
		this.mSwipeListView = mSwipeListView;
		bitmaps = new Bitmap[list.size()];
		until = new Util(context);

	}

	// public void setData(List<Map<String, String>> data) {
	// Container.mData = data;
	// }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.listimagesitem, null);
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.editText = (EditText) convertView.findViewById(R.id.edit);
			holder.textView = (TextView) convertView.findViewById(R.id.text);
			holder.mBackEdit = (Button) convertView
					.findViewById(R.id.example_row_b_action_modify);
			holder.mBackDelete = (Button) convertView
					.findViewById(R.id.example_row_b_action_del);
			holder.editText.setTag(position);
			holder.textView.setTag(position);
			holder.editText.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_UP) {
						index = (Integer) v.getTag();
					}
					return false;
				}
			});

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
			holder.editText.setTag(position);
			holder.textView.setTag(position);
		}
		// Container.et_list.add(position, holder.editText);
		// Container.tv_list.add(position, holder.textView);
		Container.et_Map.put(position, holder.editText);
		Container.tv_Map.put(position, holder.textView);
		Constants.imageLoader.displayImage("file://"
				+ list.get(position).getURL(), holder.image,
				Constants.image_display_options, null);
		Bitmap bit = Constants.GetLocalOrNetBitmap("file://"
				+ list.get(position).getURL());
		list.get(position).setHieght(bit.getHeight() + "");
		list.get(position).setWidth(bit.getWidth() + "");
		((ImgShowActivity) context).img_list.get(position).setHieght(
				bit.getHeight() + "");
		bit = null;
		holder.editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				int position = (Integer) holder.editText.getTag();
				if (s != null && !"".equals(s.toString())) {

					int count = 0;
					for (Entity r : ((ImgShowActivity) context).img_list) {
						if (r.getURL().equals(list.get(position).getURL())) {

							r.setText(s.toString());
							count = 1;
						}
						if (count == 1) {
							break;
						}
					}

				} else {
					// Container.newData.get(position).put(
					// list.get(position).keySet().iterator().next(),
					// s.toString());
				}
			}
		});
		Object value = list.get(position).getText();
		if (value != null && !"".equals(value)) {
			holder.editText.setText(value.toString());
			holder.textView.setText(value.toString());
		} else {
			holder.editText.setText("");
			holder.textView.setText("");
		}
		holder.editText.clearFocus();
		if (index != -1 && index == position) {
			holder.editText.requestFocus();
		}

		holder.mBackDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
				mSwipeListView.closeAnimate(position);
				mSwipeListView.dismiss(position);

			}
		});
		holder.mBackEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				mSwipeListView.closeAnimate(position);
				Bundle pBundle = new Bundle();
				pBundle.putString("edit", position + "");
				Intent _Intent = new Intent(context, ImgFileListActivity.class);
				if (pBundle != null) {
					_Intent.putExtras(pBundle);
				}
				context.startActivity(_Intent);
				// overridePendingTransition(R.anim.new_in_from_right,
				// R.anim.new_out_to_left);
			}
		});

		return convertView;
	}

	public void showDialog(int position) {
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		View view = LayoutInflater.from(context).inflate(
				R.layout.imgshowdialog, null);
		dialog.setView(view);
		ImageView images = (ImageView) view.findViewById(R.id.bigimage);

		Constants.imageLoader.displayImage("file://"
				+ list.get(position).getURL(), images,
				Constants.image_display_options, null);
		dialog.show();

		images.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

	}

	public class ImgClallBackLisner implements ImgCallBack {
		int num;

		public ImgClallBackLisner(int num) {
			this.num = num;
		}

		@Override
		public void resultImgCall(ImageView imageView, Bitmap bitmap) {
			bitmaps[num] = bitmap;
			imageView.setImageBitmap(bitmap);
		}
	}

	class Holder {
		ImageView image;
		EditText editText;
		TextView textView;
		Button mBackEdit, mBackDelete;
		// Button btn_delete, btn_del;
	}

}
