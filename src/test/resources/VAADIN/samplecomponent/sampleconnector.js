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
  };
})(window.samplewidget);