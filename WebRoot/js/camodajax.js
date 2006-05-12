windowTitle = "NCI Center for Bioinformatics";

function selection(src, targ){
var srcElement = document.getElementById(src);
var targetElement = document.getElementById(targ);
//alert(srcElement.name);
//alert(targetElement.name);
	new AjaxJspTag.Autocomplete(
		"/camod/autocomplete.view", {
		indicator: "indicator",
		minimumCharacters: "1",
		parser: new ResponseXmlToHtmlListParser(),
		target: targetElement.name,
		className: "autocomplete",
		source: srcElement.name
	});
}
