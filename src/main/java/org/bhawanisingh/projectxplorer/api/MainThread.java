package org.bhawanisingh.projectxplorer.api;

public class MainThread implements Runnable {

	private String sourceFolders;

	public MainThread(String sourceFolders) {
		this.sourceFolders = sourceFolders;
	}

	@Override
	public void run() {
		ProjectDetails.separateFolders(this.sourceFolders);
	}

}
