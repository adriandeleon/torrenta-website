---
title: Torrenta 0.4.1 released
description: "A fix: with the .part suffix on, a restart downloaded finished torrents again. 0.4.1 stops it and repairs the torrents it caught."
date: 2026-10-28
version: 0.4.1
---

Torrenta 0.4.1 fixes one bug, and everyone on 0.4.0 should update.

With the `.part` suffix on (**Settings → Adding torrents**), a finished torrent that had a
folder of its own came back from a restart at 0 % and started downloading again, into new
`.part` files beside the complete ones. libtorrent saves a file whose suffix has come off
under a name it then looks for in the wrong folder. 0.3.0 had the bug too, but 0.4.0's first
restart after a batch of downloads made it obvious.

**Your files were never touched.** The complete copies stayed where they were, under their own
names.

## If it happened to you

1. Install 0.4.1 and start it once. Each torrent the bug caught is found complete under its
   own names, its pieces are checked, and it comes back as it was (a large one takes a few
   minutes to check).
2. Open **View → Debug Log**. For each repaired torrent it names the leftover `.part` files.
   Once the torrent shows complete, delete them to get the space back.

## Download

From the [release page](https://github.com/adriandeleon/torrenta/releases/tag/v0.4.1):

- **macOS (Apple Silicon)**: the `macos-arm64` DMG.
- **Windows (x64)**: the MSI.
- **Linux (x64)**: the DEB (`sudo apt install ./Torrenta-*.deb`) or the RPM
  (`sudo dnf install ./Torrenta-*.rpm`).

Each bundles its own Java runtime. The builds are still unsigned: on macOS
allow the first launch under System Settings → Privacy & Security → Open
Anyway, and on Windows choose More info → Run anyway if SmartScreen warns.
Verify a download against `checksums_sha256.txt`.

What else is new since 0.3.0 is in the [0.4.0 post](/news/2026-10-27-torrenta-0-4-0-released).
