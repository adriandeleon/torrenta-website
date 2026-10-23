---
title: "Watch folders"
group: "Watching & managing"
order: 3
beta: false
summary: "Drop a <code>.torrent</code> or a <code>.magnet</code> file into a folder and it is added without a dialog, with that folder's save path, category and start."
---

Settings → Watch folders lists the folders to watch. For each:

- **what becomes of the file**: deleted, or renamed `.added`;
- **where the torrent saves**: the download folder, the watch folder itself, or another folder (the category's, when the category has one);
- **its category**, and whether it **starts paused**.

A file is read once its size has held still, so a download still being written is never half-read. A `.magnet` file holds one magnet link per line, as qBittorrent reads them. A file that is not a torrent is renamed `.invalid` and reported once. The folders are watched by the operating system's notifications, with a look every minute as a safety net.
