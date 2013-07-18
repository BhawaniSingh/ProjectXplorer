package org.bhawanisingh.projectxplorer;

import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.bhawanisingh.projectxplorer.api.logging.ExceptionLogger;
import org.bhawanisingh.projectxplorer.api.util.Directories;
import org.bhawanisingh.projectxplorer.gui.MainGUI;

public class LaunchProjectXplorer {
	public static void main(String[] args) {
		Directories.foldervalidator();
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					info.getClassName();
					break;
				}
			}
		} catch (ClassNotFoundException classNotFoundException) {
			ExceptionLogger.classNotFoundExceptionLogger(LogManager.getLogger(LaunchProjectXplorer.class.getName()), classNotFoundException);
		} catch (InstantiationException instantiationException) {
			ExceptionLogger.instantiationExceptionLogger(LogManager.getLogger(LaunchProjectXplorer.class.getName()), instantiationException);
		} catch (IllegalAccessException illegalAccessException) {
			ExceptionLogger.illegalAccessExceptionLogger(LogManager.getLogger(LaunchProjectXplorer.class.getName()), illegalAccessException);
		} catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
			ExceptionLogger.unsupportedLookAndFeelExceptionLogger(LogManager.getLogger(LaunchProjectXplorer.class.getName()), unsupportedLookAndFeelException);
		}
		new MainGUI();
	}
}
