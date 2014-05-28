package com.victop.ibs.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.victop.ibs.app.IBSApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.presenter.GetTaskListSearchResultPresenter;
import com.victop.ibs.util.Container;

/**
 * 搜索界面
 * 
 * @author vv
 * 
 */
public class MaterialSearchActivity extends ActivityBase implements
		OnClickListener {

	private ActionBar actionBar;// 导航栏
	private MenuItem search, add, save;// 搜索,添加，保存按钮
	private AutoCompleteTextView autoCompleteTextView;
	private Button searchButton;
	private String[] histories;
	private static final String LOCALHISTORY ="localhistory";
	private int model =0;
	private String title="";
	private String modeObj="";//用来区分是素材模块还是任务模块
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		final View view = View.inflate(this, R.layout.materialsearch, null);
		setContentView(view);
		IBSApplication.getInstance().addActivity(this);
		initViews();
		initData();
		initListeners();

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		 initAutoComplete("history", autoCompleteTextView); 
		 Bundle bundle = getIntent().getExtras();
		 model = bundle.getInt("tag");
		 title = bundle.getString("title");
		 modeObj = bundle.getString("modeobj");
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setTitle("搜索");
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.btn_back);
		autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto);
		searchButton = (Button) findViewById(R.id.search);
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		searchButton.setOnClickListener(this);
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

		save.setVisible(false);

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
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.search:
			saveHistory("history", autoCompleteTextView);
			
			Bundle bundle = new Bundle();
			bundle.putInt("model",model);
			bundle.putString("keyword",autoCompleteTextView.getText().toString().trim());
			bundle.putString("title",title);
			if(modeObj.equals("task")){
				openActivity(Search_GetTaskListResultActivity.class, bundle);
			}else{
				openActivity(Search_MaterialResultActivity.class,bundle);
			}
			
			MaterialSearchActivity.this.finish();
			
			break;
		}
	}
	/** 
	 
     * 把指定AutoCompleteTextView中内容保存到sharedPreference中指定的字符段 
 
     *  
 
     * @param name 
 
     *            保存在sharedPreference中的字段名 
 
     * @param autoCompleteTextView 
 
     *            要操作的AutoCompleteTextView 
 
     */  
 
    private void saveHistory(String name,  
 
            AutoCompleteTextView autoCompleteTextView) {  
 
        String text = autoCompleteTextView.getText().toString().trim();  
 
        SharedPreferences sp = getSharedPreferences(LOCALHISTORY, MODE_PRIVATE);  
 
        String longhistory = sp.getString(name, "");  
 
        if (!longhistory.contains(text + ",")) {  
 
            StringBuilder sb = new StringBuilder(longhistory);  
 
            sb.insert(0, text + ",");  
 
            sp.edit().putString(name, sb.toString().trim()).commit();  
 
        }  
 
    }  
    
    /** 
    
     * 初始化AutoCompleteTextView，最多显示12项提示，使 AutoCompleteTextView在一开始获得焦点时自动提示 
 
     *  
 
     * @param field 
 
     *            保存在sharedPreference中的字段名 
 
     * @param autoCompleteTextView 
 
     *            要操作的AutoCompleteTextView 
 
     */  
 
    private void initAutoComplete(String name,  
 
            final AutoCompleteTextView autoCompleteTextView) {  
 
        SharedPreferences sp = getSharedPreferences(LOCALHISTORY, 0);  
 
        String longhistory = sp.getString(name,"");  
 
        histories = longhistory.split(",");  
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  
 
                android.R.layout.simple_dropdown_item_1line, histories);  
 
        // 只保留最近的12条的记录  
 
        if (histories.length > 12) {  
 
            String[] newHistories = new String[12];  
 
            System.arraycopy(histories, 0, newHistories, 0, 12);  
 
            adapter = new ArrayAdapter<String>(this,  
 
                    android.R.layout.simple_dropdown_item_1line, newHistories);  
 
        }  
 
        autoCompleteTextView.setAdapter(adapter);  
        
        autoCompleteTextView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putInt("model",model);
				bundle.putString("keyword",autoCompleteTextView.getText().toString().trim());
				bundle.putString("title",title);
				if(modeObj.equals("task")){
					openActivity(Search_GetTaskListResultActivity.class, bundle);//跳转到任务搜索结果页面
				}else{
					//openActivity(Search_MaterialResultActivity.class,bundle);//跳转到素材搜素结果页面
				}
				
				MaterialSearchActivity.this.finish();
			}
		});
 
        autoCompleteTextView  
 
                .setOnFocusChangeListener(new OnFocusChangeListener() {  
 
                    @Override  
 
                    public void onFocusChange(View v, boolean hasFocus) {  
 
                        AutoCompleteTextView view = (AutoCompleteTextView) v;  
 
                        if (hasFocus) {  
                        	if(!histories[0].equals("")){
                            view.showDropDown();  
                        	}
 
                        }  
 
                    }  
 
                });  
 
    }  
}
