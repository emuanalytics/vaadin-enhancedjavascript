package com.emuanalytics.vaadin.enhancedjavascript.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONValue;
import com.vaadin.client.*;
import elemental.json.JsonArray;
import elemental.json.JsonType;
import elemental.json.JsonValue;

public class EnhancedJavaScriptConnectorHelper extends JavaScriptConnectorHelper {

    private final EnhancedJavaScriptComponentConnector connector;
    private final JavaScriptObject stateDeltas = JavaScriptObject.createObject();
    private final JavaScriptObject combinedState = JavaScriptObject.createObject();

    public EnhancedJavaScriptConnectorHelper(EnhancedJavaScriptComponentConnector connector) {
        super(connector);
        this.connector = connector;
    }

    @Override
    protected void showInitProblem(
            java.util.ArrayList<String> attemptedNames) {
        connector.getWidget().showNoInitFound(attemptedNames);
    }

    @Override
    public JavaScriptObject getConnectorWrapper() {
        JavaScriptObject connWrapper = super.getConnectorWrapper();
        augmentConnectorWrapper(connWrapper, combinedState, stateDeltas, this);
        return connWrapper;
    }

    public void setNativeState(JavaScriptObject state) {
        super.setNativeState(state);
        updateCombinedState(combinedState, stateDeltas, state);
    }

    private static native void updateCombinedState(JavaScriptObject combinedState, JavaScriptObject deltas,
                                                 JavaScriptObject input)
    /*-{
      // Clear deltas
      for(var key in deltas) {
        if (deltas.hasOwnProperty(key)) {
          delete deltas[key];
        }
      }

      // Update combined state and deltas from input
      for(var key in input) {
        if (input.hasOwnProperty(key)) {
          combinedState[key] = input[key];
          deltas[key] = input[key];
        }
      }
    }-*/;

    private void setDeferredVariableImpl(String name, JsonValue value, boolean immediate) {
        if (value.getType() == JsonType.STRING) {
            connector.getConnection().updateVariable(connector.getConnectorId(), name, value.asString(), immediate);
        } else if (value.getType() == JsonType.BOOLEAN) {
            connector.getConnection().updateVariable(connector.getConnectorId(), name, value.asBoolean(), immediate);
        } else if (value.getType() == JsonType.NUMBER) {
            connector.getConnection().updateVariable(connector.getConnectorId(), name, value.asNumber(), immediate);
        } else {
            // Other types (e.g. array, object) are transmitted to server as a JSON string
            connector.getConnection().updateVariable(connector.getConnectorId(), name, value.toJson(), immediate);
        }
    }

    private static native void augmentConnectorWrapper(JavaScriptObject obj, JavaScriptObject combinedState, JavaScriptObject stateDeltas, EnhancedJavaScriptConnectorHelper h)
	/*-{
      var nativeState = obj.getState();
      obj.getStateDeltas = function() { return stateDeltas; }
      obj.getState = function() { return combinedState; }
      obj.setDeferredVariable = function(name, value, immediate) {
        immediate = immediate || false;
        $entry(h.@com.emuanalytics.vaadin.enhancedjavascript.client.EnhancedJavaScriptConnectorHelper::setDeferredVariableImpl(*)).call(h, name, value, immediate);
      };
    }-*/;
}