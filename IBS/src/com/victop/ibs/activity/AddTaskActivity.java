package com.victop.ibs.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.base.ActivityBase;


/**
 * 新增任务类 新增任务业务逻辑
 * 
 * @author vv
 * 
 */
public class AddTaskActivity extends ActivityBase {

	private Button btn_allocation, btn_addperson, btn_choosedate, btn_tasksave;// 分配按钮,分配人按钮,选择日期,保存按钮
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
	private Date d1, d2;//当前时间,用户选择的时间

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addtask);
		ibsApplication.getInstance().addActivity(this);
		initViews();
		initData();
		initListeners();
	}

	@Override
	public void initData() {

	}

	@Override
	public void initViews() {
		btn_allocation = (Button) findViewById(R.id.btn_allocation);
		btn_addperson = (Button) findViewById(R.id.btn_addperson);
		btn_choosedate = (Button) findViewById(R.id.btn_choosedate);
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
		btn_allocation.setOnClickListener(mOnClick);
		btn_addperson.setOnClickListener(mOnClick);
		btn_choosedate.setOnClickListener(mOnClick);
		btn_tasksave.setOnClickListener(mOnClick);
		radiogroup_type.setOnCheckedChangeListener(mOnChecked);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 100:
			if (data != null) {
				Bundle b = data.getExtras();
				String name = b.getString("name");
				tv_allocationname.setText(name);
			}

			break;
		}
	}

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
		}
		if ("".equals(str_allocationname) || null == str_allocationname) {
			Toast.makeText(AddTaskActivity.this, "分配人不能为空,请选择分配人!",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if ("".equals(str_deadline) || null == str_deadline) {
			Toast.makeText(AddTaskActivity.this, "截止日期不能为空,请选择日期!",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if (d2.getTime()<d1.getTime()) {
			Toast.makeText(AddTaskActivity.this, "截止日期要大于当前时间",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if ("".equals(type) || null == type) {
			Toast.makeText(AddTaskActivity.this, "请选择任务类型!",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if ("".equals(str_detail) || null == str_detail) {
			Toast.makeText(AddTaskActivity.this, "任务详情不能为空,请输入任务详情!",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	OnClickListener mOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_allocation:
				if(validate()){
				showDialog(
						getResources().getString(R.string.allocationpositve),
						"visiable");
				}
				break;
			case R.id.btn_addperson:
				openActivityForResult(TaskAllocationActivity.class, null, 100);
				break;
			case R.id.btn_choosedate:
				new DatePickerDialog(AddTaskActivity.this, listener,
						cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
						cal.get(Calendar.DAY_OF_MONTH)).show();

				break;
			case R.id.btn_tasksave:
				if(validate()){
				showDialog(getResources().getString(R.string.tasksave), "gone");
				}
				break;
			}
		}
	};
	OnCheckedChangeListener mOnChecked = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.rbtn_urgent:
				type = "0";
				break;
			case R.id.rbtn_normal:
				type = "1";
				break;
			default:
				;
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
		tv_date.setText(simpleDateFormat.format(cal.getTime()));
		date = simpleDateFormat.format(cal.getTime());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		currentdate = df.format(new Date());
		try {
			d1 = df.parse(currentdate);
			d2 = df.parse(date);
			days = daysBetween(d1, d2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tv_datecount.setText(days + "天");

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
	public void showDialog(String title, String tag) {
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
		btn_tasksave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
			}
		});
		btn_taskpositive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
			}
		});

	}

}
