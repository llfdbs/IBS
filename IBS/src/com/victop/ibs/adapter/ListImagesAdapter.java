package com.victop.ibs.adapter;

import java.util.ArrayList;
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
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.victop.ibs.activity.R;
import com.victop.ibs.util.Constants;
import com.victop.ibs.util.Container;
import com.victop.ibs.util.ImgCallBack;
import com.victop.ibs.util.Util;

public class ListImagesAdapter extends BaseAdapter {

	Context context;
	ArrayList<String> list;
	public Bitmap bitmaps[];
	Util until;
	float DownX;
	float UpX;
	// private List<Map<String, String>> mData; // �洢��editTexֵ
	private Integer index = -1;

	public ListImagesAdapter(Context context, ArrayList<String> list) {
		this.context = context;
		this.list = list;
		bitmaps = new Bitmap[list.size()];
		until = new Util(context);

	}

	public void setData(List<Map<String, String>> data) {
		Container.mData = data;
	}

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
			holder.btn_delete = (Button) convertView.findViewById(R.id.delete);
			holder.btn_del = (Button) convertView.findViewById(R.id.btn_del);
			holder.editText.setTag(position);
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
		}
		Constants.imageLoader.displayImage("file://" + list.get(position),
				holder.image, Constants.image_display_options, null);

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
					// int position = (Integer) holder.editText.getTag();
					// ��EditText��ݷ���ı��ʱ��浽data������
					Container.mData.get(position).put("list_item_inputvalue",
							s.toString());
				} else {
					// Toast.makeText(context,
					// "ͼƬ��������Ϊ��,��������������",Toast.LENGTH_SHORT).show();
					Container.mData.get(position).put("list_item_inputvalue",
							"");
				}
			}
		});
		Object value = Container.mData.get(position)
				.get("list_item_inputvalue");
		if (value != null && !"".equals(value)) {
			holder.editText.setText(value.toString());
		} else {
			holder.editText.setText("");
		}
		holder.editText.clearFocus();
		if (index != -1 && index == position) {
			holder.editText.requestFocus();
		}
		holder.image.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				if (holder.btn_delete.getVisibility() == View.VISIBLE) {
					holder.btn_delete.setVisibility(View.GONE);
				} else {
					holder.btn_delete.setVisibility(View.VISIBLE);
				}
				return true;
			}
		});
		holder.btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, position + "", Toast.LENGTH_SHORT)
						.show();
				Container.mData.remove(position);
				list.remove(position);
				notifyDataSetChanged();
				holder.btn_delete.setVisibility(View.GONE);

			}
		});
		holder.image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				showDialog(position);

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

		Constants.imageLoader.displayImage("file://" + list.get(position),
				images, Constants.image_display_options, null);
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
		Button btn_delete, btn_del;
	}

}
