---
title: Importing from another client
description: Bring torrents over from qBittorrent, Transmission or Deluge at their progress, with their categories, tags and names, without a check.
category: Torrents
order: 3.5
---

**File → Import from Another Client…** (also in the palette) reads another BitTorrent client's
own files and adds its torrents to Torrenta as they were: at their progress, without checking the
data again.

## Where it looks

The dialog lists the clients it finds on this machine, Flatpak installs included:

- **qBittorrent**: its `BT_backup` folder, each torrent's `.fastresume` and `.torrent`, and its
  `categories.json`.
- **Transmission**, the daemon or the app: its `torrents` and `resume` folders (3.x and 4.x
  layouts).
- **Deluge**: its `state` folder and the Label plugin's labels.

Or choose a folder of your own: a copy of another machine's, say.

## What the table says

Each torrent is listed with what will happen to it, and a tick where the plan advises importing it:

- **Complete or part done**: it comes over at its progress, without a check. A stopped one stays
  stopped.
- **Files that do not match** what the other client recorded (a truncated file, a missing one):
  it is checked as it is added and comes up short rather than wrong.
- **Already in Torrenta**: left alone, and cannot be ticked.
- **Still being written** by the other client: an incomplete torrent is not imported while the
  other client runs, since two programs writing one file corrupt it. Close the other client and
  look again.

What comes with each torrent: its category (and qBittorrent's folder for it; Deluge's label
becomes the category), its tags, the name you gave it and the names you gave its files, and its
own share limits.

When the import is done, the [Event Log](/docs/transfer-list#the-status-bar) has a summary.

## qBittorrent's SQLite storage

qBittorrent can keep its torrents in a SQLite database (`torrents.db`) instead of files. Torrenta
does not read it; the dialog says so and how to switch back: in qBittorrent, **Tools → Options →
Advanced → Resume data storage type** to Fastresume files, restart it so it writes them, quit it,
and choose its folder again.

## Limits

- Imports go into libtorrent. With a [Transmission daemon](/docs/remote) as the engine, the
  command is hidden: adding there would mean a full check, which is not the promise.
- A magnet the other client had not yet got the file list for comes over as its link and fetches
  the files here.
- Torrenta only reads the other client's files. Remove the torrents there once you are happy
  with them here, keeping the data, which Torrenta now uses where it is.
