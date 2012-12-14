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
	public static final String GENETIC_INFO = "Gene Info, Gene Symbol, Taxon ID, Biocarta Pathway Name, GO - Biological Process, GO - Cellular Component, GO - Molecular Function";
	
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
	
	public static Gene searchForBiocartaPathways(Gene gene, Db2DbParams db2DbParams) {
		String db2dbResult = search(db2DbParams);
		
		db2dbResult = db2dbResult.substring( db2dbResult.indexOf(db2DbParams.getInputValues()) + db2DbParams.getInputValues().length() + 1 );
		 
		 String[] arr = db2dbResult.split(";");
		 Collection<Pathway> pathwayCollection = new ArrayList<Pathway>();
		 for ( int i=0;i<arr.length;i++ ) {
			 if( arr[i].indexOf("[") != -1) {
				 Pathway pathway = new Pathway();
				 pathway.setName(arr[i].substring(0, arr[i].indexOf("[")));
				 pathway.setDisplayValue(arr[i].substring(arr[i].indexOf(":") + 1, arr[i].indexOf("]")).trim());
				 
				 pathwayCollection.add(pathway);
			 }
		 }
		 
		 gene.setPathwayCollection(pathwayCollection);
		 return gene;
	}
	
	public static Gene searchForGeneInfo(Gene gene, Db2DbParams db2DbParams) {
		String db2dbResult = search(db2DbParams);
		
		db2dbResult = db2dbResult.substring( db2dbResult.indexOf("[") );
		 
		 String[] arr = db2dbResult.split("]");
		 for ( int i=0;i<arr.length;i++ ) {
			 if( arr[i].indexOf("Gene Symbol:") != -1 ) {
				 gene.setSymbol(arr[i].substring( arr[i].indexOf(":") + 2 ) );
			 }
			 else if( arr[i].indexOf("Description:") != -1 ) {
				 gene.setFullName(arr[i].substring( arr[i].indexOf(":") + 2 ) );
			 }
			 else if( arr[i].indexOf("Scientific Name:") != -1 ) {
				 Taxon taxon = new Taxon();
				 taxon.setAbbreviation(arr[i].substring( arr[i].indexOf(":") + 2 ) );
				 gene.setTaxon(taxon);
			 }
		 }
		 
		 return gene;
	}
	
	public static Gene searchForGeneOntology(Gene gene, Db2DbParams db2DbParams) {
		 String db2dbResult = search(db2DbParams);
		
		 db2dbResult = db2dbResult.substring( db2dbResult.indexOf(db2DbParams.getInputValues()) + db2DbParams.getInputValues().length() + 1 );
		 // multiple GO Search in the Params. need to remove TABs and insert ;
		 db2dbResult = db2dbResult.replaceAll("]\t", "];");
		 
		 String[] arr = db2dbResult.split(";");
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

}
