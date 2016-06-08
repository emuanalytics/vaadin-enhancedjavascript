EnhacedJavaScript for vaadin
============================

What is EnhancedJavaScript for vaadin?
--------------------------------------

EnhancedJavaScriptComponent is a drop-in replacement for `AbstractJavaScriptComponent`. It supports transmission of
state deltas between the server and client rather than transmitting the entire state object. This drastically reduces
the payload size for each component update request/response. It also incorporates and extends the features of
[JavascriptComponentPlus](https://github.com/akquinet/JavascriptPlusForVaadin/blob/master/README.md) - allowing 
setting deferred variables to be transmitted from client to server.

Workflow
--------

To package and install the addon, run "mvn install". This will run tests and install the resulting addon jar
in your local Maven repository cache.

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
  <version>0.1.0-SNAPSHOT</version>
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

