/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.biodbnet;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import edu.emory.mathcs.backport.java.util.Collections;

import gov.nih.nci.cabio.domain.Gene;
import gov.nih.nci.cabio.domain.GeneOntology;
import gov.nih.nci.cabio.domain.Pathway;
import gov.nih.nci.cabio.domain.Taxon;

public class BioDBnetService {
	static BioDBnet service = null;
	static BioDBnetPortType client = null;
	static BioDBnetService bioDBnetService = null;
	
	public static final String GENE_ID = "Gene ID";
	public static final String GENE_INFO = "Gene Info, Taxon ID";
	public static final String BIOCARTA_PATHWAY_NAME = "Biocarta Pathway Name";
	public static final String GENE_ONTOLOGY = "GO - Biological Process, GO - Cellular Component, GO - Molecular Function";
	public static final String GENETIC_INFO_ALL = "Gene Info, Taxon ID, Biocarta Pathway Name, GO - Biological Process, GO - Cellular Component, GO - Molecular Function, UniGene ID";
	
	static {
		service = new BioDBnet();
		client = service.getBioDBnetPort();
		bioDBnetService = new BioDBnetService();
	}
	
	private BioDBnetService() {}

	public static BioDBnetService getService() {
		return bioDBnetService;
	}
	
	public static Db2DbParams setupInputs(String geneId) {
		Db2DbParams db2DbParams = new Db2DbParams();
		db2DbParams.setInput(GENE_ID);
		db2DbParams.setInputValues(geneId);
		
		return db2DbParams;
	}
	
	public static Db2DbParams setupOutput(Db2DbParams db2DbParams, String output) {
		db2DbParams.setOutputs(output);
		
		return db2DbParams;
	}
	
	public static String search(Db2DbParams db2DbParams) {
		return client.db2Db(db2DbParams);
	}
	
	private static Gene setupBiocartaPathways(Gene gene, String bioCartaPathways) {
		 String[] arr = bioCartaPathways.split(";");
		 Collection<Pathway> pathwayCollection = new ArrayList<Pathway>();
		 for ( int i=0;i<arr.length;i++ ) {
			 if( arr[i].indexOf("[") != -1) {
				 Pathway pathway = new Pathway();
				 String pathwayName = arr[i].substring(0, arr[i].indexOf("[")-1).trim();
				 pathway.setName(pathwayName);
				 pathway.setDisplayValue(arr[i].substring(arr[i].indexOf(":") + 1, arr[i].indexOf("]")).trim());
				 
				 pathwayCollection.add(pathway);
			 }
		 }
		 
		 gene.setPathwayCollection(pathwayCollection);
		 return gene;
	}
	
	private static Gene setupGeneInfo(Gene gene, String geneInfo) {
		
		geneInfo = geneInfo.substring( geneInfo.indexOf("[") );
		 
		 String[] arr = geneInfo.split("]");
		 for ( int i=0;i<arr.length;i++ ) {
			 if( arr[i].indexOf("Gene Symbol:") != -1 ) {
				 gene.setSymbol(arr[i].substring( arr[i].indexOf(":") + 2 ) );
			 }
			 else if( arr[i].indexOf("Description:") != -1 ) {
				 gene.setFullName(arr[i].substring( arr[i].indexOf(":") + 2 ) );
			 }
		 }
		 
		 return gene;
	}
	
	private static Gene setupTaxonId(Gene gene, String taxonId) {
		
		 taxonId = taxonId.substring( taxonId.indexOf("[") );
		 
		 String[] taxonArr = taxonId.split("]");
		 for ( int i=0;i<taxonArr.length;i++ ) {
			 if( taxonArr[i].indexOf("Scientific Name:") != -1 ) {
				 Taxon taxon = new Taxon();
				 taxon.setScientificName(taxonArr[i].substring( taxonArr[i].indexOf(":") + 2 ) );
				 gene.setTaxon(taxon);
			 }
		 }
		 
		 return gene;
	}
	
	private static Gene setupGeneOntology(Gene gene, String geneOntologies) {
		 String[] arr = geneOntologies.split(";");
		 Collection<GeneOntology> geneOntologyCollection = new ArrayList<GeneOntology>();
		 for ( int i=0;i<arr.length;i++ ) {
			 if( arr[i].indexOf("[") != -1) {
				 GeneOntology geneOntology = new GeneOntology();
				 geneOntology.setId(Long.valueOf(arr[i].substring(arr[i].indexOf(":")+1, arr[i].indexOf("[")).trim()));
				 geneOntology.setName(arr[i].substring(arr[i].lastIndexOf(":") + 1, arr[i].indexOf("]")).trim());
				 
				 geneOntologyCollection.add(geneOntology);
			 }
		 }
		 
		 Collections.sort((ArrayList)geneOntologyCollection, new Comparator() {
			 public int compare(Object go1, Object go2) {
			      //ascending order
			      return ((GeneOntology)go1).getId().compareTo(((GeneOntology)go2).getId());
			    }
		 });
		 
		 gene.setGeneOntologyCollection(geneOntologyCollection);
		 return gene;
	}
	
	private static Gene setupClusTerId(Gene gene, String unigeneId) {
		 String[] unigeneArr = unigeneId.split("\\.");
		 if( unigeneArr.length >= 2 ) {
			 gene.getTaxon().setAbbreviation(unigeneArr[0]);
			 if( unigeneArr[1].indexOf(";") > -1 )
				 gene.setClusterId(Long.valueOf(unigeneArr[1].substring(0, unigeneArr[1].indexOf(";") )));
			 else
				 gene.setClusterId(Long.valueOf(unigeneArr[1].substring(0, unigeneArr[1].indexOf("\n") )));
		 }
		 		 
		 return gene;
	}
	
	public static Gene search(Gene gene, Db2DbParams db2DbParams) {
		String db2dbResult = search(db2DbParams);
		// Remove Headers
		db2dbResult = db2dbResult.substring(db2dbResult.indexOf("\n")+1);
		
		// Split Columns
		String[] geneInfoArray = db2dbResult.split("\t");
		
		gene = setupGeneInfo(gene, geneInfoArray[1]);
		gene = setupTaxonId(gene, geneInfoArray[2]);
		gene = setupBiocartaPathways(gene, geneInfoArray[3]);
		
		String geneOntologies = geneInfoArray[4] + ";" + geneInfoArray[5] + ";" + geneInfoArray[6] + ";";
		gene = setupGeneOntology(gene, geneOntologies);
		
		gene = setupClusTerId(gene, geneInfoArray[7]);
		
		return gene;
	}

}
