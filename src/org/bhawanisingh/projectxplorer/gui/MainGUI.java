package org.bhawanisingh.projectxplorer.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.projectxplorer.api.MainThread;
import org.bhawanisingh.projectxplorer.api.SourceScanner;
import org.bhawanisingh.projectxplorer.api.help.AboutDialog;
import org.bhawanisingh.projectxplorer.api.help.AboutProject;
import org.bhawanisingh.projectxplorer.api.logging.ExceptionLogger;
import org.bhawanisingh.projectxplorer.api.logging.LoggerValues;
import org.bhawanisingh.projectxplorer.api.util.Borders;
import org.bhawanisingh.projectxplorer.api.util.DetailObject;
import org.bhawanisingh.projectxplorer.api.util.PleaseWait;

public class MainGUI extends JFrame {

	private Logger loggerMainClass = LogManager.getLogger(MainGUI.class.getClass());

	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JMenuItem exitMenuItem;
	private JMenuItem aboutMenuItem;

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
	private PleaseWait pleaseWait;

	public MainGUI() {
		super(AboutProject.PROGRAM_NAME + " " + AboutProject.PROGRAM_VERSION);
		loggerMainClass.entry();
		MainGUI.MAIN_GUI = this;
		initialize();
		addComponents();
		addListeners();
		theming();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		loggerMainClass.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void initialize() {
		loggerMainClass.entry();

		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_F4);
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		aboutMenuItem = new JMenuItem("About", KeyEvent.VK_A);
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		mainPanel = new JPanel(new GridBagLayout());
		sourceTextArea = new JTextArea(4, 40);
		browseButton = new JButton("Browse");
		sourceScrollPane = new JScrollPane(sourceTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		detailBasePanel = new JPanel(new GridBagLayout());
		detailScrollPane = new JScrollPane(detailBasePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		detailPanels = new ArrayList<DetailPanel>();
		detailPanels.add(new DetailPanel());
		submitButtonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		submitButton = new JButton("Submit");
		sourceTextArea.setText("/home/Mount/Development/workspaces/eclipse_workspace");
		pleaseWait = new PleaseWait();
		loggerMainClass.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addComponents() {
		loggerMainClass.entry();

		fileMenu.add(exitMenuItem);
		helpMenu.add(aboutMenuItem);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		setJMenuBar(menuBar);

		submitButtonPanel.add(submitButton);
		Layout.add(detailBasePanel, detailPanels.get(0), 0, ++MainGUI.yPosition, 1, 1, 100, 0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL);
		Layout.add(mainPanel, sourceScrollPane, 0, 0, 4, 1, 100, 5, GridBagConstraints.EAST, GridBagConstraints.BOTH);
		Layout.add(mainPanel, browseButton, 4, 0, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(mainPanel, detailScrollPane, 0, 1, 5, 3, 100, 90, GridBagConstraints.EAST, GridBagConstraints.BOTH);
		Layout.add(mainPanel, submitButtonPanel, 0, 6, 5, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);

		this.add(mainPanel);
		setGlassPane(pleaseWait);
		loggerMainClass.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addListeners() {
		loggerMainClass.entry();

		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutDialog(MainGUI.this);
			}
		});

		browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.this.browseButtonAction();
			}
		});

		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.this.submitAction();
			}
		});
		loggerMainClass.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void theming() {
		loggerMainClass.entry();
		detailScrollPane.getViewport().setBorder(null);
		detailScrollPane.setBorder(null);
		mainPanel.setBorder(Borders.COMPOUND_ETCHED_LOWERED);
		loggerMainClass.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void browseButtonAction() {
		loggerMainClass.entry();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setDialogTitle("Select The Source Directory");
		if (fileChooser.showDialog(null, "Select") == JFileChooser.APPROVE_OPTION) {
			try {
				sourceTextArea.insert(fileChooser.getSelectedFile().getCanonicalPath() + "\n", sourceTextArea.getText().length());
				loggerMainClass.exit(LoggerValues.SUCCESSFUL_EXIT);
			} catch (IOException ioException) {
				loggerMainClass.exit(LoggerValues.UNSUCCESSFUL_EXIT);
				ExceptionLogger.ioExceptionLogger(loggerMainClass, ioException);
			}
		}
	}

	private void submitAction() {
		loggerMainClass.entry();
		MainThread mainRunnable = new MainThread(sourceTextArea.getText());
		mainThread = new Thread(mainRunnable);
		mainThread.start();
		loggerMainClass.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	public void addDetails(DetailObject detailObject) {
		DetailPanel detailPanel = new DetailPanel(detailObject.getLanguage());
		detailPanels.add(detailPanel);
		detailPanel.setBlankLinesLabel(detailObject.getBlankLines());
		detailPanel.setLineOfCodeLabel(detailObject.getLineOfCode());
		detailPanel.setCommentsLabel(detailObject.getCommentLines());
		detailPanel.setNumberOfFilesLabel(detailObject.getNumberOfFiles());
		Layout.add(detailBasePanel, detailPanel, 0, ++MainGUI.yPosition, 1, 1, 100, 0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL);
	}

	public void updateDetails(DetailObject detailObject) {
		for (DetailPanel detailPanel : detailPanels) {
			if (detailPanel.getLanguage().equalsIgnoreCase(detailObject.getLanguage())) {
				detailPanel.setBlankLinesLabel(detailObject.getBlankLines());
				detailPanel.setLineOfCodeLabel(detailObject.getLineOfCode());
				detailPanel.setCommentsLabel(detailObject.getCommentLines());
				detailPanel.setNumberOfFilesLabel(detailObject.getNumberOfFiles());
				detailPanel.setTotalLinesLabel(detailObject.getBlankLines() + detailObject.getCommentLines() + detailObject.getLineOfCode());
				long codelines = detailObject.getBlankLines() + detailObject.getCommentLines() + detailObject.getLineOfCode();
				long totallines = DetailObject.getTOTAL_BLANK_LINES() + DetailObject.getTOTAL_COMMENT_LINES() + DetailObject.getTOTAL_LINES_OF_CODE();
				float percentage = (float) codelines / (float) totallines * 100.0f;
				detailPanel.setCodeShareLabel(percentage);
				break;
			}
		}
		detailPanels.get(0).setBlankLinesLabel(DetailObject.getTOTAL_BLANK_LINES());
		detailPanels.get(0).setLineOfCodeLabel(DetailObject.getTOTAL_LINES_OF_CODE());
		detailPanels.get(0).setCommentsLabel(DetailObject.getTOTAL_COMMENT_LINES());
		detailPanels.get(0).setNumberOfFilesLabel(DetailObject.getTOTAL_NUMBER_OF_FILES());
		detailPanels.get(0).setTotalLinesLabel(DetailObject.getTOTAL_BLANK_LINES() + DetailObject.getTOTAL_COMMENT_LINES() + DetailObject.getTOTAL_LINES_OF_CODE());
	}

	public void lastUpdateDetail() {
		loggerMainClass.entry();
		long BLANK_LINES = DetailObject.getTOTAL_BLANK_LINES();
		long CODE_LINES = DetailObject.getTOTAL_LINES_OF_CODE();
		long COMMENT_LINES = DetailObject.getTOTAL_COMMENT_LINES();
		long NUMBER_OF_FILES = DetailObject.getTOTAL_NUMBER_OF_FILES();
		long TOTAL_LINES = BLANK_LINES + CODE_LINES + COMMENT_LINES;
		detailPanels.get(0).setBlankLinesLabel(BLANK_LINES);
		detailPanels.get(0).setLineOfCodeLabel(CODE_LINES);
		detailPanels.get(0).setCommentsLabel(COMMENT_LINES);
		detailPanels.get(0).setNumberOfFilesLabel(NUMBER_OF_FILES);
		detailPanels.get(0).setTotalLinesLabel(TOTAL_LINES);

		long blankLines;
		long codeLines;
		long commentLines;
		long numberOfFiles;
		long totalLines;
		float codeShare;

		for (int i = 1; i < detailPanels.size(); ++i) {
			blankLines = SourceScanner.detailObjects.get(i - 1).getBlankLines();
			codeLines = SourceScanner.detailObjects.get(i - 1).getLineOfCode();
			commentLines = SourceScanner.detailObjects.get(i - 1).getCommentLines();
			numberOfFiles = SourceScanner.detailObjects.get(i - 1).getNumberOfFiles();
			totalLines = blankLines + codeLines + commentLines;
			codeShare = (float) totalLines / (float) TOTAL_LINES * 100.0f;
			detailPanels.get(i).setBlankLinesLabel(blankLines);
			detailPanels.get(i).setLineOfCodeLabel(codeLines);
			detailPanels.get(i).setCommentsLabel(commentLines);
			detailPanels.get(i).setNumberOfFilesLabel(numberOfFiles);
			detailPanels.get(i).setTotalLinesLabel(totalLines);
			detailPanels.get(i).setCodeShareLabel(codeShare);
		}
		loggerMainClass.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	public void reset() {
		loggerMainClass.entry();
		if (detailPanels.size() > 1) {
			for (int i = 1; i < detailPanels.size(); ++i) {
				detailBasePanel.remove(detailPanels.get(i));
			}
		}
		DetailPanel detailPanel = detailPanels.get(0);
		detailPanels.removeAll(detailPanels);
		detailPanels.add(detailPanel);
		loggerMainClass.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	public static MainGUI getMAINGUI() {
		return MainGUI.MAIN_GUI;
	}

	public PleaseWait getPleaseWait() {
		return pleaseWait;
	}
}
