package com.emuanalytics.vaadin.enhancedjavascript.samplecomponent;

import com.emuanalytics.vaadin.enhancedjavascript.EnhancedJavaScriptComponent;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.JavaScriptFunction;
import elemental.json.JsonArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JavaScript({"vaadin://samplecomponent/samplewidget.js", "vaadin://samplecomponent/sampleconnector.js"})
@StyleSheet({"vaadin://samplecomponent/samplecomponent.css"})
public class SampleEnhancedComponent extends EnhancedJavaScriptComponent {

    private final List<ClickListener> clickListeners = new ArrayList<>();
    private final List<VariableChangeListener> variableChangeListeners = new ArrayList<>();

    public SampleEnhancedComponent() {
        addFunction("onClick", new JavaScriptFunction() {
            @Override
            public void call(JsonArray arguments) {
                String value = arguments.getString(0);
                clickListeners.stream().forEach(l -> {l.onClick(value);});
            }
        });
    }

    public void setValue(String value) {
        getState().value = value;
    }

    public void setTitle(String title) {
        getState().title = title;
    }

    public void setValueViaRPC(String title) {
        callFunction("setValueViaRPC", title);
    }

    @Override
    protected void onChangeVariables(Map<String, Object> variables) {
        String value = (String)variables.get("inputValue");
        variableChangeListeners.stream().forEach(l -> {l.onChange(value);});
    }

    @Override
    protected SampleEnhancedState getState() {
        return (SampleEnhancedState) super.getState();
    }

    public void addClickListener(ClickListener listener) {
        this.clickListeners.add(listener);
    }

    public void addVariableChangeListener(VariableChangeListener listener) {
        this.variableChangeListeners.add(listener);
    }

    public interface ClickListener {
        void onClick(String value);
    }

    public interface VariableChangeListener {
        void onChange(String value);
    }
}
