---
title: Torrenta 0.1.0 released
description: "The first release: a keyboard-first BitTorrent client for Linux, macOS and Windows, with search, RSS rules, torrent creation and a Transmission daemon as an engine."
date: 2026-10-22
version: 0.1.0
---

Torrenta 0.1.0 is the first release. It is a BitTorrent client in which
every action is a command, reachable by a chord or by name from the command
palette (`Alt+X`), on [libtorrent](https://www.libtorrent.org), the engine
inside qBittorrent and Deluge.

## Download

From the [release page](https://github.com/adriandeleon/torrenta/releases/tag/v0.1.0):

- **macOS (Apple Silicon)**: the `macos-arm64` DMG. Intel Macs are not
  supported yet: the engine's native library is built for Apple Silicon only.
- **Windows (x64)**: the MSI.
- **Linux (x64)**: the DEB (`sudo apt install ./Torrenta-*.deb`) or the RPM
  (`sudo dnf install ./Torrenta-*.rpm`).

Each bundles its own Java runtime. The builds are unsigned: on macOS allow the
first launch under System Settings → Privacy & Security → Open Anyway, and on
Windows choose More info → Run anyway if SmartScreen warns. Verify a download
against `checksums_sha256.txt`.

## What is in it

0.1.0 is everything the roadmap planned from 0.2 to 0.9, in one release:

- **The window**: a transfer list with comfortable or compact rows, a sidebar
  of status, category, tag and tracker filters, an omnibox that filters with
  `state:seeding` or `size>4g`, runs commands and adds pasted magnets, a details
  pane that says why a torrent is not moving, and a status bar with the port,
  the DHT, the rates and the free space.
- **Adding**: an Add dialog with file priorities, free space, category, tags and
  start options, a magnet's file list fetched while it is open; drops, the
  clipboard, watch folders, and Torrenta as the program for `.torrent` files and
  `magnet:` links.
- **Managing**: categories with save paths, tags, the queue, share limits,
  per-torrent limits, speed limits with a weekly schedule, an IP filter,
  run-on-event commands and desktop notifications.
- **Finding**: a Search tab over Torznab servers and definition-file sites, and
  an RSS reader with auto-download rules and episode filters.
- **Creating**: v1, v2 and hybrid torrents, seeded the moment they are made.
- **A Transmission daemon as the engine**, for a seedbox or a NAS.
- **Six themes, six languages**, a tray icon, a statistics window, and settings
  synced through a Git repository of yours.

The [features](/#features) and the [docs](/docs) go through each, and the
[changelog](https://github.com/adriandeleon/torrenta/blob/master/CHANGELOG.md)
has the release in full.
