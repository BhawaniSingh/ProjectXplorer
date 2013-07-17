package org.bhawanisingh.projectxplorer.api.util;

public class DetailObject {

	private static long TOTAL_LINES_OF_CODE = 0;
	private static long TOTAL_BLANK_LINES = 0;
	private static long TOTAL_COMMENT_LINES = 0;
	private static long TOTAL_NUMBER_OF_FILES = 0;

	private String language;
	private long numberOfFiles;
	private long blankLines;
	private long lineOfCode;
	private long commentLines;

	public DetailObject(String language) {
		this.language = language;
		this.blankLines = 0;
		this.lineOfCode = 0;
		this.commentLines = 0;
	}

	public static long getTOTAL_LINES_OF_CODE() {
		return DetailObject.TOTAL_LINES_OF_CODE;
	}

	public static long getTOTAL_BLANK_LINES() {
		return DetailObject.TOTAL_BLANK_LINES;
	}

	public static long getTOTAL_COMMENT_LINES() {
		return DetailObject.TOTAL_COMMENT_LINES;
	}

	public static long getTOTAL_NUMBER_OF_FILES() {
		return DetailObject.TOTAL_NUMBER_OF_FILES;
	}

	public String getLanguage() {
		return this.language;
	}

	public long getNumberOfFiles() {
		return this.numberOfFiles;
	}

	public long getBlankLines() {
		return this.blankLines;
	}

	public long getLineOfCode() {
		return this.lineOfCode;
	}

	public long getCommentLines() {
		return this.commentLines;
	}

	public static void updateTOTAL_LINES_OF_CODE() {
		++DetailObject.TOTAL_LINES_OF_CODE;
	}

	public static void updateTOTAL_BLANK_LINES() {
		++DetailObject.TOTAL_BLANK_LINES;
	}

	public static void updateTOTAL_COMMENT_LINES() {
		++DetailObject.TOTAL_COMMENT_LINES;
	}

	public static void updateTOTAL_NUMBER_OF_FILES() {
		++DetailObject.TOTAL_NUMBER_OF_FILES;
	}

	public void updateNumberOfFiles() {
		++this.numberOfFiles;
	}

	public void updateBlankLines() {
		++this.blankLines;
	}

	public void updateLineOfCode() {
		++this.lineOfCode;
	}

	public void updateCommentLines() {
		++this.commentLines;
	}

}
