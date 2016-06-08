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

import com.emuanalytics.vaadin.enhancedjavascript.client.EnhancedJavaScriptComponentState;
import com.vaadin.server.EnhancedJavaScriptCallbackHelper;
import com.vaadin.server.JsonCodec;
import com.vaadin.server.VariableOwner;
import com.vaadin.shared.communication.ServerRpc;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.JavaScriptFunction;
import elemental.json.Json;
import elemental.json.JsonValue;

import java.util.Map;

public abstract class EnhancedJavaScriptComponent extends AbstractComponent implements VariableOwner {
    private EnhancedJavaScriptCallbackHelper callbackHelper = new EnhancedJavaScriptCallbackHelper(
            this);

    @Override
    protected <T extends ServerRpc> void registerRpc(T implementation,
                                                     Class<T> rpcInterfaceType) {
        super.registerRpc(implementation, rpcInterfaceType);
        callbackHelper.registerRpc(rpcInterfaceType);
    }

    protected void addFunction(String functionName, JavaScriptFunction function) {
        callbackHelper.registerCallback(functionName, function);
    }

    protected void callFunction(String name, Object... arguments) {
        callbackHelper.invokeCallback(name, arguments);
    }

    @Override
    protected EnhancedJavaScriptComponentState getState() {
        return (EnhancedJavaScriptComponentState) super.getState();
    }

    // Support for deferred variables
    public void changeVariables(Object source, Map<String, Object> variables) {
        this.onChangeVariables(variables);
    }

    protected void onChangeVariables(Map<String, Object> variables) {
    }
}