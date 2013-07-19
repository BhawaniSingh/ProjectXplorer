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
		long initialTime = System.nanoTime();
		MainGUI.getMAINGUI().getPleaseWait().start();
		MainGUI.getMAINGUI().reset();
		DetailObject.reset();
		SourceScanner.detailObjects = new ArrayList<DetailObject>();
		SourceScanner.separateFolders(sourceFolders);
		MainGUI.getMAINGUI().lastUpdateDetail();
		MainGUI.getMAINGUI().getPleaseWait().stop();
		long finalTime = System.nanoTime();
		System.err.println("Total Time in nano seconds : " + (finalTime - initialTime));
		System.err.println("Total Time in seconds : " + ((float) finalTime - (float) initialTime) / 1000000000.000);
	}

}
