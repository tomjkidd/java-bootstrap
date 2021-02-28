# java-bootstrap

A sample repository showing a minimal java setup, used to test deployment of other packages

## Usage

```
# clean up
mvn clean

# run tests
mvn test

# compile the project
mvn compile

# execute the project
mvn exec:java -Dexec.mainClass=com.tomjkidd.App -Dexec.args="foo bar"

# install in local maven repo
mvn install

# deploy to github packages
mvn deploy
```
