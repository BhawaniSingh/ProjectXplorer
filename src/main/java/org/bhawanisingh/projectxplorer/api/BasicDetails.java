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

	public static void cSharpDetails(File fileName, DetailObject detailObject) {
		BasicDetails.javaDetails(fileName, detailObject);
	}

	public static void jspDetails(File fileName, DetailObject detailObject) {
		BasicDetails.jspStyleComments(fileName, detailObject, "<%--", "--%>");
	}

	public static void xmlDetails(File fileName, DetailObject detailObject) {
		BasicDetails.jspStyleComments(fileName, detailObject, "<!--", "-->");
	}

	public static void visualBasicDetails(File fileName, DetailObject detailObject) {
		BasicDetails.singleLineComments(fileName, detailObject, "'");
	}

	public static void pythonDetails(File fileName, DetailObject detailObject) {
		BasicDetails.singleLineComments(fileName, detailObject, "#");
	}

	public static void javaDetails(File fileName, DetailObject detailObject) {
		detailObject.updateNumberOfFiles();
		DetailObject.updateTOTAL_NUMBER_OF_FILES();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.trim().equalsIgnoreCase("")) {
					DetailObject.updateTOTAL_BLANK_LINES();
					detailObject.updateBlankLines();
				} else if (line.trim().startsWith("//")) {
					DetailObject.updateTOTAL_COMMENT_LINES();
					detailObject.updateCommentLines();
				} else if (line.trim().startsWith("/*")) {
					DetailObject.updateTOTAL_COMMENT_LINES();
					detailObject.updateCommentLines();
					while (line != null) {
						if (line.trim().contains("*/")) {
							break;
						}
						DetailObject.updateTOTAL_COMMENT_LINES();
						detailObject.updateCommentLines();
						line = bufferedReader.readLine();
					}
				} else {
					DetailObject.updateTOTAL_LINES_OF_CODE();
					detailObject.updateLineOfCode();
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException fileNotFoundException) {
		} catch (IOException ioException) {
		}
		MainGUI.getMAINGUI().updateDetails(detailObject);
	}

	public static void jspStyleComments(File fileName, DetailObject detailObject, String startComment, String endComment) {
		detailObject.updateNumberOfFiles();
		DetailObject.updateTOTAL_NUMBER_OF_FILES();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.trim().equalsIgnoreCase("")) {
					DetailObject.updateTOTAL_BLANK_LINES();
					detailObject.updateBlankLines();
				} else if (line.trim().startsWith(startComment)) {
					DetailObject.updateTOTAL_COMMENT_LINES();
					detailObject.updateCommentLines();
					while (line != null) {
						if (line.trim().contains(endComment)) {
							break;
						}
						DetailObject.updateTOTAL_COMMENT_LINES();
						detailObject.updateCommentLines();
						line = bufferedReader.readLine();
					}
				} else if (line.trim().startsWith("<!--")) {
					DetailObject.updateTOTAL_COMMENT_LINES();
					detailObject.updateCommentLines();
					while (line != null) {
						if (line.trim().contains("-->")) {
							break;
						}
						DetailObject.updateTOTAL_COMMENT_LINES();
						detailObject.updateCommentLines();
						line = bufferedReader.readLine();
					}
				} else {
					DetailObject.updateTOTAL_LINES_OF_CODE();
					detailObject.updateLineOfCode();
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException fileNotFoundException) {
		} catch (IOException ioException) {
		}
		MainGUI.getMAINGUI().updateDetails(detailObject);
	}

	public static void singleLineComments(File fileName, DetailObject detailObject, String comment) {
		detailObject.updateNumberOfFiles();
		DetailObject.updateTOTAL_NUMBER_OF_FILES();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.trim().equalsIgnoreCase("")) {
					detailObject.updateBlankLines();
					DetailObject.updateTOTAL_BLANK_LINES();
				} else if (line.trim().startsWith(comment)) {
					DetailObject.updateTOTAL_COMMENT_LINES();
					detailObject.updateCommentLines();
				} else {
					DetailObject.updateTOTAL_LINES_OF_CODE();
					detailObject.updateLineOfCode();
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException fileNotFoundException) {
		} catch (IOException ioException) {
		}
		MainGUI.getMAINGUI().updateDetails(detailObject);
	}

	public static void phpDetails(File fileName, DetailObject detailObject) {
		detailObject.updateNumberOfFiles();
		DetailObject.updateTOTAL_NUMBER_OF_FILES();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains("<?php")) {
					line = line.substring(line.indexOf("<?php"));
				} else if (line.contains("<?")) {
					line = line.substring(line.indexOf("<?"));
				}

				if (line.trim().equalsIgnoreCase("")) {
					DetailObject.updateTOTAL_BLANK_LINES();
					detailObject.updateBlankLines();
				} else if (line.trim().startsWith("//")) {
					DetailObject.updateTOTAL_COMMENT_LINES();
					detailObject.updateCommentLines();
				} else if (line.trim().startsWith("#")) {
					DetailObject.updateTOTAL_COMMENT_LINES();
					detailObject.updateCommentLines();
				} else if (line.trim().startsWith("/*")) {
					DetailObject.updateTOTAL_COMMENT_LINES();
					detailObject.updateCommentLines();
					while (line != null) {
						if (line.trim().contains("*/")) {
							break;
						}
						DetailObject.updateTOTAL_COMMENT_LINES();
						detailObject.updateCommentLines();
						line = bufferedReader.readLine();
					}
				} else {
					DetailObject.updateTOTAL_LINES_OF_CODE();
					detailObject.updateLineOfCode();
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException fileNotFoundException) {
		} catch (IOException ioException) {
		}
		MainGUI.getMAINGUI().updateDetails(detailObject);
	}
}
