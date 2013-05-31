/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod;

import com.thoughtworks.selenium.SeleneseTestCase;

public class AbstractSelenTestCaseImpl extends SeleneseTestCase {

	public void setUp() throws Exception {
		String seleniumBrowser = System.getProperty("selenium.browser");
		System.out.println("seleniumBrowser:"+seleniumBrowser);
		System.out.println("selnurl:"+System.getProperty("selenium.url"));
		if(seleniumBrowser==null) {
			seleniumBrowser = "*firefox";
		}
		setUp(System.getProperty("selenium.url"),
		      seleniumBrowser);

	}
}
