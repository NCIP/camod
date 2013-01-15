/*
 * Created on Nov 5, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package gov.nih.nci.evs.app.servlet;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import gov.nih.nci.evs.app.util.MessageLog;

/**
 * @author MashettS
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InitializeLog extends HttpServlet {

	private static final long serialVersionUID = 3258745453799404851L;
	private PrintStream infops = null;

	public void init(ServletConfig config) throws ServletException {
		super.init(config); // required

		// Set MessageLog
		ServletContext context = config.getServletContext();
		String infoLog = context
				.getInitParameter("gov.nih.nci.evs.app.logging.info");

		try {
			if (infoLog != null) {
				if (infoLog.equals("off"))
					MessageLog.setInfoOn(false);
				else {
					infops = new PrintStream(
							new FileOutputStream(infoLog, true), true);
					MessageLog.setInfoStream(infops);
				}
			}
		} catch (FileNotFoundException exc) {
			System.err.println("Unable to open info log " + infoLog);
			MessageLog.printInfo("Unable to open info log " + infoLog);
		} catch (Exception e) {
			System.err
					.println("Exception occured in initializing message log: "
							+ e.getMessage());
			MessageLog
					.printInfo("Exception occured in initializing message log: "
							+ e.getMessage());
		}
			MessageLog.printInfo("Information Logging");
	}
}
