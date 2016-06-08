package com.emuanalytics.vaadin.enhancedjavascript.automated;

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

import com.emuanalytics.vaadin.enhancedjavascript.BasicTestUI;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import org.vaadin.addonhelpers.automated.AbstractWebDriverCase;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BasicAutomatedTest extends AbstractWebDriverCase {
    BasicTestPage page;

    @Before
    public void setup() throws MalformedURLException {
        startBrowser();
        driver.navigate().to(new URL("http://localhost:5678/") + BasicTestUI.class.getName());
        page = PageFactory.initElements(driver, BasicTestPage.class);
        waitForLoading();
    }

    @Test
    public void testSetValueFromServer() {
        page.setServerValue("test value");
        page.clickSetValueButton();
        assertThat(page.componentInputValue(), is("test value"));
    }

    @Test
    public void testSetTitleFromServer() {
        page.setServerValue("Test Title");
        page.clickSetTitleButton();
        assertThat(page.componentTitle(), is("Test Title"));
    }

    @Test
    public void testClickEvent() {
        page.typeInComponent("testClickEvent");
        page.clickComponent();
        assertThat(page.lastEventText(), is("Click (value=testClickEvent)"));
    }

    @Test
    public void testDeferredVariableChange() {
        page.typeInComponent("testVariableChange");
        page.clickComponent();
        assertThat(page.variableChangeText(), is("inputValue Changed (value=testVariableChange)"));
    }

    @Test
    public void testImmediateVariableChange() {
        page.clickImmediateCheckboxChecked();
        page.typeInComponent("testVariableChange");
        assertThat(page.variableChangeText(), is("inputValue Changed (value=testVariableChange)"));
    }
}