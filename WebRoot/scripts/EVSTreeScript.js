var skin = 'evsMOD';
windowTitle = "NCI Center for Bioinformatics";

// targetFormName (String) - The name of the form with the target form fields (organ, organTissueName, organTissueCode) * NO SPACES *
// species (String) - desired species * NO SPACES *

function showMouseTissueTree(form, inConceptCode, inConceptName, inDisplayName, leafNode)
{
	var rootNode = 'Murine_Tissue_Type';
	var roleType= '';
	var sementicType = 'T023,T024,T025';
	var title = 'Tissue Select';
	var params = ["true", "true", "-1", "0"];
	var postMsg = false;

	showTree(form, inConceptCode, inConceptName, inDisplayName, rootNode, roleType, sementicType, skin, params, leafNode, title, '', postMsg, '')
}

function showMouseDiagnosisTree(form, inConceptCode, inConceptName, inDisplayName, leafNode, rootNode)
{
	var rootNode = 'Mouse_Disorder_by_Site';
	var roleType= '';
	var sementicType = 'T047,T191';
	var title = 'Diagnosis Select';
	var params = ["true", "true", "-1", "0"];
	var postMsg = true;

	showTree(form, inConceptCode, inConceptName, inDisplayName, rootNode, roleType, sementicType,  skin, params, leafNode, title, '', postMsg, '')		
}

function showHumanTissueTree(form, inConceptCode, inConceptName, inDisplayName, leafNode)
{
	var rootNode = 'Organ_System';
	var roleType= 'Anatomic_Structure_is_Physical_Part_Of';
	var sementicType = 'T023,T017';
	var title = 'Human Tissue Select';
	var params = ["true", "true", "-1", "0"];
	var postMsg = false;

	showTree(form, inConceptCode, inConceptName, inDisplayName, rootNode, roleType, sementicType, skin, params, leafNode, title, '', postMsg, '')
}

function showTree(form, conceptCode, conceptName, displayName, rootNode, roleType, sementicType,  skin, params, leafNode, title, fieldsToBlank, postMsg, rootTop)
{
    var now = new Date();
    var glob = now.getHours()+now.getSeconds()+now.getMilliseconds();
    var targetURL;
    var descendants;
    var isaFlag;
    var depthLevel;
    var attribute;
 
    descendants = params[0];
    isaFlag = params[1];
    depthLevel = params[2];
    attribute = params[3];

    if((fieldsToBlank==undefined)||(fieldsToBlank==''))
    {
        if((rootTop==undefined)||(rootTop==''))
        {
	        if((roleType==undefined)||(roleType==''))
	        {
	            targetURL = '/IEVSTree/webtree/WebTreeMain.jsp?treeParams=rootNode='+rootNode+';conceptName='+conceptName+';conceptCode='+conceptCode+';displayName='+displayName+';descendants='+descendants+';isaFlag='+isaFlag+';depthLevel='+depthLevel+';attributes='+attribute+';onlyLeaf='+leafNode+';sementicType='+sementicType+';formName='+form+';postMsg='+postMsg+';&skin='+skin+'&windowTitle='+title+'&rand='+glob;
	        } 
	        else 
	        {
	            targetURL = '/IEVSTree/webtree/WebTreeMain.jsp?treeParams=rootNode='+rootNode+';conceptName='+conceptName+';conceptCode='+conceptCode+';displayName='+displayName+';descendants='+descendants+';isaFlag='+isaFlag+';depthLevel='+depthLevel+';attributes='+attribute+';onlyLeaf='+leafNode+';roleType='+roleType+';sementicType='+sementicType+';formName='+form+';postMsg='+postMsg+';&skin='+skin+'&windowTitle='+title+'&rand='+glob;
	        }
        }
        else 
        {
            targetURL = '/IEVSTree/webtree/WebTreeMain.jsp?treeParams=rootNode='+rootNode+';rootTop='+rootTop+';conceptName='+conceptName+';conceptCode='+conceptCode+';displayName='+displayName+';descendants='+descendants+';isaFlag='+isaFlag+';depthLevel='+depthLevel+';attributes='+attribute+';onlyLeaf='+leafNode+';roleType='+roleType+';sementicType='+sementicType+';formName='+form+';postMsg='+postMsg+';&skin='+skin+'&windowTitle='+title+'&rand='+glob;

        }
    }
    else 
    {
        if((roleType==undefined)||(roleType==''))
        {
		    targetURL = '/IEVSTree/webtree/WebTreeMain.jsp?treeParams=rootNode='+rootNode+';conceptName='+conceptName+';conceptCode='+conceptCode+';displayName='+displayName+';descendants='+descendants+';isaFlag='+isaFlag+';depthLevel='+depthLevel+';attributes='+attribute+';onlyLeaf='+leafNode+';sementicType='+sementicType+';formName='+form+';fieldsToBlank='+fieldsToBlank+';postMsg='+postMsg+';&skin='+skin+'&windowTitle='+title+'&rand='+glob;
		} 
		else 
		{
		    targetURL = '/IEVSTree/webtree/WebTreeMain.jsp?treeParams=rootNode='+rootNode+';conceptName='+conceptName+';conceptCode='+conceptCode+';displayName='+displayName+';descendants='+descendants+';isaFlag='+isaFlag+';depthLevel='+depthLevel+';attributes='+attribute+';onlyLeaf='+leafNode+';roleType='+roleType+';sementicType='+sementicType+';formName='+form+';fieldsToBlank='+fieldsToBlank+';postMsg='+postMsg+';&skin='+skin+'&windowTitle='+title+'&rand='+glob;
		}
    }
  
    // open target window
    windowOpen(targetURL, 810, 500, title);
}

 // pop-up window utilitiy function
 function windowOpen(url,w,h,title)
 {
     window.name = 'root';
     remote = window.open(url,'none','width= '+w+',height='+h+', resizable=yes,scrollbars=yes');
     if (remote != null) 
     {
         remote.title = title;
         if (remote.opener == null) 
         {
             remote.opener = self;
             remote.name = 'popup';
         }
     }
     remote.focus();
}
