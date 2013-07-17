package org.bhawanisingh.projectxplorer.gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetailPanel extends JPanel {

	private String language;

	private JLabel numberOfFilesLabel;
	private JLabel lineOfCodeLabel;
	private JLabel commentsLabel;
	private JLabel blankLinesLabel;
	private JLabel totalLinesLabel;
	private JLabel codeShareLabel;

	public DetailPanel() {
		this.language = "Default";
		this.initialize();
		this.addComponents();
		this.theming();
	}

	public DetailPanel(String language) {
		this.language = language;
		this.initialize();
		this.addComponents();
		this.theming();
	}

	private void initialize() {
		this.setLayout(new GridLayout(7, 1));
		this.numberOfFilesLabel = new JLabel("Number of Files : ");
		this.lineOfCodeLabel = new JLabel("Lines of Code : ");
		this.commentsLabel = new JLabel("Commented Lines : ");
		this.blankLinesLabel = new JLabel("Blank Lines : ");
		this.totalLinesLabel = new JLabel("Total Lines : ");
		this.codeShareLabel = new JLabel("Share in Project : ");
	}

	private void addComponents() {
		this.add(this.numberOfFilesLabel);
		this.add(this.lineOfCodeLabel);
		this.add(this.commentsLabel);
		this.add(this.blankLinesLabel);
		this.add(this.totalLinesLabel);
		this.add(this.codeShareLabel);
	}

	private void theming() {
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(Borders.ETCHED_LOWERED, this.language), Borders.PADDING_BORDER));
	}

	public String getLanguage() {
		return this.language;
	}

	public void setNumberOfFilesLabel(long numberOfFiles) {

		this.numberOfFilesLabel.setText("Number of Files : " + numberOfFiles);
	}

	public void setLineOfCodeLabel(long lineOfCode) {
		this.lineOfCodeLabel.setText("Lines of Code : " + lineOfCode);
	}

	public void setCommentsLabel(long comments) {
		this.commentsLabel.setText("Commented Lines : " + comments);
	}

	public void setBlankLinesLabel(long blankLines) {
		this.blankLinesLabel.setText("Blank Lines : " + blankLines);
	}

	public void setTotalLinesLabel(long totalLines) {
		this.totalLinesLabel.setText("Total Lines : " + totalLines);
	}

	public void setCodeShareLabel(long codeShare) {
		this.codeShareLabel.setText("Share in Project : " + codeShare);
	}

}
