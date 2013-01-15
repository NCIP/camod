package gov.nih.nci.evs.app.servlet;


import javax.servlet.http.*;
import javax.servlet.*;
import gov.nih.nci.evs.app.util.MessageLog;
import gov.nih.nci.evs.app.util.TreeInitThread;
import gov.nih.nci.evs.app.util.TreeShutdownThread;
import gov.nih.nci.evs.app.util.TreeCacheManager;


public class InitTreeServlet extends HttpServlet
{
	private static final long serialVersionUID = 3258745453799404851L;
	//private PrintStream infops = null;

  public void init(ServletConfig config) throws ServletException
  {
    System.out.println("Entered InitTreeServlet init method: ");
    super.init(config);    // required
 
	  	try
	    {
	        TreeShutdownThread treeShutdown = new TreeShutdownThread();      
	        Runtime.getRuntime().addShutdownHook(treeShutdown);
	
	        // kick off thread to load evs trees
	        TreeInitThread thread = new TreeInitThread("EVSTree");
	        thread.start();  
	  	
	    }  catch(Exception e)  {
			  MessageLog.printInfo(" Error in caching evs trees...");
		    	e.printStackTrace();               
		    	throw new ServletException("Error occured while caching trees - "+e.getMessage());
	    }

	}

  public void destroy() {     
    try {
    	TreeCacheManager.getInstance().shutdown();    
    } catch (Exception e) {
    	System.err.println("Exception in Tree Cache Shutdown: "+e.getMessage());
    }    
  }
  
}

