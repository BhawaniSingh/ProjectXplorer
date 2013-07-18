package org.bhawanisingh.projectxplorer.api;

import java.util.ArrayList;

import org.bhawanisingh.projectxplorer.api.util.DetailObject;
import org.bhawanisingh.projectxplorer.gui.MainGUI;

public class MainThread implements Runnable {

	private String sourceFolders;

	public MainThread(String sourceFolders) {
		this.sourceFolders = sourceFolders;
	}

	@Override
	public void run() {
		MainGUI.getMAINGUI().reset();
		DetailObject.reset();
		SourceScanner.detailObjects = new ArrayList<DetailObject>();
		SourceScanner.separateFolders(this.sourceFolders);
		MainGUI.getMAINGUI().lastUpdateDetail();
	}

}
