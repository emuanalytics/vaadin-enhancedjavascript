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

(function(widgetModule) {
  'use strict';

  window.com_emuanalytics_vaadin_enhancedjavascript_samplecomponent_SampleEnhancedComponent = function () {

    var connector = this;
    var immediate = false;
    var widget = new widgetModule.SampleWidget(this.getElement());

    widget.click = function() {
      connector.onClick(widget.getValue());
    };

    widget.inputChanged = function(value) {
      connector.setDeferredVariable("inputValue", value, immediate);
    };

    this.onStateChange = function() {

      var stateDeltas = this.getStateDeltas();
      var state = this.getState();

      console.log('State Delta:', stateDeltas);
      console.log('Consolidated state:', state);

      if (stateDeltas.value) {
        widget.setValue(stateDeltas.value);
      }

      if (stateDeltas.title) {
        widget.setTitle(stateDeltas.title);
      }

      if (stateDeltas.immediate) {
        immediate = stateDeltas.immediate;
      }
    };

    this.setValueViaRPC = function(value) {
      widget.setValue(value);
    };
  };

})(window.samplewidget);