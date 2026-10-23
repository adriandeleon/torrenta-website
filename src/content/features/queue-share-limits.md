---
title: "Queue, share and speed limits"
group: "Watching & managing"
order: 2
beta: false
summary: "How many torrents run at once, a ratio or seeding time that pauses or removes, per-torrent caps, and alternative speed limits on a weekly schedule."
---

- **The queue**: how many torrents download, seed and run in all (0 for no limit), and whether a slow torrent holds a slot. Move the selection to the top, up, down or to the bottom with `Ctrl+Shift+Home`, `Up`, `Down` and `End`; Force Resume (`Ctrl+Shift+U`) ignores the queue.
- **Share limits**: a ratio and a seeding time; a finished torrent that reaches either is paused or removed, as you choose. libtorrent only ranks seeds by these limits, so Torrenta applies them itself, once a second, and says so in the log.
- **Per torrent** (`Ctrl+L`): download and upload caps, connections, upload slots, first and last pieces first, and a share limit of its own in place of the global one.
- **Speed limits**, and **alternative limits** switched by hand (`Ctrl+Shift+J`, or the status bar's "Alt speed") or by a weekly schedule, overnight included.

"Paused" always means paused by you: a torrent the queue is holding back is queued, never paused, and the queue never resumes what you stopped.
