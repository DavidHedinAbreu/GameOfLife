# GameOfLife
An Java implementation of Conway's Game of Life with a fancy GUI.

RUNNING THE EXECUTABLE JAR:

If you launch the executable jar by double-clicking on the desktop icon, and your Java Runtime version is out of date, nothing will happen.
However, if you run Gameoflife.jar from the command prompt, using 

java -jar Gameoflife.jar

there will be an error message about the version number:

Error: LinkageError occurred while loading main class Game
        java.lang.UnsupportedClassVersionError: Game has been compiled by a more recent version of the Java Runtime (class file version 61.0), this version of the Java Runtime only recognizes class file versions up to xx.x

Solution: update your version of Java.

Another error seen when executing the jar from the command prompt is:

Error: A JNI error has occurred, please check your installation and try again

In this case, you may have environment variables that contain a path to the wrong version of the Runtime, and duplicate Runtimes, one in your Java Runtime Environment and one in your Java Development kit.
Solution: uninstall the JRE, so that you have only one version of the Runtime.  The path in the environment variables should be automatically updated.
Alternate: edit the path in the environment variables.  

Both of these issues will prevent running the executable, and both can be avoided by having only one, up-to-date, version of the Runtime environment present on your machine (for example, having only one copy of the most up-to-date JDK, and uninstalling any JREs and older JDKs).

NOTES FOR DEVELOPMENT:
Consider making "time" a static, volatile variable since multiple Animation threads may access it. There is a little "stutter" in the time (generation) value when buttons are pressed.
Package statement not working, probably because of my poor understanding of packages.
Add "edit" button and Editor class for the initial generation.
Would it be fun to have competing players edit their half of the arena in a shared version accessible online, to see whose gliders/guns/trains reach the opposite side of the arena first?
Yes I think so!  Need rules for interactions (who wins when entities interact?, etc.).
The "edit" function might be for the whole arena, or just the player's half, and then the game would begin once both players have finalized their edits, preventing knowledge of the other player's constructions until game begins.
