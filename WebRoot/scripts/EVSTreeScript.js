    var skin = 'evsMOD';   

    windowTitle = "NCI Center for Bioinformatics";    

    // show tissue tree with fieldToBlank parameter
    function showMouseTissueTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)
    {
	var paramsHT = {};

        paramsHT.treeNameKey = 'MouseTissue';
		paramsHT.formName = form;
		paramsHT.conceptCode = inConceptCode;
		paramsHT.conceptName = inConceptName;
		paramsHT.displayName = inDisplayName;		
        paramsHT.postMsg = false;
        paramsHT.onlyLeaf = onlyLeaf;   
        if (fieldsToBlank !== undefined) {
        	paramsHT.fieldsToBlank = fieldsToBlank;
		}
        paramsHT.windowTitle = 'Tissue Select';

	showTree(paramsHT);
    }

    // show diagnosis tree with rootConceptNode parameter
    function showMouseDiagnosisTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf)
    {
        var paramsHT = {};

        paramsHT.treeNameKey = 'MouseDiagnosis';        
		paramsHT.formName = form;
		paramsHT.conceptCode = inConceptCode;
		paramsHT.conceptName = inConceptName;
		paramsHT.displayName = inDisplayName;
        paramsHT.onlyLeaf = onlyLeaf;           
        paramsHT.windowTitle = 'Diagnosis Select';        
        paramsHT.postMsg = true;

        showTree(paramsHT);		
     }

    function showHumanTissueTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf, fieldsToBlank)	
    {      
	var paramsHT = {};
     
        paramsHT.treeNameKey = 'HumanTissue';
		paramsHT.formName = form;
		paramsHT.conceptCode = inConceptCode;
		paramsHT.conceptName = inConceptName;
		paramsHT.displayName = inDisplayName;        	
        paramsHT.postMsg = false;
        paramsHT.onlyLeaf = onlyLeaf;        
        if (fieldsToBlank !== undefined) {
        	paramsHT.fieldsToBlank = fieldsToBlank;
        }

        paramsHT.windowTitle = 'Human Tissue Select';

	showTree(paramsHT);
    }

    function showHumanDiagnosisTree(form, inConceptCode, inConceptName, inDisplayName, onlyLeaf)	
    {      
	var paramsHT = {};
     
        paramsHT.treeNameKey = 'HumanDiagnosis';
		paramsHT.formName = form;
		paramsHT.conceptCode = inConceptCode;
		paramsHT.conceptName = inConceptName;
		paramsHT.displayName = inDisplayName;        	
        paramsHT.postMsg = false;
        paramsHT.onlyLeaf = onlyLeaf;       

        paramsHT.windowTitle = 'Human Diagnosis Select';

	showTree(paramsHT);
    }

    // new showTree function (dynamically builds name=value parameter string for url from hashtable)
    function showTree(paramsHT) 
    { 
	var now = new Date();
        var glob = now.getHours()+now.getSeconds()+now.getMilliseconds();
        paramsHT.rand = glob;
        
        var treeParams = "";
        for (var paramName in paramsHT) {
           treeParams += paramName+"="+paramsHT[paramName]+";";
        }
      
	targetURL = '/EVSTree/webtree/WebTreeMain.jsp?treeParams='+treeParams+'&skin='+skin;

	// open target window
        windowOpen(targetURL, 810, 500, paramsHT.windowTitle);
    }


    // pop-up window utilitiy function
    function windowOpen(url,w,h,title)
    {
      window.name = 'root';
      remote = window.open(url,'none','width= '+w+',height='+h+', resizable=yes,scrollbars=yes');
      //remote = window.open(url);
	if (remote !== null) {
  		remote.title = title;
        if (remote.opener == null) {
          remote.opener = self;
          remote.name = 'popup';
        }
      }
      remote.focus();
    }


