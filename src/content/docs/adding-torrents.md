---
title: Adding torrents
description: Every way a torrent gets into Torrenta, and every choice the Add dialog offers.
category: Torrents
order: 3
---

Every way of adding a torrent ends in the same Add dialog, unless you tell Torrenta to skip it.

## Ways in

| From | How |
| --- | --- |
| A `.torrent` file | `Ctrl+O` (File → Add Torrent File…), or drop it on the window |
| A magnet link or a link to a `.torrent` | `Ctrl+M` (Add Magnet Link…); the field starts with the clipboard's link if it holds one |
| The clipboard | `Ctrl+V` adds whatever magnet or torrent link is on it |
| The omnibox | Paste a magnet, a `.torrent` link or a path into `Ctrl+K` |
| The command line | Any argument that is not an option |
| The desktop | A double-clicked `.torrent` or a browser's `magnet:` link |
| A watched folder | Added without a dialog; see [Watch folders](/docs/automation) |
| Search and RSS | `Enter` on a result or an article; see [Search](/docs/search) and [RSS](/docs/rss) |

When the window comes back to the front and the clipboard holds a magnet or a torrent link, the
status bar says so and names the key to add it. A link to a `.torrent` is fetched first; a file
larger than 10 MiB is refused as not being a torrent.

### From the desktop and a second launch

One Torrenta runs per settings folder. Starting a second one with `.torrent` files, magnet
links or URLs on its command line hands them to the running one, which comes to the front (out of
the tray if it was hidden there) and opens the Add dialog for each. The second prints nothing and
is gone within a second. Started with nothing, it only brings the window forward.

That is how a double-clicked file and a browser's magnet link arrive. The packages declare both
types, and **Settings → General → Operating system → Register as handler for .torrent files and
magnet links** (on by default) makes Torrenta the default at each start: `xdg-mime default` on
Linux, and the `magnet:` protocol and `.torrent` default under your user's registry classes on
Windows (unticking removes them). On macOS the app bundle declares both, and Finder's **Get Info
→ Open with** chooses.

## The Add dialog

The header says where the torrent came from ("FROM FILE", "FROM CLIPBOARD", "FROM SEARCH" …), its
name, and its size, file count, format (v1, v2 or hybrid), tracker count and comment.

On the left is the **file tree**: a select-all box, each file's size and its priority (Skip, Low,
Normal or High). `Space` ticks a file, `+` and `−` step its priority, and `←` `→` fold a folder.

On the right:

- **Save to**, with a disk bar and a sentence such as "412 GiB free on /data · 5.9 GiB leaves
  406 GiB". With an incomplete folder set, the line says where the files start and the free space
  of both folders.
- **Category.** A category with a save path fills the folder when you choose it.
- **Stop when**: Never, Metadata arrives, or Files are checked.
- **Tags**, comma-separated. A new tag is created as you type it.
- **Content layout**: as the torrent has it, in a folder of its own, or without its root folder.
  The default is in **Settings → Adding torrents**.
- **Options**: Sequential download, First and last pieces first (so a video plays and seeks
  early), and Skip hash check.

| Key | Does |
| --- | --- |
| `Enter` | Add and start |
| `Shift+Enter` | Add paused (the other way round when Start paused is the default) |
| `Esc` | Cancel |

Each key is shown beside its button. The defaults for Start paused, Sequential download and the
content layout are under **Settings → Adding torrents**.

### Magnets: the file list while you wait

A magnet carries no file list. While the dialog is open, Torrenta fetches it and fills the tree
live ("Fetching file list · 3 peers · 12s"). Everything else can be set in the meantime. Then:

- **Add now, pick files when ready** (`Enter`) adds the torrent at once, set to stop when its
  metadata arrives. When it has, select the torrent and choose **Torrent → Choose Files…** (also
  in the context menu and the palette) to tick its files; that starts it.
- **Wait here for the file list** (`Ctrl+Enter`) keeps the dialog open until the tree is full.
  It is offered only while the list can still come.

Either way, the new torrent starts with the peers the fetch already found instead of looking for
the swarm again.

### Skipping the dialog

Tick **Next time, add with these settings without asking** in the dialog, or **Settings →
Adding torrents → Add without asking**, and every input is added with the defaults and no
dialog opens. Untick the setting to bring the dialog back.

## Where the files go

Downloads go to the download folder (`~/Downloads` until you change it in **Settings → General
→ Downloads**), or to the category's save path when the category has one. Three more choices sit
on that page, all off by default:

- **Incomplete folder.** New torrents download there and are moved to their save path (or their
  category's) the moment they complete. A file already at the destination is never written over.
- **Append .part to incomplete files.** Each file carries the suffix until it is complete; a file
  that was already complete keeps its name.
- **Pre-allocate disk space for all files.** A file is written at its full size from its first
  piece.

What is still owed to a torrent's files (the move, the names to take back) is kept beside its
resume data, so a restart in the middle of a download knows both.

To move a torrent that is already in the list, use **Torrent → Set Location…** (also in the
palette). See [The transfer list](/docs/transfer-list#acting-on-the-selection).
