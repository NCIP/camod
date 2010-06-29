package gov.nih.nci.camod.util;


import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.EVSApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;

/**
  * <!-- LICENSE_TEXT_START -->
* Copyright 2008,2009 NGIT. This software was developed in conjunction with the National Cancer Institute,
* and so to the extent government employees are co-authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105.
* Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
* 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the disclaimer of Article 3, below. Redistributions
* in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other
* materials provided with the distribution.
* 2. The end-user documentation included with the redistribution, if any, must include the following acknowledgment:
* "This product includes software developed by NGIT and the National Cancer Institute."
* If no such end-user documentation is to be included, this acknowledgment shall appear in the software itself,
* wherever such third-party acknowledgments normally appear.
* 3. The names "The National Cancer Institute", "NCI" and "NGIT" must not be used to endorse or promote products derived from this software.
* 4. This license does not authorize the incorporation of this software into any third party proprietary programs. This license does not authorize
* the recipient to use any trademarks owned by either NCI or NGIT
* 5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
* MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE,
* NGIT, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  * <!-- LICENSE_TEXT_END -->
  */

/**
  * @author EVS Team
  * @version 1.0
  *
  * Modification history
  *     Initial implementation kim.ong@ngc.com
  *
 */

public class RemoteServerUtil {
	static private String _serviceInfo = "EvsServiceInfo";
	private Properties systemProperties = null;

    public RemoteServerUtil() {

    }

	/**
	 * Establish a remote LexBIG connection.
	 *
	 */
	public static LexBIGService createLexBIGService()
    {
		LexBIGService appService = null;
		String serviceUrl = "http://lexevsapi51.nci.nih.gov/lexevsapi51";

		try {
			appService = (LexBIGService)ApplicationServiceProvider.getApplicationServiceFromUrl(serviceUrl, "EvsServiceInfo");
			
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound exception in getApplicationService." + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exception getApplicationService. " + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Caught general exception getApplicationService. " + e);
			e.printStackTrace();
		}
		System.out.println("Exiting getApplicationService");
		return appService;
	}

	/**
	 * Establish a remote LexBIG connection.
	 *
	 */
	public static LexBIGService createLexBIGService(String url)
    {
		LexBIGService lbSvc = null;
		try {
		    lbSvc = (LexBIGService) ApplicationServiceProvider.getApplicationServiceFromUrl(url, _serviceInfo);

			return lbSvc;
	    } catch (Exception e) {
			e.printStackTrace();
            return null;
        }
	}
	
    /**
     * Get the application service based on the properties file
     *
     * @return the preferred name, or an empty string if something goes wrong.
     */     
    public static ApplicationService getCabioApplicationService()
    {
		ApplicationService appService = null;

		try {
			System.out.println("CaBioApplicationService.getCabioApplicationService Enter : " );
		
			appService=ApplicationServiceProvider.getApplicationService("ServiceInfo");
			
			System.out.println("ApplicationService : " + appService.toString());

		}		
		catch (FileNotFoundException e) {
			System.out.println("Caught FileNotFoundException properties for caBIO: " +  e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Caught IOException finding file for properties for caBIO: " + e);
			e.printStackTrace();
		} 
		catch (Exception e) {
			System.out.println("Caught Exception e for caBIO: " + e);
			e.printStackTrace();
		}		
		return appService;
    }	

}

