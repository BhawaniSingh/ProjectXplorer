package org.bhawanisingh.projectxplorer.api.logging;

import java.io.IOException;
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
		ExceptionLogger.genericExceptionLogger(logger, nullPointerException);
	}

	public static void unknownHostExceptionLogger(Logger logger, UnknownHostException unknownHostException) {
		ExceptionLogger.genericExceptionLogger(logger, unknownHostException);
	}

	public static void ioExceptionLogger(Logger logger, IOException ioException) {
		ExceptionLogger.genericExceptionLogger(logger, ioException);
	}

	public static void instantiationExceptionLogger(Logger logger, InstantiationException instantiationException) {
		ExceptionLogger.genericExceptionLogger(logger, instantiationException);
	}

	public static void illegalAccessExceptionLogger(Logger logger, IllegalAccessException illegalAccessException) {
		ExceptionLogger.genericExceptionLogger(logger, illegalAccessException);
	}

	public static void unsupportedLookAndFeelExceptionLogger(Logger logger, UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
		ExceptionLogger.genericExceptionLogger(logger, unsupportedLookAndFeelException);
	}

	private static void genericExceptionLogger(Logger logger, Throwable throwable) {
		logger.catching(throwable);
		logger.error("\nType Of Exceptpion\t => \t" + throwable.getClass() + "\n\t" +
				"Cause\t => \t" + throwable.getCause() + "\n\t" +
				"Message\t => \t" + throwable.getMessage() + "\n\t" +
				"Localized Message\t => \t" + throwable.getLocalizedMessage());
	}

}