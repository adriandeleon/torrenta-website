---
title: "A Transmission daemon as the engine"
group: "Engine & network"
order: 2
beta: false
summary: "Drive <code>transmission-daemon</code> on a seedbox or a NAS from the same window. What the daemon cannot do is hidden, never greyed out or faked."
---

Settings → Engine lists the engine profiles: Local (libtorrent) is always there, and any number of remote `transmission-daemon`s can join it, each a name, a URL, a user and a password, with Test connection for the daemon's version.

- **Switch in place**: File → Switch engine profile… restarts the engine; the list empties and fills from the new one.
- **Everything the RPC can do works**: pause, resume, force resume, recheck, reannounce, remove with or without data, file priorities, trackers, per-torrent caps, categories and tags (as the daemon's labels), the detail pane with the pieces map, statistics, watch folders, RSS rules, search, run-on-event and notifications.
- **What it cannot is hidden**: the torrent creator, a magnet's file list before adding, a few Add-dialog and Settings choices. A capability missing takes its menu item, its chord and its palette entry with it.
- **The daemon keeps its own settings.** A value you change while it is in use is sent, and only that value, so nothing you left alone overwrites what the daemon's administrator set.

Profiles stay on this machine, readable by you alone, and their passwords are never logged. Tested against Transmission 4.1; 4.0 works too, without sequential download.
