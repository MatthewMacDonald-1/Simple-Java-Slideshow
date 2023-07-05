# Simple Java Slideshow

## License

[See license](LICENSE.md)

This project is licensed under the [GNU General Public License v3.0](https://www.gnu.org/licenses/).

## Release

[Latest release](https://github.com/MatthewMacDonald-1/Simple-Java-Slideshow/releases)

## Instructions

Note: this project was built using the Java Version 11 JDK.

1. Download the [latest release](https://github.com/MatthewMacDonald-1/Simple-Java-Slideshow/releases)
2. Run via the command line using `java -jar Simple-Java-Slideshow.jar [args]` when in the same folder.
    - See options section for optional arguments.
3. Once you have run the jar you will be asked to select a folder in which the program will search for images within (it will also search sub folders). You will also be able to set the time that image is displayed for, the default is 2,000 milliseconds (min 1,000 and max 10,000).
    - The supported image formats/extensions are `png`, `jpg`, `jpeg`, `gif`, `BMP`, `WBMP`
4. Once selected the textarea below will list the files found by the program and you will be able to start the slideshow after clicking the start button.
5. Once started the program will automatically go into full screen mode with the cursor hidden. The cursor will reappear when brought to the top of the screen as the Menu Bar appears again (Menu bar will only appear when cursor is within 50 pixels).
6. You can use the window menu to change back to windowed mode and vice versa.

## Options

- Launch the app with no setting UI and searches its directory for images. Use `no_ui` to use this mode.
- You can set an cache size from the command line using `cache num` where `num` is an integer (will crash unless positive).
- You can also set a custom time delay using the command line using `time_day num` where `num` is a non negative integer in milliseconds. OD not this override is only used when using the `no_ui` command line option.