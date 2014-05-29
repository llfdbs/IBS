package com.victop.ibs.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.victop.ibs.activity.R;
import com.victop.ibs.util.ImgCallBack;
import com.victop.ibs.util.Util;

public class ImgsAdapter extends BaseAdapter {

	Context context;
	List<String> data;
	public Bitmap bitmaps[];
	Util util;
	OnItemClickClass onItemClickClass;
	private int index = -1;
	private String img_position = "";
	List<View> holderlist;
	// 定义一个向量作为选中与否容器
	private Vector<Boolean> mImage_bs = new Vector<Boolean>();

	// 记录上一次选中的图片位置，-1表示未选中任何图片
	private int lastPosition = -1;

	public ImgsAdapter(Context context, List<String> data,
			OnItemClickClass onItemClickClass, String img_position) {
		this.context = context;
		this.data = data;
		this.img_position = img_position;
		this.onItemClickClass = onItemClickClass;
		bitmaps = new Bitmap[data.size()];
		util = new Util(context);
		holderlist = new ArrayList<View>();
		for (int i = 0; i < data.size(); i++) {
			mImage_bs.add(false);
		}
	}

	@Override
	public int getCount() {
		return data.size();
	}

	// 修改选中的状态
	public void changeState(int position) {

		if (lastPosition != -1) {
			// 取消上一次的选中状态
			mImage_bs.setElementAt(false, lastPosition);
		}
		// 直接取反即可
		mImage_bs.setElementAt(!mImage_bs.elementAt(position), position);
		lastPosition = position; // 记录本次选中的位置
		notifyDataSetChanged(); // 通知适配器进行更新
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		Holder holder;
		if (arg0 != index && arg0 > index) {
			index = arg0;
			arg1 = LayoutInflater.from(context)
					.inflate(R.layout.imgsitem, null);
			holder = new Holder();
			holder.imageView = (ImageView) arg1.findViewById(R.id.imageView1);
			holder.checkBox = (CheckBox) arg1.findViewById(R.id.checkBox1);
			arg1.setTag(holder);
			holderlist.add(arg1);
		} else {
			holder = (Holder) holderlist.get(arg0).getTag();
			arg1 = holderlist.get(arg0);
		}
		if (bitmaps[arg0] == null) {
			util.imgExcute(holder.imageView, new ImgClallBackLisner(arg0),
					data.get(arg0));
		} else {
			holder.imageView.setImageBitmap(bitmaps[arg0]);

		}
		if ("".equals(img_position)) {

		} else {
			if (mImage_bs.elementAt(arg0)) {
				holder.checkBox.setChecked(true);
			} else {
				holder.checkBox.setChecked(false);
			}

		}
		arg1.setOnClickListener(new OnPhotoClick(arg0, holder.checkBox));
		return arg1;
	}

	class Holder {
		ImageView imageView;
		CheckBox checkBox;
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

	public interface OnItemClickClass {
		public void OnItemClick(View v, int Position, CheckBox checkBox);
	}

	class OnPhotoClick implements OnClickListener {
		int position;
		CheckBox checkBox;

		public OnPhotoClick(int position, CheckBox checkBox) {
			this.position = position;
			this.checkBox = checkBox;
		}

		@Override
		public void onClick(View v) {
			if (data != null && onItemClickClass != null) {
				onItemClickClass.OnItemClick(v, position, checkBox);
			}
		}
	}

}