// Supporting Javascript functions for the EVS Data Tree
//
// Aug-2002 : Marc Piparo (SAIC - Celebration, FL)
//

// optional confirm selection, selects concept with confirmation and calls setSelection()
//function confirmSelection(formName, treeType, value, code, parent, displayName)
function confirmSelection(form, conceptName, value, code, conceptCode, displayName, formDisplayName, fieldsToBlank)
{ 

	var confirmationMessage;
  if (parent == 'true')
  {
    confirmationMessage = "Confirm Selection: \""+displayName+"\"\n\nMore specific data exists for the term you selected.\n\nClick \"OK\" to select this general term, or \nClick \"Cancel\" to continue searching for a more specific term.";
  }
  else
  { 
    confirmationMessage = "Confirm Selection: \""+displayName+"\"\n\nClick \"OK\" to select this term, or \nClick \"Cancel\" to continue searching for another term.";  
  }
	var confirmation = confirm(confirmationMessage);

	if (confirmation == true){
		if((fieldsToBlank=='null')||(fieldsToBlank==''))		
		setSelection(form, conceptName, value, code, displayName, conceptCode, formDisplayName);
			else{
			setSelection(form, conceptName, value, code, displayName, conceptCode, formDisplayName);
			deleteDiagnosis(form ,fieldsToBlank)
			}
	}
}

// sets selection of concept
//function setSelection(formName, treeType, value, code, displayName)
function setSelection(formName, conceptName, value, code, displayName, conceptCode, formDisplayName)

{

var form;
  if (formName != null) 
  	form = top.opener.document.forms[formName]; 
  else  alert("formName is null, a formName must be specified");
  
  if (form == null) {
  alert("Form not found!\nformName = "+formName);
  }
	var  formDisplayedName = top.opener.document.forms[formName].elements[formDisplayName];
	formDisplayedName.value = displayName;
	var  formConceptName = top.opener.document.forms[formName].elements[conceptName]; 
	formConceptName.value = value;
	var  formConceptCode = top.opener.document.forms[formName].elements[conceptCode];
	formConceptCode.value = code;
 top.close();
}

function deleteDiagnosis(formID, fieldsToBlank) {

	try{
	var temp = new Array();
	temp = fieldsToBlank.split(',');
		for(var i = 0, len=temp.length; i < len; i++){ 
		var temp2 = temp[i];
		top.opener.document.forms[formID].elements[temp2].value = '';
		}
	} catch(err){
	alert(err.description);
	}
top.close(); 
}

function setManualDiagnosis(formName, conceptName, conceptCode, formDisplayName  )
{ 
  var reply = window.prompt("Enter your diagnosis below:", "");
  if (reply != 'null' && reply !== '')
  {
    setSelection(formName, conceptName, reply, "C000000", reply, conceptCode, formDisplayName)
  }
}

