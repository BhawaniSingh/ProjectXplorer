package org.bhawanisingh.projectxplorer.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.bhawanisingh.projectxplorer.api.MainThread;
import org.bhawanisingh.projectxplorer.api.ProjectDetails;
import org.bhawanisingh.projectxplorer.api.help.AboutProject;
import org.bhawanisingh.projectxplorer.api.util.DetailObject;

public class MainGUI extends JFrame {

	private JPanel mainPanel;
	private JScrollPane sourceScrollPane;
	private JButton browseButton;
	private JTextArea sourceTextArea;
	private JScrollPane detailScrollPane;
	private JPanel detailBasePanel;
	private ArrayList<DetailPanel> detailPanels;
	private JPanel submitButtonPanel;
	private JButton submitButton;

	private static MainGUI MAIN_GUI;
	private static int yPosition = -1;
	private Thread mainThread;

	public MainGUI() {
		super(AboutProject.PROGRAM_NAME + " " + AboutProject.PROGRAM_VERSION);
		MainGUI.MAIN_GUI = this;
		this.initialize();
		this.addComponents();
		this.addListeners();
		this.theming();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}

	private void initialize() {
		this.mainPanel = new JPanel(new GridBagLayout());
		this.sourceTextArea = new JTextArea(4, 40);
		this.browseButton = new JButton("Browse");
		this.sourceScrollPane = new JScrollPane(this.sourceTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.detailBasePanel = new JPanel(new GridBagLayout());
		this.detailScrollPane = new JScrollPane(this.detailBasePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.detailPanels = new ArrayList<DetailPanel>();
		this.detailPanels.add(new DetailPanel());
		this.submitButtonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		this.submitButton = new JButton("Submit");
		this.sourceTextArea.setText("/home/Mount/Development/workspaces/eclipse_workspace");
	}

	private void addComponents() {
		this.submitButtonPanel.add(this.submitButton);
		Layout.add(this.detailBasePanel, this.detailPanels.get(0), 0, ++MainGUI.yPosition, 1, 1, 100, 0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL);
		Layout.add(this.mainPanel, this.sourceScrollPane, 0, 0, 4, 1, 100, 5, GridBagConstraints.EAST, GridBagConstraints.BOTH);
		Layout.add(this.mainPanel, this.browseButton, 4, 0, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(this.mainPanel, this.detailScrollPane, 0, 1, 5, 3, 100, 90, GridBagConstraints.EAST, GridBagConstraints.BOTH);
		Layout.add(this.mainPanel, this.submitButtonPanel, 0, 6, 5, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);

		this.add(this.mainPanel);
	}

	private void addListeners() {
		this.browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MainGUI.this.browseButtonAction();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		});
		this.submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.this.submitAction();
			}
		});
	}

	private void theming() {
		this.detailScrollPane.getViewport().setBorder(null);
	}

	private void browseButtonAction() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setDialogTitle("Select The Source Directory");
		if (fileChooser.showDialog(null, "Select") == JFileChooser.APPROVE_OPTION) {
			this.sourceTextArea.insert(fileChooser.getSelectedFile().getCanonicalPath() + "\n", this.sourceTextArea.getText().length());
		}
	}

	private void submitAction() {
		MainThread mainRunnable = new MainThread(this.sourceTextArea.getText());
		this.mainThread = new Thread(mainRunnable);
		this.mainThread.start();
	}

	public void addDetails(DetailObject detailObject) {
		DetailPanel detailPanel = new DetailPanel(detailObject.getLanguage());
		this.detailPanels.add(detailPanel);
		detailPanel.setBlankLinesLabel(detailObject.getBlankLines());
		detailPanel.setLineOfCodeLabel(detailObject.getLineOfCode());
		detailPanel.setCommentsLabel(detailObject.getCommentLines());
		detailPanel.setNumberOfFilesLabel(detailObject.getNumberOfFiles());
		Layout.add(this.detailBasePanel, detailPanel, 0, ++MainGUI.yPosition, 1, 1, 100, 0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL);

	}

	public void updateDetails(DetailObject detailObject) {
		for (DetailPanel detailPanel : this.detailPanels) {
			if (detailPanel.getLanguage().equalsIgnoreCase(detailObject.getLanguage())) {
				detailPanel.setBlankLinesLabel(detailObject.getBlankLines());
				detailPanel.setLineOfCodeLabel(detailObject.getLineOfCode());
				detailPanel.setCommentsLabel(detailObject.getCommentLines());
				detailPanel.setNumberOfFilesLabel(detailObject.getNumberOfFiles());
				detailPanel.setTotalLinesLabel(detailObject.getBlankLines() + detailObject.getCommentLines() + detailObject.getLineOfCode());
				long codelines = detailObject.getBlankLines() + detailObject.getCommentLines() + detailObject.getLineOfCode();
				long totallines = DetailObject.getTOTAL_BLANK_LINES() + DetailObject.getTOTAL_COMMENT_LINES() + DetailObject.getTOTAL_LINES_OF_CODE();
				float percentage = ((float) codelines / (float) totallines) * 100.0f;
				detailPanel.setCodeShareLabel(percentage);
				break;

			}
		}
		this.detailPanels.get(0).setBlankLinesLabel(DetailObject.getTOTAL_BLANK_LINES());
		this.detailPanels.get(0).setLineOfCodeLabel(DetailObject.getTOTAL_LINES_OF_CODE());
		this.detailPanels.get(0).setCommentsLabel(DetailObject.getTOTAL_COMMENT_LINES());
		this.detailPanels.get(0).setNumberOfFilesLabel(DetailObject.getTOTAL_NUMBER_OF_FILES());
		this.detailPanels.get(0).setTotalLinesLabel(DetailObject.getTOTAL_BLANK_LINES() + DetailObject.getTOTAL_COMMENT_LINES() + DetailObject.getTOTAL_LINES_OF_CODE());
	}

	public void lastUpdateDetail() {

		long BLANK_LINES = DetailObject.getTOTAL_BLANK_LINES();
		long CODE_LINES = DetailObject.getTOTAL_LINES_OF_CODE();
		long COMMENT_LINES = DetailObject.getTOTAL_COMMENT_LINES();
		long NUMBER_OF_FILES = DetailObject.getTOTAL_NUMBER_OF_FILES();
		long TOTAL_LINES = BLANK_LINES + CODE_LINES + COMMENT_LINES;
		this.detailPanels.get(0).setBlankLinesLabel(BLANK_LINES);
		this.detailPanels.get(0).setLineOfCodeLabel(CODE_LINES);
		this.detailPanels.get(0).setCommentsLabel(COMMENT_LINES);
		this.detailPanels.get(0).setNumberOfFilesLabel(NUMBER_OF_FILES);
		this.detailPanels.get(0).setTotalLinesLabel(TOTAL_LINES);

		long blankLines;
		long codeLines;
		long commentLines;
		long numberOfFiles;
		long totalLines;
		float codeShare;

		for (int i = 1; i < this.detailPanels.size(); ++i) {
			blankLines = ProjectDetails.detailObjects.get(i - 1).getBlankLines();
			codeLines = ProjectDetails.detailObjects.get(i - 1).getLineOfCode();
			commentLines = ProjectDetails.detailObjects.get(i - 1).getCommentLines();
			numberOfFiles = ProjectDetails.detailObjects.get(i - 1).getNumberOfFiles();
			totalLines = blankLines + codeLines + commentLines;
			codeShare = ((float) totalLines / (float) TOTAL_LINES) * 100.0f;
			this.detailPanels.get(i).setBlankLinesLabel(blankLines);
			this.detailPanels.get(i).setLineOfCodeLabel(codeLines);
			this.detailPanels.get(i).setCommentsLabel(commentLines);
			this.detailPanels.get(i).setNumberOfFilesLabel(numberOfFiles);
			this.detailPanels.get(i).setTotalLinesLabel(totalLines);
			this.detailPanels.get(i).setCodeShareLabel(codeShare);
		}
	}

	public void reset() {
		if (this.detailPanels.size() > 1) {
			for (int i = 1; i < this.detailPanels.size(); ++i) {
				this.detailBasePanel.remove(this.detailPanels.get(i));
			}
		}
		DetailPanel detailPanel = this.detailPanels.get(0);
		this.detailPanels.removeAll(this.detailPanels);
		this.detailPanels.add(detailPanel);
	}

	public static MainGUI getMAINGUI() {
		return MainGUI.MAIN_GUI;
	}
}
