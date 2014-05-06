package com.victop.ibs.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dodola.model.DuitangInfo;
import com.dodowaterfall.Helper;
import com.dodowaterfall.widget.ScaleImageView;
import com.example.android.bitmapfun.util.ImageFetcher;

/**
 * 全部素材，未审核素材 搜素素材 未完成素材
 * 
 * @author vv
 * 
 */
public class MaterialAllActivity extends FragmentActivity implements
		IXListViewListener, OnClickListener, OnItemSelectedListener {
	private ImageFetcher mImageFetcher;// 图片加载类
	private XListView mAdapterView = null; // 自定义控件类
	private StaggeredAdapter mAdapter = null;
	private int currentPage = 0;
	ContentTask task = new ContentTask(this, 2);

	private Button btn_back, btn_add, btn_search = null;
	private TextView tv_title = null;
	private Spinner sp_newtime;
	private final String MATERIAL = "material_style";
	private final String AUDIT = "audit";
	private final String UNADIT = "unaudit";
	private final String NOTCOMPLETE = "notcomplete";
	private static final String[] mCountries = { "最新时间", "按首字母排序" };

	private class ContentTask extends
			AsyncTask<String, Integer, List<DuitangInfo>> {

		private Context mContext;
		private int mType = 1;

		public ContentTask(Context context, int type) {
			super();
			mContext = context;
			mType = type;
		}

		@Override
		protected List<DuitangInfo> doInBackground(String... params) {
			try {
				return parseNewsJSON(params[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<DuitangInfo> result) {
			if (mType == 1) {

				mAdapter.addItemTop(result);
				mAdapter.notifyDataSetChanged();
				mAdapterView.stopRefresh();

			} else if (mType == 2) {
				mAdapterView.stopLoadMore();
				mAdapter.addItemLast(result);
				mAdapter.notifyDataSetChanged();
			}

		}

		@Override
		protected void onPreExecute() {
		}

		public List<DuitangInfo> parseNewsJSON(String url) throws IOException {
			List<DuitangInfo> duitangs = new ArrayList<DuitangInfo>();
			String json = "";
			if (Helper.checkConnection(mContext)) {
				try {
					json = Helper.getStringFromUrl(url);

				} catch (IOException e) {
					Log.e("IOException is : ", e.toString());
					e.printStackTrace();
					return duitangs;
				}
			}
			Log.d("MainActiivty", "json:" + json);

			try {
				if (null != json) {
					JSONObject newsObject = new JSONObject(json);
					JSONObject jsonObject = newsObject.getJSONObject("data");
					JSONArray blogsJson = jsonObject.getJSONArray("blogs");

					for (int i = 0; i < blogsJson.length(); i++) {
						JSONObject newsInfoLeftObject = blogsJson
								.getJSONObject(i);
						DuitangInfo newsInfo1 = new DuitangInfo();
						newsInfo1
								.setAlbid(newsInfoLeftObject.isNull("albid") ? ""
										: newsInfoLeftObject.getString("albid"));
						newsInfo1
								.setIsrc(newsInfoLeftObject.isNull("isrc") ? ""
										: newsInfoLeftObject.getString("isrc"));
						newsInfo1.setMsg(newsInfoLeftObject.isNull("msg") ? ""
								: newsInfoLeftObject.getString("msg"));
						newsInfo1.setHeight(newsInfoLeftObject.getInt("iht"));
						duitangs.add(newsInfo1);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return duitangs;
		}
	}

	/**
	 * 添加内容
	 * 
	 * @param pageindex
	 * @param type
	 *            1为下拉刷新 2为加载更多
	 */
	private void AddItemToContainer(int pageindex, int type) {
		if (task.getStatus() != Status.RUNNING) {
			String url = "http://www.duitang.com/album/1733789/masn/p/"
					+ pageindex + "/24/";
			Log.d("MainActivity", "current url:" + url);
			ContentTask task = new ContentTask(this, type);
			task.execute(url);

		}
	}

	public class StaggeredAdapter extends BaseAdapter {
		private Context mContext;
		private LinkedList<DuitangInfo> mInfos;
		private XListView mListView;

		public StaggeredAdapter(Context context, XListView xListView) {
			mContext = context;
			mInfos = new LinkedList<DuitangInfo>();
			mListView = xListView;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			DuitangInfo duitangInfo = mInfos.get(position);

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
				convertView.setTag(holder);
			}

			holder = (ViewHolder) convertView.getTag();
			holder.imageView.setImageWidth(duitangInfo.getWidth());
			holder.imageView.setImageHeight(duitangInfo.getHeight());
			holder.contentView.setText(duitangInfo.getMsg());
			mImageFetcher.loadImage(duitangInfo.getIsrc(), holder.imageView);
			holder.linear_box.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MaterialAllActivity.this,
							MaterialDetailActivity.class);
					startActivity(intent);
				}
			});
			return convertView;
		}

		class ViewHolder {
			ScaleImageView imageView;
			TextView contentView;
			TextView timeView;
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

		public void addItemLast(List<DuitangInfo> datas) {
			mInfos.addAll(datas);
		}

		public void addItemTop(List<DuitangInfo> datas) {
			for (DuitangInfo info : datas) {
				mInfos.addFirst(info);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.materitalall);
		btn_back = (Button) findViewById(R.id.back);
		btn_add = (Button) findViewById(R.id.add);
		btn_search = (Button) findViewById(R.id.btn_search);
		sp_newtime = (Spinner) findViewById(R.id.sp_newtime);

		tv_title = (TextView) findViewById(R.id.title);

		btn_add.setBackgroundResource(R.drawable.btn_add);

		btn_back.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		btn_search.setOnClickListener(this);
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this,
				R.layout.simple_spinner, mCountries);
		// ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_newtime.setAdapter(ad);
		Bundle b = getIntent().getExtras();
		if (b != null) {
			String rr = b.getString(MATERIAL);
			if (rr.equals(AUDIT)) {
				tv_title.setText("全部素材");
			} else if (rr.equals(UNADIT)) {
				tv_title.setText("素材(未审核20)");
			} else if (rr.equals(NOTCOMPLETE)) {
				tv_title.setText("素材(未完成20)");
			}
		}

		// tv_title.setText("全部素材");

		mAdapterView = (XListView) findViewById(R.id.list);
		mAdapterView.setPullLoadEnable(true);
		mAdapterView.setXListViewListener(this);

		mAdapter = new StaggeredAdapter(this, mAdapterView);

		mImageFetcher = new ImageFetcher(this, 240);
		mImageFetcher.setLoadingImage(R.drawable.empty_photo);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mImageFetcher.setExitTasksEarly(false);
		mAdapterView.setAdapter(mAdapter);
		AddItemToContainer(currentPage, 2);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}

	@Override
	public void onRefresh() {
		// AddItemToContainer(++currentPage, 1);
		mAdapterView.stopRefresh();
	}

	@Override
	public void onLoadMore() {
		// AddItemToContainer(++currentPage, 2);
		mAdapterView.stopLoadMore();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.add:
			Intent intent_search = new Intent(MaterialAllActivity.this,MaterialAddActivity.class);

			startActivity(intent_search);
			break;
		case R.id.btn_search:
			Intent intent = new Intent(MaterialAllActivity.this,
					MaterialSearchActivity.class);
			Bundle b = new Bundle();
			startActivity(intent);
			finish();

			break;
		case R.id.sp_newtime:
			break;

		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // if(MENU_PREFERENCES == item.getItemId()){
	// // mActionModeHandler.startActionMode();
	// // }
	// if (R.id.action_settings == item.getItemId()) {
	// Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG)
	// .show();
	// }
	// return super.onOptionsItemSelected(item);
	// }
	//
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // TODO Auto-generated method stub
	// getMenuInflater().inflate(R.menu.main, menu);
	//
	// return true;
	// }

}// end of class
