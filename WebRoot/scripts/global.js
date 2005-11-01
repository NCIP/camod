
function chkOther(vocab, otherVocab) {
	
	if( vocab.value == 'Other' ) {
		enableField(otherVocab);
	}
	else {
	    disableField(otherVocab);
	}
}

function chkOtherName() {

    var name = document.forms[0].name;
    var otherName = document.forms[0].otherName;

    chkOther(name, otherName);  	
}

function chkOtherAdminRoute() {

	var route = document.forms[0].administrativeRoute;
	var otherRoute = document.forms[0].otherAdministrativeRoute;
	
    chkOther(route, otherRoute);  	
}

function disableFieldUnsized(field)
{
		field.value = '';
		field.disabled = true;
		field.className = "formFieldUnSizedDisabled";
}


function enableFieldUnsized(field)
{
    field.disabled = false;
    field.className = "formFieldUnSized";
}


function chkOtherUnsized(vocab, otherVocab) {
	if( vocab.value == 'Other' ) {
		enableFieldUnsized(otherVocab);
	}
	else {
	    disableFieldUnsized(otherVocab);
	}
}


function disableField(field)
{
		field.value = '';
		field.disabled = true;
		field.className = "formFieldSizedDisabled";
}


function enableField(field)
{
    field.disabled = false;
    field.className = "formFieldSized";
}

function select(group)
{   
    for (var i=0; i < group.options.length; i++)  
    {  
        group.options[i].selected = true;    
    }
}

function unselect(group)
{   
    for (var i=0; i < group.options.length; i++)  
    {  
        group.options[i].selected = false;    
    }
}

/****************************
 * sortItems(obj)
 *
 * Sorts items in a select
 * box.
 ****************************/
function sortItems(obj)  
{   var temp_opts = new Array();
    var temp = new Object();
    for (var i=0; i<obj.options.length; i++)
        temp_opts[i] = obj.options[i];
    
    for (var x=0; x<temp_opts.length-1; x++)  
    {   for (var y=(x+1); y<temp_opts.length; y++)  
        {   if(temp_opts[x].text > temp_opts[y].text)  
            {   temp = temp_opts[x].text;
                temp_opts[x].text = temp_opts[y].text;
                temp_opts[y].text = temp;
                temp = temp_opts[x].value;
                temp_opts[x].value = temp_opts[y].value;
                temp_opts[y].value = temp;
            }
        }
    }

    for (var i=0; i<obj.options.length; i++) 
    {   obj.options[i].value = temp_opts[i].value;
        obj.options[i].text = temp_opts[i].text;
    }
} // end function sortItems()

/*******************************************
 * transferItem(source, destination, sort)
 *
 * Transfers items from one select
 * box to a different text box. With 
 * an optional sort flag.
 *******************************************/
function transferItem(source, destination, sort)
{   var i = 0;
    var newitem;
    var retval = false;
        
    while (i < source.options.length)
    {   if (source.options[i].selected) 
        {   
	        var value=source.options[i].value;
	        var duplicate = false;
			for (j=0; j < destination.options.length; j++)
			{
				if (destination.options[j].value == value)
				{ // its a duplicate, dont add it
					duplicate = true;
				}
			} 
			if ( ! duplicate)
			{
	        	newitem = new Option(source.options[i].text, source.options[i].value, 0, 0);
            	destination.options[destination.options.length] = newitem;
            	retval = true;
            	source.options[i] = null;
            } 
            else
            {
	            source.options[i] = null;
	            i = i+1;
            }
        }
        else
            i = i + 1;
    }
    
    // Sort items 
    if(sort == "YES")
        sortItems(destination);
    else
        sortItems(source);

} // end function transferItem()

function removeSelected(selectedGroup, group)
{
    for (var i=0; i < selectedGroup.options.length; i++)  
    {  
        for (var j = 0; j < group.options.length; j++)  
        {
            if (group.options[j].value == selectedGroup.options[i].value)
            {
                group.options[j] = null;
                break;
            }
        }
    }
}