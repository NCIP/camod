   function initRequest(url) {
       if (window.XMLHttpRequest) {
           return new XMLHttpRequest();
       } else if (window.ActiveXObject) {
           isIE = true;
           return new ActiveXObject("Microsoft.XMLHTTP");
       }
   }

   function doCompletion() {
       if (completeField.value == "") {
           clearTable();
       } else {
           var url = "autocomplete?action=complete&id=" + 
                   escape(completeField.value);
           var req = initRequest(url);
           req.onreadystatechange = function() {
               if (req.readyState == 4) {
                   if (req.status == 200) {
                       parseMessages(req.responseXML);
                   } else if (req.status == 204){
                       clearTable();
                   }
               }
           };
           req.open("GET", url, true);
           req.send(null);
       }
   }
   
   function parseMessages(responseXML) {
       clearTable();
           var employees = responseXML.getElementsByTagName(
                   "employees")[0];
       if (employees.childNodes.length > 0) {
           completeTable.setAttribute("bordercolor", "black");
           completeTable.setAttribute("border", "1");
       } else {
           clearTable();
       }
    
       for (loop = 0; loop < employees.childNodes.length; loop++) {
           var employee = employees.childNodes[loop];
           var firstName = employee.getElementsByTagName(
                   "firstName")[0];
           var lastName = employee.getElementsByTagName(
                   "lastName")[0];
           var employeeId = employee.getElementsByTagName(
                   "id")[0];
           appendEmployee(
                   firstName.childNodes[0].nodeValue,
                   lastName.childNodes[0].nodeValue, 
                   employeeId.childNodes[0].nodeValue);
       }
   }

   			