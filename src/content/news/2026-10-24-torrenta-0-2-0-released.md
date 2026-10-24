---
title: Torrenta 0.2.0 released
description: "Links that are not torrents are downloaded too, the sidebar folds and resizes, and the details pane fills in the moment you click."
date: 2026-10-24
version: 0.2.0
---

Torrenta 0.2.0 downloads more than torrents, and smooths a few corners of the
window.

## Download

From the [release page](https://github.com/adriandeleon/torrenta/releases/tag/v0.2.0):

- **macOS (Apple Silicon)**: the `macos-arm64` DMG.
- **Windows (x64)**: the MSI.
- **Linux (x64)**: the DEB (`sudo apt install ./Torrenta-*.deb`) or the RPM
  (`sudo dnf install ./Torrenta-*.rpm`).

Each bundles its own Java runtime. The builds are still unsigned: on macOS
allow the first launch under System Settings → Privacy & Security → Open
Anyway, and on Windows choose More info → Run anyway if SmartScreen warns.
Verify a download against `checksums_sha256.txt`.

## What is new

- **Downloads from links.** Paste, type or drop a link to any file (http,
  https, ftp, ftps, sftp) and it joins the list beside the torrents, fetched by
  curl: progress, rate, pause and resume from where it stopped, remove with or
  without the file, a category, tags, Set Location and a limit. A small
  **Download Link** dialog asks for the folder, the name and whether to start
  paused. A few run at once (three by default), and the download limit is
  shared among them. See [Downloads from links](/features/link-downloads).
- **Downloads are easy to tell from torrents**: a chain before the name, a
  **From links** entry in the sidebar, and `is:link` / `is:torrent` in the
  omnibox.
- **The sidebar folds with a click and resizes.** A click on a section's
  heading folds or opens it; drag the edge facing the list to make it wider or
  narrower (a double-click goes back to the default). Both are remembered.
- **Glyphs** beside each page in the Settings sidebar, and coloured ones on the
  details pane's Pause, Reannounce and Open folder buttons.

## Fixed

- **`Ctrl+Shift+D` was both Set Location and the details pane**, so only one of
  them fired. Set Location keeps it; the details pane is now **`Ctrl+I`**.
- **The details pane showed the previous torrent for up to a second after a
  click.** It now fills in at once, in about 20 ms.

The [changelog](https://github.com/adriandeleon/torrenta/blob/master/CHANGELOG.md)
has the release in full.
