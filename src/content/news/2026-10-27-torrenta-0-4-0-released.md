---
title: Torrenta 0.4.0 released
description: "Simple mode for someone new to torrents, Report a Problem from the debug log, tag chips in the Add dialog, and a window that keeps up with thousands of torrents."
date: 2026-10-27
version: 0.4.0
---

Torrenta 0.4.0 makes it smaller when you want it small and lighter when the list is long:
a Simple mode that leaves only what someone new to torrents needs, a bug report written for
you from the debug log, and a window that stays quick with thousands of torrents.

Use [0.4.1](/news/2026-10-28-torrenta-0-4-1-released) instead: with the `.part` suffix on,
0.4.0's first restart downloaded finished torrents again. 0.4.1 fixes that and repairs the
torrents it caught.

## Download

From the [release page](https://github.com/adriandeleon/torrenta/releases/tag/v0.4.0):

- **macOS (Apple Silicon)**: the `macos-arm64` DMG.
- **Windows (x64)**: the MSI.
- **Linux (x64)**: the DEB (`sudo apt install ./Torrenta-*.deb`) or the RPM
  (`sudo dnf install ./Torrenta-*.rpm`).

Each bundles its own Java runtime. The builds are still unsigned: on macOS
allow the first launch under System Settings → Privacy & Security → Open
Anyway, and on Windows choose More info → Run anyway if SmartScreen warns.
Verify a download against `checksums_sha256.txt`.

## What is new

- **Simple mode.** View → Simple Mode, the toolbar's switch beside Settings,
  Settings → General, or `torrenta --simple` for one session. It leaves the
  list, the ways a torrent arrives (a file, a magnet, the clipboard, a drop) and
  the actions someone new to torrents needs: pause and resume, remove, choose files,
  open the folder, copy the magnet, and the diagnosis card's remedies. The sidebar,
  Search and RSS, the rarer commands, the detail pane's Peers, Trackers, Log and
  Speed tabs and the Add dialog's advanced options are put away. Nothing is lost:
  turning it off brings each back as it was, and RSS rules, watch folders and
  automation keep running. The list gets its own columns (name with its progress,
  size, the two rates, time left), remembered apart from the full list's. See
  [Simple mode](/features/simple-mode).
- **Report a Problem.** Help → Report a Problem… (also in the Debug Log window)
  writes a report from this session's and the last session's logs, with the
  version, engine, Java and OS. Home-folder paths, your user name, torrent names,
  magnet links, info-hashes, tracker passkeys, public peer addresses, e-mail
  addresses and tokens are taken out first. Edit it, then **Open GitHub Issue**
  fills in a new issue. `torrenta --report` does the same when Torrenta will not
  start. Torrenta sends nothing itself. See [Report a Problem](/features/report-problem).
- **The Add dialog offers the tags you already have**: a chip for each under the
  tags field, and suggestions as you type (`Down`/`Up`, `Enter` or `Tab` to take one).

### Changed

- **The toolbar leaves Search and RSS to the view strip**; `Ctrl+2` and `Ctrl+3`
  still reach them.
- **A removal's button is red**, in every Remove question.
- **Text cut short with "…" shows the whole of it on hover**, everywhere.
- **The Sync page waits for its switch**, and Create on GitHub connects the
  repository it makes at once.
- **Thousands of torrents no longer weigh on the window.** The list sorts once a
  second, the sidebar counts in one pass, a launch no longer rewrites every
  seed's resume data, and saves are spread out instead of all at once.
- **A Transmission daemon with thousands of torrents is polled lightly**: most
  seconds ask only for the recently active ones.
- **The detail pane keeps up with a torrent of 50,000 files**, reading only
  what the showing tab needs.
- **A 300,000-line IP blocklist** parses in about 70 ms and keeps about 3 MiB in
  memory instead of 40.
- **A long session stays within bounds**: speed history, the search cache and the
  session log are capped.

## Fixed

- **Removing a torrent while its files were being read could crash Torrenta.**
- **A torrent removed and added again was forgotten at the next start.**
- **The `.part` suffix and the move out of the incomplete folder could be lost**
  for a torrent checked very quickly after the add.
- **A warning that keeps coming no longer buries the debug log**: a repeated line
  is written three times, then counted.
- **No more D-Bus warnings from systemd's device signals on Linux.**
- **Hidden to the tray or minimised, Torrenta stops drawing a window nobody sees.**
- **The log of a session that crashed survives the next launch**, as
  `torrenta-session.prev.log`.

The
[changelog](https://github.com/adriandeleon/torrenta/blob/master/CHANGELOG.md)
has the release in full.
