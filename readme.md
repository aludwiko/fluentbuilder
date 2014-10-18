#FluentBuilderGenerator

The FluentBuilderGenerator generate builders (based on the Builder Pattern) for object creation for unit testing. With help of these generated classes it is possible to create custom objects in a fluent syntax style. The generator is implementation of javax.annotation.processing.AbstractProcessor
 from [Annotation Processing Tool](http://docs.oracle.com/javase/7/docs/technotes/guides/apt/) and it can be used with Maven, Gradle, Ant or just Eclipse.

After the fork from [BLUECARAT AG](https://github.com/bluecarat/fluentbuilder) many changes was merged and released in 1.1.0 version.

##Features
* generating of builder classes
* creation of custom objects in fluent style syntax
* setting of inherited and hidden fields (BLUECARAT Edition)
* invocation of a particular constructor (BLUECARAT Edition)
* support for classes with no default constructor without the need to modify the generated code (BLUECARAT Edition)

GitHub Repository: https://github.com/aludwiko/fluentbuilder

##Setup Code Generation

Checkout project Wiki: https://github.com/aludwiko/fluentbuilder/wiki

##Changelog
###1.1.0
NEW:
 * support for classes without default constructors #14
 * support for inheritance #13 (overridden/hidden fields are NOT supported)
FIXED:
 * compiler arguments must be in single line #12
