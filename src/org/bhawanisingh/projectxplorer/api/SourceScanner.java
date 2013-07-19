package org.bhawanisingh.projectxplorer.api;

import java.io.File;
import java.util.ArrayList;

import org.bhawanisingh.projectxplorer.api.util.DetailObject;
import org.bhawanisingh.projectxplorer.api.util.SupportedFiles;
import org.bhawanisingh.projectxplorer.gui.MainGUI;

public class SourceScanner {

	public static ArrayList<DetailObject> detailObjects;

	public static void separateFolders(String folderList) {
		String[] folders = folderList.split("\n");
		for (String folder : folders) {
			SourceScanner.sourceFolderDetails(folder);
		}
	}

	public static void sourceFolderDetails(String sourceFolder) {
		System.runFinalization();
		// if (!(DetailObject.getTOTAL_LINES_OF_CODE() > 20000000)) {
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
		// }

	}

	private static void fileSelector(File sourceFile) {
		String name = sourceFile.getName().substring(sourceFile.getName().lastIndexOf(".") + 1);
		name = name.trim();
		if (SupportedFiles.isJava(name)) {
			BasicDetails.javaDetails(sourceFile, SourceScanner.getDetailObject("Java"));
		} else if (SupportedFiles.isFXML(name)) {
			BasicDetails.xmlDetails(sourceFile, SourceScanner.getDetailObject("Java"));
		} else if (SupportedFiles.isCPP(name)) {
			BasicDetails.cppDetails(sourceFile, SourceScanner.getDetailObject("C/C++"));
		} else if (SupportedFiles.isObjectiveC(name)) {
			BasicDetails.cppDetails(sourceFile, SourceScanner.getDetailObject("Objective-C"));
		} else if (SupportedFiles.isCSharp(name)) {
			BasicDetails.cSharpDetails(sourceFile, SourceScanner.getDetailObject("C Sharp"));
		} else if (SupportedFiles.isXAML(name)) {
			BasicDetails.xmlDetails(sourceFile, SourceScanner.getDetailObject("C Sharp"));
		} else if (SupportedFiles.isPython(name)) {
			BasicDetails.pythonDetails(sourceFile, SourceScanner.getDetailObject("Python"));
		} else if (SupportedFiles.isVisualBasic(name)) {
			BasicDetails.visualBasicDetails(sourceFile, SourceScanner.getDetailObject("Visual Basic"));
		} else if (SupportedFiles.isXML(name)) {
			BasicDetails.xmlDetails(sourceFile, SourceScanner.getDetailObject("XML"));
		} else if (SupportedFiles.isHTML(name)) {
			BasicDetails.xmlDetails(sourceFile, SourceScanner.getDetailObject("HTML"));
		} else if (SupportedFiles.isJSP(name)) {
			BasicDetails.jspDetails(sourceFile, SourceScanner.getDetailObject("JSP"));
		} else if (SupportedFiles.isCSS(name)) {
			BasicDetails.cssDetails(sourceFile, SourceScanner.getDetailObject("CSS"));
		} else if (SupportedFiles.isPHP(name)) {
			BasicDetails.phpDetails(sourceFile, SourceScanner.getDetailObject("PHP"));
		} else if (SupportedFiles.isJS(name)) {
			BasicDetails.jsDetails(sourceFile, SourceScanner.getDetailObject("JavaScript"));
		}
	}

	private static DetailObject getDetailObject(String language) {
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
		return detailObject;
	}
}
