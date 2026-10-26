---
title: The details pane
description: The selected torrent's header, the Overview with its diagnosis, and the Files, Peers, Trackers, Log and Speed tabs.
category: Torrents
order: 5
---

The details pane shows one torrent: the one selected. With several selected, it asks you to
select one. `Ctrl+I` (**View → Details Pane**) shows or hides it.

## Where it sits

The pane sits under the list, beside it on the right (running the full height of the window), or
is collapsed. Use **View → Details Pane on the Right** / **Details Pane at the Bottom**, the
palette's **Move Details Pane Between Bottom and Right**, or **Settings → General → Appearance →
Details pane**. Hiding the pane keeps its place, and each place keeps its own size. The tab you
were on is remembered.

## The header

At the top: a disc in the state's colour, the torrent's name and a line with its size, file
count, category and how long ago it was added. Beside them are **Pause** (or **Resume**), **Reannounce**,
**Open folder** and **···** for more actions. These run the same commands as the menus, with the
same keys.

## Overview

**The diagnosis.** When a torrent is not moving, an amber card says why, in plain words, and
offers what can be done about it. The first rule that fits wins:

| Situation | The card says | Remedies offered |
| --- | --- | --- |
| An error stopped it | what libtorrent reported | Try again, Open its folder |
| It is waiting in the queue | its place and when it starts | Start it now (force resume) |
| No peer is connected | no trackers, trackers not answering, or nobody sharing it right now | Add trackers…, Ask the trackers again |
| Pieces are on no peer in the swarm | how many, how much, and the availability | Add trackers…; Skip that file and finish the rest, when the missing pieces all belong to one wanted file and there are others |
| Peers are connected but none is sending | that they send when a slot frees; nothing to do but wait | none |
| Nothing has arrived for a while | how long, though peers are connected | Ask the trackers again |

Two more cards can appear above it:

- **Files to choose**, for a torrent added with "Add now, pick files when ready" once its file
  list is in, with **Choose files…**.
- **A red card for a locked archive**, when the torrent's first `.rar`, `.zip` or `.7z` turns
  out to be encrypted, with **Skip that file** and **Remove the torrent**. See
  [the content check](/docs/adding-torrents#the-content-check).

**The piece strip.** A bar of the torrent's pieces, bucketed to fit the width. Pieces you have
are filled; pieces that are missing and that no connected peer has are amber. A seed shows no
amber. While a file is [streaming](#stream-in-a-player), a ring marks the pieces being read
ahead.

**The sparkline.** Download and upload over the last minute, or over as much history as the
selection has.

**The facts.** Pieces (how many you have, of how many, and their size), peers connected and
seeds, downloaded and uploaded with the ratio, the info-hash, the save path, and the rest, with a
Copy button. At 760 pixels wide and up they are laid out in two columns, below that in one.

## Files

The torrent's files as a tree, each with its icon, progress and priority (Skip, Low, Normal or
High). Change a priority in place; the change is sent to the engine at once. The right-click
menu has **Open File**, **Open Folder**, **Open Containing Folder** and **Rename…** for files and
folders.

A file's icon comes from its extension: folder, video, audio, image, book (ebooks and comic
archives), document, subtitle, archive (split archives such as `.r00` or `.001` included), disc
image, data, program, or other.

A file that was [already on disk](/docs/adding-torrents#files-already-on-disk) in another
torrent is marked, and its tooltip says where it came from ("Linked from …").

The Files tab is empty until a magnet's metadata arrives.

### Stream in a player

The Files tab's right-click menu also has **Stream in Player** and **Copy Stream URL**: play a
file while it downloads.

- **Stream in Player** opens the file in mpv or VLC, wherever they are installed, or in the
  program chosen under **Settings → Adding torrents → Media player**. The player is given the
  file's address as its one argument, with no shell.
- **Copy Stream URL** copies the address for any other player on this computer.

The pieces the player is about to reach come first: the file's first and last pieces at the start
(where a video's index sits), then a window ahead of the playhead that tightens as the player
nears it. A jump ahead waits for its piece rather than playing garbage. A file you had skipped
becomes wanted, at low priority, once you play it.

The address is on this computer only: loopback, a port of the system's choosing, and a token per
file, so nothing else can be fetched from it. Streaming needs libtorrent.

## Peers

Every connected peer: address, client, flags, progress, download and upload rates, and how much
has been downloaded from and uploaded to it. The right-click menu has **Copy Address** and **Ban
Peer**, which adds the peer's host to the manual bans of the
[IP filter](/docs/connection#ip-filter) at once.

## Trackers

Each tracker's URL, tier, status (working, updating, not contacted, error) and last message.
**Add Trackers…** takes URLs separated by spaces or lines, **Reannounce** asks them all again, and
**Remove** takes the selected tracker off. A torrent with no trackers still finds peers through DHT and PEX.

Under the trackers are the torrent's **web seeds** (HTTP or HTTPS servers that hold the files),
with **Add Web Seeds…** and **Remove**.

## Log

The [Event Log](/docs/transfer-list#the-status-bar) for this torrent alone: when it was added,
when its metadata arrived, when it was checked or finished, its tracker and disk errors.

## Speed

The torrent's download and upload graph over the last minute, ten minutes, hour or day, with the
current rate and the peak in the legend. Every torrent keeps its recent rates while it is in the
list, so the graph is not empty the first time you open it. The same graph for the whole session
is in **View → Statistics** (`Ctrl+Shift+T`).
