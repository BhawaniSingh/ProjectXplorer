package org.bhawanisingh.projectxplorer.api.logging;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Bhawani Singh
 */

public class ExceptionLogger {

	public static void classNotFoundExceptionLogger(Logger logger, ClassNotFoundException classNotFoundException) {
		logger.catching(classNotFoundException);
		logger.error("\nType Of Exceptpion\t => \t" + classNotFoundException.getClass() + "\n\t" +
				"Cause\t => \t" + classNotFoundException.getCause() + "\n\t" +
				"Message\t => \t" + classNotFoundException.getMessage() + "\n\t" +
				"Localized Message\t => \t" + classNotFoundException.getLocalizedMessage() + "\n\t" +
				"Exception\t => \t" + classNotFoundException.getException());
	}

	public static void nullPointExceptionLogger(Logger logger, NullPointerException nullPointerException) {
		genericExceptionLogger(logger, nullPointerException);
	}

	public static void unknownHostExceptionLogger(Logger logger, UnknownHostException unknownHostException) {
		genericExceptionLogger(logger, unknownHostException);
	}

	public static void ioExceptionLogger(Logger logger, IOException ioException) {
		genericExceptionLogger(logger, ioException);
	}

	public static void instantiationExceptionLogger(Logger logger, InstantiationException instantiationException) {
		genericExceptionLogger(logger, instantiationException);
	}

	public static void illegalAccessExceptionLogger(Logger logger, IllegalAccessException illegalAccessException) {
		genericExceptionLogger(logger, illegalAccessException);
	}

	public static void unsupportedLookAndFeelExceptionLogger(Logger logger, UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
		genericExceptionLogger(logger, unsupportedLookAndFeelException);
	}

	public static void uriSyntaxExceptionLogger(Logger logger, URISyntaxException uriSyntaxException) {
		genericExceptionLogger(logger, uriSyntaxException);
	}

	public static void fileNotFoundExceptionLogger(Logger logger, FileNotFoundException fileNotFoundException) {
		genericExceptionLogger(logger, fileNotFoundException);
	}

	private static void genericExceptionLogger(Logger logger, Throwable throwable) {
		logger.catching(throwable);
		logger.error("\nType Of Exceptpion\t => \t" + throwable.getClass() + "\n\t" +
				"Cause\t => \t" + throwable.getCause() + "\n\t" +
				"Message\t => \t" + throwable.getMessage() + "\n\t" +
				"Localized Message\t => \t" + throwable.getLocalizedMessage());
	}
}