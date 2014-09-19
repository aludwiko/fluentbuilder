#FluentBuilderGenerator BLUECARAT Edition
The FluentBuilderGenerator helps to implement the Builder Pattern for object creation by generating builder classes for you. With help of these generated classes it is possible to create custom objects in a fluent syntax style. The generator uses Maven's Annotation Processing Tool and is perfectly suited for test object creation.

The BLUECARAT Edition is a fork of the original FluentBuilderGenerator project written by A. Ludwikowski, which can be found at: https://github.com/aludwiko/fluentbuilder

##Features
* generating of builder classes
* creation of custom objects in fluent style syntax
* setting of inherited and hidden fields (BLUECARAT Edition)
* invocation of a particular constructor (BLUECARAT Edition)
* support for classes with no default constructor without the need to modify the generated code (BLUECARAT Edition)

GitHub Repository: https://github.com/bluecarat/fluentbuilder

GitHub Wiki: https://github.com/bluecarat/fluentbuilder/wiki


##Getting Started

###Setup Code Generation

To use the FluentBuilderGenerator for generating builder classes you need to add it as a dependency to your Maven POM. After that you must also add the maven-processor-plugin to the build plugins.

```xml
<plugin>
   <groupId>org.bsc.maven</groupId>
   <artifactId>maven-processor-plugin</artifactId>
   <version>2.0.5</version>
   <executions>
      <execution>
         <id>generate-fluentbuilders</id>
         <phase>generate-test-sources</phase>
         <goals>
            <goal>process</goal>
         </goals>
         <configuration>
            <compilerArguments>-Adebug=true -AuseIndefineArticles=true -encoding UTF-8</compilerArguments>
            <processors>
               <processor>info.ludwikowski.fluentbuilder.processor.FluentBuilderProcessor</processor>
            </processors>
            <sourceDirectory>src/test/java/de/bluecarat/dummy/example</sourceDirectory>
            <outputDirectory>target/generated-sources</outputDirectory>
         </configuration>
      </execution>
   </executions>
</plugin>
```

Now add a @GenerateBuilder annotation (and its import) to every class for
which you want to generate a builder.

```java
import info.ludwikowski.fluentbuilder.annotation.GenerateBuilder;

@GenerateBuilder
public class SampleClass {
...
```

With these settings builder classes for every annotated class in "src/test/java/de/bluecarat/dummy/example" and its
subdirectories will be generated. This will take place during the "generate-test-sources" phase. The generated builders can be found in "target/generated-sources". The "compilerArguments" tag can be used to set additional parameters and settings for the generator which are further explained in the GitHub wiki.

###How to use the generated code

At first you have to make sure that your "target/generated-sources" (or custom directory) is in your project's build path during the test phase. The build-helper-maven-plugin can help with that:

```xml
<plugin>
   <groupId>org.codehaus.mojo</groupId>
   <artifactId>build-helper-maven-plugin</artifactId>
   <version>1.9</version>
   <executions>
      <execution>
         <id>add-test-source</id>
         <phase>generate-test-sources</phase>
         <goals>
            <goal>add-test-source</goal>
         </goals>
         <configuration>
            <sources>
               <source>target/generated-sources</source>
            </sources>
         </configuration>
      </execution>
   </executions>
</plugin>
```

After that you can statically import the necessary builders into your test class.

```java
import static de.bluecarat.dummy.example.SampleClassBuilder.aSampleClass;
```

Now you can create custom ExampleClass objects by chaining your parameters together and initializing it with the build() method at the end of your chain.

```java
final SampleClass testObject = aSampleClass.withName("Test").withId(0).build();
```

More information can be found in the GitHub wiki at:

##Changelog
1.0.13:

- initial fork
- basic refactoring of the original code
- added support for inherited fields
- added support for classes without default constructor
- added support for selecting a particular constructor
- extended support for classes without default constructor

	
##Company Information
Forked by BLUECARAT AG
 
values at work.

Albin-Köbis-Straße 4

51147 Köln

info@bluecarat.de

http://www.bluecarat.de/

http://www.hochkaraeter.de/ 

	
##License Information
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

