package org.bhawanisingh.projectxplorer.api.util;

public class SupportedFiles {
	public static final String[] JAVA = { "java" };
	public static final String[] FXML = { "fxml" };
	public static final String[] XAML = { "xaml" };
	public static final String[] C_SHARP = { "cs" };
	public static final String[] VISUAL_BASIC = { "vb" };
	public static final String[] JSP = { "jsp", "JSP" };
	public static final String[] PYTHON = { "py", "PY" };
	public static final String[] CSS = { "css", "CSS", "LESS", "less", "SASS", "sass" };
	public static final String[] JS = { "js", "JS" };
	public static final String[] XML = { "XML", "xml" };
	public static final String[] PHP = { "php" };
	public static final String[] HTML = { "html", "htm", "HTML", "HTM" };
	public static final String[] CPP = { "c", "i", "ii", "h",
			"cc", "cp", "cxx", "cpp",
			"CPP", "c++", "C", "hh",
			"H", "hp", "hxx", "hpp",
			"HPP", "h++", "tcc" };
	public static final String[] OBJECTIVE_C = { "m", "mi", "mii", "M", "mm" };

	public static final boolean isJava(String fileName) {
		for (String file : JAVA) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isFXML(String fileName) {
		for (String file : FXML) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isXAML(String fileName) {
		for (String file : XAML) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isCSharp(String fileName) {
		for (String file : C_SHARP) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isVisualBasic(String fileName) {
		for (String file : VISUAL_BASIC) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isJSP(String fileName) {
		for (String file : JSP) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isPython(String fileName) {
		for (String file : PYTHON) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isCSS(String fileName) {
		for (String file : CSS) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isJS(String fileName) {
		for (String file : JS) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isXML(String fileName) {
		for (String file : XML) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isPHP(String fileName) {
		for (String file : PHP) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isHTML(String fileName) {
		for (String file : HTML) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isCPP(String fileName) {
		for (String file : CPP) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isObjectiveC(String fileName) {
		for (String file : OBJECTIVE_C) {
			if (file.equals(fileName)) {
				return true;
			}
		}
		return false;
	}
}
