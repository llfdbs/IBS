/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.victop.ibs.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.victop.ibs.activity.R;

/**
 * 图片加载类设置属性
 * 
 * @author vv
 */
public final class Constants {
	public static ImageLoader imageLoader = ImageLoader.getInstance();
	public static DisplayImageOptions image_display_options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.task_material_head)
			.showImageForEmptyUri(R.drawable.task_material_head)
			.showImageOnFail(R.drawable.task_material_head).imageScaleType(ImageScaleType.EXACTLY)
			.displayer(new RoundedBitmapDisplayer(0)).cacheInMemory(true)
			.cacheOnDisc(true).build();

	public static class Config {
		public static final boolean DEVELOPER_MODE = false;
	}
	public static Bitmap GetLocalOrNetBitmap(String url) {
		Bitmap bitmap = null;
		InputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new URL(url).openStream(), 2 * 1024);
			final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
			out = new BufferedOutputStream(dataStream, 2 * 1024);
			copy(in, out);
			out.flush();
			byte[] data = dataStream.toByteArray();
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			data = null;
			return bitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	private static void copy(InputStream in, OutputStream out)
			throws IOException {
		byte[] b = new byte[2 * 1024];
		int read;
		while ((read = in.read(b)) != -1) {
			out.write(b, 0, read);
		}
	}

}
