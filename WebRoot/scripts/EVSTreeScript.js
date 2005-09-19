    var skinName = 'evs';
    windowTitle = "NCI Center for Bioinformatics";

    // targetFormName (String) - The name of the form with the target form fields (organ, organTissueName, organTissueCode) * NO SPACES *
    // species (String) - desired species * NO SPACES *

    function showTissueTree(targetFormName, input)
    {
      var now = new Date();
      var glob = now.getHours()+now.getSeconds()+now.getMilliseconds();
      var targetURL;
      var rootNode = "Murine_Tissue_Type";
      input = 'rootNode='+rootNode+";"+input;

      targetURL = '/EVSTree/webtree/WebTreeMain.jsp?treeParams='+escape(input)+';formName='+escape(targetFormName)+';type=tissue;&skin='+skinName+'&windowTitle='+windowTitle+'&rand='+glob;

      // open target window
      windowOpen(targetURL, 810, 500, 'Tissue Select');
    }

    // pop-up window utilitiy function
    function windowOpen(url,w,h,title)
    {
      window.name = 'root';
      remote = window.open(url,'none','width= '+w+',height='+h+', resizable=yes,scrollbars=yes');
      if (remote != null) {
  		remote.title = title;
        if (remote.opener == null) {
          remote.opener = self;
          remote.name = 'popup';
        }
      }
      remote.focus();
    }

    function showDiagnosisTree(targetFormName, input)
	{
	    var now = new Date();
	    var glob = now.getHours()+now.getSeconds()+now.getMilliseconds();   
	  	var targetURL;
	    var rootNode="Mouse_Disorder_by_Site";
	    //var rootNode = escape(document.forms[targetFormName].organTissueName.value);
	    targetURL = '/EVSTree/webtree/WebTreeMain.jsp?treeParams='+escape(input)+';rootNode='+escape(rootNode)+';formName='+escape(targetFormName)+';type=diagnosis;&skin='+skinName+'&windowTitle='+windowTitle+'&rand='+glob;   
	  
	    // open target window
	    windowOpen(targetURL, 810, 500, 'Diagnosis Select');	
	}
