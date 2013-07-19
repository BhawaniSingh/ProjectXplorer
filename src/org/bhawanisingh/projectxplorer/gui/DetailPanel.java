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
		language = "Default";
		initialize(false);
		addComponents(false);
		theming();
	}

	public DetailPanel(String language) {
		this.language = language;
		initialize(true);
		addComponents(true);
		theming();
	}

	private void initialize(boolean check) {
		setLayout(new GridLayout(7, 1));
		numberOfFilesLabel = new JLabel("Number of Files \t:\t ");
		lineOfCodeLabel = new JLabel("Lines of Code \t:\t ");
		commentsLabel = new JLabel("Commented Lines \t:\t ");
		blankLinesLabel = new JLabel("Blank Lines \t:\t ");
		totalLinesLabel = new JLabel("Total Lines \t:\t ");
		if (check) {
			codeShareLabel = new JLabel("Share in Project \t:\t ");
		}

	}

	private void addComponents(boolean check) {
		this.add(numberOfFilesLabel);
		this.add(lineOfCodeLabel);
		this.add(commentsLabel);
		this.add(blankLinesLabel);
		this.add(totalLinesLabel);
		if (check) {
			this.add(codeShareLabel);
		}
	}

	private void theming() {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(Borders.ETCHED_LOWERED, language), Borders.PADDING_BORDER));
	}

	public String getLanguage() {
		return language;
	}

	public void setNumberOfFilesLabel(long numberOfFiles) {

		numberOfFilesLabel.setText("Number of Files \t:\t" + numberOfFiles);
	}

	public void setLineOfCodeLabel(long lineOfCode) {
		lineOfCodeLabel.setText("Lines of Code \t:\t " + lineOfCode);
	}

	public void setCommentsLabel(long comments) {
		commentsLabel.setText("Commented Lines \t:\t " + comments);
	}

	public void setBlankLinesLabel(long blankLines) {
		blankLinesLabel.setText("Blank Lines \t:\t " + blankLines);
	}

	public void setTotalLinesLabel(long totalLines) {
		totalLinesLabel.setText("Total Lines \t:\t " + totalLines);
	}

	public void setCodeShareLabel(float codeShare) {
		codeShareLabel.setText("Share in Project \t:\t " + codeShare);
	}
}
