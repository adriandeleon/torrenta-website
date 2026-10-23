---
title: Creating torrents
description: Make a v1, v2 or hybrid torrent of a file or folder, and seed it at once.
category: Torrents
order: 6
---

`Ctrl+N` (**File → New Torrent…**, or the toolbar's page glyph) opens the New Torrent dialog.
`Ctrl+N` does the same in the RSS tab, where `N` alone adds a feed.

The dialog does not block the window, so you can keep working while a large source is hashed.

## The source

Drop a file or a folder on the dialog, type its path, or use **File…** or **Folder…**. The
header sizes the source and shows its file count, total size and the piece count for the piece
size chosen, updated live as you change it.

## The choices

| Choice | What it does |
| --- | --- |
| **Format** | Hybrid (v1 + v2), the default; v1 only; or v2 only |
| **Piece size** | 16 KiB to 32 MiB, or **Automatic**, which aims at about 1,500 pieces (qBittorrent's rule) and names the size it picked |
| **Private torrent** | Marks the torrent private, for a private tracker |
| **Start seeding after creation** | Adds the finished torrent to the list and seeds it at once |
| **Align files to piece boundaries** | Always on for v2 and hybrid, libtorrent's canonical layout |
| **Trackers** | One URL per line; a blank line starts the next tier |
| **Web seeds** | One HTTP or HTTPS URL per line |
| **Comment** | Optional |
| **Source** | A private tracker's source tag, optional |

The trackers start out as you left them after the last creation, or, the first time, as the
default trackers on **Settings → Protocol**.

Tracker tiers look like this:

```text
udp://tracker.example.org:1337/announce
https://backup.example.org/announce

udp://other-tier.example.net:6969/announce
```

The first two URLs are one tier, the third is the next.

## Creating it

Press **Create…**. Torrenta asks where to save the `.torrent` (the folder is remembered for next
time), then hashes the pieces on a worker with a progress bar ("Hashing · 120 of 1,480 pieces")
and a **Cancel** button. Cancel stops at the next piece, and nothing is written. The file is
written whole, beside its final name, then moved into place, so a half-written `.torrent` never
appears.

When it is done, the dialog stays open and shows:

- **Info-hash v1** and **Info-hash v2** (one of them for a v1-only or v2-only torrent),
- the **Magnet** link,

each with a Copy button.

## Seeding it

With **Start seeding after creation** ticked, the torrent is added through the usual add path
with the source's parent folder as its save path and the hash check skipped, so it seeds at
once. The status line says "added and seeding", or why it was not added. If the torrent is
already in the list, the one already there is kept.

## Exporting a torrent you already have

**Torrent → Export .torrent…** (`Ctrl+Shift+E`) writes the `.torrent` of a torrent in the list:
its info dictionary as it was loaded (so the info-hash is the same), its trackers in their tiers
and its web seeds as they are now. With several selected, it writes one file per torrent into a
folder. A magnet whose file list has not arrived has nothing to export yet.

## With a remote engine

Transmission's RPC has no way to create a torrent, so with a
[Transmission daemon](/docs/remote) as the engine, `Ctrl+N` and its toolbar button are hidden.
