package com.emuanalytics.vaadin.enhancedjavascript.client;

import com.emuanalytics.vaadin.enhancedjavascript.EnhancedJavaScriptComponent;
import com.vaadin.client.JavaScriptConnectorHelper;
import com.vaadin.client.communication.HasJavaScriptConnectorHelper;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.client.ui.JavaScriptWidget;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.JavaScriptComponentState;

@Connect(EnhancedJavaScriptComponent.class)
public final class EnhancedJavaScriptComponentConnector extends
        AbstractComponentConnector implements HasJavaScriptConnectorHelper {

    private final JavaScriptConnectorHelper helper = new EnhancedJavaScriptConnectorHelper(this) {
        @Override
        protected void showInitProblem(
                java.util.ArrayList<String> attemptedNames) {
            getWidget().showNoInitFound(attemptedNames);
        }
    };

    @Override
    public JavaScriptWidget getWidget() {
        return (JavaScriptWidget) super.getWidget();
    }

    @Override
    protected void init() {
        super.init();
        helper.init();
    }

    @Override
    public JavaScriptConnectorHelper getJavascriptConnectorHelper() {
        return helper;
    }

    @Override
    public JavaScriptComponentState getState() {
        return (JavaScriptComponentState) super.getState();
    }

    @Override
    public void onUnregister() {
        super.onUnregister();
        helper.onUnregister();
    }
}