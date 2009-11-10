package unit.web.alltests;

import unit.web.*;
import unit.web.allwebtests.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * @author pandyas
 */
public class AllTestsSuite extends TestCase {

	public AllTestsSuite(String arg0) {
		super(arg0);
	}
	
    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        // Submission tests
        suite.addTest(AllSearchTests.suite());
        //suite.addTest(AllSubmissionTests.suite());

        return suite;
    }

    public static void main(String args[])
    {
        TestRunner.run(suite());
    }	

}
