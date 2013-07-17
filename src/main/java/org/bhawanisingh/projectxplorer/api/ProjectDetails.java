package org.bhawanisingh.projectxplorer.api;

import java.io.File;
import java.util.ArrayList;

import org.bhawanisingh.projectxplorer.api.util.DetailObject;
import org.bhawanisingh.projectxplorer.gui.MainGUI;

public class ProjectDetails {

	public static ArrayList<DetailObject> detailObjects = new ArrayList<DetailObject>();

	public static void separateFolders(String folderList) {
		String[] folders = folderList.split("\n");
		for (String folder : folders) {
			ProjectDetails.sourceFolderDetails(folder);
		}
	}

	public static void sourceFolderDetails(String sourceFolder) {
		File tempFile;
		File rootDir = new File(sourceFolder);
		String[] childDir = rootDir.list();
		for (String child : childDir) {
			tempFile = new File(rootDir, child);
			if (tempFile.isFile()) {
				String name = tempFile.getName().substring(tempFile.getName().lastIndexOf(".") + 1);
				name = name.trim();
				if ("java".equals(name)) {
					BasicDetails.javaDetails(tempFile, ProjectDetails.getDetailObject(tempFile, "java"));
				} else if ("py".equals(name)) {
					BasicDetails.pythonDetails(tempFile, ProjectDetails.getDetailObject(tempFile, "py"));
				}
			} else {
				ProjectDetails.sourceFolderDetails(tempFile.getAbsolutePath());
			}
		}
	}

	private static DetailObject getDetailObject(File tempFile, String fileType) {
		boolean objectFound = false;
		DetailObject detailObject = null;
		for (DetailObject detailObject1 : ProjectDetails.detailObjects) {
			if (detailObject1.getLanguage().equalsIgnoreCase(fileType)) {
				objectFound = true;
				detailObject = detailObject1;
			}
		}
		if (!objectFound) {
			detailObject = new DetailObject(fileType);
			ProjectDetails.detailObjects.add(detailObject);
			MainGUI.getMAINGUI().addDetails(detailObject);
		}
		return detailObject;
	}
}
