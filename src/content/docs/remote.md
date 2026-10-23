---
title: "Remote: a Transmission daemon"
description: Drive a transmission-daemon on a seedbox or a NAS from the same window, and what changes when you do.
category: Engine & network
order: 12
---

The same window can drive a `transmission-daemon` on a seedbox or a NAS instead of libtorrent
inside Torrenta. Torrenta speaks Transmission's RPC (JSON over HTTP or HTTPS, with basic
authentication), and the rest of the window works as it does locally, minus what the daemon
cannot do.

Tested against transmission-daemon 4.1.0-beta.2 (RPC 18). Anything from 4.0 (RPC 17) works,
with sequential download missing before 4.1.

## Setting up a profile

Open **Settings → Engine**, the first page of the Engine group. It lists the engine profiles:
**Local (libtorrent)** is always there. **Add…** takes:

- a **name**,
- the daemon's **URL**: `http://host:9091` is enough, the `/transmission/rpc` path is added,
- a **username** and **password**, blank when the daemon asks for none.

**Test connection** asks the daemon for its version, its RPC version and its download folder.

Then either:

- **Use at next start**: the next launch drives this profile, or
- **Switch now**: the engine restarts in place. The list empties, the old engine stops, the new
  one comes up and the list fills from it.

**File → Switch engine profile…** (also in the palette) does the same from anywhere.

While a daemon is in use, the window title names the profile and the status bar says
"Connecting to …" until it answers. When the daemon cannot be reached, the window stays up with
the reason and two buttons, **Retry** and **Switch profile…**, which are palette commands too.

The profiles are kept in `profiles.json` in the config folder: readable by you alone where the
file system has permissions, never synced, and never logged with their passwords. Which profile
starts is a setting of this machine and does not sync either.

## What is hidden

The daemon's RPC has no word for some of what libtorrent does. Torrenta hides those rather than
greying them out or imitating them:

- the torrent creator (`Ctrl+N` and its toolbar button),
- the magnet file list before adding: the Add dialog says the daemon lists the files once the
  torrent is added, and "Add now, pick files when ready" becomes plain Add and Add paused,
- the IP filter from ranges: the local list, manual bans and Ban Peer (the blocklist URL and its
  switch stay, because the daemon fetches the list itself),
- first and last pieces first, and the Add dialog's skip check and stop condition,
- upload slots, for the session and per torrent,
- the proxy, interface binding and IPv6,
- anonymous mode and counting overhead in rate limits,
- the incomplete folder, `.part` and pre-allocation,
- the limit on torrents active in all,
- super seeding, a name of your own for a torrent (`F2`) and the content layout on add (files
  and folders can still be renamed from the Files tab),
- adding and removing web seeds (the daemon lists them, but cannot change them),
- sequential download, before RPC 18.

**Open folder**, **Open file**, **Browse** and the free-space readout stay only when the daemon
is on this machine (`localhost`, `127.*` or `::1`): a seedbox's paths are its own. For **Set
Location…**, the folder is a path on the daemon's machine.

## What works

Everything else works as it does with libtorrent: pause, resume, force resume, recheck,
reannounce, remove with or without data, queue order, per-file priorities, trackers (added and
removed), per-torrent rate caps and peer limits, the details pane (files, peers, trackers, and
the piece map decoded from the daemon's bitfield), the statistics (the daemon's own counters; what
it lacks shows a dash), the Speed, Queue, Protocol and Connection pages, watch folders, RSS rules,
search, run on event and notifications.

**Categories and tags** go into the daemon's labels, one flat list: the category travels as
`category:<name>`, the tags as they are.

The daemon does not push events, so the Event Log's added, finished, removed and error lines come
from comparing one poll with the last. Something another client did shows up the same way.

## The daemon keeps its own settings

The Engine pages still show this machine's settings file. A value you change there while a daemon
is in use is sent to the daemon, and only that value: rows you leave alone never overwrite what
the daemon's administrator configured.

The Add dialog's save path starts as the daemon's download folder; a torrent sent with the folder
unchanged gets the daemon's own. Speeds are converted, since the daemon counts kilobytes of 1,000
bytes and the pages count KiB.

The daemon keeps its torrents, their resume data and their labels. Torrenta stores nothing about
them locally.
