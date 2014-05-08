package com.victop.ibs.adapter;

import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.victop.ibs.activity.R;
import com.victop.ibs.util.Constants;
import com.victop.ibs.util.Container;
import com.victop.ibs.util.ImgCallBack;
import com.victop.ibs.util.Util;
import com.victop.ibs.view.SwipeListView;

public class ListImagesAdapter extends BaseAdapter {

	Context context;
	List<Map<String, String>> list;
	public Bitmap bitmaps[];
	Util until;
	float DownX;
	float UpX;
	// private List<Map<String, String>> mData; // �洢��editTexֵ
	private Integer index = -1;
	SwipeListView mSwipeListView;

	public ListImagesAdapter(Context context, List<Map<String, String>> list,
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
		if (convertView == null) {

			convertView = LayoutInflater.from(context).inflate(
					R.layout.listimagesitem, null);
			image = (ImageView) convertView.findViewById(R.id.image);
			editText = (EditText) convertView.findViewById(R.id.edit);
			textView = (TextView) convertView.findViewById(R.id.text);
			mBackEdit = (Button) convertView
					.findViewById(R.id.example_row_b_action_modify);
			mBackDelete = (Button) convertView
					.findViewById(R.id.example_row_b_action_del);
			editText.setTag(position);
			// textView.setTag(position);
			editText.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_UP) {
						index = (Integer) v.getTag();
					}
					return false;
				}
			});

			convertView.setTag(editText);
		} else {
			editText = (EditText) convertView.getTag();
			editText.setTag(position);
			// textView.setTag(position);
		}
		// System.out.println(position+"------------");
		Container.et_list.add(position, editText);
		Container.tv_list.add(position, textView);

		Constants.imageLoader.displayImage("file://"
				+ list.get(position).keySet().iterator().next(), image,
				Constants.image_display_options, null);

		editText.addTextChangedListener(new TextWatcher() {

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
				int position = (Integer) editText.getTag();
				if (s != null && !"".equals(s.toString())) {
					// Map<String, String> map =
					// Container.newData.get(position);
					Container.newData.get(position).put(
							list.get(position).keySet().iterator().next(),
							s.toString());

				} else {
					// Container.newData.get(position).put(
					// list.get(position).keySet().iterator().next(),
					// s.toString());
				}
			}
		});
		Object value = list.get(position).values().iterator().next();
		if (value != null && !"".equals(value)) {
			editText.setText(value.toString());
			textView.setText(value.toString());
		} else {
			editText.setText("");
			textView.setText("");
		}
		editText.clearFocus();
		if (index != -1 && index == position) {
			editText.requestFocus();
		}

		mBackDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
				mSwipeListView.closeAnimate(position);
				mSwipeListView.dismiss(position);

			}
		});
		// btn_delete.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Toast.makeText(context, position + "", Toast.LENGTH_SHORT)
		// .show();
		// Container.mData.remove(position);
		// list.remove(position);
		// notifyDataSetChanged();
		// btn_delete.setVisibility(View.GONE);
		//
		// }
		// });
		// image.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// showDialog(position);
		//
		// }
		// });
		return convertView;
	}

	public void showDialog(int position) {
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		View view = LayoutInflater.from(context).inflate(
				R.layout.imgshowdialog, null);
		dialog.setView(view);
		ImageView images = (ImageView) view.findViewById(R.id.bigimage);

		Constants.imageLoader.displayImage("file://"
				+ list.get(position).keySet().iterator().next(), images,
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

	ImageView image;
	EditText editText;
	TextView textView;
	Button mBackEdit, mBackDelete;
	//
	// class Holder {
	// ImageView image;
	// EditText editText;
	// TextView textView;
	// Button mBackEdit, mBackDelete;
	// // Button btn_delete, btn_del;
	// }

}
