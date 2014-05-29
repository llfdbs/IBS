package com.victop.ibs.adapter;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.victop.ibs.activity.R;

public class ImagePagerAdapter extends PagerAdapter {
    private Context context;
	private Integer[] images;
	private LayoutInflater inflater;
	private String[] arr_detail={"我是第一张描述","ddddddddddddd","sssssssssssss"};
    public ImagePagerAdapter(Context context,Integer[] images){
    	this.context = context;
    	this.images = images;
    	this.inflater = LayoutInflater.from(context);
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
	@Override
	public Object instantiateItem(ViewGroup view, int position) {
		View imageLayout = inflater.inflate(R.layout.pager_item, view, false);
		assert imageLayout != null;
		ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
		TextView tv_detail = (TextView) imageLayout.findViewById(R.id.image_detail);
		TextView tv_position = (TextView) imageLayout.findViewById(R.id.image_position);
		imageView.setBackgroundResource(images[position]);
		tv_detail.setText(arr_detail[position]);
		tv_position.setText((position+1)+"/"+images.length);
		view.addView(imageLayout, 0);
		return imageLayout;
	}
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}
}
