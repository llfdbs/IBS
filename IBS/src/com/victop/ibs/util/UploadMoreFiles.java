package com.victop.ibs.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.android.uploadfiles.UploadFiles;

public class UploadMoreFiles {

	public void uploadMoreFiles(String actionUrl, String newName,
			List<File> fileList, String modeId) {

		try {
			(new UploadFiles()).uploadFileClient(actionUrl, fileList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
