// EvsTree.js
// Javascript functions for the EVS Data Tree
//
// Aug-2002 :  Marc Piparo (SAIC - Celebration, FL)
//


// popup tree window for tissues/organ
// parameters:

var skinName = 'evs';
windowTitle = "NCI Center for Bioinformatics";

// targetFormName (String) - The name of the form with the target form fields (organ, organTissueName, organTissueCode) * NO SPACES *
// species (String) - desired species * NO SPACES *
// linkDepth (Int) - optional tree depth to begin making nodes selectable links, default=0
function showTissueTree(targetFormName, species, linkDepth)
{
  if (linkDepth == null) linkDepth = 0;
  if (species == null) species = " ";
  var now = new Date();
  var glob = now.getHours()+now.getSeconds()+now.getMilliseconds();
  var targetURL;
  
  targetURL = '/camod/jsp/webtree/WebTreeMain.jsp?treeClass=gov.nih.nci.ncicb.evs.EvsTrees&treeParams=type:tissue;species:'+escape(species)+';linkLevel:'+linkDepth+';formName:'+escape(targetFormName)+'&skin='+skinName+'&windowTitle='+windowTitle+'&rand='+glob;   
	
  // open target window	
  windowOpen(targetURL, 810, 500, 'Tissue Select');
}

// popup tree for diagnoses
// parameters: 
// targetFormName (String) - The name of the form with the target form fields (TumorClassification, DiagnosisName, DiagnosisCode) * NO SPACES *
// species (String) - desired species * NO SPACES *
// linkDepth (Int) - optional tree depth to begin making nodes selectable links, default=0
function showDiagnosisTree(targetFormName, species, linkDepth)
{
  if (linkDepth == null) linkDepth = 0;
  if (species == null) species = " ";
    
  //  var selectedOrgan = getSelectedOrgan();
  if (document.forms[targetFormName].organ && (document.forms[targetFormName].organTissueName.value == null || document.forms[targetFormName].organTissueName.value == ""))
  {
    alert("You must first select an Organ/Tissue!");
  }
  else
  {
    var now = new Date();
    var glob = now.getHours()+now.getSeconds()+now.getMilliseconds();   
  	var targetURL;
      
    var searchOrgan = escape(document.forms[targetFormName].organTissueName.value);    
    
    targetURL = '/camod/jsp/webtree/WebTreeMain.jsp?treeClass=gov.nih.nci.ncicb.evs.EvsTrees&treeParams=type:diagnosis;species:'+escape(species)+';linkLevel:'+linkDepth+';formName:'+escape(targetFormName)+';organ:'+searchOrgan+'&skin='+skinName+'&treeDirective=openToName:neoplasm&windowTitle='+windowTitle+'&rand='+glob;   
  
    // open target window
    windowOpen(targetURL, 810, 500, 'Diagnosis Select');	
  }
}	

// pop-up window utilitiy function
function windowOpen(url,w,h,title)
{
  window.name = 'root';
  // remote = window.open(url,'none','width= '+w+',height='+h+', resizable=yes,scrollbars=yes,menubar=yes,status=yes'); 
  remote = window.open(url,'none','width= '+w+',height='+h+', resizable=yes,scrollbars=yes');
  if (remote != null) 
  {
		remote.title = title;
    if (remote.opener == null) 
    {
      remote.opener = self;
      remote.name = 'popup';
      //remote.location.href = url;
    }
  }
  remote.focus();
}


