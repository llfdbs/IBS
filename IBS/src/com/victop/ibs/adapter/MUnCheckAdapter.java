package com.victop.ibs.adapter;

import java.util.LinkedList;
import java.util.List;

import me.maxwin.view.XListView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodowaterfall.widget.ScaleImageView;
import com.example.android.bitmapfun.util.ImageFetcher;
import com.victop.ibs.activity.MaterialAllActivity;
import com.victop.ibs.activity.MaterialDetailActivity;
import com.victop.ibs.activity.R;
import com.victop.ibs.bean.UnCheckedMaterialBean;

public class MUnCheckAdapter extends BaseAdapter {
	private Context mContext;
	private LinkedList<UnCheckedMaterialBean> mInfos;
	private XListView mListView;
	ImageFetcher mImageFetcher;
	private int state = 0;
	private final int UNCOMPLETE = 1;// 未完成
	private final int UNCHECK = 2;// 未审核
	private final int ALLMATL = 3;// 全部（）

	public MUnCheckAdapter(Context context, XListView xListView, int state) {

		mContext = context;
		this.state = state;
		mInfos = new LinkedList<UnCheckedMaterialBean>();
		mListView = xListView;
		mImageFetcher = new ImageFetcher(context, 240);
		mImageFetcher.setLoadingImage(R.drawable.empty_photo);
		mImageFetcher.setExitTasksEarly(false);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		UnCheckedMaterialBean duitangInfo = mInfos.get(position);

		if (convertView == null) {
			LayoutInflater layoutInflator = LayoutInflater.from(parent
					.getContext());
			convertView = layoutInflator.inflate(R.layout.infos_list, null);
			holder = new ViewHolder();
			holder.imageView = (ScaleImageView) convertView
					.findViewById(R.id.news_pic);
			holder.contentView = (TextView) convertView
					.findViewById(R.id.news_title);
			holder.linear_box = (LinearLayout) convertView
					.findViewById(R.id.news_list);
			holder.timeView = (TextView) convertView
					.findViewById(R.id.headtime);
			holder.headname = (TextView) convertView
					.findViewById(R.id.headname);
			holder.headicon = (ImageView) convertView.findViewById(R.id.head);
			holder.newsState = (TextView) convertView
					.findViewById(R.id.news_state);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.imageView.setImageWidth(200);
		if (null != duitangInfo.getImghigh()
				&& null != duitangInfo.getImgwidth()
				&& !"".equals(duitangInfo.getImghigh())
				&& !"".equals(duitangInfo.getImgwidth())) {
			float height = ((Float.valueOf(duitangInfo.getImghigh())) / Float
					.valueOf(duitangInfo.getImgwidth())) * 200;

			holder.imageView.setImageHeight((int) height + 100);
		} else {
			holder.imageView.setImageHeight(150);
		}
		if (null != duitangInfo.getMaterialmemo())
			holder.contentView.setText(duitangInfo.getMaterialmemo());
		if (null != duitangInfo.getImgname()) {
			mImageFetcher
					.loadImage(
							"http://img4.duitang.com/uploads/item/201307/29/20130729153409_YCfU2.thumb.200_0.jpeg",
							holder.imageView);
		} else {
			holder.imageView.setBackgroundResource(R.drawable.one);
		}
		// if (state == UNCOMPLETE) { // imgname;// 图片名称
		// Constants.imageLoader.displayImage(
		// "file://" + duitangInfo.getIsrc(), holder.imageView,
		// Constants.image_display_options, null);
		// } else
		if (null != duitangInfo.getAdddate())
			holder.timeView.setText(duitangInfo.getAdddate());
		if (null != duitangInfo.getAddman())
			holder.headname.setText(duitangInfo.getAddman());
		if (state == 1) {
			if (null != duitangInfo.getMaterialstatus()) {
				if ("1".equals(duitangInfo.getMaterialstatus())) {
					holder.newsState.setText("未完成");
				} else if ("2".equals(duitangInfo.getMaterialstatus())) {
					holder.newsState.setText("已完成");
				} else {
					holder.newsState.setText("默认");
				}
			}
		} else {
			if (null != duitangInfo.getMaterialstatus()) {// 0为默认，0代表还未确认（存草稿），1代表未审核，2，已审核
				if ("1".equals(duitangInfo.getMaterialstatus())) {
					holder.newsState.setText("未审核");
				} else if ("2".equals(duitangInfo.getMaterialstatus())) {
					holder.newsState.setText("已审核");
				} else {
					holder.newsState.setText("默认");
				}

			}
		}

		holder.linear_box.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent _Intent = new Intent(mContext,
						MaterialDetailActivity.class);
				Bundle pBundle = new Bundle();
				pBundle.putString("materialstatus", mInfos.get(position).getMaterialstatus());
				pBundle.putString("materialid", mInfos.get(position).getMaterialid());
				_Intent.putExtras(pBundle);

				mContext.startActivity(_Intent);
			}
		});
		return convertView;
	}

	class ViewHolder {
		ScaleImageView imageView;
		TextView contentView;
		TextView timeView;
		TextView headname;
		TextView newsState;
		ImageView headicon;
		LinearLayout linear_box;
	}

	@Override
	public int getCount() {
		return mInfos.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mInfos.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	public void addItemLast(List<UnCheckedMaterialBean> datas) {
		mInfos.addAll(datas);
	}

	public void addItemTop(List<UnCheckedMaterialBean> datas) {
		for (UnCheckedMaterialBean info : datas) {
			mInfos.addFirst(info);
		}
	}
}