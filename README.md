# Simple Java Slideshow

## Release

[Latest release](https://github.com/MatthewMacDonald-1/Simple-Java-Slideshow/releases/tag/Release)

## Instructions

1. Download the [jar file](build/Simple-Java-Slideshow%20(java%20ver%2011).jar) in the build folder
2. Run via the command line using `java -jar 'Simple-Java-Slideshow (java ver 011).jar' [args]` when in the same folder.
    - Optional arg is the number of images stored in the memory. Default is 3 the minimum is 2. i.e. `java -jar '... .jar' 2`.
3. Once you have run the jar you will be asked to select a folder in which the program will search for images within (it will also search sub folders). You will also be able to set the time that image is displayed for, the default is 2,000 milliseconds (min 1,000 and max 10,000).
    - The supported image formats/extensions are `png`, `jpg`, `jpeg`, `gif`, `BMP`, `WBMP`
4. Once selected the textarea below will list the files found by the program and you will be able to start the slideshow after clicking the start button.
5. Once started the program will automatically go into full screen mode with the cursor hidden. The cursor will reappear when brought to the top of the screen as the Menu Bar appears again (Menu bar will only appear when cursor is within 50 pixels).
6. You can use the window menu to change back to windowed mode and vice versa.
