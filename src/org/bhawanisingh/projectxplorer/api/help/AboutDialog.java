package org.bhawanisingh.projectxplorer.api.help;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.projectxplorer.api.logging.ExceptionLogger;
import org.bhawanisingh.projectxplorer.api.logging.LoggerValues;
import org.bhawanisingh.projectxplorer.api.util.Borders;
import org.bhawanisingh.projectxplorer.gui.Layout;

public class AboutDialog extends JDialog {

	private Logger loggerAboutDialog = LogManager.getLogger(AboutDialog.class);

	private int yPosition = -1;

	private static final long serialVersionUID = -1975351542158843913L;

	private JPanel mainPanel;
	private JPanel displayPanel;
	private JPanel emptyPanel;

	private JScrollPane licenseScrollPane;
	private JScrollPane developerScrollPane;
	private JEditorPane developerPane;
	private JTextArea licenseTextArea;
	private JLabel programLabel;
	private JLabel versionLabel;
	private JButton licenseButton;
	private JButton developerButton;

	public AboutDialog(JFrame frame) {

		loggerAboutDialog.entry();
		setTitle("About " + AboutProject.PROGRAM_NAME);
		initialize();
		themeing();
		addComponents();
		addEvents();
		textToArea();
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
		loggerAboutDialog.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void initialize() {

		loggerAboutDialog.entry();
		mainPanel = new JPanel(new GridBagLayout());

		programLabel = new JLabel(AboutProject.PROGRAM_NAME);
		versionLabel = new JLabel("Version : " + AboutProject.PROGRAM_VERSION);

		displayPanel = new JPanel(new CardLayout());
		displayPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0, 255), new Color(100, 100, 100, 0)));
		displayPanel.setBackground(new Color(0, 0, 0, 0));

		licenseTextArea = new JTextArea(15, 60);
		licenseScrollPane = new JScrollPane(licenseTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		developerPane = new JEditorPane();
		developerScrollPane = new JScrollPane(developerPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		licenseButton = new JButton("License");
		developerButton = new JButton("Developer");
		emptyPanel = new JPanel();
		new JButton("Close");
		loggerAboutDialog.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void themeing() {

		loggerAboutDialog.entry();
		licenseTextArea.setEditable(false);
		developerPane.setEditable(false);
		mainPanel.setBorder(Borders.PADDING_BORDER);
		displayPanel.setBorder(Borders.COMPOUND_ETCHED_LOWERED);
		loggerAboutDialog.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addComponents() {

		loggerAboutDialog.entry();
		displayPanel.add(developerScrollPane);
		displayPanel.add(licenseScrollPane);

		Layout.add(mainPanel, programLabel, 0, ++yPosition, 1, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);

		Layout.add(mainPanel, versionLabel, 0, ++yPosition, 1, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);

		Layout.add(mainPanel, displayPanel, 0, ++yPosition, 3, 1, 100, 100, GridBagConstraints.EAST, GridBagConstraints.BOTH);

		Layout.add(mainPanel, emptyPanel, 0, ++yPosition, 1, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.BOTH);
		Layout.add(mainPanel, developerButton, 1, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(mainPanel, licenseButton, 2, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);

		this.add(mainPanel);
		loggerAboutDialog.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addEvents() {

		loggerAboutDialog.entry();
		developerPane.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					if (Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().browse(e.getURL().toURI());
						} catch (IOException e1) {
							loggerAboutDialog.error("Error In browsing Links", e1);
							ExceptionLogger.ioExceptionLogger(loggerAboutDialog, e1);
						} catch (URISyntaxException e1) {
							loggerAboutDialog.error("Error In browsing Links", e1);
							ExceptionLogger.uriSyntaxExceptionLogger(loggerAboutDialog, e1);
						}
					}
				}
			}
		});

		developerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) displayPanel.getLayout();
				cl.first(displayPanel);

			}
		});

		licenseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) displayPanel.getLayout();
				cl.last(displayPanel);

			}
		});
		loggerAboutDialog.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void textToArea() {

		loggerAboutDialog.entry();
		StringBuilder builder = new StringBuilder("");
		developerPane.setContentType("text/html");
		licenseTextArea.setText("");
		licenseTextArea.setText(aboutSoft());
		builder.append("<HTML>");
		if (!(AboutProject.DEVELOPERS.length == 1 && AboutProject.DEVELOPERS[0].trim().length() == 0)) {
			builder.append("\n\t<b><u>Developers</u> :</b>\n\t<br />\n\t<ul>");
			for (int i = 0; i < AboutProject.DEVELOPERS.length; ++i) {
				builder.append("\n\t\t<li><b >" + AboutProject.DEVELOPERS[i] + "</b>  &lt;<a href=\"http://mailto:" + AboutProject.DEVELOPERS_EMAIL[i] + "\">" + AboutProject.DEVELOPERS_EMAIL[i] + "</a>&gt;  &lt;<a href=http://\"" + AboutProject.DEVELOPERS_WEBSITE[i] + "\">" + AboutProject.DEVELOPERS_WEBSITE[i] + "</a>&gt;</li>");
			}
			builder.append("\n\t</font></ul>");
		}
		if (!(AboutProject.TESTERS.length == 1 && AboutProject.TESTERS[0].trim().length() == 0)) {
			builder.append("\n\t<b><u>Testers</u> :</b>\n\t<br />\n\t<ul>");
			for (int i = 0; i < AboutProject.TESTERS.length; ++i) {
				builder.append("\n\t\t<li color=\"red\">" + AboutProject.TESTERS[i] + ", &lt;<a href=\"mailto:" + AboutProject.TESTERS_EMAIL[i] + "\">" + AboutProject.TESTERS_EMAIL[i] + "</a>&gt;  &lt;<a href=http://\"" + AboutProject.TESTERS_WEBSITE[i] + "\">" + AboutProject.TESTERS_WEBSITE[i] + "</a>&gt;</li>");
			}
			builder.append("\n\t</ul>");
		}
		if (!(AboutProject.CONTRIBUTERS.length == 1 && AboutProject.CONTRIBUTERS[0].trim().length() == 0)) {
			builder.append("\n\t<b><u>Contributers</u> :</b>\n\t<br />\n\t<ul>");
			for (int i = 0; i < AboutProject.CONTRIBUTERS.length; ++i) {
				builder.append("\n\t\t<li>" + AboutProject.CONTRIBUTERS[i] + ", &lt;<a href=\"mailto:" + AboutProject.CONTRIBUTERS_EMAIL[i] + "\">" + AboutProject.CONTRIBUTERS_EMAIL[i] + "</a>&gt;  &lt;<a href=http://\"" + AboutProject.CONTRIBUTERS_WEBSITE[i] + "\">" + AboutProject.CONTRIBUTERS_WEBSITE[i] + "</a>&gt;</li>");
			}
			builder.append("\n\t</ul>");
		}
		builder.append("\n</html>");
		developerPane.setText(builder.toString());
		loggerAboutDialog.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private String aboutSoft() {
		loggerAboutDialog.entry();
		StringBuffer license = new StringBuffer(100);
		try {
			InputStream inputStream = AboutProject.class.getResourceAsStream("/license_template.lic");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains("<AUTHOR>")) {
					line = line.replace("<AUTHOR>", AboutProject.AUTHOR_NAME);
				}
				if (line.contains("<PROGRAM>")) {
					line = line.replace("<PROGRAM>", AboutProject.PROGRAM_NAME);
				}
				if (line.contains("<YEAR>")) {
					line = line.replace("<YEAR>", AboutProject.COPYRIGHT_YEAR);
				}
				if (line.contains("<ORGANISATION>")) {
					line = line.replace("<ORGANISATION>", AboutProject.ORGANIZATION);
				}
				license.append(line + "\n");
			}
			reader.close();
			inputStream.close();
			loggerAboutDialog.exit(LoggerValues.SUCCESSFUL_EXIT);
		} catch (FileNotFoundException fileNotFoundException) {
			loggerAboutDialog.error("Error In Finding Temporary License");
			ExceptionLogger.fileNotFoundExceptionLogger(loggerAboutDialog, fileNotFoundException);
			loggerAboutDialog.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		} catch (IOException ioException) {
			loggerAboutDialog.error("I/O Exception I Don't Know");
			ExceptionLogger.ioExceptionLogger(loggerAboutDialog, ioException);
			loggerAboutDialog.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		}
		return license.toString();
	}
}