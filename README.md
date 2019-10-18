misc-tools [![License MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE) [![Build Status](https://travis-ci.com/adrianoluis/misc-tools.png?branch=master)](https://travis-ci.com/adrianoluis/misc-tools)
============

## Suporte
Se você tiver algum problema ou sugestão, abra um ticket [aqui](https://github.com/adrianoluis/misc-tools/issues).

## Como Usar

Basta colocar o seguinte trecho de código nas configurações de compilação apropriada:

#### Apache Maven

##### Repositório
```xml
<repositories>
    <repository>
        <id>misc-tools-mvn-repo</id>
        <url>https://raw.githubusercontent.com/adrianoluis/misc-tools/mvn-repo/</url>
        <releases>
            <enabled>true</enabled>
            <updatePolicy>never</updatePolicy>
        </releases>
    </repository>
</repositories>
```

##### Dependência
```xml
<dependency>
    <groupId>me.pagar</groupId>
    <artifactId>misc-tools</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Gradle/Grails

##### Repositório
```groovy
repositories {
    maven { url 'https://raw.githubusercontent.com/adrianoluis/misc-tools/mvn-repo' }
}
```

##### Dependência
```groovy
compile('me.pagar:misc-tools:1.0.0') {
    transitive = true
}
```

#### Apache Buildr

##### Dependência
```
'me.pagar:misc-tools:jar:1.0.0'
```

#### Apache Ivy

##### Dependência
```xml
<dependency org="me.pagar" name="misc-tools" rev="1.0.0">
    <artifact name="misc-tools" type="jar" />
</dependency>
```

#### Groovy Grape

##### Dependência
```groovy
@Grapes(
  @Grab(group='me.pagar', module='misc-tools', version='1.0.0')
)
```

#### Scala SBT

##### Dependência
```scala
libraryDependencies += "me.pagar" % "misc-tools" % "1.0.0"
```

#### Leiningen

##### Dependência
```clojure
[me.pagar/misc-tools "1.0.0"]
```
