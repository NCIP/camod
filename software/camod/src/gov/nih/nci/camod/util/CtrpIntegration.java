package gov.nih.nci.camod.util;

import gov.nih.nci.camod.domain.ClinicalTrialProtocol;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;
//import org.w3c.dom.Node;
//import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;



public class CtrpIntegration {
	protected transient final Log log = LogFactory.getLog(getClass());
/*	
	public Document retrieveXmlDoc(String url) {
		Document document = null;
		DocumentBuilderFactory builderFactory =
		        DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
		    builder = builderFactory.newDocumentBuilder();
		    File fXmlFile = new File("c:\\downloads\\ctrp.xml");

		    
		    document = builder.parse(new URL(url).openStream());
//		    document = builder.parse(fXmlFile);
		    
		    

		} catch (ParserConfigurationException e) {
		    e.printStackTrace();  
		} catch (SAXException e) {
		    e.printStackTrace();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		return document;
	}
*/	
	
	public Document retrieveXmlDoc(String url) {
		Document document = null;
		DocumentBuilderFactory builderFactory =
		        DocumentBuilderFactory.newInstance();
		SAXBuilder builder = null;
		try {
			builder = new SAXBuilder();
			document = (Document) builder.build(new URL(url).openStream());

//		    File xmlFile = new File("c:\\downloads\\ctrp.xml");
//		    document = (Document) builder.build(xmlFile);
		} catch (IOException e) {
			log.error("Issue with CTRP XML response");
			e.printStackTrace();
		} catch (JDOMException e) {
			log.error("Issue with CTRP XML response");
			e.printStackTrace();
		}
		
		return document;
	}
	public Collection<ClinicalTrialProtocol> parseXmlDoc(Document document) {
		System.out.println("Root element :" + document.getRootElement().getName());
		
		Element rootNode = document.getRootElement();
		List list = rootNode.getChildren("studyProtocol");
		
		Collection<ClinicalTrialProtocol> clinicalTrialProtocolCollection = new ArrayList();
 
		for (int i = 0; i < list.size(); i++) {
 
		   Element node = (Element) list.get(i);
		   ClinicalTrialProtocol clinicalTrialProtocol = new ClinicalTrialProtocol();
		   
		   clinicalTrialProtocol.setLeadOrganizationName(node.getChildText("leadOrganizationName"));
		   clinicalTrialProtocol.setPDQIdentifier(node.getChildText("nciIdentifier"));
		   clinicalTrialProtocol.setNCTIdentifier(node.getChildText("nctIdentifier"));
		   clinicalTrialProtocol.setTitle(node.getChildText("officialTitle"));
		   clinicalTrialProtocol.setPhase(node.getChildText("phaseName"));
		   clinicalTrialProtocol.setPIName(node.getChildText("piFullName"));
		   clinicalTrialProtocol.setCurrentStatus(node.getChildText("studyStatusCode"));
		   
		   clinicalTrialProtocolCollection.add(clinicalTrialProtocol);
 /*
		   System.out.println("***********************");
		   System.out.println("leadOrganizationName : " + node.getChildText("leadOrganizationName"));
		   System.out.println("nciIdentifier " + node.getChildText("nciIdentifier"));
		   System.out.println("nctIdentifier " + node.getChildText("nctIdentifier"));
		   System.out.println("officialTitle " + node.getChildText("officialTitle"));
		   System.out.println("phaseName" + node.getChildText("phaseName"));
		   System.out.println("piFullName " + node.getChildText("piFullName"));
		   System.out.println("studyStatusCode " + node.getChildText("studyStatusCode"));
*/		   
 		}
		
		return clinicalTrialProtocolCollection;

		
	}
	
	public Collection<ClinicalTrialProtocol> getClinicalProtocols(Long nscNumber) {
		String url = PropertyUtil.getProperty("ctrp.url") + nscNumber;
		
		Document document = retrieveXmlDoc(url);
		
		return parseXmlDoc(document);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CtrpIntegration ctrpIntegration = new CtrpIntegration();
		String url = "http://ncias-d416-v.nci.nih.gov:39080/registry/rest/studyProtocols?agentNsc=737664";
		
		//url = "http://ncias-d416-v.nci.nih.gov:39080/registry/rest/studyProtocols?agentNsc=704805";
		
//		url = "http://www.weather.unisys.com/forexml.cgi?20876";
		
//		url = "http://trials-dev.nci.nih.gov/registry/rest/studyProtocols?agentNsc=737664";

	
		Document document = ctrpIntegration.retrieveXmlDoc(url);
		
		Collection<ClinicalTrialProtocol> clinicalTrialProtocolCollection = ctrpIntegration.parseXmlDoc(document);
		
//		Collection<ClinicalTrialProtocol> clinicalTrialProtocolCollection = ctrpIntegration.getClinicalProtocols(new Long(737664));
		
		for( ClinicalTrialProtocol clinicalTrialProtocol: clinicalTrialProtocolCollection) {
			System.out.println("<<<<<<<<<<<<<<<<<<<");
			System.out.println(clinicalTrialProtocol.getLeadOrganizationName());
			System.out.println(clinicalTrialProtocol.getPDQIdentifier());
			System.out.println(clinicalTrialProtocol.getNCTIdentifier());
			System.out.println(clinicalTrialProtocol.getTitle());
			System.out.println(clinicalTrialProtocol.getPhase());
			System.out.println(clinicalTrialProtocol.getPIName());
			System.out.println(clinicalTrialProtocol.getCurrentStatus());
		}

	}

}
