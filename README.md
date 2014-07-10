# YoTWo

An social app that allows users to send and receive short custom voice messages.


Requirements:
----------------------

**(For the Android app):**

* Java JDK 1.7
* Android SDK, API levels 18-19, and HAXM
* IntelliJ 13

**(For the Rails backend server)**

* Ruby 2.1.1
* Bundler
* Postgres

Installation Instructions
-----------------------

_All instructions are for Mac OS X_

### Java 1.7
Check your version with `java -version`

> java version "1.7.xxx" <br>
> Java(TM) SE Runtime Environment (build 1.7.xxx)

Download [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) if you don't have it

### Android SDK
While there are other ways, I recommend you use [brew](http://brew.sh/) to install it. Easy and fast.

`brew install android-sdk`

Add the following to your `~/.bash_profile`. `export ANDROID_HOME=/usr/local/opt/android-sdk`

Verify it installed correctly with `which android` and you should see
> /usr/local/bin/android

If you don't see that, it probably means that your brew home is not on your PATH. Add this to your `~/.bash_profile`. 
`export PATH=$PATH:/usr/local/bin` (Don't forget to reload it after editing.)

### Android APIs
Open up the Android SDK Manager with `android`

Download/install the latest versions of the following:

* Tools
    * Android SDK Tools
    * Android SDK Platform-tools
    * Android SDK Build-tools
* Android 4.4.2 (API 19)
    * All of it is fine, but you don't really need the Android Wear System Image, ARM EABI System Image, Google APIs (ARM System Image), Glass Development Kit Preview
    * You can always download this stuff later if you want it
* Android 4.3 (API 18)
    * All of it
* Extras
    * Android Support Repository
    * Android Support Library
    * Intel x86 Emulation Accelerator (HAXM installer)

### HAXM
Now install HAXM. HAXM is a hardware accelerator. Without it, your emulators will run..... really...... slowly.......

The SDK Manager downloaded the installer to the "extras" directory of the main SDK directory. If you installed it 
with brew, then `cd /usr/local/Cellar/android-sdk/22.6.2/extras/intel/Hardware_Accelerated_Execution_Manager`. Extract 
and run the "dmg" installer there. Use the default options during the installation process.

[More info](https://software.intel.com/en-us/android/articles/installation-instructions-for-intel-hardware-accelerated-execution-manager-mac-os-x).

### IntelliJ 13
IntelliJ has pretty awesome Android support. I'm assuming everyone already has this or knows how to download it.

_**The following instructions for Ruby, Bundler, and Postgres are only needed to run the backend Rails server that the 
Android app talks to. I suppose this is optional, but you may clutter the prod server on Heroku with your test data if you don't run it locally during development.**_

### Ruby 2.1.1
Again, there are other ways to install Ruby, but I like [rvm](https://rvm.io) so I'll go with that here.

First, install rvm by following the instructions [here](https://rvm.io/rvm/install).

Now you can install Ruby with `rvm install ruby-2.1.1`

### Bundler
Bundler is a manager for Ruby gems and will be used to install the dependencies for the Rails app. `gem install bundler`

### Postgres
I recommend using the Postgres app. Download [here](http://postgresapp.com/).

Once its installed, if you want to be able to run things like "psql" from the command line, then add this to 
your `~/.bash_profile`: `export PATH=$PATH:/Applications/Postgres.app/Contents/Versions/9.3/bin`.

Note: that path was copied from the [Postgres App docs](http://postgresapp.com/documentation/cli-tools.html), and its 
been known to actually be incorrect in the past. Check that it works with `which psql`


Create an Emulator
--------------------------------
First, lets create an emulator. This will allow you to deploy and test the app without hooking up an actual Android device.

Open the Android Virtual Device Manager with `android avd`

1. Click "New..."
2. Name it
3. Choose a device: Nexus 7
4. Target: Android 4.4.2 - API Level 19
5. CPU: Intel/Atom x86
6. Check "Hardware keyboard present"
7. Skin: "Skin with dynamic hardware controls"
8. No camera
9. Default storage, SD Card, and RAM
10. Emulation Options: Check "Use Host GPU" (This will hook up HAXM!)

Now you can start the emulator from the terminal with `emulator @<name>` 


Verify your Android Setup
------------------------------------
A good way to verify that all your Android dependencies are installed correctly and you are able to integrate IntelliJ is to use a bootstrap project.

Follow the instructions at [robolectric/deckard-gradle](https://github.com/robolectric/deckard-gradle) to download the 
run the Robolectric-Deckard-Gradle sample app.

You should be able to build and test things from the terminal using `./gradlew` commands, or from IntelliJ. :)

### IMPORTANT: Some IntelliJ Notes

* Don't skip the "IntelliJ Extra Step" in those instructions. Otherwise, it won't work.
* After editing your "Test output path" as instructed, you should do "Build > Clean Project" and then "Build > Make Project" before trying to run tests.
* If you see a `android.content.res.Resources$NotFoundException` error when running the unit test, it means the JUnit 
test runner isn't looking for your Android resources in the right path. To fix it:
    * Run > Edit Configurations...
    * Remove any JUnit configurations you have
    * Defaults > JUnit
    * Choose "Test Kind: All in directory"
    * Working directory needs to be "<your project path>/app/src/main"
    * Save it
* You can deploy to an Android device or emulator from IntelliJ. Check the Launch Configurations for the little green Android
man. You can even debug it, capture logs, and take screenshots all from inside IntelliJ! Awesome!

_The worst part... When using gradle, every time IntelliJ does a "Gradle Sync" some of your project settings are lost. Most significantly,
 the ordering of your Module Dependencies. So after a "Gradle Sync," when you run tests you will get the "Junit stub!" error
 and will have to re-order your dependencies again as outlined by the deckard-gradle instructions. Really annoying but 
 I don't know a way to permanently fix it yet. LAME INTELLIJ!_ :(


Setup Instructions
---------------------------------
Now that everything is installed, you should be able to checkout and run the apps.

The Android app: <github url once we change the name>
You will have to follow the same fiddly steps as you did when setting up the Deckard-Gradle project in IntelliJ. Super lame.

The Rails server: <github url once we change the name>
Follow the project README file to setup and run the server locally.