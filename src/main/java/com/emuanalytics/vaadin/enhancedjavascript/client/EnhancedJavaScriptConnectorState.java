package com.emuanalytics.vaadin.enhancedjavascript.client;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public interface EnhancedJavaScriptConnectorState extends Serializable {
    public Set<String> getCallbackNames();
    public Map<String, Set<String>> getRpcInterfaces();
}