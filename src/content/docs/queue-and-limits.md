---
title: Categories, queue and limits
description: File torrents under categories and tags, order the queue, and set share and speed limits globally or per torrent.
category: Torrents
order: 7
---

## Categories and tags

A torrent is filed under one category or none, and carries any number of tags.

- **Set Category…** (`Ctrl+Shift+G`) files the selection under an existing category, none, or
  **New category…**. The Torrent and right-click menus also list the categories as a submenu.
- **Edit Tags…** (`Ctrl+T`) shows a checkbox per existing tag and a field for new ones,
  comma-separated.
- The Add dialog offers both.

A category can have a **save path**. Choosing that category in the Add dialog fills the folder,
so new torrents filed under it download there; with an incomplete folder, they are moved there
when complete. Changing a category's path later moves nothing already added.

**Settings → Categories and tags** (also **Torrent → Manage Categories and Tags…**) adds
categories, sets their save paths, and adds and removes tags. Removing a category leaves its
torrents filed under no category; removing a tag takes it off every torrent. Both ask first.

The sidebar has a section for each, with counts, and the omnibox takes `cat:` and `tag:` terms.
See [The transfer list](/docs/transfer-list#the-omnibox).

## The queue

The selected torrents move in the download queue, keeping their order among themselves:

| Key | Move |
| --- | --- |
| `Ctrl+Shift+Home` | To the top |
| `Ctrl+Shift+Up` | Up one |
| `Ctrl+Shift+Down` | Down one |
| `Ctrl+Shift+End` | To the bottom |

They are also under **Torrent → Queue** and in the right-click menu. The Queue column (hidden by
default) shows the positions and sorts by them.

**Force Resume** (`Ctrl+Shift+U`) starts a torrent now, ignoring the queue. A queued torrent
reads "Queued #n" in the list; that is different from "Paused", which only you do.

## Queue limits

**Settings → Queue and share limits → Queue** sets how many torrents run at once (0 for no
limit):

- **Active downloads**
- **Active seeds**
- **Active torrents**, in all
- **Don't count slow torrents**: a slow torrent does not hold a slot.

## Share limits

On the same page, **Share limits** apply to every finished torrent:

- **Ratio limit**
- **Seeding time limit, in minutes**
- **When a limit is reached**: Pause, Remove, Remove with data, or Super seed.

Whichever limit is reached first triggers the action, which the Event Log records ("Share
limit reached: paused …"). Torrenta checks once a second; libtorrent itself pauses nothing by
these limits. A torrent paused or removed this way raises the "seeding done" event that
[automation](/docs/automation) can act on.

## Per-torrent limits

**Torrent → Limits…** (`Ctrl+L`, also in the right-click menu and the palette) sets, for the
selection:

| Limit | Meaning |
| --- | --- |
| **Download**, **Upload** | Rate caps in KiB/s, 0 for none |
| **Connections** | Peers at once, 0 for the global limit |
| **Upload slots** | Peers unchoked at once, 0 for the global limit |
| **Download the first and last pieces first** | The edge pieces of every wanted file first, so a video plays and seeks early; available once the metadata has arrived |
| **Share limits** | The global limits, or this torrent's own ratio, seeding time and action |

The caps and counts are kept in the torrent's resume data; its own share limit is kept in the
settings file and forgotten when the torrent is removed.

## Speed limits

**Settings → Speed** holds:

- **Limits**: download and upload in KiB/s, 0 for none.
- **Alternative limits**: a second pair, switched on with **Use alternative limits now**, the
  status bar's **Alt speed** pill, or **View → Alternative Speed Limits** (`Ctrl+Shift+J`),
  which is checked while they are in force.
- **Schedule**: **Switch to the alternative limits on a schedule**, with From, To and the
  days of the week. A window that runs past midnight belongs to the day it starts on. The
  schedule is checked every minute.
- **Count protocol overhead** in the limits.

A peer on your local network is not held to the session's rate limits; a torrent's own limit
still applies to it.
