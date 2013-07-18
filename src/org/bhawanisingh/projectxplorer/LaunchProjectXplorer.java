package org.bhawanisingh.projectxplorer;

import org.bhawanisingh.projectxplorer.api.util.Directories;
import org.bhawanisingh.projectxplorer.gui.MainGUI;

public class LaunchProjectXplorer {
	public static void main(String[] args) {
		Directories.foldervalidator();
		new MainGUI();
	}
}
