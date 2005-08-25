// JavaScript.js
// Supporting Javascript functions for the CGAP WebTree skin functionality
//
// Oct-2002 : Marc Piparo (SAIC - Celebration, FL)
//

// optional confirm selection, selects concept with confirmation and calls setSelection()
function confirmSelection(formName, formSelectMode, value, parent, keyword)
{ 
  var confirmationMessage;
  if (parent == true)
  {
    confirmationMessage = "Confirm Selection: \""+value+"\"\n\nMore specific data exists for the term you selected.\n\nClick \"OK\" to select this general term, or \nClick \"Cancel\" to continue searching for a more specific term.";
  }
  else
  { 
    confirmationMessage = "Confirm Selection: \""+value+"\"\n\nClick \"OK\" to select this term, or \nClick \"Cancel\" to continue searching for another term.";  
  }
	var confirmation = confirm(confirmationMessage);
	if (confirmation == true)
	{
		setSelection(formName, formSelectMode, value, keyword);
	}
}

// sets selection of concept
function setSelection(formName, formSelectMode, value, keyword)
{
  var form;
  if (formName != null) form = top.opener.document.forms[formName]; else alert("formName is null, a formName must be specified");
  if (form == null) alert("Form not found!\nformName = "+formName);

  if (keyword == 'tissue')
  {  
    setTissueSelection(value, form, formSelectMode);
  }
  else if (keyword == 'histology')
  {
    setHistologySelection(value, form, formSelectMode);
  }
  else if (keyword == 'keyword')
  {
    setKeywordSelection(value, form, formSelectMode);
  }
}

// sets tissue (called by setSelection())
function setTissueSelection(value, form, formSelectMode)
{
  if (form.tissue)
  {
    var fieldValue = form.tissue.value;    
    if (formSelectMode == 'append' && fieldValue.length > 0)
    {
      form.tissue.value += ', '+value;      
    }
    else 
    {
      form.tissue.value = value;
    }  
    
    if (formSelectMode != 'append') top.close();    
  }
}

// sets histology (called by setSelection())
function setHistologySelection(value, form, formSelectMode)
{
  if (form.histology)
  {  
    var fieldValue = form.histology.value;        
    if (formSelectMode == 'append' && fieldValue.length > 0)
    {
      form.histology.value += ', '+value;
    }
    else 
    {
      form.histology.value = value;
    }  
    
    if (formSelectMode != 'append') top.close();    
  }
}

// sets keyword (called by setSelection())
function setKeywordSelection(value, form, formSelectMode)
{
  if (form.keyword)
  {  
    var fieldValue = form.keyword.value;        
    if (formSelectMode == 'append' && fieldValue.length > 0)
    {
      form.keyword.value += ', '+value;
    }
    else 
    {
      form.keyword.value = value;
    }  
    
    if (formSelectMode != 'append') top.close();    
  }
}


