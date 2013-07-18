package org.bhawanisingh.projectxplorer.gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.bhawanisingh.projectxplorer.api.util.Borders;

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
		this.initialize(false);
		this.addComponents(false);
		this.theming();
	}

	public DetailPanel(String language) {
		this.language = language;
		this.initialize(true);
		this.addComponents(true);
		this.theming();
	}

	private void initialize(boolean check) {
		this.setLayout(new GridLayout(7, 1));
		this.numberOfFilesLabel = new JLabel("Number of Files \t:\t ");
		this.lineOfCodeLabel = new JLabel("Lines of Code \t:\t ");
		this.commentsLabel = new JLabel("Commented Lines \t:\t ");
		this.blankLinesLabel = new JLabel("Blank Lines \t:\t ");
		this.totalLinesLabel = new JLabel("Total Lines \t:\t ");
		if (check) {
			this.codeShareLabel = new JLabel("Share in Project \t:\t ");
		}

	}

	private void addComponents(boolean check) {
		this.add(this.numberOfFilesLabel);
		this.add(this.lineOfCodeLabel);
		this.add(this.commentsLabel);
		this.add(this.blankLinesLabel);
		this.add(this.totalLinesLabel);
		if (check) {
			this.add(this.codeShareLabel);
		}
	}

	private void theming() {
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(Borders.ETCHED_LOWERED, this.language), Borders.PADDING_BORDER));
	}

	public String getLanguage() {
		return this.language;
	}

	public void setNumberOfFilesLabel(long numberOfFiles) {

		this.numberOfFilesLabel.setText("Number of Files \t:\t" + numberOfFiles);
	}

	public void setLineOfCodeLabel(long lineOfCode) {
		this.lineOfCodeLabel.setText("Lines of Code \t:\t " + lineOfCode);
	}

	public void setCommentsLabel(long comments) {
		this.commentsLabel.setText("Commented Lines \t:\t " + comments);
	}

	public void setBlankLinesLabel(long blankLines) {
		this.blankLinesLabel.setText("Blank Lines \t:\t " + blankLines);
	}

	public void setTotalLinesLabel(long totalLines) {
		this.totalLinesLabel.setText("Total Lines \t:\t " + totalLines);
	}

	public void setCodeShareLabel(float codeShare) {
		this.codeShareLabel.setText("Share in Project \t:\t " + codeShare);
	}
}
