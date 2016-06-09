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

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class BasicTestPage {
    private WebDriver driver;
    @FindBy(id = "sample-component")
    private WebElement sampleComponent;
    @FindBy(id = "server-value-input")
    private WebElement serverValueInput;
    @FindBy(id = "set-value-button")
    private WebElement setValueButton;
    @FindBy(id = "set-title-button")
    private WebElement setTitleButton;
    @FindBy(id = "set-value-rpc-button")
    private WebElement setValueRPCButton;
    @FindBy(id = "last-event-field")
    private WebElement lastEventInput;
    @FindBy(id = "last-variable-change-field")
    private WebElement lastVariableChangeInput;
    @FindBy(id = "immediate-checkbox")
    private WebElement immediateCheckbox;

    public void setServerValue(String value) {
        serverValueInput.sendKeys(value);
    }
    public void clickSetValueButton() {
        setValueButton.click();
    }
    public void clickSetTitleButton() {
        setTitleButton.click();
    }
    public void clickSetValueRPCButton() {
        setValueRPCButton.click();
    }
    public void clickComponent() {
        sampleComponent.findElements(By.cssSelector("input")).get(1).click();
    }
    public void clickImmediateCheckboxChecked() {
        immediateCheckbox.click();
    }

    public String lastEventText()  { return lastEventInput.getAttribute("value"); }
    public String variableChangeText()  { return lastVariableChangeInput.getAttribute("value"); }

    public String componentInputValue() {
        return sampleComponent.findElements(By.cssSelector("input")).get(0).getAttribute("value");
    }

    public String componentTitle() {
        return sampleComponent.findElements(By.cssSelector("div")).get(1).getText();
    }

    public void typeInComponent(String text) {
        sampleComponent.findElements(By.cssSelector("input")).get(0).sendKeys(text);
    }

    public BasicTestPage(WebDriver driver) {
        this.driver = driver;
    }
}