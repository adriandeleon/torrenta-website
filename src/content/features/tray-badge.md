---
title: "Tray icon and badge"
group: "Your desktop"
order: 1
beta: false
summary: "The arrow-in-a-tray mark beside the clock, teal while something downloads, red on an error; close to the tray and keep downloading. The rate on the macOS dock icon."
---

- **A tray icon**, on by default: teal while something downloads, red while a torrent has an error, muted otherwise, with the rates and a count of what the torrents are doing as its menu's first line, then Show Torrenta and Quit. On Linux it is a StatusNotifierItem on the session bus, which GNOME (with the AppIndicator extension), KDE and others show.
- **Close to the tray**: closing the window hides it and the torrents go on; Quit ends the program. Without an icon to bring it back, closing quits instead, so the program is never unreachable.
- **A badge**: the download rate on the macOS dock icon, and the count of downloading torrents on a Linux launcher that honours Unity's badge (KDE's task manager, Dash to Dock). Cleared when nothing downloads.
