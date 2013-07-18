package org.bhawanisingh.projectxplorer.api.util;

import java.io.File;

public class Directories {

	public static final String[] AVAILABLE_FILETYPES = { "java", "cs", "c", "i", "ii",
			"m", "mi", "mii", "M", "mm",
			"h", "cc", "cp", "cxx", "cpp",
			"CPP", "c++", "C", "hh", "H",
			"hp", "hxx", "hpp", "HPP", "h++", "tcc",
			"py", "js" };

	public static String APP_HOME = System.getProperty("user.home") + "/.ProjectXplorer";
	public static String LOGS_DIR = Directories.APP_HOME + "/logs";

	public static void foldervalidator() {
		File file = new File(Directories.APP_HOME);
		if (!file.exists()) {
			file.mkdirs();
		}

		file = new File(Directories.LOGS_DIR);
		if (!file.exists()) {
			file.mkdir();
		}
	}

}