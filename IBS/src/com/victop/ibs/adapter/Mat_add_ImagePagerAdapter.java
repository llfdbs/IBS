package com.victop.ibs.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.victop.ibs.activity.R;
import com.victop.ibs.util.Constants;
import com.victop.ibs.util.PictureUtil;

/**
 * 新增素材图片浏览适配器
 * 
 * @author Administrator
 * 
 */
public class Mat_add_ImagePagerAdapter extends PagerAdapter {
	private Context context;
	// private Integer[] images;
	private LayoutInflater inflater;
	// private String[] arr_detail = { "我是第一张描述", "ddddddddddddd",
	// "sssssssssssss" };
	private List<Map<String, String>> list;

	public Mat_add_ImagePagerAdapter(Context context,
			List<Map<String, String>> list) {
		this.context = context;
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
		// TextView tv_detail = (TextView) imageLayout
		// .findViewById(R.id.image_detail);
		// TextView tv_position = (TextView) imageLayout
		// .findViewById(R.id.image_position);
		Constants.imageLoader.displayImage("file://"
				+ list.get(position).keySet().iterator().next(), imageView,
				Constants.image_display_options, null);

		// imageView.setBackgroundResource(images[position]);
		// tv_detail.setText(list.get(position).values().iterator().next());
		// tv_position.setText((position + 1) + "/" + list.size());
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
