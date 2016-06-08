(function(global) {
  'use strict';

  var samplewidget = global.samplewidget = global.samplewidget || {};

  samplewidget.SampleWidget = function (element) {
    var self = this;

    element.innerHTML = [
      '<div>',
      '<div class="caption" id="widget-title">Hello, world!</div>',
      '<div class="textinput">Enter a value: ',
      '<input type="text" name="value"/>',
      '<input type="button" value="Click"/>',
      '</div>'
    ].join('\n');

    element.classList.add('sample-widget');

    var titleDiv = element.getElementsByTagName("div")[1];
    var textInput = element.getElementsByTagName("input")[0];
    var button = element.getElementsByTagName("input")[1];

    this.getValue = function () {
      return textInput.value;
    };

    this.setValue = function (value) {
      textInput.value = value;
    };

    this.setTitle = function (value) {
      titleDiv.innerHTML = value;
    };

    // Default implementation of the click handler
    this.click = function () {
      alert("Error: Must implement click() method");
    };

    this.inputChanged = function (value) {
      alert("Error: Must implement inputChanged() method");
    };

    // Set up button click
    button.onclick = function () {
      self.click();
    };

    // Set up onchange deferred variable
    textInput.onkeyup = function () {
      self.inputChanged(textInput.value);
    };
  };
})(window);