# Mountain Group 105

TODO: Short description

## Quick Start Guide

TODO

## Build (and Run) from Source

### IntelliJ IDEA (IDE) Instructions

First download the source code by going to `File > New > Project from Version Control...`, set `Version control:` to `Git`, and the `URL:` to `https://github.com/CSC207-2022F-UofT/course-project-group-105.git`.
Then press `Clone`.

Next, if IntelliJ does not immediately recognize the fact this is a Gradle project (you can tell by the lack of a `Gradle` tab on any of the edges), navigate to the `build.gradle` file in the IDE.
An icon with an elephant should appear, click it.
There should now be a `Gradle` tab on one of the edges.

Finally, open the `Gradle` tab and navigate to `course-project-group-105 > Tasks > application` and double-click run.

### Command-Line (CLI) Instructions

First, this project requires [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html), so make sure that is installed.

Next clone the repository and switch to the directory which can be done by

```sh
git clone https://github.com/CSC207-2022F-UofT/course-project-group-105.git
cd course-project-group-105
```

Finally, this is a standard Gradle project, so to start the application simply run

```sh
gradlew.bat run
```

in the Windows CMD, or

```sh
./gradlew run
```

on any Unix-like operating system.
