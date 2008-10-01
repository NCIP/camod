/*
 * DomainSuite.java
 * JUnit based test
 *
 * Created on November 22, 2005, 1:19 PM
 */

package gov.nih.nci.camod.domain;

import junit.framework.*;

/**
 *
 * @author piparom
 */
public class DomainSuite extends TestCase {
  
  public DomainSuite(String testName) {
    super(testName);
  }

  protected void setUp() throws Exception {
  }

  protected void tearDown() throws Exception {
  }

  /**
   * suite method automatically generated by JUnit module
   */
  public static Test suite() {
    TestSuite suite = new TestSuite("DomainSuite");
    suite.addTest(gov.nih.nci.camod.domain.AbstractCancerModelTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.AgentTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.AgentTargetTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.AnimalAvailabilityTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.AnimalDistributorTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.AnimalModelTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.AnimalModelSearchResultTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.AvailabilityTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.BaseObjectTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.BiologicalProcessTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.CellLineTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.ChemicalClassTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.ClinicalMarkerTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.CommentsTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.CommentsSearchResultTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.ConditionalityTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.ContactInfoTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.DiseaseTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.EndpointCodeTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.EngineeredGeneTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.EnvironmentalFactorTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.ExpressionFeatureTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.ExpressionLevelDescTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.GeneDeliveryTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.GeneFunctionTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.GeneticAlterationTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.GenomicSegmentTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.GenotypeSummaryTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.HistopathologyTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.ImageTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.InducedMutationTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.InvivoResultTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.LogTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.MicroArrayDataTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.ModelSectionTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.ModificationTypeTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.MutationIdentifierTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.NomenclatureTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.OrganTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.OrganizationTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.PartyTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.PersonTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.PersonSearchResultTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.PhenotypeTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.PublicationTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.PublicationStatusTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.RegulatoryElementTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.RegulatoryElementTypeTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.RepositoryInfoTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.RoleTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.ScreeningResultTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.SegmentTypeTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.SexDistributionTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.SpeciesTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.SpontaneousMutationTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.StrainTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.TargetedModificationTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.TaxonTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.TherapyTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.TransgeneTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.TreatmentTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.TumorCodeTest.suite());
    //suite.addTest(gov.nih.nci.camod.domain.TransplantTest.suite());
    suite.addTest(gov.nih.nci.camod.domain.YeastModelTest.suite());
    return suite;
  }
  
}
