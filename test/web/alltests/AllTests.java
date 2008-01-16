package web.alltests;

import web.*;
import web.allwebtests.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * @author pandyas
 */
public class AllTests extends TestCase {

	public AllTests(String arg0) {
		super(arg0);
	}
	
    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        // Submission tests
        //suite.addTest(AllSearchTests.suite());
        //suite.addTest(AllSubmissionTests.suite());
        
        //suite.addTest(SearchPopulateCellLinesTest.suite());
        //suite.addTest(SearchPopulateCITest.suite());
        //suite.addTest(SearchPopulateGeneticDescriptionTest.suite());
        //suite.addTest(SearchPopulateHistopathologyTest.suite());
        //suite.addTest(SearchPopulateModelCharacteristicsTest.suite());        
        //suite.addTest(SearchPopulatePublicationTest.suite());
        //suite.addTest(SearchPopulateTherapyTest.suite());        
        //suite.addTest(SearchPopulateTransIntTest.suite());
        //suite.addTest(SearchPopulateTransplantTest.suite());        


        return suite;
    }

    public static void main(String args[])
    {
        TestRunner.run(suite());
    }	

}
