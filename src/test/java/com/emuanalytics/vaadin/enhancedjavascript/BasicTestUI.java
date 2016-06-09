package com.emuanalytics.vaadin.enhancedjavascript;

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
        return "Basic test of EnhancedJavascriptComponent";
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

        Button setValueRPCButton = new Button("Set Value Via RPC", clickEvent -> {
            sampleComponent.setValueViaRPC(valueField.getValue());
        });
        setValueRPCButton.setId("set-value-rpc-button");

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
                setValueRPCButton,
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
