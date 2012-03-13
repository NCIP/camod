package gov.nih.nci.camod.webapp.dwr;

import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.bean.Author;
import gov.nih.nci.camod.bean.PublicationBean;
import gov.nih.nci.camod.bean.XmlPublication;
import gov.nih.nci.camod.util.StringUtils;
import gov.nih.nci.camod.webapp.dwr.PubMedXMLHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.validator.DynaValidatorForm;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

public class DWRPublicationManager {

	protected transient final Log logger = LogFactory.getLog(getClass());

	public PublicationBean clearPublication() {
		WebContext wctx = WebContextFactory.get();
		DynaValidatorForm form = (DynaValidatorForm) wctx.getSession()
				.getAttribute("publicationForm");
		PublicationBean pbean = new PublicationBean();
		if (form != null) {
			form.set("publication", pbean);
		}
		return pbean;
	}

	public PublicationBean searchPubMedById(String pubmedID) {
		// New a pubBean each time, so we know if parsing is success or not.
		PublicationBean newPubBean = new PublicationBean();
		if (!StringUtils.isEmpty(pubmedID) && !pubmedID.equals("0")) {
			try {
				newPubBean = getPublicationFromPubMedXML(pubmedID);
			} catch (Exception ex) {
				logger.warn("Invalid PubMed ID: " + pubmedID);
			}
		}
		return newPubBean;
	}
	
	public PublicationBean getPublicationFromPubMedXML(String pubMedId)
			{
		PublicationBean newPubBean = null;
		try {
			Long pubMedIDLong = Long.valueOf(pubMedId.trim());
			PubMedXMLHandler phandler = PubMedXMLHandler.getInstance();
			newPubBean = new PublicationBean();
			if (phandler.parsePubMedXML(pubMedIDLong, newPubBean)) {
				XmlPublication newPub = (XmlPublication) newPubBean.getDomainFile();
				newPub.setPubMedId(pubMedIDLong);
			}
		} catch (Exception e) {
			String err = "Invalid PubMed ID: " + pubMedId;
		}
		return newPubBean;
	}

	public PublicationBean retrievePubMedInfo(String pubmedID) {
		WebContext wctx = WebContextFactory.get();

		// New a pubBean each time, so we know if parsing is success or not.
		PublicationBean newPubBean = searchPubMedById(pubmedID);
		// Copy PubMed data into form bean
		PublicationBean publicationBean = new PublicationBean();
		publicationBean.copyPubMedFieldsFromPubMedXML(newPubBean);
		return publicationBean;
	}
}