package gov.nih.nci.camod;


/**
 * Constant values used throughout the application.
 *
 * <p>
 * <a href="Constants.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class Constants {
    //~ Static fields/initializers =============================================
   
    /** The name of the ResourceBundle used in this application */
    public static final String BUNDLE_KEY = "ApplicationResources";
    
    /** The application scoped attribute for persistence engine used */
    public static final String DAO_TYPE = "daoType";
    public static final String DAO_TYPE_HIBERNATE = "hibernate";

    /** Application scoped attribute for authentication url */
    public static final String AUTH_URL = "authURL";

    /** Application scoped attributes for SSL Switching */
    public static final String HTTP_PORT = "httpPort";
    public static final String HTTPS_PORT = "httpsPort";

    /** The application scoped attribute for indicating a secure login */
    public static final String SECURE_LOGIN = "secureLogin";

    /** The encryption algorithm key to be used for passwords */
    public static final String ENC_ALGORITHM = "algorithm";

    /** A flag to indicate if passwords should be encrypted */
    public static final String ENCRYPT_PASSWORD = "encryptPassword";

    /** File separator from System properties */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /** User home from System properties */
    public static final String USER_HOME =
        System.getProperty("user.home") + FILE_SEP;

    /**
     * The session scope attribute under which the breadcrumb ArrayStack is
     * stored
     */
    public static final String BREADCRUMB = "breadcrumbs";

    /**
     * The session scope attribute under which the User object for the
     * currently logged in user is stored.
     */
    public static final String USER_KEY = "currentUserForm";

    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_EDIT_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "admin";

    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "tomcat";

    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";

    /**
     * Name of cookie for "Remember Me" functionality.
     */
    public static final String LOGIN_COOKIE = "sessionId";

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";
    
    /**
     * The request scope attribute that holds the person form.
     */
    public static final String PERSON_KEY = "personForm";

    /**
     * The request scope attribute that holds the person list
     */
    public static final String PERSON_LIST = "personList";
  
    /**
     * The request scope attribute that holds the login results
     */
    public static final String FAILURE = "failure";
    
    /**
     * The request scope attribute that holds the login results
     */
    public static final String SUCCESS = "success";
    
    public static final String UPT_CONTEXT_NAME = "camod";
    
    /** 
     * Used to store list of models currently logged on user has previous entered
     */
    public static final String USERMODELLIST = "usermodellist";
    
    /**
     *  Used to store lists for drop down menus
     */
    public static final String SPECIESDROP = "speciesdrop";
    public static final String STRAINDROP = "straindrop";
    public static final String SEXDISTRIBUTIONDROP = "sexdrop";
    
    /**
     * Used to determine the current model to edit on submission/edit
     * also used to display the name of the model and it's current status
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
    public static final String LOGINFAILED = "loginfailed";
}
