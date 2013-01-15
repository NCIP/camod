package gov.nih.nci.evs.app.util;

import java.io.*;

/**
 *	The caBIO Software License, Version 1.0
 *
 *	Copyright 2001 SAIC. This software was developed in conjunction with the National Cancer
 *	Institute, and so to the extent government employees are co-authors, any rights in such works
 *	shall be subject to Title 17 of the United States Code, section 105.
 *
 *	Redistribution and use in source and binary forms, with or without modification, are permitted
 *	provided that the following conditions are met:
 *
 *	1. Redistributions of source code must retain the above copyright notice, this list of conditions
 *	and the disclaimer of Article 3, below.  Redistributions in binary form must reproduce the above
 *	copyright notice, this list of conditions and the following disclaimer in the documentation and/or
 *	other materials provided with the distribution.
 *
 *	2.  The end-user documentation included with the redistribution, if any, must include the
 *	following acknowledgment:
 *
 *	"This product includes software developed by the SAIC and the National Cancer
 *	Institute."
 *
 *	If no such end-user documentation is to be included, this acknowledgment shall appear in the
 *	software itself, wherever such third-party acknowledgments normally appear.
 *
 *	3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or
 *	promote products derived from this software.
 *
 *	4. This license does not authorize the incorporation of this software into any proprietary
 *	programs.  This license does not authorize the recipient to use any trademarks owned by either
 *	NCI or SAIC-Frederick.
 *
 *
 *	5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED
 *	WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 *	MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE
 *	DISCLAIMED.  IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR
 *	THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *	EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *	PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *	PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 *	OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *	NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *	SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
/**
 * This class performs common message logging. Log messages can take on one of
 * three varieties: informational, warning, or error. Each type of log message
 * is sent to its own destination; different types of messages can share the
 * same destination. In addition, logging can be turned on and off for the
 * individual logging types.
 * 
 * @author J.P. Zurilgen
 * @version $Revision: 1.4 $
 */
public class MessageLog {
	private static boolean errorOn = true, infoOn = true, warningOn = true;

	private static PrintStream errorStream = System.err,
			infoStream = System.out, warningStream = System.out;

	/*
	 * Prints an error message to the error stream. If error logging is turned
	 * off, no message is printed.
	 * 
	 * @param s message to print
	 * 
	 * @see #setErrorStream(java.io.PrintStream)
	 */
	public static synchronized void printError(String s) {
		if (errorOn) {
			errorStream.println(s);
			errorStream.flush();
		}
	}

	/*
	 * Prints an informational message to the informational stream. If
	 * informational logging is turned off, no message is printed.
	 * 
	 * @param s message to print
	 * 
	 * @see #setInfoStream(java.io.PrintStream)
	 */
	public static synchronized void printInfo(String s) {
		if (infoOn) {
			infoStream.println(s);
			infoStream.flush();
		}
	}

	/*
	 * Prints a warning message to the warning stream. If warning logging is
	 * turned off, no message is printed.
	 * 
	 * @param s message to print
	 * 
	 * @see #setWarningStream(java.io.PrintStream)
	 */
	public static synchronized void printWarning(String s) {
		if (warningOn) {
			warningStream.println(s);
			warningStream.flush();
		}
	}

	/**
	 * Turns error logging on or off.
	 * 
	 * @param on
	 *            true to turn on, false to turn off
	 */
	public static synchronized void setErrorOn(boolean on) {
		errorOn = on;
	}

	/**
	 * Turns informational logging on or off.
	 * 
	 * @param on
	 *            true to turn on, false to turn off
	 */
	public static synchronized void setInfoOn(boolean on) {
		infoOn = on;
	}

	/**
	 * Turns warning logging on or off.
	 * 
	 * @param on
	 *            true to turn on, false to turn off
	 */
	public static synchronized void setWarningOn(boolean on) {
		warningOn = on;
	}

	/**
	 * Sets the stream where error log messages go.
	 * 
	 * @param ps
	 *            previously opened stream for messages
	 */
	public static synchronized void setErrorStream(PrintStream ps) {
		errorStream = ps;
	}

	/**
	 * Sets the stream where informational log messages go.
	 * 
	 * @param ps
	 *            previously opened stream for messages
	 */
	public static synchronized void setInfoStream(PrintStream ps) {
		infoStream = ps;
	}

	/**
	 * Sets the stream where warning log messages go.
	 * 
	 * @param ps
	 *            previously opened stream for messages
	 */
	public static synchronized void setWarningStream(PrintStream ps) {
		warningStream = ps;
	}

	/**
	 * Performs a total configuration of message logging. If both file and
	 * display printing are requested, only display printing is performed. If
	 * one type of logging is specified as both on and off, it is set to off.
	 * 
	 * @param filePrint
	 *            type of file logging: info, warning, error, or all
	 * @param file
	 *            for file logging, name of file to log to
	 * @param displayPrint
	 *            type of display logging: info, warning, error, or all
	 * @param toggleOn
	 *            types of logs to turn on: info, warning, error, or all
	 * @param toggleOff
	 *            types of logs to turn off: info, warning, error, or all
	 */
	public static synchronized void setPrintArea(String filePrint, String file,
			String displayPrint, String toggleOn, String toggleOff) {
		if (filePrint != null) {
			try {
				PrintStream ps = new PrintStream(new FileOutputStream(file),
						true);
				if (filePrint == "all") {
					setErrorStream(ps);
					setInfoStream(ps);
					setWarningStream(ps);
				} else if (filePrint == "error")
					setErrorStream(ps);
				else if (filePrint == "info")
					setInfoStream(ps);
				else if (filePrint == "warning")
					setWarningStream(ps);
			}

			catch (FileNotFoundException f) {
				printError("File not found!");
			}
		}

		if (displayPrint != null) {
			if (displayPrint == "all") {
				setErrorStream(System.err);
				setInfoStream(System.out);
				setWarningStream(System.out);
			} else if (displayPrint == "error")
				setErrorStream(System.err);
			else if (displayPrint == "info")
				setInfoStream(System.out);
			else if (displayPrint == "warning")
				setWarningStream(System.out);
		}

		if (toggleOn != null) {
			if (toggleOn == "all") {
				setErrorOn(true);
				setInfoOn(true);
				setWarningOn(true);
			}

			else if (toggleOn == "error")
				setErrorOn(true);

			else if (toggleOn == "info")
				setInfoOn(true);

			else if (toggleOn == "warning")
				setWarningOn(true);
		}

		if (toggleOff != null) {
			if (toggleOff == "all") {
				setErrorOn(false);
				setInfoOn(false);
				setWarningOn(false);
			}

			else if (toggleOff == "error")
				setErrorOn(false);

			else if (toggleOff == "info")
				setInfoOn(false);

			else if (toggleOff == "warning")
				setWarningOn(false);
		}
	}
}
