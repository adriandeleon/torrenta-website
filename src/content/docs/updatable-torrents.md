---
title: Updatable torrents
description: Follow a BEP 46 link to get each new version of a torrent as it is published, or publish one of your own from the Create Torrent dialog.
category: Torrents
order: 6.5
---

An updatable torrent ([BEP 46](https://www.bittorrent.org/beps/bep_0046.html)) is a name in the
DHT, signed by a key its publisher holds, that points at the current version of a torrent. Its
link does not change when the content does: a dataset, a distribution or a collection can grow,
and whoever follows the link gets each new version.

## Following one

An updatable link looks like `magnet:?xs=urn:btpk:<key>&s=<name>`. It is taken wherever a magnet
is: typed or pasted, opened from a browser, given on the command line, a line in a watch folder's
`.magnet` file, an RSS article or a search result. Torrenta looks the name up in the DHT and adds
the version it points at, through the [Add dialog](/docs/adding-torrents) as usual.

From then on, Torrenta looks for a newer version on a schedule:

- **Settings → Adding torrents → Look for newer versions every** (6 hours by default; 0 never
  looks).
- A newer version is added into the **same folder**, filed under the same category and tags. Its
  files that did not change are [linked from the old version](/docs/adding-torrents#files-already-on-disk)
  rather than downloaded again, so an update costs only what changed.
- Once the new version is complete, the old one is no longer followed. With **Remove the old
  version once the new one is complete** on, it also leaves the list; its files stay on disk
  either way, since the new version may share them.

**Settings → Automation** can [run a program or notify](/docs/automation) when a new version is
added ("A newer version of … was added").

## Publishing one

In the [Create Torrent dialog](/docs/creating-torrents), tick **Publish as updatable** and give
the key a name (letters, digits, dots, dashes and underscores). Create makes the key, seeds the
torrent and puts its link in the DHT. The dialog shows the updatable link: that is what others
follow, and what you give out instead of a magnet.

On a published torrent:

- **Torrent → New Version…** opens the Create dialog for the next version under the same key.
  Whoever follows the link gets it the next time they look.
- **Torrent → Copy Updatable Link** gives the link again.

Both are in the palette too, and only for the current version of something you published.

### The key

The key is kept in `keys/<name>.key` in Torrenta's folder, readable by you alone, on this computer
only: it never syncs. Anyone with the file can publish versions under your link, and without it
there will be no more versions, ever, so keep a copy somewhere safe.

Items fade from the DHT after a couple of hours, so while Torrenta runs it puts the link back
every hour (the first a minute after a start) when it is no longer found there. If no DHT node
took the link when you created it, the dialog says so, and Torrenta keeps trying every hour.

## Limits

Following and publishing need libtorrent and its DHT: with a [Transmission daemon](/docs/remote)
as the engine, New Version… and Copy Updatable Link are hidden.
