package org.bhawanisingh.projectxplorer.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.bhawanisingh.projectxplorer.api.util.DetailObject;
import org.bhawanisingh.projectxplorer.gui.MainGUI;

public class BasicDetails {
	public static void cppDetails(File fileName, DetailObject detailObject) {
		BasicDetails.javaDetails(fileName, detailObject);
	}

	public static void javaDetails(File fileName, DetailObject detailObject) {
		detailObject.updateNumberOfFiles();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.trim().equalsIgnoreCase("")) {
					detailObject.updateBlankLines();
				} else if (line.trim().startsWith("//")) {
					detailObject.updateCommentLines();
				} else if (line.trim().startsWith("/*")) {
					detailObject.updateCommentLines();
					while (line != null) {
						if (line.trim().contains("*/")) {
							break;
						}
						detailObject.updateCommentLines();
						line = bufferedReader.readLine();
					}
				} else {
					detailObject.updateLineOfCode();
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException fileNotFoundException) {
		} catch (IOException ioException) {
		}
		MainGUI.getMAINGUI().updateDetails(detailObject);
		System.out.println("Lines of code : " + detailObject.getLineOfCode());
		System.out.println("Blank Lines : " + detailObject.getBlankLines());
		System.out.println("Commented Lines : " + detailObject.getCommentLines());
		System.out.println("Total Lines : " + (detailObject.getCommentLines() + detailObject.getBlankLines() + detailObject.getLineOfCode()));
	}

	public static void xmlDetails(File fileName, DetailObject detailObject) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.trim().equalsIgnoreCase("")) {
					detailObject.updateBlankLines();
				} else if (line.trim().contains("<!--")) {
					detailObject.updateCommentLines();
					while (line != null) {
						if (line.trim().contains("-->")) {
							break;
						}
						detailObject.updateCommentLines();
						line = bufferedReader.readLine();
					}
				} else {
					detailObject.updateLineOfCode();
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException fileNotFoundException) {
		} catch (IOException ioException) {
		}
		MainGUI.getMAINGUI().updateDetails(detailObject);
		System.out.println("Lines of code : " + detailObject.getLineOfCode());
		System.out.println("Blank Lines : " + detailObject.getBlankLines());
		System.out.println("Commented Lines : " + detailObject.getCommentLines());
		System.out.println("Total Lines : " + (detailObject.getCommentLines() + detailObject.getBlankLines() + detailObject.getLineOfCode()));

	}

	public static void visualBasicDetails(File fileName, DetailObject detailObject) {
		BasicDetails.singleLine(fileName, detailObject, ";");
	}

	public static void pythonDetails(File fileName, DetailObject detailObject) {
		BasicDetails.singleLine(fileName, detailObject, "#");
	}

	public static void singleLine(File fileName, DetailObject detailObject, String comment) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.trim().equalsIgnoreCase("")) {
					detailObject.updateBlankLines();
				} else if (line.trim().startsWith(comment)) {
					detailObject.updateCommentLines();
				} else {
					detailObject.updateLineOfCode();
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException fileNotFoundException) {
		} catch (IOException ioException) {
		}
		MainGUI.getMAINGUI().updateDetails(detailObject);
		System.out.println("Lines of code : " + detailObject.getLineOfCode());
		System.out.println("Blank Lines : " + detailObject.getBlankLines());
		System.out.println("Commented Lines : " + detailObject.getCommentLines());
		System.out.println("Total Lines : " + (detailObject.getCommentLines() + detailObject.getBlankLines() + detailObject.getLineOfCode()));

	}
}
