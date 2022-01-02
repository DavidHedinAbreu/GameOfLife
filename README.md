# GameOfLife
An Java implementation of Conway's Game of Life with a fancy GUI.

RUNNING THE EXECUTABLE JAR:

If you launch the executable jar "Gameoflife.jar" by double-clicking on the desktop icon, nothing may happen if your only Java Runtime version is out-of-date, or if your system is able to find only an out-of-date version of the Java runtime (although you have several versions installed, including an up-to-date version).
To see the error that is preventing execution, you can run Gameoflife.jar from the command prompt, using 

java -jar Gameoflife.jar

If you try this "verbose" option for running the executable, there may be an error message about the version number:

Error: LinkageError occurred while loading main class Game
        java.lang.UnsupportedClassVersionError: Game has been compiled by a more recent version of the Java Runtime (class file version 61.0), this version of the Java Runtime only recognizes class file versions up to xx.x

Solution: install the latest version of Java.

Another error may pop up regarding the Java Native Interface (JNI):

Error: A JNI error has occurred, please check your installation and try again

In this case, you may have environment variables that contain a path to the wrong (lower) version of the Runtime, although you have duplicate Runtimes installed as part of your Java Runtime Environment (JRE) and one in your Java Development Kit (JDK), one of which may indeed contain a Runtime version that is up-to-date.
Solution: install the latest version of the JDK (or JRE).  Then uninstall all versions of the JRE and JDK except the most up-to-date, so that you have only one version of Runtime.  The path in the environment variables should be automatically updated.  
Alternate: edit the path in the environment variables to point to the most up-to-date version of Runtime.  

Both of these issues will prevent execution, and both can be avoided by having only one, up-to-date, version of the Runtime environment present on your machine.

NOTES FOR DEVELOPMENT:

Consider making "time" a static, volatile variable since multiple Animation threads may access it. There is a little "stutter" in the time (generation) value when buttons are pressed.
Package statement not working, probably because of my poor understanding of packages.
Add "edit" button and Editor class for the initial generation.
Would it be fun to have competing players edit their half of the arena in a shared version accessible online, to see whose gliders/guns/trains reach the opposite side of the arena first? Yes I think so!  Need rules for interactions (who wins when entities interact?, etc.).
The "edit" function might be for just the player's half, and then the game would begin once both players have finalized their edits, preventing knowledge of the other player's constructions until game begins.
