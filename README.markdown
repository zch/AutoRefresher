AutoRefresher
=============
AutoRefresher is a plugin for eclipse that implements automatic refreshing of file resources for Mac OS X using system events (through the [NativeFSEvents](https://github.com/zch/NativeFSEvents) library). The plugin replaces the default filesystem polling used by eclipse on these operating systems.

Installation
------------
Download the bundle from http://vaadin.com/addon/autorefresher and copy the contents of the plugins directory to your eclipse/dropins/ directory.

Start eclipse and open Preferences -> General -> Workspace and check the "Refresh using native hooks or polling" checkbox.

Enjoy!