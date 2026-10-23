---
title: Watch folders and automation
description: Add torrents dropped into a folder, run a program when a torrent is added, finishes or is done seeding, and show desktop notifications.
category: Automation
order: 10
---

## Watch folders

A watched folder is picked up without a dialog: drop a `.torrent` into it, or a `.magnet` file
holding one magnet link per line (as qBittorrent reads them), and the torrent is added with the
folder's choices.

Add folders in **Settings → Watch folders** with **Add…**. Each entry has:

| Choice | Options |
| --- | --- |
| **Folder** | The folder to watch |
| **Save to** | The download folder, the watch folder itself, or another folder. A category with a save path overrides it |
| **After adding** | Delete the file, or rename it to `name.torrent.added` |
| **Category** | The category to file it under |
| **Start paused** | Whether the torrent starts paused |

What happens to a file:

- A `.torrent` is read once its size has held still, so a file still being copied is not taken
  half-written.
- When it has been added, the file is deleted or renamed `.added`, as the entry says. The Event Log
  says "Added … from watch folder …".
- A torrent already in the list is reported, and the file is still handled.
- A file that is not a torrent is renamed `.invalid` once and reported once, then left alone.
- A folder that does not exist is noted on the page's status line.

Folders are watched through the file system and also polled every minute as a safety net, for
network mounts and macOS. The list is `watch-folders.json` in the config folder; it belongs to
this machine and never syncs.

## Run on event

**Settings → Automation → Run on event** takes one optional command line per event:

| Event | When |
| --- | --- |
| **On added** | A torrent was added (not the ones restored at start) |
| **On finished** | A download finished |
| **On seeding done** | A [share limit](/docs/queue-and-limits#share-limits) paused or removed it |

The line is split as a shell would split it (quotes keep a value together), and each argument is
filled in on its own, so a name with spaces stays one argument. **No shell runs it**: pipes,
redirection and variables reach the program as plain text. To use them, point the line at a
script of your own.

The placeholders are qBittorrent's, spelled the same:

| Placeholder | Is replaced by |
| --- | --- |
| `%N` | Torrent name |
| `%L` | Category |
| `%G` | Tags, comma-separated |
| `%F` | Content path: the root folder, or the one file |
| `%R` | Root path (empty without a root folder) |
| `%D` | Save path |
| `%C` | Number of files |
| `%Z` | Size in bytes |
| `%T` | Current tracker |
| `%I` | Info-hash v1 |
| `%J` | Info-hash v2 |
| `%K` | Torrent id |
| `%%` | A percent sign |

For example:

```text
/home/me/bin/on-done.sh "%N" "%F" %Z
```

The program's first three lines of output and its exit code go to the Event Log. Its input is
empty. A program still running after ten minutes is killed, and the log says so.

The command lines name programs on this machine, so they stay on it and never sync.

## Desktop notifications

**Settings → Automation → Desktop notifications** has a switch per event:

- **Torrent added**
- **Download finished** (the only one on by default)
- **Seeding done**
- **Torrent or tracker error**, for a torrent, file or tracker error

Each shows a short notification such as "*name* finished". They go through the platform's own
facility:

| Platform | Through |
| --- | --- |
| Linux | `notify-send` |
| macOS | `osascript` |
| Windows | A PowerShell toast |

Each is given the text as arguments, never pasted into a script. A machine without the tool is
noted once in the Event Log. **Test notification** on the page shows one now. The switches sync.

## Related

- The tray icon and the dock or launcher badge show activity without a notification; see
  [Settings, themes and sync](/docs/settings#tray-badge-and-file-handler).
- RSS rules add torrents by themselves; see [RSS](/docs/rss#rules).
