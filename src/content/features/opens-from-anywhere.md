---
title: "Opens from anywhere"
group: "Adding & creating"
order: 2
beta: false
summary: "The program for <code>.torrent</code> files and <code>magnet:</code> links: a double-click, a browser's link, a drop on the window, or the magnet already on your clipboard."
---

- **The clipboard.** When the clipboard holds a magnet or a link to a `.torrent`, the status bar says so and `Ctrl+V` adds it. The welcome screen offers it too, every time the window gets the focus.
- **Drag and drop** files, magnets and links onto the window.
- **The operating system's handler.** The packages declare `.torrent` files and `magnet:` links, and Settings → General → Operating system makes Torrenta the default at each start: `xdg-mime` on Linux, the user's file associations on Windows, the bundle's declaration on macOS.
- **One instance.** A second Torrenta started with a file, a magnet or a URL hands it to the running one over a local socket and is gone within a second; the window comes to the front, out of the tray if it was hidden there, and opens the Add dialog.
- **The command line**: `torrenta "magnet:?xt=…"` or a path, the same way.
