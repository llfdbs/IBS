package com.victop.ibs.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.victop.ibs.adapter.PropertygridviewAdapter;
import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.MaterialPropertyBean;
import com.victop.ibs.bean.PropertyBean;
import com.victop.ibs.handler.BaseHandler;
import com.victop.ibs.presenter.Getpresenter;
import com.victop.ibs.xml.Datajson;

/**
 * 素材模块 属性接界面
 * 
 * @author vv
 * 
 */
public class PropertyActivity extends ActivityBase implements OnClickListener {

	private EditText et_product;
	private RadioButton rb_on, rb_off;
	private RadioGroup rg_come;
	private TextView tv_tagcontent, tv_comecontent;
	private int tag = 0;// 0代表编辑状态，1代表查看状态
	private Dialog dialog;
	private Button btn_choose;
	private View popView;

	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private final int TEXT_STATE = 0;// 文本
	private final int SPIN_STATE = 1;// 下拉单选
	private final int TWO_STATE = 2;// 2选1单选
	private final int MORE_STATE = 3;// 多选1 同1
	private final int MOREM_STATE = 4;// 多选多
	private TextView tv_tag, tv_get, tv_hangye, tv_chosem;

	List<PropertyBean> pro_text = new ArrayList<PropertyBean>();
	List<PropertyBean> pro_spin = new ArrayList<PropertyBean>();
	List<PropertyBean> pro_two = new ArrayList<PropertyBean>();
	List<PropertyBean> pro_morem = new ArrayList<PropertyBean>();
	private TextView tv_choosem;
	private ListView areaCheckListView;
	private Boolean[] areaState;
	private String classid = "";
	List<PropertyBean> pro_all = new ArrayList<PropertyBean>();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.propertylayout, null);
		setContentView(view);
		IBSApplication.getInstance().addActivity(this);
		initViews();
		initData();
		initListeners();
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

		Bundle b = getIntent().getExtras();
		if (null != b) {
			classid = b.getString("classid");
		}
		BaseHandler bHandler = new BaseHandler(this, mHandler);
		HashMap<String, String> Datamap = new HashMap<String, String>();
		Datamap.put("classid", classid);

		Map<String, Class> clsMap = new HashMap<String, Class>();
		clsMap.put(PropertyBean.datasetId, PropertyBean.class);
		Getpresenter.getInstance().getInitbData(bHandler, clsMap, Datamap,
				PropertyBean.modelId, PropertyBean.datasetId, null,
				PropertyBean.formId);
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:// 获取到数据
				// Map<String, List> dataMap = (Map<String, List>) msg.obj;
				// List<PropertyBean> mUnCheckedMaterialBean = dataMap
				// .get(PropertyBean.datasetId);

				Datajson obj = null;
				obj = (Datajson) getXmlStream("property.xml",
						PropertyBean.class, Datajson.class);
				List<PropertyBean> mPropertyBean = obj.getmPropertyBean();

				for (PropertyBean p : mPropertyBean) {
					System.out.println("属性id:" + p.getNatureid() + "   属性名称:"
							+ p.getNaturename() + "   展示类型:" + p.getViewtype()
							+ "   属性明细ID:" + p.getNaturedetailid()
							+ "  属性明细名称:" + p.getNaturedetailname());
					if (p.getViewtype().equals(TEXT_STATE + "")) {
						pro_text.add(p);
					} else if (p.getViewtype().equals(SPIN_STATE + "")
							|| p.getViewtype().equals(MORE_STATE + "")) {
						pro_spin.add(p);
					} else if (p.getViewtype().equals(TWO_STATE + "")) {
						pro_two.add(p);
					} else if (p.getViewtype().equals(MOREM_STATE + "")) {
						pro_morem.add(p);
					}
				}

				if (pro_text.size() > 0) {
					if (pro_text.size() > 1) {
						tv_tag.setText(pro_text.get(1).getNaturename() + ":");
						tv_tagcontent.setText(pro_text.get(1)
								.getNaturedetailname());
					}

				} else {

				}
				if (pro_two.size() > 0) {
					tv_get.setText(pro_two.get(0).getNaturename() + ":");

					tv_comecontent
							.setText(pro_two.get(0).getNaturedetailname());
				} else {
					tv_get.setVisibility(View.GONE);
					tv_comecontent.setVisibility(View.GONE);
				}
				if (pro_spin.size() > 0) {
					tv_hangye.setText(pro_spin.get(0).getNaturename());
					tv_hangye.setVisibility(View.VISIBLE);
					btn_choose.setVisibility(View.VISIBLE);

				} else {
					tv_hangye.setVisibility(View.GONE);
					btn_choose.setVisibility(View.GONE);
				}
				if (pro_morem.size() > 0) {
					tv_chosem.setText(pro_morem.get(0).getNaturename());
					tv_chosem.setVisibility(View.VISIBLE);
					tv_choosem.setVisibility(View.VISIBLE);
				} else {
					tv_chosem.setVisibility(View.GONE);
					tv_choosem.setVisibility(View.GONE);
				}

				break;
			case 1:
				String rr = (String) msg.obj;
				Toast.makeText(getApplicationContext(), rr, 1000).show();
				finish();
				break;
			case 2:
			case 3:
				String r2r = (String) msg.obj;
				Toast.makeText(getApplicationContext(), r2r, 1000).show();
				break;
			}
		}
	};

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle("详情");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		et_product = (EditText) findViewById(R.id.et_product);
		rb_on = (RadioButton) findViewById(R.id.rbtn_no);
		rb_off = (RadioButton) findViewById(R.id.rbtn_off);
		rg_come = (RadioGroup) findViewById(R.id.radiogroup_type);
		tv_chosem = (TextView) findViewById(R.id.hangye_more);
		tv_tag = (TextView) findViewById(R.id.tag_porper);
		tv_get = (TextView) findViewById(R.id.get_proper);
		tv_hangye = (TextView) findViewById(R.id.hangye);
		tv_choosem = (TextView) findViewById(R.id.tv_choosemore);
		tv_choosem.setOnClickListener(this);
		tv_chosem.setOnClickListener(this);

		tv_tagcontent = (TextView) findViewById(R.id.tagname);
		tv_comecontent = (TextView) findViewById(R.id.getname);
		if (tag == 0) {
			btn_choose = (Button) findViewById(R.id.btn_choose);
			btn_choose.setOnClickListener(this);
		} else if (tag == 1) {
			et_product.setEnabled(false);
			et_product.setBackgroundResource(R.drawable.login_bg);
		}
		et_product.setVisibility(View.GONE);
		tv_get.setVisibility(View.GONE);
		tv_comecontent.setVisibility(View.GONE);
		tv_hangye.setVisibility(View.GONE);
		btn_choose.setVisibility(View.GONE);
		tv_chosem.setVisibility(View.GONE);
		tv_choosem.setVisibility(View.GONE);
		rg_come.setVisibility(View.GONE);
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btn_choose:
			showDielog();
			break;
		case R.id.tv_choosemore:
		case R.id.hangye_more:
			Bundle b = new Bundle();
			b.putSerializable("morem", (Serializable) pro_morem);
			openActivityForResult(PropertylistActivity.class, b, 100);
			break;
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 100:
			if (data != null) {
				Bundle b = data.getExtras();
				List<PropertyBean> mPropertyBean = (List<PropertyBean>) b
						.getSerializable("morem");
				String rr = "";
				for (PropertyBean p : mPropertyBean) {
					if ("".equals(rr)) {
						rr = p.getNaturedetailname();
					} else {
						rr = rr + "/" + p.getNaturedetailname();
					}
				}
				tv_choosem.setText(rr);
			}
			break;
		}
	}

	GridView gridview;

	private void showDielog() {
		LayoutInflater inflate = LayoutInflater.from(this);

		popView = inflate.inflate(R.layout.property_gridlayout, null);

		gridview = (GridView) popView.findViewById(R.id.gridview);
		PropertygridviewAdapter adapter = new PropertygridviewAdapter(this,
				pro_spin);

		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				btn_choose.setText(pro_spin.get(arg2).getNaturedetailname());
				// adapter.setSeclection(arg2);
				// adapter.notifyDataSetChanged();
				dialog.dismiss();
			}
		});

		dialog = new Dialog(this, R.style.dialog);
		dialog.setContentView(popView);
		dialog.show();
		// WindowManager windowManager = getWindowManager();
		// Display display = windowManager.getDefaultDisplay();
		// WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		// lp.width = (int) (display.getWidth() * 0.95);
		//
		// lp.height = (int) (display.getHeight() * 0.9);

		// dialog.getWindow().setAttributes(lp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		search = menu.findItem(R.id.search);
		add = menu.findItem(R.id.add);
		save = menu.findItem(R.id.save);
		search.setVisible(false);
		add.setVisible(false);
		save.setVisible(true);
		if (tag == 0) {
			save.setTitle("完成");

		} else if (tag == 1) {
			save.setTitle("");
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:

			finish();

			break;
		case R.id.search:
			break;
		case R.id.add:

			break;
		case R.id.save:
			// classid
			RadioButton rb_on,
			rb_off;
			List<MaterialPropertyBean> mMaterialPropertyBean = new ArrayList<MaterialPropertyBean>();
			if (pro_text.size() > 0) {
				MaterialPropertyBean m = new MaterialPropertyBean();
				m.setClassid(classid);
				m.setNatureid(pro_text.get(0).getNatureid());
				m.setNaturedetailid(pro_text.get(0).getNaturedetailid());
				m.setMaterialguid(getMyUUID());
				m.setNaturevalue(pro_text.get(0).getNaturename());
				// m.setMatnatureid(pro_text.get(0).);
			}
			if (pro_two.size() > 0) {
				MaterialPropertyBean m = new MaterialPropertyBean();
				m.setClassid(classid);
				m.setNatureid(pro_text.get(0).getNatureid());
				m.setNaturedetailid(pro_text.get(0).getNaturedetailid());
				m.setMaterialguid(getMyUUID());
				m.setNaturevalue(pro_text.get(0).getNaturename());
				// m.setMatnatureid(pro_text.get(0).);
			}

			List<PropertyBean> pro_spin = new ArrayList<PropertyBean>();
			List<PropertyBean> pro_two = new ArrayList<PropertyBean>();
			List<PropertyBean> pro_morem = new ArrayList<PropertyBean>();

			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
