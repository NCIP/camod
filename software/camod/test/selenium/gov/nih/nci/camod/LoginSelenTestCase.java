package selenium.gov.nih.nci.camod;


public class LoginSelenTestCase extends AbstractSelenTestCaseImpl {

	public void testSanity() throws Exception {
		selenium.open("http://cancermodels-qa.nci.nih.gov/camod/jsp/viewLicense.jsp;jsessionid=29A9A79B85FCF8FA9FE50DBCB716DF48");
		selenium.click("link=CLICKING HERE");
		selenium.type("MAINbody:sideBarView:loginForm:username", "camodcurator");
		selenium.type("MAINbody:sideBarView:loginForm:password", "TB!ontb9");
		selenium.click("MAINbody:sideBarView:loginForm:loginButton2");

		selenium.waitForCondition("selenium.browserbot.getCurrentWindow().document.getElementById('MAINbody:navigationForm:searchLink')",
				                  "30000");
		selenium.click("MAINbody:navigationForm:searchLink");
		//assert something?
	}
}
