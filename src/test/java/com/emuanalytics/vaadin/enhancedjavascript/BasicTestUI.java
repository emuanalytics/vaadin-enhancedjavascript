package com.emuanalytics.vaadin.enhancedjavascript;

import com.emuanalytics.vaadin.enhancedjavascript.samplecomponent.SampleEnhancedComponent;
import com.vaadin.annotations.Widgetset;
import com.vaadin.ui.*;

@Widgetset("com.emuanalytics.vaadin.enhancedjavascript.EnhancedJavascriptWidgetset")
public class BasicTestUI extends org.vaadin.addonhelpers.AbstractTest {

    protected SampleEnhancedComponent sampleComponent;
    TextField lastEventField;
    TextField lastVariableChangeField;

    @Override
    public String getDescription() {
        return "Basic test of JavascriptComponentExtra";
    }

    @Override
    public Component getTestComponent() {

        // The sample component under test
        sampleComponent = new SampleEnhancedComponent();
        sampleComponent.setId("sample-component");

        Component componentWrapper = createComponentContainer(sampleComponent);
        Component testPanel = createTestPanel();

        HorizontalLayout layout = new HorizontalLayout(componentWrapper, testPanel);

        layout.setWidth("100%");
        layout.setHeight("100%");
        layout.setMargin(true);
        layout.setExpandRatio(componentWrapper, 1);

        sampleComponent.addClickListener(value -> {
            lastEventField.setValue("Click (value=" + value + ")");
        });

        sampleComponent.addVariableChangeListener(value -> {
            lastVariableChangeField.setValue("inputValue Changed (value=" + value.toString() + ")");
        });

        return layout;
    }

    private Component createComponentContainer(SampleEnhancedComponent component) {

        Label label = new Label("Component Under Test:");
        VerticalLayout layout = new VerticalLayout(label, component);
        layout.setSpacing(true);

        return layout;
    }

    private Component createTestPanel() {

        TextField valueField = new TextField("Server value to send:");
        valueField.setId("server-value-input");

        Button setValueButton = new Button("Set Value From Server", clickEvent -> {
            sampleComponent.setValue(valueField.getValue());
        });
        setValueButton.setId("set-value-button");

        Button setTitleButton = new Button("Set Title From Server", clickEvent -> {
            sampleComponent.setTitle(valueField.getValue());
        });
        setTitleButton.setId("set-title-button");

        CheckBox immediateCheckbox = new CheckBox("Immediate variable notification");
        immediateCheckbox.addValueChangeListener(e -> {
            sampleComponent.setImmediate(immediateCheckbox.getValue());
        });
        immediateCheckbox.setId("immediate-checkbox");

        lastEventField = new TextField("Last event:");
        lastEventField.setId("last-event-field");

        lastVariableChangeField = new TextField("Last variable change:");
        lastVariableChangeField.setId("last-variable-change-field");

        VerticalLayout testLayout = new VerticalLayout(
                valueField,
                setValueButton,
                setTitleButton,
                immediateCheckbox,
                lastEventField,
                lastVariableChangeField);

        testLayout.setWidth("300px");
        testLayout.setSpacing(true);

        for (Component c: testLayout) {
            c.setWidth("100%");
        }

        return testLayout;
    }

}
