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
| A link to any other file | Pasted, typed or dropped like a torrent link; see [Links that are not torrents](#links-that-are-not-torrents) |
| An updatable link | A `magnet:?xs=urn:btpk:…` link, anywhere a magnet goes; see [Updatable torrents](/docs/updatable-torrents) |
| Another client | File → Import from Another Client…; see [Importing](/docs/importing) |

When the window comes back to the front and the clipboard holds a magnet, a torrent link or a
link to a file, the status bar says so and names the key to add it. A link to a `.torrent` is fetched first; a file
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
- **Tags**, comma-separated. A new tag is created as you type it. Under the field, a chip for
  each tag you already have is lit while the tag is in the field; a press adds or removes it.
  Typing suggests the tags that match the one at the caret, those that start with it first:
  `Down` and `Up` move, `Enter` or `Tab` takes one, and `Esc` closes the list rather than the
  dialog. `Down` on an empty tag lists them all.
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
  metadata arrives. When it has, the list reads **Files to choose** instead of "Paused by you",
  and its Overview offers **Choose files…** (also **Torrent → Choose Files…**, the context menu
  and the palette) to tick its files; that starts it. A restart before the file list arrived
  keeps it waiting for you.
- **Wait here for the file list** (`Ctrl+Enter`) keeps the dialog open until the tree is full.
  It is offered only while the list can still come.

Either way, the new torrent starts with the peers the fetch already found instead of looking for
the swarm again.

### The content check

Above the file tree, a card names the files that deserve a look before anything is downloaded:

- a program named like a video or a document (`Film.mkv.exe`, spaces before the last dot
  included);
- a name whose real extension is hidden by right-to-left characters;
- a Windows shortcut (`.lnk`, `.url` and the like);
- a program, or a link to a web page, in a film or an album;
- an archive where the name promises a film and nothing else is there;
- a file named for a password or a codec.

Red is danger, amber a caution. **Skip these files** unticks them and keeps the rest. A program
in a software torrent is not flagged, and a file you skip stops being reported.

Adds without a dialog (watch folders, RSS rules, an article's Download, Add without asking,
[headless](/docs/headless) and [Remote access](/docs/remote-access)) follow **Settings → Adding
torrents → Flagged files when adding without a dialog**: **Skip those files** (the default),
**Refuse the torrent**, or **Add them anyway**. The Event Log says which, and what was found. A
magnet has no file list yet, so it stops once its metadata is in, is checked, and then starts,
starts without the flagged files, or is removed before anything was downloaded; a restart in
between keeps it waiting.

**An archive that asks for a password** is caught once the torrent starts: its first `.rar`,
`.zip` or `.7z` gets its first piece before anything else, and if the archive is encrypted (its
files, or its names too), the [Overview](/docs/details-pane#overview) shows a red card saying so,
with **Skip that file** and **Remove the torrent**. A torrent that hides its content behind a
password usually sells the password. This needs the files on this machine.

### The Swarm line

Under the free space, a **Swarm** line says how the swarm looks before you add, with the counts:

| Verdict | Means |
| --- | --- |
| **Healthy** | Three seeds and up |
| **Thin** | A seed or two, or no seed but every piece among the peers |
| **Incomplete** | No seed, and pieces of the chosen files that no peer has |
| **Dead** | No one at all |

It is there at once for a `.torrent`, and for a magnet once its file list is in, and it follows
the files you tick. It reads the trackers' counts and the peers it meets without downloading
anything, and gives no time estimate: there is no rate to measure one from.

A private torrent is not looked at, since a private tracker counts every announce, unless
**Settings → Adding torrents → Look at a private torrent's swarm** is on. With a
[Transmission daemon](/docs/remote) the line is not shown.

### Files already on disk

When a new torrent holds a file that another torrent in the list has complete, the dialog offers
to put that file in place instead of downloading it: an **Already on disk** line such as "Link 3
files (4.2 GB) from …", ticked by default.

- **What matches.** For v2 and hybrid torrents, any file with the same content, whatever its
  name. For a v1 torrent, a file whose pieces hold nothing else (a single-file torrent, or a
  padded one) when the other torrent was made with the same piece size.
- **A link** (on the same file system) writes no bytes: the two torrents share the file. The
  add's own check then verifies every byte.
- **On another drive** the file is copied: a clone where the file system can make one (`cp
  --reflink` on Linux, `cp -c` on macOS), which uses no space either, else a full copy, whose size
  the dialog shows first.

**Settings → Adding torrents → Files already on disk** chooses: **Download them again**, **Link
them, never copy**, **Link them, ask before copying** (the default) or **Link them, copy from
other drives**. Watch folders, RSS rules and adds without a dialog link without asking. The
[Files tab](/docs/details-pane#files) marks such a file: "Linked from …", "Cloned from …" or
"Copied from …".

### Skipping the dialog

Tick **Next time, add with these settings without asking** in the dialog, or **Settings →
Adding torrents → Add without asking**, and every input is added with the defaults and no
dialog opens. Untick the setting to bring the dialog back.

## Links that are not torrents

A link to a file that is not a torrent (http, https, ftp, ftps or sftp) is downloaded by
[curl](https://curl.se) and joins the list beside the torrents. Instead of the Add dialog,
**Download Link** asks three things:

- **Folder**, with Browse and the free space there. The download folder by default.
- **Name**: the link's own to begin with. A name you type is kept over the one the server gives.
- **Start paused.**

"Add without asking" skips it, as it does the Add dialog. Links that arrive together are asked
about one after the other.

The file grows under a `.part` name and takes the server's name for it when it is done, never over
a file that is already there. Pause stops curl; resume carries on from where it stopped. A link
that turns out to be a `.torrent` is added as a torrent when it arrives. The clipboard offers a
link to a file the way it offers a magnet; a link to a web page is not offered.

In the list a download has a chain before its name, Done where a torrent would be Seeding, and the
server where a torrent names its peers. The sidebar's **From links** and the omnibox's `is:link`
pick them out. Pause, remove, a category, tags, Rename, Set Location and Limits work as they do
for a torrent; commands only a torrent understands (Recheck, Reannounce, Sequential, Super
Seeding, the queue, Copy Info-Hash, Copy Magnet Link, Export .torrent) are off for downloads and
skip them in a mixed selection.

**Settings → Adding torrents** has two rows for them:

- **Links at once**: how many run at the same time (3); the rest wait their turn.
- **curl program**: empty uses the curl on the `PATH`.

curl runs through the engine's proxy, and the download limit in force (the alternative one
included) is shared equally among the downloads from links, apart from the torrents'; a download's
own limit holds when it is lower. They run on this machine whichever engine has the torrents.

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
