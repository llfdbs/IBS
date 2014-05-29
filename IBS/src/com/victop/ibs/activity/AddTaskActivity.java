package com.victop.ibs.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.bean.AddTaskBean;
import com.victop.ibs.bean.TasksaveBean;
import com.victop.ibs.handler.TaskAddHandler;
import com.victop.ibs.presenter.TaskPresenter;
import com.victop.ibs.util.Container;
 

/**
 * 新增任务类 新增任务业务逻辑
 * 
 * @author vv
 * 
 */
public class AddTaskActivity extends ActivityBase {

	private Button btn_tasksave;// 保存按钮
	private ImageButton imgbtn_addperson, imgbtn_choosedate;// 分配人按钮,选择日期
	private EditText edt_taskname, edt_taskdetail;// 任务名称,任务描述
	private TextView tv_allocationname, tv_date, tv_datecount;// 分配人名称,截止时间,天数
	private RadioGroup radiogroup_type;// 类型
	private String str_taskName, str_allocationname, str_deadline, str_days,
			str_detail;// 任务名称,分配人名称,截止时间,天数,任务详情
	private String type;
	private Calendar cal = Calendar.getInstance();
	private String date;// 用户选择的时间字符串
	private String currentdate;// 当前时间字符串
	private int days;// 时间差
	private Date d1, d2;// 当前时间,用户选择的时间
	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
 
	AddTaskBean mAddTaskBean;
	List<AddTaskBean> Alllist;
	List<TasksaveBean> tasksavelist;
	String mUserMessageBean;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addtask);
		IBSApplication.getInstance().addActivity(this);

		initViews();
		initData();
		initListeners();
	}

	@Override
	public void initData() {

	 
		mAddTaskBean = new AddTaskBean();
	}

	@Override
	public void initViews() {
		actionBar = getSupportActionBar();
		actionBar.setTitle(getResources().getString(R.string.addtask));
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		imgbtn_addperson = (ImageButton) findViewById(R.id.imgbtn_addperson);
		imgbtn_choosedate = (ImageButton) findViewById(R.id.imgbtn_choosedate);
		btn_tasksave = (Button) findViewById(R.id.btn_tasksave);
		edt_taskname = (EditText) findViewById(R.id.edt_taskname);
		edt_taskdetail = (EditText) findViewById(R.id.edt_taskdetail);
		tv_allocationname = (TextView) findViewById(R.id.tv_allocationname);
		tv_date = (TextView) findViewById(R.id.tv_date);
		tv_datecount = (TextView) findViewById(R.id.tv_datecount);
		radiogroup_type = (RadioGroup) findViewById(R.id.radiogroup_type);
	}

	@Override
	public void initListeners() {
		tv_allocationname.setOnClickListener(mOnClick);
		tv_date.setOnClickListener(mOnClick);
		imgbtn_addperson.setOnClickListener(mOnClick);
		imgbtn_choosedate.setOnClickListener(mOnClick);
		btn_tasksave.setOnClickListener(mOnClick);
		radiogroup_type
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						switch (checkedId) {
						case R.id.rbtn_urgent:
							type = "紧急任务";
							break;
						case R.id.rbtn_normal:
							type = "一般任务";
							break;
						default:
							break;
						}
					}
				});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 100:
			if (data != null) {
				Bundle b = data.getExtras();
				mUserMessageBean = b.getString("user");
				tv_allocationname.setText(mUserMessageBean);
			}
			break;
		}
	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {

			case 0:// 获取到数据
				tasksavelist = (List<TasksaveBean>) msg.obj;
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

	// 校验用户名密码
	public boolean validate() {
		str_taskName = edt_taskname.getText().toString().trim();
		str_allocationname = tv_allocationname.getText().toString();
		str_deadline = tv_date.getText().toString().trim();
		str_detail = edt_taskdetail.getText().toString().trim();
		if ("".equals(str_taskName) || null == str_taskName) {
			Toast.makeText(AddTaskActivity.this, "任务名称不能为空,请输入任务名称!",
					Toast.LENGTH_SHORT).show();
			return false;
		} else {
			mAddTaskBean.setTaskname(str_taskName);
		}
		if ("".equals(str_allocationname) || null == str_allocationname) {
			Toast.makeText(AddTaskActivity.this, "分配人不能为空,请选择分配人!",
					Toast.LENGTH_SHORT).show();
			return false;
		} else {

		}
		if ("".equals(str_deadline) || null == str_deadline) {
			Toast.makeText(AddTaskActivity.this, "截止日期不能为空,请选择日期!",
					Toast.LENGTH_SHORT).show();
			return false;
		} else {
			mAddTaskBean.setDuedate(str_deadline);
		}
		if (d2.getTime() < d1.getTime()) {
			Toast.makeText(AddTaskActivity.this, "截止日期要大于当前时间",
					Toast.LENGTH_SHORT).show();
			return false;
		} else {

		}
		if ("".equals(type) || null == type) {
			Toast.makeText(AddTaskActivity.this, "请选择任务类型!", Toast.LENGTH_SHORT)
					.show();
			return false;
		} else {
			mAddTaskBean.setTasklevel(type);
		}
		if ("".equals(str_detail) || null == str_detail) {
			Toast.makeText(AddTaskActivity.this, "任务详情不能为空,请输入任务详情!",
					Toast.LENGTH_SHORT).show();
			return false;
		} else {
			mAddTaskBean.setTaskmemo(str_detail);
		}
		return true;
	}

	OnClickListener mOnClick = new OnClickListener() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.tv_allocationname:
				openActivityForResult(TaskAllocationActivity.class, null, 100);
				break;
			case R.id.imgbtn_addperson:
				openActivityForResult(TaskAllocationActivity.class, null, 100);
				break;
			case R.id.tv_date:
				new DatePickerDialog(AddTaskActivity.this, listener,
						cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
						cal.get(Calendar.DAY_OF_MONTH)).show();
				break;
			case R.id.imgbtn_choosedate:
				new DatePickerDialog(AddTaskActivity.this, listener,
						cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
						cal.get(Calendar.DAY_OF_MONTH)).show();

				break;
			case R.id.btn_tasksave:

				str_taskName = edt_taskname.getText().toString().trim();

				if ("".equals(str_taskName) || null == str_taskName) {
					Toast.makeText(AddTaskActivity.this, "任务名称不能为空,请输入任务名称!",
							Toast.LENGTH_SHORT).show();
				} else {
					showDialog(getResources().getString(R.string.tasksave),
							"gone");
				}
				// mAddTaskBean.setTaskstatus("taskstatus");
				// mAddTaskBean.setTaskcode("taskcode");
				// mAddTaskBean.setFinishtime("finishtime");
				// mAddTaskBean.setPostdate("postdate");
				// mAddTaskBean.setAdddate(getTime());
				// mAddTaskBean.setResponsibleid("10021");
				// mAddTaskBean.setResponsiblename("路人甲");
				// mAddTaskBean.setReceptname("酱油男");
				// mAddTaskBean.setTaskid("201405130001");
				// TaskAllocationBean taskAllocationBean = new
				// TaskAllocationBean();
				// taskAllocationBean.setHrid("6655");
				// taskAllocationBean.setTaskallotid("t3332");
				// taskAllocationBean.setIsfinish("0");
				// taskAllocationBean.setTaskid("66999");

				// List<AddTaskModel> addTaskModel = new
				// ArrayList<AddTaskModel>();
				// AddTaskModel mAddTaskModel = new AddTaskModel();
				// mAddTaskModel.setAddtaskbean(mAddTaskBean);
				// mAddTaskModel.setTaskallocation(taskAllocationBean);
				// addTaskModel.add(mAddTaskModel);
				//
				// mDataaddtask.setAddTaskModel(addTaskModel);
				// // 保存数据xml到本地
				// Datajson obj = new Datajson();
				// Gson gson = new Gson();
				// String name = gson.toJson(mDataaddtask);
				// obj.setName(name);
				// System.out.println(obj.getName());
				// saveXmlStream("/sdcard", "addtask.xml", obj,
				// Datajson.class);

				break;
			}
		}
	};

	/**
	 * 处理日期和时间控件的Handler
	 */

	private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, monthOfYear);
			cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateDate();
		}
	};

	private void updateDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		date = simpleDateFormat.format(cal.getTime());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		currentdate = df.format(new Date());
		try {
			d1 = df.parse(currentdate);
			d2 = df.parse(date);
			if (d2.getTime()<d1.getTime()) {
				Calendar cals = Calendar.getInstance();
				cal.setTime(d1);
				Toast.makeText(AddTaskActivity.this, "截止日期要大于当前时间,当前日期为:"+currentdate,
						Toast.LENGTH_SHORT).show();
				return;
			}else{
			days = daysBetween(d1, d2);
			tv_date.setText(simpleDateFormat.format(cal.getTime()));
			tv_datecount.setText(days + "天");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	// 分配,保存弹出框
	public void showDialog(String title, final String tag) {
		final Dialog dialog = new Dialog(AddTaskActivity.this,
				R.style.taskdialog);
		View view = LayoutInflater.from(AddTaskActivity.this).inflate(
				R.layout.dialog, null);
		dialog.setContentView(view);
		TextView tv_title = (TextView) view.findViewById(R.id.dialog_title);
		Button btn_cancle = (Button) view.findViewById(R.id.btn_cancel);
		Button btn_tasksave = (Button) view.findViewById(R.id.btn_tasksave);
		Button btn_taskpositive = (Button) view
				.findViewById(R.id.btn_taskpositive);
		tv_title.setText(title);
		if (tag.equals("visiable")) {
			btn_taskpositive.setVisibility(View.VISIBLE);
			btn_tasksave.setVisibility(View.GONE);
		} else {

			btn_tasksave.setVisibility(View.VISIBLE);
			btn_taskpositive.setVisibility(View.GONE);

		}
		dialog.show();
		btn_cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		btn_tasksave.setOnClickListener(new OnClickListener() {// 保存任务

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						TasksaveBean tasksaveBean = new TasksaveBean();
						tasksaveBean.setTaskname(edt_taskname.getText()
								.toString());
						tasksaveBean.setTaskmemo(edt_taskdetail.getText()
								.toString());
						tasksaveBean.setTasklevel(type);
						tasksaveBean.setTaskcode(getMyUUID());
						tasksaveBean.setTaskstatus("0");
						// tasksaveBean.setFinishtime();
						tasksaveBean.setDuedate(date);
						tasksaveBean.setResponsibleid(com.victop.android.session.Container.getInstance().getUser().getUserCode());
						tasksaveBean.setResponsiblename(com.victop.android.session.Container.getInstance().getUser().getUserName());
						if (null != mUserMessageBean)
							tasksaveBean.setReceptname(mUserMessageBean);
						tasksaveBean.setAdddate(getDate());
						tasksaveBean.setPostdate(date);
						List<TasksaveBean> mTasksaveBean = new ArrayList<TasksaveBean>();
						mTasksaveBean.add(tasksaveBean);
						TaskAddHandler taskAddHandler;

						taskAddHandler = new TaskAddHandler(
								AddTaskActivity.this, mHandler, "8");

						TaskPresenter.getInstance().saveTask(taskAddHandler,
								mTasksaveBean);

						// SavePresenter.getInstance().SaveInitData(
						// taskAddHandler, "IBS11117", "8", dataMap,
						// dataParamMap);
						dialog.dismiss();
					}
				});
		btn_taskpositive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				TasksaveBean tasksaveBean = new TasksaveBean();
				tasksaveBean.setTaskname(edt_taskname.getText().toString());
				tasksaveBean.setTaskmemo(edt_taskdetail.getText().toString());
				tasksaveBean.setTaskstatus("0");
				// tasksaveBean.setFinishtime();
				tasksaveBean.setDuedate(date);
				if (null != mUserMessageBean)
					tasksaveBean.setReceptname(mUserMessageBean);
				tasksaveBean.setAdddate(getDate());
				tasksaveBean.setPostdate(date);
				List<TasksaveBean> mTasksaveBean = new ArrayList<TasksaveBean>();
				mTasksaveBean.add(tasksaveBean);
				TaskAddHandler taskAddHandler;

				taskAddHandler = new TaskAddHandler(AddTaskActivity.this,
						mHandler, "7");
				TaskPresenter.getInstance().AllocationTask(taskAddHandler,
						mTasksaveBean);

				// SavePresenter.getInstance().SaveInitData(
				// taskAddHandler, "IBS11117", "8", dataMap,
				// dataParamMap);

				dialog.dismiss();

			}
		});

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
		save.setTitle("分配");
		save.setIcon(null);

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

		case R.id.save:
			if (validate()) {
				showDialog(
						getResources().getString(R.string.allocationpositve),
						"visiable");
			}
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
