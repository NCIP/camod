/**
 *	The caMOD Software License, Version 1.0
 *
 *	Copyright 2005 SAIC. This software was developed in conjunction with the National Cancer
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
 *	4. This license does not authorize the incorporation of this software into any third party proprietary
 *	programs.  This license does not authorize the recipient to use any trademarks owned by either
 *	NCI or SAIC.
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
 * $Id: HttpTests.java,v 1.5 2006-01-06 17:20:50 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2006/01/06 17:18:52  pandyas
 * Modified names for Search tests to include "Populate"
 *
 * Revision 1.3  2006/01/06 16:09:32  pandyas
 * Modified names for Search tests to include "Populate"
 *
 * Revision 1.2  2005/12/28 16:42:19  georgeda
 * Changes for testing
 *
 * Revision 1.1  2005/12/27 15:04:33  georgeda
 * Test cleanup
 *
 */
package web;

import junit.framework.Test;
import junit.framework.TestSuite;
import web.search.SearchPopulateCITest;
import web.search.SearchPopulateCellLinesTest;
import web.search.SearchGeneticDescriptionTest;
import web.search.SearchHistopathologyTest;
import web.search.SearchPopulateModelCharacteristicsTest;
import web.search.SearchPopulatePublicationTest;
import web.search.SearchPopulateTherapyTest;
import web.search.SearchPopulateXenograftTest;
import web.submission.EditModelCharacteristicsTest;
import web.submission.SubmitCITest;
import web.submission.SubmitEditDeleteCellLinesTest;
import web.submission.SubmitEditDeleteHistopathologyTest;
import web.submission.SubmitEditDeleteImageTest;
import web.submission.SubmitEditDeleteModelAvailabilityTest;
import web.submission.SubmitEditDeletePublicationTest;
import web.submission.SubmitEditDeleteTherapyTest;
import web.submission.SubmitGDTest;
import web.submission.SubmitPageTest;

/**
 * @author georgeda
 */
public class HttpTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        // Please keep them ordered
        
        // Search tests
        suite.addTest(SearchPopulateCellLinesTest.suite());
        suite.addTest(SearchPopulateCITest.suite());
        suite.addTest(SearchGeneticDescriptionTest.suite());
        suite.addTest(SearchHistopathologyTest.suite());
        suite.addTest(SearchPopulateModelCharacteristicsTest.suite());        
        suite.addTest(SearchPopulatePublicationTest.suite());
        suite.addTest(SearchPopulateTherapyTest.suite());
        suite.addTest(SearchPopulateXenograftTest.suite());
        
        // Submission tests
        suite.addTest(EditModelCharacteristicsTest.suite());
        suite.addTest(SubmitCITest.suite());
        suite.addTest(SubmitEditDeleteCellLinesTest.suite());
        suite.addTest(SubmitEditDeleteHistopathologyTest.suite());
        suite.addTest(SubmitEditDeleteImageTest.suite());
        suite.addTest(SubmitEditDeleteModelAvailabilityTest.suite());
        suite.addTest(SubmitEditDeletePublicationTest.suite());
        suite.addTest(SubmitEditDeleteTherapyTest.suite());
        suite.addTest(SubmitGDTest.suite());
        suite.addTest(SubmitPageTest.suite());
        
        return suite;
    }
}
