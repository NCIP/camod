/**
 * 
 * $Id: Constants.java,v 1.40 2005-10-11 18:12:08 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.39  2005/10/10 14:05:38  georgeda
 * Cleanup and additions for comment curation
 *
 * Revision 1.37  2005/10/05 20:27:59  guruswas
 * implementation of drug screening search page
 *
 * Revision 1.36  2005/10/05 19:24:14  pandyas
 * added clinical marker list
 *
 * Revision 1.35  2005/10/05 16:21:50  pandyas
 * added histopthology and therapy lists
 *
 * Revision 1.34  2005/10/04 20:18:48  georgeda
 * Updates from search changes
 *
 * Revision 1.33  2005/10/04 20:09:41  schroedn
 * Added Spontaneous Mutation, InducedMutation, Histopathology, TargetedModification and GenomicSegment
 *
 * Revision 1.32  2005/10/03 16:07:39  pandyas
 * modified histopathology constant name to reflect contents
 *
 * Revision 1.31  2005/10/03 15:31:00  pandyas
 * added clinical marker and histopathology constants
 *
 * Revision 1.30  2005/10/03 13:04:19  georgeda
 * Updates from search changes
 *
 * Revision 1.29  2005/09/30 18:47:46  pandyas
 * added all differences to my copy before uploading
 *
 * Revision 1.25  2005/09/27 16:34:31  georgeda
 * Changed administravive route drop down
 *
 *
 */
package gov.nih.nci.camod;

/**
 * Constant values used throughout the application.
 * 
 * <p>
 * <a href="Constants.java.html"><i>View Source</i></a>
 * </p>
 */
public class Constants {

	// ~ Static fields/initializers
	// =============================================

	/** The name of the camod resource bundle used in this application */
	public static final String CAMOD_BUNDLE = "camod";

	/** The name of the ResourceBundle used in this application */
	public static final String BUNDLE_KEY = "ApplicationResources";

	/** The application scoped attribute for persistence engine used */
	public static final String DAO_TYPE = "daoType";

	public static final String DAO_TYPE_HIBERNATE = "hibernate";

	/** Application scoped attributes for SSL Switching */
	public static final String HTTP_PORT = "httpPort";

	public static final String HTTPS_PORT = "httpsPort";

	/**
	 * The key for the coordinator username in the camod.properties file
	 */
	public static final String COORDINATOR_USERNAME_KEY = "coordinator.username";

	/**
	 * The name of the Administrator role, as specified in web.xml
	 */
	public static final String ADMIN_ROLE = "admin";

	/**
	 * The name of the configuration hashmap stored in application scope.
	 */
	public static final String CONFIG = "appConfig";


	public static final String UPT_CONTEXT_NAME = "camod";

	/**
	 * Used to store list of models currently logged on user has previous
	 * entered
	 */
	public static final String USERMODELLIST = "usermodellist";

    /**
     * Used to store lists for drop down menus
     */
    public interface CaArray {
        public static final String URI_START = "caarray.uri_start";
        public static final String URI_END = "caarray.uri_end";
    }
    
	/**
	 * Used to store lists for drop down menus
	 */
	public interface Dropdowns {

		public static final String ADD_BLANK_OPTION = "ADD_BLANK_OPTION";

		public static final String OTHER_OPTION = "Other";

		public static final String SPECIESDROP = "speciesdrop.db";

		public static final String STRAINDROP = "straindrop.db";

		public static final String SEXDISTRIBUTIONDROP = "SexDistributions.txt";

		public static final String DOSAGEUNITSDROP = "DoseUnits.txt";

		public static final String ADMINISTRATIVEROUTEDROP = "AdministrativeRoutes.txt";

		public static final String AGEUNITSDROP = "AgeUnits.txt";

		public static final String PUBDROP = "PublicationStatus.txt";

		public static final String TOXICITYGRADESDROP = "ToxicityGrades.txt";

		public static final String CLINICALMARKERSDROP = "ClinicalMarkers.txt";

		// Various Dose Units
		public static final String CHEMTHERAPYDOSEUNITSDROP = "ChemTherapyDoseUnits.txt";

		public static final String ENVFACTORUNITSDROP = "EnvFactorUnits.txt";

		public static final String GENOMESEGSIZEUNITSDROP = "GenomeSegSizeUnits.txt";

		public static final String HISTOPATHVOLUMEUNITSDROP = "HistopathVolumeUnits.txt";

		public static final String HISTOPATHWEIGHTUNITSDROP = "HistopathWeightUnits.txt";

		public static final String HORMONEUNITSDROP = "HormoneUnits.txt";

		public static final String NUTFACTORUNITSDROP = "NutFactorUnits.txt";

		public static final String RADIATIONUNITSDROP = "RadiationUnits.txt";

		public static final String VIRALTREATUNITSDROP = "ViralTreatUnits.txt";

		public static final String TARGETEDMODIFICATIONDROP = "TargetedModificationTypes.txt";

		public static final String GENOMICSEGMENTDROP = "SegmentTypes.txt";

		// Specific to a single screen
		public static final String CHEMICALDRUGDROP = "chemdrugdrop.db";

		public static final String ENVIRONFACTORDROP = "envfactordrop.db";

		public static final String GROWTHFACTORDROP = "growfactordrop.db";

		public static final String HORMONEDROP = "hormonedrop.db";

		public static final String NUTRITIONFACTORDROP = "nutritionfactordrop.db";

		public static final String RADIATIONDROP = "radiationdrop.db";

		public static final String SURGERYDROP = "surgerydrop.db";

		public static final String VIRUSDROP = "virusdrop.db";

		public static final String VIRALVECTORDROP = "viralvectordrop.db";

		public static final String GRAFTTYPEDROP = "grafttypedrop.db";

		public static final String PRINCIPALINVESTIGATORDROP = "principalinvestigatordrop.db";

		public static final String INDUCEDMUTATIONDROP = "inducedmutationdrop.db";

		// Query dropdowns
		public static final String CHEMICALDRUGQUERYDROP = "chemdrugquerydrop.db";

		public static final String GROWTHFACTORQUERYDROP = "growfactorquerydrop.db";

		public static final String HORMONEQUERYDROP = "hormonequerydrop.db";

		public static final String RADIATIONQUERYDROP = "radiationquerydrop.db";

		public static final String VIRUSQUERYDROP = "virusquerydrop.db";

		public static final String SURGERYQUERYDROP = "surgeryquerydrop.db";

		public static final String SPECIESQUERYDROP = "speciesquerydrop.db";

		public static final String PRINCIPALINVESTIGATORQUERYDROP = "principalinvestigatorquerydrop.db";

		public static final String INDUCEDMUTATIONAGENTQUERYDROP = "inducedmutationagentquerydrop.db";

		// These two are used to display the species and strain currently in the
		// AnimalModelCharacteristics
		public static final String MODELSPECIES = "modelspecies";

		public static final String MODELSTRAIN = "modelstrain";

		public static final String CHEMICALCLASSESDROP = "ChemicalClasses.txt";

		public static final String BIOLOGICALPROCESSDROP = "BiologicalProcess.txt";

		public static final String THERAPEUTICTARGETSDROP = "TherapeuticTargets.txt";
	}

	/**
	 * Defines the global constants used as parameters to requests
	 */
	public interface Parameters {

		public static final String MODELID = "aModelID";

		public static final String MODELSECTIONNAME = "aModelSectionName";
		
        public static final String MODELSECTIONVALUE = "modelSectionValue";
        
		public static final String COMMENTSID = "aCommentsID";
		
		public static final String COMMENTSLIST = "aCommentsList";
        
        
	}

	public interface Pages {
        
		public static final String MODEL_CHARACTERISTICS = "General Information Page";

		public static final String CARCINOGENIC_INTERVENTION = "Carcinogenic Interventions Page";

		public static final String PUBLICATIONS = "Publications page";

		public static final String HISTOPATHOLOGY = "Histopathology Page";

		public static final String THERAPEUTIC_APPROACHES = "Therapeutic Approaches Page";

		public static final String CELL_LINES = "Cell Lines Page";

		public static final String IMAGES = "Images Page";

		public static final String MICROARRAY = "Microarray Page";

		public static final String GENETIC_DESCRIPTION = "Genetic Description Page";
        
        public static final String XENOGRAFT = "Xenograft Page";
	}

	/**
	 * Used to determine the current model to edit on submission/edit also used
	 * to display the name of the model and it's current status
	 */
	public static final String MODELID = "modelid";

	public static final String MODELDESCRIPTOR = "modeldescriptor";

	public static final String MODELSTATUS = "modelstatus";

	/**
	 * Used to prepopulate forms
	 */
	public static final String FORMDATA = "formdata";

	public static final String ANIMALMODEL = "animalmodel";

	/**
	 * Used to store username for current user
	 */
	public static final String CURRENTUSER = "camod.loggedon.username";

	public static final String CURRENTUSERROLES = "camod.loggedon.userroles";

	public static final String LOGINFAILED = "loginfailed";

	/**
	 * Used for search results
	 */
	public static final String SEARCH_RESULTS = "searchResults";

	public static final String TRANSGENE_COLL = "transgeneColl";

	public static final String GENOMIC_SEG_COLL = "genomicSegColl";

	public static final String TARGETED_MOD_COLL = "targetedModColl";

	public static final String TARGETED_MOD_GENE_MAP = "targetedModGeneMap";

	public static final String INDUCED_MUT_COLL = "inducedMutColl";

	public static final String TRANSGENE_CNT = "transgeneCnt";

	public static final String GENOMIC_SEG_CNT = "genomicSegCnt";

	public static final String TARGETED_MOD_CNT = "targetedModCnt";

	public static final String INDUCED_MUT_CNT = "inducedMutCnt";

	public static final String THERAPEUTIC_APPROACHES_COLL = "therapeuticApproachesColl";

	public static final String CLINICAL_PROTOCOLS = "clinProtocols";

	public static final String YEAST_DATA = "yeastData";

	public static final String INVIVO_DATA = "invivoData";

	public static final String PRECLINICAL_MODELS = "preClinicalModels";

	public static final String CARCINOGENIC_INTERVENTIONS_COLL = "carcinogenicInterventionColl";

	public static final String DRUG_SCREEN_OPTIONS = "drugScreenSearchOptions";

	// /////////////////////////////////////////////////////////////
	// Submission specific constants
	// /////////////////////////////////////////////////////////////

	public interface Submit {

		/**
		 * Used to store required lists for the cardiogentic intervention
		 * section of the sidebar menu of the submission section
		 */
		public static final String CHEMICALDRUG_LIST = "chemicaldrug_list";

		public static final String ENVIRONMENTALFACTOR_LIST = "environmentalfactor_list";

		public static final String GENEDELIVERY_LIST = "genedelivery_list";

		public static final String GROWTHFACTORS_LIST = "growthfactors_list";

		public static final String HORMONE_LIST = "hormone_list";

		public static final String NUTRITIONALFACTORS_LIST = "nutritionalfactors_list";

		public static final String RADIATION_LIST = "radiation_list";

		public static final String SURGERYOTHER_LIST = "surgeryother_list";

		public static final String VIRALTREATMENT_LIST = "viraltreatment_list";

		public static final String XENOGRAFT_LIST = "xenograft_list";

		public static final String SPONTANEOUSMUTATION_LIST = "spontaneousmutation_list";

		public static final String INDUCEDMUTATION_LIST = "inducedmutation_list";

		public static final String TARGETEDMODIFICATION_LIST = "targetedmodification_list";

		public static final String GENOMICSEGMENT_LIST = "genomicsegment_list";

		public static final String HISTOPATHOLOGY_LIST = "histopathology_list";

		public static final String THERAPY_LIST = "therapy_list";

		public static final String CLINICALMARKER_LIST = "clinicalmarker_list";

		/**
		 * Used to store a list of names for the Publication section of the
		 * sidebar menu of the submission section
		 */
		public static final String PUBLICATION_LIST = "publication_list";

		/**
		 * Used to store a list of names for the Cell Line section of the
		 * sidebar menu of the submission section
		 */
		public static final String CELLLINE_LIST = "cellline_list";
	}

	// /////////////////////////////////////////////////////////////
	// Admin specific constants
	// /////////////////////////////////////////////////////////////

	public interface Admin {

		/**
		 * Defines the different roles in the system
		 */
		public interface Roles {

			/**
			 * A constant that defines the submitter role
			 */
			public static final String SUBMITTER = "Public Submitter";

			/**
			 * A constant that defines the coordinator role
			 */
			public static final String COORDINATOR = "MMHCC Coordinator";

			/**
			 * A constant that defines the Editor role
			 */
			public static final String EDITOR = "MMHCC Editor";

			/**
			 * A constant that defines the screener role
			 */
			public static final String SCREENER = "MMHCC Screener";
		}

		/**
		 * Defines the different roles in the system
		 */
		public interface Actions {

			/**
			 * A constant that defines the text for the generic approved action
			 */
			public static final String APPROVE = "approve";

			/**
			 * A constant that defines the text for the assign editor action
			 */
			public static final String ASSIGN_EDITOR = "assign_editor";

			/**
			 * A constant that defines the text for the assign screener action
			 */
			public static final String ASSIGN_SCREENER = "assign_screener";

			/**
			 * A constant that defines the text for the need more information
			 * action
			 */
			public static final String NEED_MORE_INFO = "need_more_info";

			/**
			 * A constant that defines the text for the generic reject action
			 */
			public static final String REJECT = "reject";

			/**
			 * A constant that defines the text for the complete
			 */
			public static final String COMPLETE = "complete";
		}

		/**
		 * A constant that defines what file is used for the model curation
		 * process
		 */
		public static final String MODEL_CURATION_WORKFLOW = "config/CurationConfig.xml";

		/**
		 * A constant that defines what file is used for the comment curation
		 * process
		 */
		public static final String COMMENT_CURATION_WORKFLOW = "config/CommentCurationConfig.xml";

		/**
		 * Used to set/pull the objects needing to be reviewed out of the
		 * request
		 */
		public static final String COMMENTS_NEEDING_REVIEW = "commentsNeedingReview";

		/**
		 * Used to set/pull the objects needing to be reviewed out of the
		 * request
		 */
		public static final String COMMENTS_NEEDING_ASSIGNMENT = "commentsNeedingAssignment";
		
		/** Used to set/pull the objects needing to be edited out of the request */
		public static final String MODELS_NEEDING_EDITING = "modelsNeedingEditing";

		/**
		 * Used to set/pull the objects needing to be assigned an editor out of
		 * the request
		 */
		public static final String MODELS_NEEDING_EDITOR_ASSIGNMENT = "modelsNeedingEditorAssignment";

		/** Used to set/pull the objects needing to be edited out of the request */
		public static final String MODELS_NEEDING_MORE_INFO = "modelsNeedingMoreInfo";

		/**
		 * Used to set/pull the objects needing to be screened out of the
		 * request
		 */
		public static final String MODELS_NEEDING_SCREENING = "modelsNeedingScreening";

		/**
		 * Used to set/pull the objects needing to be assigned a screener out of
		 * the request
		 */
		public static final String MODELS_NEEDING_SCREENER_ASSIGNMENT = "modelsNeedingScreenerAssignment";
	}

	public interface EmailMessage {

		public static final String SENDER = "email.sender";

		public static final String RECIPIENTS = "email.recipients";

		public static final String FROM = "email.from";

		public static final String MESSAGE = "email.message";

		public static final String SUBJECT = "email.subject";

	}

	/**
	 * 
	 * Constants used for fetching EVS data
	 * 
	 */
	public interface Evs {

		/**
		 * The namespace to fetch the concepts from
		 */
		public static final String NAMESPACE = "NCI_Thesaurus";

		/**
		 * The tag used to get the display name
		 */
		public static final String DISPLAY_NAME_TAG = "Display_Name";

		/**
		 * The key for the URI in the camod.properties file
		 */
		public static final String URI_KEY = "evs.uri";
	}
}
