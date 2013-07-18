package org.bhawanisingh.projectxplorer.api;

import java.io.File;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.projectxplorer.api.logging.LoggerValues;
import org.bhawanisingh.projectxplorer.api.util.DetailObject;
import org.bhawanisingh.projectxplorer.gui.MainGUI;

public class SourceScanner {

	private static Logger loggerSourceScanner = LogManager.getLogger(SourceScanner.class);

	public static ArrayList<DetailObject> detailObjects;

	public static void separateFolders(String folderList) {
		loggerSourceScanner.entry();
		String[] folders = folderList.split("\n");
		for (String folder : folders) {
			SourceScanner.sourceFolderDetails(folder);
		}
		loggerSourceScanner.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	public static void sourceFolderDetails(String sourceFolder) {
		loggerSourceScanner.entry();
		File sourceFile;
		File main = new File(sourceFolder);
		if (main.isFile()) {
			SourceScanner.fileSelector(main);
		} else {
			String[] childDir = main.list();
			for (String child : childDir) {
				sourceFile = new File(main, child);
				if (sourceFile.isFile()) {
					SourceScanner.fileSelector(sourceFile);
				} else {
					SourceScanner.sourceFolderDetails(sourceFile.getAbsolutePath());
				}
			}
		}
		loggerSourceScanner.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private static void fileSelector(File sourceFile) {
		loggerSourceScanner.entry();
		String name = sourceFile.getName().substring(sourceFile.getName().lastIndexOf(".") + 1);
		name = name.trim();
		if ("java".equals(name)) {
			BasicDetails.javaDetails(sourceFile, SourceScanner.getDetailObject("Java"));
		} else if ("c".equalsIgnoreCase(name) || "cpp".equalsIgnoreCase(name)) {
			BasicDetails.cppDetails(sourceFile, SourceScanner.getDetailObject("C/C++"));
		} else if ("cs".equals(name)) {
			BasicDetails.cSharpDetails(sourceFile, SourceScanner.getDetailObject("C Sharp"));
		} else if ("py".equals(name)) {
			BasicDetails.pythonDetails(sourceFile, SourceScanner.getDetailObject("Python"));
		} else if ("vb".equals(name)) {
			BasicDetails.visualBasicDetails(sourceFile, SourceScanner.getDetailObject("Visual Basic"));
		} else if ("xml".equals(name)) {
			BasicDetails.xmlDetails(sourceFile, SourceScanner.getDetailObject("XML"));
		} else if ("htm".equals(name) || "html".equals(name)) {
			BasicDetails.xmlDetails(sourceFile, SourceScanner.getDetailObject("HTML"));
		} else if ("jsp".equals(name)) {
			BasicDetails.xmlDetails(sourceFile, SourceScanner.getDetailObject("JSP"));
		} else if ("php".equals(name)) {
			BasicDetails.phpDetails(sourceFile, SourceScanner.getDetailObject("PHP"));
		}
		loggerSourceScanner.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private static DetailObject getDetailObject(String language) {
		loggerSourceScanner.entry();
		boolean objectFound = false;
		DetailObject detailObject = null;
		for (DetailObject detailObject1 : SourceScanner.detailObjects) {
			if (detailObject1.getLanguage().equalsIgnoreCase(language)) {
				objectFound = true;
				detailObject = detailObject1;
			}
		}
		if (!objectFound) {
			detailObject = new DetailObject(language);
			SourceScanner.detailObjects.add(detailObject);
			MainGUI.getMAINGUI().addDetails(detailObject);
		}
		loggerSourceScanner.exit(LoggerValues.SUCCESSFUL_EXIT);
		return detailObject;
	}
}
