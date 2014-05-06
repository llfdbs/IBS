package com.victop.ibs.adapter;
import com.victop.ibs.activity.R;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImagePagerAdapter extends PagerAdapter {
    private Context context;
	private Integer[] images;
	private LayoutInflater inflater;
	private int[] all_id={R.drawable.one,R.drawable.two,R.drawable.three};
    public ImagePagerAdapter(Context context,Integer[] images){
    	this.context = context;
    	this.images = images;
    	this.inflater = LayoutInflater.from(context);
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return all_id.length;
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
		imageView.setBackgroundResource(images[position]);
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
