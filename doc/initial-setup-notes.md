# Resources

https://metamug.com/amp/java/build-run-java-maven-project-command-line.html
https://maven.apache.org/guides/introduction/introduction-to-the-pom.html
https://maven.apache.org/pom.html
https://maven.apache.org/pom.html#properties
https://www.baeldung.com/maven-java-version

https://www.flyingmachinestudios.com/programming/how-clojure-babies-are-made-the-java-cycle/
https://clojure.org/reference/compilation
https://clojure.org/reference/java_interop#_calling_clojure_from_java
https://mvnrepository.com/artifact/org.clojure/clojure/1.10.2
https://clojure.github.io/clojure/javadoc/
https://github.com/seancorfield/depstar#aot-compilation

# Exploration log

```bash
# From ~/github
#generate simple java maven project
mvn archetype:generate -DgroupId=com.tomjkidd -DartifactId=java-bootstrap -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
cd ~/github/java-bootstrap
git init
touch readme.md
mvn compile
```

The compilation failed with an error like

```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.1:compile (default-compile) on project java-bootstrap: Compilation failure: Compilation failure:
[ERROR] Source option 5 is no longer supported. Use 7 or later.
[ERROR] Target option 5 is no longer supported. Use 7 or later.
[ERROR] -> [Help 1]
org.apache.maven.lifecycle.LifecycleExecutionException: Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.1:compile (default-compile) on project java-bootstrap: Compilation failure
```

A little googling led to https://qascript.com/mvn-compile-error-compilation-error-error-source-option-5-is-no-longer-supported-use-7-or-later/

This suggested updating the `pom.xml` to the versions I want (JDK 11)

```
<project>
  <properties>
       <java.version>1.11</java.version>
       <maven.compiler.source>11</maven.compiler.source>
       <maven.compiler.target>11</maven.compiler.target>
  </properties>
</project>
```

After that change, the compilation works as expected

```
# compile
mvn compile

# verify the class file major version
javap -v target/classes/com/tomjkidd/App.class

# execute the project
mvn exec:java -Dexec.mainClass=com.tomjkidd.App
```

Updated the project to print passed args

```java
package com.tomjkidd;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Boom shaka lakka!" );
        Arrays.asList(args).forEach(System.out::println);
    }
}
```

```
# execute with args
mvn exec:java -Dexec.mainClass=com.tomjkidd.App -Dexec.args="foo bar"
```

Next, time to get clojure and add it to `pom.xml`

```
<!-- https://mvnrepository.com/artifact/org.clojure/clojure -->
<dependency>
    <groupId>org.clojure</groupId>
    <artifactId>clojure</artifactId>
    <version>1.10.2</version>
</dependency>
```

Then to integrate it... https://clojure.github.io/clojure/javadoc/

```java
package com.tomjkidd;

import java.util.Arrays;
import clojure.java.api.Clojure;
import clojure.lang.IFn;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Boom shaka lakka!" );
        Arrays.asList(args).forEach(System.out::println);

        IFn plus = Clojure.var("clojure.core", "+");
        Object result = plus.invoke(1, 2);

        System.out.println(result);
    }
}
```
