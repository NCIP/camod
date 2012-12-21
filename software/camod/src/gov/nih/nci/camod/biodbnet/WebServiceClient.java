package gov.nih.nci.camod.biodbnet;

public class WebServiceClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 BioDBnet service = new BioDBnet();
		 BioDBnetPortType client = service.getBioDBnetPort();
		 
//		 String input = "Ensembl Gene ID";
//		 String inputValues = "ENSG00000121410, ENSG00000171428";
//		 String output = "Gene ID";
//		 String outputs = "Gene Symbol, Ensembl Protein ID";
//		 String taxonId = "9606";
//		 String dbPath = "Ensembl Gene ID->Gene ID->Homolog - Mouse Gene ID->Ensembl Gene ID";
//		 String outputTaxon = "10090";
		 
		 String input = "Gene ID";
		 String inputValues = "18128";	
		 String outputs = "Gene Info, Gene Symbol, Taxon ID, Biocarta Pathway Name, GO - Biological Process, GO - Cellular Component, GO - Molecular Function, UniGene ID";
//		 String outputs = "Gene Info, Taxon ID";
//		 String outputs = "Biocarta Pathway Name";
//		 String outputs = "GO - Biological Process, GO - Cellular Component, GO - Molecular Function";
		 
		 Db2DbParams db2DbParams = new Db2DbParams();
		 db2DbParams.setInput(input);
		 db2DbParams.setInputValues(inputValues);
//		 db2DbParams.setTaxonId(taxonId);
		 db2DbParams.setOutputs(outputs);
		 
		 DbReportParams dbReportParams = new DbReportParams();
		 dbReportParams.setInput("Gene ID");
		 dbReportParams.setInputValues("70422");
//		 dbReportParams.setTaxonId(taxonId);		 

//		 String inputResult = client.getInputs();
//		 String outputResult = client.getOutputsForInput(input);
//		 String directOutputResult = client.getDirectOutputsForInput(input);
		 String db2dbResult = client.db2Db(db2DbParams);
//		 String dbReportResult = client.dbReport(dbReportParams);
		 
		 System.out.println(db2dbResult);
		 db2dbResult = db2dbResult.substring(db2dbResult.indexOf("\n")+1);
		 System.out.println(db2dbResult);
		 
		 String[] test = db2dbResult.split("\t");
		 System.out.println(test.length);
		 System.out.println(test[0]);
		 System.out.println(test[1]);
		 System.out.println(test[2]);
		 System.out.println(test[3]);
		 System.out.println(test[4]);
		 System.out.println(test[5]);
		 System.out.println(test[6]);
		 System.out.println(test[7]);
		 System.out.println(test[8]);
//		 String dbFindResult = client.dbFind(output, inputValues, taxonId);
//		 String dbWalkResult = client.dbWalk(dbPath, inputValues, taxonId);
//		 String dbOrthoResult = client.dbOrtho(input, output, inputValues, taxonId, outputTaxon);
		 
//		 db2dbResult = db2dbResult.substring( db2dbResult.indexOf("[") );
		 db2dbResult = db2dbResult.substring( db2dbResult.indexOf(db2DbParams.getInputValues()) + db2DbParams.getInputValues().length() + 1 );
		 db2dbResult = db2dbResult.replaceAll("]\t", "];");
		 
		 int ig = db2dbResult.indexOf("]\t");
		 
		 System.out.println("*******************" + db2dbResult);
		 
		 String[] arr = db2dbResult.split(";");
		 System.out.println("*******************" + arr[1]);
		 
		 for ( int i=0;i<arr.length;i++ ) {
			 System.out.println(Long.valueOf(arr[i].substring(arr[i].indexOf(":")+1, arr[i].indexOf("[")).trim()));
			 System.out.println(arr[i].substring(arr[i].lastIndexOf(":") + 1, arr[i].indexOf("]")).trim());
			 
			 if( arr[i].indexOf("Gene Symbol:") != -1 ) {
				 System.out.println(arr[i].substring( arr[i].indexOf(":") + 2 ) );
			 }
			 else if( arr[i].indexOf("Description:") != -1 ) {
				 System.out.println(arr[i].substring( arr[i].indexOf(":") + 2 ) );
			 }
			 else if( arr[i].indexOf("Scientific Name:") != -1 ) {
				 System.out.println(arr[i].substring( arr[i].indexOf(":") + 2 ) );
			 }
		 }
	}

}
