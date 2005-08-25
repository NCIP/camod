// CGAP.js
// Javascript functions for the CGAP Data Tree
//
// Oct-2002 :  Marc Piparo (SAIC - Celebration, FL)
//

windowTitle = "NCI Center for Bioinformatics"

// popup tree window for tissues/organ
// parameters:


function showCGAPTree(targetFormName, keyword, formSelectionMode, linkLevel, base_url)
{
  // this function will launch the CGAP WebTree
  // parameters:  targetFormName - the HTML form name that the selection javascript will post results to
  //              keyword - the target keyword which the tree should use as its root
  //              formSelectionMode - the mode of node selection back to the HTML form (targetFormName), 'append' or 'replace'
  //              linkLevel - tree level to begin making active selectable links
  
  var now = new Date();
  var glob = now.getHours()+now.getSeconds()+now.getMilliseconds();
  var targetURL;

  targetURL = base_url+'/ncicb/webtree/WebTreeMain.jsp?treeClass=gov.nih.nci.ncicb.cgap.CGAPTree&treeParams=formName:'+escape(targetFormName)+';formSelectionMode:'+escape(formSelectionMode)+';keyword:'+escape(keyword)+';linkLevel:'+escape(linkLevel)+'&windowTitle='+windowTitle+'&skin=cgap&rand='+glob;   	
	
  // open target window	
  windowOpen(targetURL, 810, 500, 'Tissue Select');
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

