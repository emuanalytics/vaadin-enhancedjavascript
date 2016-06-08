package com.emuanalytics.vaadin.enhancedjavascript;

import com.vaadin.annotations.Widgetset;
import org.vaadin.addonhelpers.TServer;

@Widgetset("com.emuanalytics.vaadin.enhancedjavascript.EnhancedJavascriptWidgetset")
public class UIRunner extends TServer {

    /**
     * Starts and embedded server for the tests
     */
    public static void main(String[] args) throws Exception {
        new UIRunner().startServer();
    }
}