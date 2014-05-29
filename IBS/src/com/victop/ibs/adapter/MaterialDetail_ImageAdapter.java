package com.victop.ibs.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.android.bitmapfun.util.ImageFetcher;
import com.victop.ibs.activity.MaterialDetailActivity;
import com.victop.ibs.activity.R;
import com.victop.ibs.bean.MaterialDetailPictureBean;

public class MaterialDetail_ImageAdapter extends BaseAdapter {
	// private List<String> imageUrls; // 图片地址list
	private List<MaterialDetailPictureBean> imageUrls;
	private Context context;
	private MaterialDetail_ImageAdapter self;
	Uri uri;
	Intent intent;
	ImageView imageView;
	ImageFetcher mImageFetcher;

	public MaterialDetail_ImageAdapter(Context context,
			List<MaterialDetailPictureBean> imageUrls) {
		this.imageUrls = imageUrls;
		this.context = context;
		this.self = this;
		mImageFetcher = new ImageFetcher(context, 240);
		mImageFetcher.setLoadingImage(R.drawable.empty_photo);
		mImageFetcher.setExitTasksEarly(false);
	}

	public int getCount() {
		return Integer.MAX_VALUE;
	}

	public Object getItem(int position) {
		return imageUrls.get(position % imageUrls.size());
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("unused")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				switch (msg.what) {
				case 0: {
					self.notifyDataSetChanged();
				}
					break;
				}

				super.handleMessage(msg);
			} catch (Exception e) {
			}
		}
	};

	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		// Bitmap image;
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.materialdetail_galleryitem, null); // 实例化convertView
			Gallery.LayoutParams params = new Gallery.LayoutParams(
					Gallery.LayoutParams.FILL_PARENT, 300);
			convertView.setLayoutParams(params);
			holder.iv = (ImageView) convertView
					.findViewById(R.id.gallery_image);

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
			// image = (Bitmap) convertView.getTag();
		}

//		Constants.imageLoader.displayImage(
//				"file://"
//						+ imageUrls.get(position % imageUrls.size())
//								.getImgurl(), holder.iv,
//				Constants.image_display_options, null);
		holder.iv.setScaleType(ImageView.ScaleType.FIT_XY);
		// holder.iv.setImageResource(imgs[position % imgs.length]);
		// 设置缩放比例：保持原样
		// holder.iv.setScaleType(ImageView.ScaleType.FIT_XY);

		((MaterialDetailActivity) context).changePointView(position
				% imageUrls.size());
		/*
		 * imageView.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub
		 * 
		 * //((String) imageView).setSpan(new URLSpan("http://www.36939.net/"),
		 * 13, 15,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		 * 
		 * 
		 * } });
		 */
		return convertView;

	}

	class Holder {
		public ImageView iv;
	}
}
