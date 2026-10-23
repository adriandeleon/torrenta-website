---
title: Introduction
description: What Torrenta is, what it can do, and how the documentation is organized.
category: Getting started
order: 1
---

**Torrenta** is a keyboard-first, cross-platform BitTorrent client. Every action is a command in
the command palette, most have a key of their own, and the menus run the same commands, so you
can work without touching the mouse or with it. It is free and open source under the MIT License.

It runs on Linux (x64), macOS (Apple Silicon) and Windows (x64). The installers bundle their own
Java runtime, so there is nothing else to install. Under the window is
[libtorrent](https://www.libtorrent.org) 2.x, the engine behind qBittorrent and Deluge, or, if
you prefer, a `transmission-daemon` on a seedbox or a NAS.

Version 0.1.0, released on 2026-10-22, is the first release. Downloads are on the
[releases page](https://github.com/adriandeleon/torrenta/releases).

## What is in it

- **One window.** A transfer list with live columns, a sidebar of filters with counts, a details
  pane that says why a torrent is not moving, and a status bar that shows the port, DHT, rates and
  free space, and keeps a history of what it said.
- **Many ways in.** `.torrent` files, magnet links, links to `.torrent` files, drag and drop, the
  clipboard, the command line, a double-clicked file or a browser's magnet link, and watched
  folders. All of them go through one Add dialog with a file tree, priorities and free space.
- **The omnibox.** One box filters the list (`state:seeding size>4g -tag:keep`), runs a command
  after `>`, or adds whatever you paste.
- **Organisation.** Categories with their own save paths, tags, queue order, per-torrent limits,
  share limits that pause or remove, and global and scheduled speed limits.
- **Finding torrents.** A Search tab over your Prowlarr or Jackett server and over definition
  files for sites without an API, and an RSS reader with qBittorrent-style auto-download rules.
- **Creating torrents.** v1, v2 or hybrid, with trackers in tiers, web seeds and a source tag.
- **Automation.** Run a program when a torrent is added, finishes or is done seeding, and show
  desktop notifications.
- **Network control.** Port and UPnP/NAT-PMP, proxy, encryption, DHT/PEX/LSD/uTP, and an IP
  filter fed by a blocklist URL.
- **A desktop citizen.** One instance per settings folder, a tray icon, a download badge, six
  themes, six interface languages, and settings sync through a Git repository of your own.

## Start here

- **[Getting Started](/docs/getting-started)**: install Torrenta, add a first torrent, and learn
  the handful of keys worth knowing.
- **[Keyboard](/docs/keyboard)**: how the keys are organised and the ones that work inside
  dialogs and tabs. The full command list is on the [Keybindings](/keybindings) page.

## By area

Torrents:

- [Adding torrents](/docs/adding-torrents): every way in, and the Add dialog.
- [The transfer list](/docs/transfer-list): rows, columns, the omnibox, the sidebar and the
  status bar.
- [The details pane](/docs/details-pane): Overview, Files, Peers, Trackers, Log and Speed.
- [Creating torrents](/docs/creating-torrents): the New Torrent dialog.
- [Categories, queue and limits](/docs/queue-and-limits): filing, queueing, share and speed
  limits.

Finding torrents:

- [Search](/docs/search): Torznab providers and definition files.
- [RSS and auto-download rules](/docs/rss): feeds, articles and rules.

Automation:

- [Watch folders and automation](/docs/automation): watched folders, run on event,
  notifications.

Engine and network:

- [Connection, privacy and the IP filter](/docs/connection): the Connection, Protocol and IP
  filter pages.
- [Remote: a Transmission daemon](/docs/remote): drive a seedbox from the same window.

Customization:

- [Settings, themes and sync](/docs/settings): the Settings window, appearance, languages, the
  tray, settings sync and the files Torrenta keeps.
- [Keyboard](/docs/keyboard).

Help:

- [FAQ](/docs/faq).
