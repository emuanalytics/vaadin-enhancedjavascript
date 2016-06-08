package com.emuanalytics.vaadin.enhancedjavascript.automated;

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