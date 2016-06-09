EnhacedJavaScript for vaadin
============================

What is EnhancedJavaScript for vaadin?
--------------------------------------

EnhancedJavaScriptComponent is a drop-in replacement for `AbstractJavaScriptComponent`. It supports transmission of
state deltas between the server and client rather than transmitting the entire state object. This reduces
the payload size for each component update request/response. It also incorporates and extends the features of
[JavascriptComponentPlus](https://github.com/akquinet/JavascriptPlusForVaadin/blob/master/README.md) - allowing 
you to set deferred variables to be transmitted from client to server.

Workflow
--------

To package and install the addon, run the following commands. This will run tests and install the resulting addon jar
in your local Maven repository cache.

```bash
mvn package -DskipTests=true
mvn install
```

To run tests only, run "mvn test".

To run the test UI in interactive mode run `UIRunner.main()` (found under the test directory).

Debugging client side code
  - run "mvn vaadin:run-codeserver" on a separate console while the test UI is running
  - activate Super Dev Mode in the debug window of the application
  
Project setup
-------------

For use in Maven-based projects add the following dependency to your application's pom file:

```xml
<dependency>
  <groupId>com.emuanalytics</groupId>
  <artifactId>vaadin-enhancedjavascript</artifactId>
  <version>0.1.1</version>
</dependency>
```

You will also need to include the widgetset in your project by including it in your own application-specific
widget set:

```xml
<inherits name="com.emuanalytics.vaadin.enhancedjavascript.EnhancedJavascriptWidgetset" />
```

Usage
-----
Your Javascript component should extend `com.emuanalytics.vaadin.enhancedjavascript.EnhancedJavaScriptComponent`
rather than Vaadin's supplied `AbstractJavaScriptComponent`.

Your Javascript state class should extend `com.emuanalytics.vaadin.enhancedjavascript.EnhancedJavaScriptComponentState`
rather than Vaadin's supplied `AbstractJavaScriptComponentState`.

Other than this, creating a component with EnhancedJavascript is exactly the same as creating an 
AbstractJavaScriptComponent-based component.

###Extra features
An additional JavaScript method is provided on your connector object - `getStateDeltas()`. This is designed to be used
in your `onStateChange` method and returns an object containing just the deltas (the properties that have been updated)
in response to the state change event.

To set a deferred variable, call the `setDeferredVariable(variableName, value, immediateFlag)` method of your connector.
This works in the same way as the method provided by [JavascriptComponentPlus](https://github.com/akquinet/JavascriptPlusForVaadin/blob/master/README.md)
with the added feature of allowing immediate transmission of the change event to the server.


Demo
----
Look in the test directory for a sample component.

Acknowledgements
-----------------
This addon is based in part on
[JavascriptComponentPlus](https://github.com/akquinet/JavascriptPlusForVaadin/blob/master/README.md)
from Sebastian Rothbucher.

The testing framework is supported by [Addon Test Helpers](https://vaadin.com/directory#!addon/addon-test-helpers)
from Matti Tahvonen.

