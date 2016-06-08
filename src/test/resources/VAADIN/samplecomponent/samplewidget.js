/*
 * Copyright 2016 Emu Analytics Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

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