function fillPubMedInfo() {
	var pubMedId = dwr.util.getValue("pmid");
	var anum = /^\d+$/;
	if (!anum.test(pubMedId)) {
		alert("PubMed ID must be a number!");
	}

	if (pubMedId != null && pubMedId != 0) {
		PublicationManager.retrievePubMedInfo(
				pubMedId, populatePubMedInfo);
	}
}

function populatePubMedInfo(publication) {
	if (publication != null) {
		if (publication.domainFile.title == null) {
			alert("PubMed ID does not exist. Please verify!");
		}		
		dwr.util.setValue("jTitle",
				publication.domainFile.title, {
					escapeHtml : false
				});
		
		dwr.util.setValue("journal",
				publication.domainFile.journalName, {
					escapeHtml : false
				});
		
		dwr.util.setValue("year",
				publication.domainFile.year, {
					escapeHtml : false
				});
		
		dwr.util.setValue("volume",
				publication.domainFile.volume, {
					escapeHtml : false
				});

		dwr.util.setValue("startPage",
				publication.domainFile.startPage, {
					escapeHtml : false
				});
		
		dwr.util.setValue("endPage",
				publication.domainFile.endPage, {
					escapeHtml : false
				});
		
/*		var authors = publication.authors;
		if (authors.length > 0) {
			var firstAuthor = authors[0].firstName + " " + authors[0].lastName + " " + authors[0].initial;
			*/
			dwr.util.setValue("authors",
					publication.primaryAuthor, {
						escapeHtml : false
					});
		//}
	
	} else {
		sessionTimeout();
	}
}

function sessionTimeout() {
	alert("Session has timed out.  Please log in again.");
	location.href = "login.do";
}
