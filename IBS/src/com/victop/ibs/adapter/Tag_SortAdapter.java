package com.victop.ibs.adapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.victop.ibs.activity.R;
import com.victop.ibs.activity.TagActivity;
import com.victop.ibs.bean.SortModel;
import com.victop.ibs.util.Container;
import com.victop.ibs.view.MyGridView;

public class Tag_SortAdapter extends BaseAdapter implements SectionIndexer {
	private List<SortModel> list = null;
	private Context mContext;
	private boolean ischeck = false;
	int int_temp = 0;
	HashMap<Integer, Integer> Mark_hashmap = new HashMap<Integer, Integer>();
	Tag_SortAdapter adapter;

	public Tag_SortAdapter(Context mContext, List<SortModel> list) {
		this.mContext = mContext;
		this.list = list;

	}

	public Tag_SortAdapter getInstance() {
		return new Tag_SortAdapter(mContext, list);
	}

	/**
	 * 
	 * 
	 * @param list
	 */
	public void updateListView(List<SortModel> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final SortModel mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.tag_listitem,
					null);
			viewHolder.tvTitle = (MyGridView) view.findViewById(R.id.title);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			viewHolder.linear = (LinearLayout) view.findViewById(R.id.llyt_tv);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		int section = getSectionForPosition(position);

		if (position == getPositionForSection(section)) {

			viewHolder.linear.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getSortLetters());
			Tag_GirdViewAdapter adapter = new Tag_GirdViewAdapter(mContext,
					list.subList(getPositionForSection(section), position
							+ getLastPositionForSection(section)),
					getPositionForSection(section));

			viewHolder.tvTitle.setAdapter(adapter);

			viewHolder.tvTitle.setVisibility(View.VISIBLE);

		} else {
			// viewHolder.tvLetter.setVisibility(View.GONE);
			viewHolder.tvTitle.setVisibility(View.GONE);
			viewHolder.linear.setVisibility(View.GONE);

		}

		viewHolder.tvTitle.setSelector(new ColorDrawable(Color.TRANSPARENT));
		viewHolder.tvTitle.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				TextView bn = (TextView) arg1.findViewById(R.id.itemTv);

				int temp = position + arg2;
				if (null != list.get(temp))
					int_temp = list.get(temp).getTag();
				if (int_temp == 0)
					int_temp = 1;
				else
					int_temp = 0;
				if (int_temp == 1) {
					bn.setBackgroundDrawable(mContext.getResources()
							.getDrawable(R.drawable.tag_et1));
					bn.setTextColor(0Xffffffff);
				} else {
					bn.setBackgroundDrawable(mContext.getResources()
							.getDrawable(R.drawable.tag_et));
					bn.setTextColor(0Xff000000);
				}
//				bn.setPadding(1, 1, 1, 1);
//				bn.setGravity(Gravity.CENTER);
				System.out.println(int_temp + "-------------");
				HashMap<Integer, SortModel> hashMap = Container.getInstance()
						.getTaghashSortModel();
				if (int_temp == 1) {
					Container.getInstance().setTaghashSortModle(temp,
							list.get(temp));
					list.get(temp).setTag(1);
				} else {
					if (null != hashMap.get(temp))
						hashMap.remove(temp);
					list.get(temp).setTag(0);
				}

				if (null != hashMap && hashMap.size() != 0) {
					String name = "标签（选中" + hashMap.size() + "个）";
					((TagActivity) mContext).settitleName(name);
					Iterator iter = hashMap.keySet().iterator();
					while (iter.hasNext()) {
						Object key = iter.next();
						Object val = hashMap.get(key);
						System.out.println(key + ((SortModel) val).getName());
					}
				} else {
					((TagActivity) mContext).settitleName("标签");
				}

			}

		});
		return view;
	}

	final static class ViewHolder {
		TextView tvLetter;
		MyGridView tvTitle;
		LinearLayout linear;
	}

	/**
	 * ���ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
	 */
	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}

	/**
	 * ��ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * ��ݷ�����ַ� asciiֵ��ȡ���һ�γ��ָ����ַ��λ��
	 */
	@SuppressLint("DefaultLocale")
	public int getPositionForString(String section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getName();
			// char firstChar = sortStr.toUpperCase().charAt(0);
			if (sortStr == section) {
				return i;
			}
		}

		return -1;
	}

	public int getLastPositionForSection(int section) {
		int temp = 0;
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				temp++;

			}

		}

		return temp;
	}

	/**
	 * ��ȡӢ�ĵ�����ĸ����Ӣ����ĸ��#���档
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String sortStr = str.trim().substring(0, 1).toUpperCase();
		// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}