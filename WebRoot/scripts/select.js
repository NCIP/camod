// Select.js
// Javascript Behaviour for the Select Control
// Copyright by Matthias Hertel, http://www.mathertel.de
// This work is licensed under a Creative Commons Attribution 2.0 Germany License.
// See http://creativecommons.org/licenses/by/2.0/de/
// -----
// 22.08.2005 created by Matthias Hertel
// 27.09.2005 PageProperty attribute added
// 25.12.2005 CreateOptions defaultOption added

var SelectBehaviour = {

  // ----- Properties -----
  name: null,
  pageproperty: "",
  nosubmit: true,

  // ----- Events -----
  onkeypress: function(evt) {
    evt = evt || window.event;
    if (evt.keyCode == 13)
      evt.srcElement.Raise();
  }, // onkeypress

  onchange: function (evt) {
    evt = evt || window.event;
    evt.srcElement.Raise();
  }, // onchange


  // ---- Methods -----
  init: function () {
    if ((this.pageproperty != null) && (this.pageproperty.length > 0))
      jcl.DataConnections.RegisterProvider(this, this.pageproperty);
  },


  Raise: function () {
    jcl.DataConnections.Raise(this.pageproperty, this.value);
  }, // Raise


  // Create Option elements for this SELECT from a string
  CreateOptions: function (options, defaultOption) {
    var currentValue = this.value;
    this.length = 0;

    options = options.split(";");
    for (n = 0; n < options.length; n++) {
      var s = options[n];
      var o = document.createElement("OPTION");

      if (s.indexOf(':') < 0) {
        o.value = n+1;
        o.innerHTML = s;
      } else {
        s = s.split(':');
        o.value = s[0];
        o.innerHTML = s[1];
      } // if
      this.appendChild(o);
      if ((o.value == currentValue) || (o.value == defaultOption))
        o.selected = true;
    } // for
    this.Raise();
  } // CreateOptions

} // SelectBehaviour


   
   			