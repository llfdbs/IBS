package com.victop.ibs.activity;

import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;

import com.victop.ibs.adapter.MaterialDetail_ImageAdapter;
import com.victop.ibs.app.ibsApplication;
import com.victop.ibs.base.ActivityBase;
import com.victop.ibs.view.MyGallery;

public class MaterialDetailActivity extends ActivityBase {
	public List<String> urls ;
	public MyGallery images_ga;
	private int positon = 0;
	//private Thread timeThread = null;
	public boolean timeFlag = true;
	//private boolean isExit = false;
	//public ImageTimerTask timeTaks = null;
	Uri uri;
	Intent intent;
	int gallerypisition = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.materialdetail);
		ibsApplication.getInstance().addActivity(this);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		 Bitmap image= BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);  
	        //imagesCache.put("background_non_load",image);  //设置缓存中默认的图片
	  
	        images_ga = (MyGallery) findViewById(R.id.image_wall_gallery); 
	        images_ga.setImageActivity(this);
	     
	        MaterialDetail_ImageAdapter imageAdapter = new MaterialDetail_ImageAdapter(this);  
	        images_ga.setAdapter(imageAdapter);  
	        LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
	        pointLinear.setBackgroundColor(Color.argb(200, 135, 135, 152));
	        for (int i = 0; i < 4; i++) {
	        	ImageView pointView = new ImageView(this);
	        	if(i==0){
	        		pointView.setBackgroundResource(R.drawable.feature_point_cur);
	        	}else
	        		pointView.setBackgroundResource(R.drawable.feature_point);
	        	    pointLinear.addView(pointView);
			}
	        images_ga.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					System.out.println(arg2+"arg2");
					/*switch (arg2) {
					case 0:
						 uri = Uri.parse("http://www.36939.net/");
						intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
						
						break;
					case 1:
						 uri = Uri.parse("http://www.jiqunejia.com/default.aspx");
						 intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
						
						break;
					case 2:
						 uri = Uri.parse("http://www.jiqunejia.tv/");
						 intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
						
						break;
					case 3:
						uri = Uri.parse("http://city.4000100006.com/");
						 intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
						
						break;

					default:
						break;
					}*/
					
				}
			});
				
	}
	 public void changePointView(int cur){
	    	LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
	    	View view = pointLinear.getChildAt(positon);
	    	View curView = pointLinear.getChildAt(cur);
	    	if(view!=null&& curView!=null){
	    		ImageView pointView = (ImageView)view;
	    		ImageView curPointView = (ImageView)curView;
	    		pointView.setBackgroundResource(R.drawable.feature_point);
	    		curPointView.setBackgroundResource(R.drawable.feature_point_cur);
	    		positon = cur;
	    	}
	    }

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub

	}

}
