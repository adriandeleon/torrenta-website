---
title: "Battery and metered networks"
group: "Engine & network"
order: 5
beta: false
summary: "On battery, or on a network paid by the byte, use the alternative speed limits or pause the torrents, and carry on when it ends."
---

A laptop on a train should not seed at full speed over a phone's hotspot. **Settings → Speed → Battery and network** decides what happens:

- **On battery**: while the computer runs on its battery, the alternative speed limits, or every running torrent paused and resumed when it is plugged in again.
- **On a metered network**: the same choice while the network is one the system marks as paid by the byte (a phone's hotspot, a capped plan).
- **The status bar says why**: "Alt speed: on battery".
- **Your switch holds.** Turning the alternative limits on or off by hand holds until the schedule, the battery or the network next changes, as in qBittorrent.

The switch is this machine's and no longer travels with Settings sync. The state is read from UPower and NetworkManager on Linux, `pmset` on macOS and the system on Windows. macOS has no way to tell a metered network, so that row is not offered there. Hidden for a remote engine. See [Categories, queue and limits](/docs/queue-and-limits#battery-and-metered-networks).
