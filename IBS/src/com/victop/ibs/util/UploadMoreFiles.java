package com.victop.ibs.util;

import java.io.File;
import java.util.List;

import com.android.uploadfiles.UploadFiles;

public class UploadMoreFiles {

	public void uploadMoreFiles(String actionUrl, String newName,
			List<File> fileList, String modeId) {

		UploadFiles.uploadFile(actionUrl, newName, fileList, modeId);

	}
}
