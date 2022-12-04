# Mountain Group 105

You are a group of four weary travelers.
You have been walking for so long that you no longer have any memory of where you came from or who you are.
You cannot recall anything but the endless action of putting one foot in front of the other, traversing this empty landscape.
Suddenly, you look up, startled out of your reverie.
An imposing mountain looms before you.
Your party steps forward, drawn to it for some inexplicable reason.
You crane your neck to see if you can make out the peak, but the morning mist impedes your view.
You all know you cannot go back to wherever you came from.
You have to keep going.
You have to climb the mountain.
Maybe whatever waits for you up there will remind you of who you are… and what you’re searching for.

## Quickstart Guide

This section contains instructions on how to download and run pre-compiled Jar distribution.

First, this project requires [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html), so make sure that is installed.

Next, navigate to the [latest GitHub release](https://github.com/CSC207-2022F-UofT/course-project-group-105/releases/latest), then download and unzip the attached `course-project-group-105.zip` file.

Finally, navigate to the `bin` folder and run either `course-project-group-105.bat` (on Windows) or `course-project-group-105`.

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

on any Unix-like operating system (or the built-in IntelliJ IDEA terminal).

## Highlights (and extra hints for the TA)

- Functionality
  - All twelve (12) of our original user stories are complete.
  - In addition we've added the following extra functionality:
    - Persistence: When a battle ends your characters' stats are automatically saved and are recalled on next game open.
      Even if you lose the game your characters' stats get saved!
    - Minimap: Press 'm' to open a minimap that will show a visual representation of the map you've discovered so far!
- Code Organization
  - Code is organized by layers, `com.mg105.user_interface`, `com.mg105.interface_adapters`, `com.mg105.use_cases`, `com.mg105.entities`.
  - The `com.mg105.user_interface` package is the only package that knows anything about the graphics library, JavaFX.
  - The `com.mg105.utils` package mostly keeps track of constants.
- Testing
  - As of 246787a13d, test coverage is at 44%, 96% for `com.mg105.entities` only and 86% for `com.mg105.use_cases` only.
- Documentation
  - Current up-to-date Javadoc can be found [here](https://docs.mg105.com/).
- Extra GitHub Features Used
  - GitHub actions to make sure sensitive files (`.idea/*`) are not accidentally modified in a PR ([link](https://github.com/CSC207-2022F-UofT/course-project-group-105/actions/workflows/sanity.yml)).
  - GitHub releases for every merge into `main`, built by GitHub actions ([link](https://github.com/CSC207-2022F-UofT/course-project-group-105/releases)).
  - GitHub pages that host up-to-date Javadoc of `main`, built automatically by GitHub actions ([link](https://docs.mg105.com/)).

## Copyright

Unless mentioned otherwise, code is licensed under the GNU Affero General Public License, Version 3.0.
See the [LICENSE](/LICENSE) file for more details.

Here are the exceptions:

- `imgs/background2.jpg` by Fred Seibert is licensed under [CC BY-NC-ND 2.0](https://www.creativecommons.org/licenses/by-nc-nd/2.0/).
