---
title: Torrenta 0.3.0 released
description: "A qBittorrent-compatible web API and a headless mode, import from other clients, a content check and a swarm preview before adding, undo, saved views, streaming and updatable torrents."
date: 2026-10-26
version: 0.3.0
---

Torrenta 0.3.0 is the largest release yet: four milestones of the roadmap (1.1 to 1.4), from
a look at a torrent before it is added to a server the *arr apps can talk to.

## Download

From the [release page](https://github.com/adriandeleon/torrenta/releases/tag/v0.3.0):

- **macOS (Apple Silicon)**: the `macos-arm64` DMG.
- **Windows (x64)**: the MSI.
- **Linux (x64)**: the DEB (`sudo apt install ./Torrenta-*.deb`) or the RPM
  (`sudo dnf install ./Torrenta-*.rpm`).

Each bundles its own Java runtime. The builds are still unsigned: on macOS
allow the first launch under System Settings → Privacy & Security → Open
Anyway, and on Windows choose More info → Run anyway if SmartScreen warns.
Verify a download against `checksums_sha256.txt`. There is no Homebrew cask or
winget package yet; the release page is the one place to get it.

## What is new

### Before you add

- **A content check.** The Add dialog names files that deserve a look: a
  program named like a video (`Film.mkv.exe`), a name whose real extension is
  hidden by right-to-left characters, a Windows shortcut, an archive where a
  film was promised. **Skip these files** unticks them. Watch folders, RSS
  rules and adds without a dialog follow a setting: skip them (the default),
  refuse the torrent, or add them anyway. An archive that asks for a password
  is found within seconds of starting. See [Content check](/features/content-check).
- **A swarm preview.** A Swarm line reads Healthy, Thin, Incomplete or Dead
  before you add, and follows the files you tick. See
  [Swarm preview](/features/swarm-preview).
- **Files already on disk are not downloaded again.** When another torrent in
  the list has a file complete, the Add dialog offers to link it into place,
  for v2 and hybrid torrents, and for a v1 torrent cut in the same piece size. See
  [Files already on disk](/features/dedup).
- **Import from qBittorrent, Transmission or Deluge.** File → Import from
  another client… brings their torrents over at their progress, without a
  check, with categories, tags and names. See [Import](/features/import).

### The keyboard

- **Saved views.** `Ctrl+S` saves the omnibox's filter as a view in the
  sidebar, with a live count; `Ctrl+Alt+1` to `9` choose the first nine.
- **Commands with arguments, over every match.** `>set-category Films`,
  `>add-tag keep`, `>limit-down 500k`, `>queue top`, `>remove` and more act on
  what the filter shows, or on the selection.
- **Undo.** Remove (keeping the files), Set Location, a category and tags:
  the status bar offers Undo for ten seconds, and `Ctrl+Z` takes back the last
  ten for ten minutes.
- **Removed files go to the trash** (the Recycle Bin on Windows) instead of
  being deleted, so Remove with files can be undone from the file manager.
- **Shortcuts you can change** under Settings → Keyboard, synced with the
  other settings.
- **Four more filter keys**: `seeding>2d`, `eta<30m`, `seeds<3` and
  `uploaded>10g`.

### Headless and the web API

- **Remote access** speaks qBittorrent's WebUI API, so Sonarr, Radarr,
  Lidarr, Readarr, Prowlarr and the phone apps made for qBittorrent connect to
  Torrenta as they are. It is off until switched on, on `127.0.0.1:8080` until
  you choose an address, and always asks for a password. It can also serve a
  web UI written for qBittorrent, such as VueTorrent, for a phone's browser.
  See [Remote access](/docs/remote-access).
- **`torrenta --headless`**, or the `torrenta-nox` launcher beside the app,
  runs everything but the window, for a server or a NAS. See
  [Headless](/docs/headless).

### Protocol extras

- **Play a file while it downloads** in mpv or VLC, from the Files tab; the
  pieces the player is about to reach come first.
- **Updatable torrents (BEP 46).** Follow a `magnet:?xs=urn:btpk:…` link and
  get each new version as it is published, with unchanged files linked rather
  than downloaded again; or publish one from the Create Torrent dialog. See
  [Updatable torrents](/docs/updatable-torrents).
- **On battery or a metered network**, use the alternative speed limits or
  pause the torrents (macOS has no way to tell a metered network).
- **A leak check** for a VPN: a shield beside the port says Protected,
  Exposed, Halted or Unchecked, and can pause the torrents while exposed. A VPN
  interface that drops is said at once and bound again when it is back.

### Elsewhere

- **The Search tab's results have a filter box** (`provider:jack`,
  `size>1g`, `seeds>=10`, `date<7d`) and a Category column.
- **Search providers, category names and tags sync** with the other settings;
  API keys never do.
- **A torrent waiting for you to pick its files says so**: "Files to choose"
  in the list, and Choose files… on its Overview.

## Fixed

- **Binding to an interface now binds the connections Torrenta makes**, not
  only the ones it accepts: with a VPN's interface set, peers Torrenta dialled
  used to leave by the system's own route.
- **A finished torrent no longer "finishes" again at every start**, running
  the on-finished command and its notification each time.
- **"Add now, pick files when ready" holds across a restart.**
- **Quitting with a dialog open is clean**, from the tray menu or the system's.
- **Every dialog shows its buttons**: the Limits dialog cut off Cancel and
  Apply.
- **The port's state is no longer missed at startup.**
- **Fewer ways to fail on a missing value**, among them an Add dialog that
  waited forever when the engine went away.

Moving removed files to the trash has not been tried by hand on macOS or
Windows yet. The
[changelog](https://github.com/adriandeleon/torrenta/blob/master/CHANGELOG.md)
has the release in full.
