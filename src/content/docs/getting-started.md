---
title: Getting Started
description: Install Torrenta, add your first torrent, and learn the keys worth knowing first.
category: Getting started
order: 2
---

Install Torrenta, add a torrent, and learn the few keys that do most of the work.

## Install

Download the package for your platform from the
[releases page](https://github.com/adriandeleon/torrenta/releases). Each one bundles its own Java
runtime, so you need nothing else. Verify a download against `checksums_sha256.txt` on the same
page.

There are three builds, because the engine's native library exists only for these platforms:

| Platform | File |
| --- | --- |
| Linux x64 | `Torrenta-<version>-linux-x64.deb` or `.rpm` |
| macOS Apple Silicon | `Torrenta-<version>-macos-arm64.dmg` |
| Windows x64 | `Torrenta-<version>-windows-x64.msi` |

Intel Macs and ARM Linux are not supported yet.

### Linux

```bash
# Debian / Ubuntu
sudo apt install ./Torrenta-*.deb

# Fedora
sudo dnf install ./Torrenta-*.rpm
```

The packages register Torrenta for `.torrent` files and `magnet:` links.

### macOS

Open the `.dmg` and drag Torrenta to Applications.

The app is not yet signed with an Apple Developer ID, so macOS refuses to open it the first time.
Allow it once under **System Settings → Privacy & Security → Open Anyway**.

### Windows

Run the `.msi`. The installer is unsigned, so SmartScreen may warn on the first run; allow it to
continue. The installer associates `.torrent` files with Torrenta.

Homebrew and winget packages are not published yet; 0.1.0 is on GitHub only.

## First launch

Until you add a torrent, the window shows a welcome screen instead of an empty list:

- **A drop zone.** Drop a `.torrent` file, a magnet link or a link to a torrent anywhere on the
  window.
- **Four keys**: `Ctrl+O` opens a `.torrent` file, `Ctrl+V` adds what is on the clipboard,
  `Ctrl+K` filters, runs a command or takes anything you paste, and `Ctrl+,` opens Settings.
- **The clipboard.** When the clipboard holds a magnet or a link to a torrent, the welcome screen
  says so each time the window gets the focus, with an **Add it** button.
- **The download folder.** "Downloads go to …" names the folder and its free space, with a
  **change** link. The default is `~/Downloads`.

On macOS, read `Ctrl` as `Cmd` throughout these docs, except for `Ctrl+Tab`, which is `Ctrl`
everywhere.

## Add your first torrent

1. Copy a magnet link in your browser, then press `Ctrl+V` in Torrenta. (Or press `Ctrl+O` and
   pick a `.torrent` file, or drop one on the window.)
2. The Add dialog opens. For a magnet, the file list is fetched while the dialog is open and the
   tree fills in as it arrives.
3. Untick any files you do not want, check the folder and the free space it shows, and press
   `Enter` to add. `Shift+Enter` adds it paused.

The torrent appears in the list with its progress, rates and a one-line status. Select it and
press `Ctrl+Shift+D` for the [details pane](/docs/details-pane), which tells you why a torrent
is not moving when it is not. See [Adding torrents](/docs/adding-torrents) for every option in
the dialog.

## The keys to learn first

| Key | Does |
| --- | --- |
| `Alt+X` | The command palette: every command, found by typing part of its name |
| `Ctrl+K` | The omnibox: filter the list, run a command after `>`, paste a magnet |
| `Ctrl+V` | Add the magnet or torrent link on the clipboard |
| `Ctrl+O` | Add a `.torrent` file |
| `Space` | Pause the selection, or resume it if it is all paused |
| `Delete` | Remove the selection (it asks first); `Shift+Delete` also deletes the files |
| `Ctrl+Shift+D` | Show or hide the details pane |
| `Ctrl+1` / `Ctrl+2` / `Ctrl+3` | Transfers, Search, RSS |
| `Ctrl+,` | Settings |

You do not need to memorise more. Open the palette, type a few letters, and run the command;
each row shows its key, so you pick them up as you go. The full list is on the
[Keybindings](/keybindings) page, and [Keyboard](/docs/keyboard) covers the keys inside dialogs
and tabs.

## Where settings and data live

Everything Torrenta keeps is in one folder, `~/.torrenta`, on every platform: the settings
file, one resume file per torrent, categories, feeds, search providers and the rest. Removing a
torrent deletes its file there.

To keep it elsewhere, start Torrenta with `--config-dir DIR` or set the `TORRENTA_CONFIG_DIR`
environment variable. One Torrenta runs per settings folder; starting it again hands any files
or links on its command line to the one already running.

The folder's contents are listed in [Settings, themes and sync](/docs/settings#the-files-torrenta-keeps).
