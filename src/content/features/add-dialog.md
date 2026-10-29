---
title: "The Add dialog"
group: "Adding & creating"
order: 1
beta: false
summary: "Choose files and priorities, the folder with the space it leaves free, a category, tags and how it starts. A magnet's file list fills in while the dialog is open."
---

Every torrent goes through one dialog, wherever it came from (a file, a magnet, a URL, a drop, the clipboard, the command line, another program), unless you tell it to stop asking.

- **The files**, as a tree: tick what you want, set priorities with `+` and `−`, fold with the arrows.
- **The save path**, with a bar and a sentence saying how much room the torrent leaves on that disk.
- **Category and tags**: a category with its own folder fills the save path when chosen. A chip for each tag you already have sits under the tags field, and typing suggests the ones that match.
- **How it starts**: paused or not, a stop condition (after the metadata, after the check), sequential, first and last pieces first, skip the hash check.
- **A magnet never blocks it.** Its file list is fetched while the dialog is open, and the torrent then starts with the peers that fetch already found. Or choose "Add now, pick files when ready": the list reads "Files to choose" once its file list is in, and its Overview offers Choose files….
- **A look before you add**: a [content check](/features/content-check) card for files that deserve one, a [Swarm line](/features/swarm-preview) saying whether anyone has the files, and [files already on disk](/features/dedup) linked instead of downloaded.

`Enter` adds, `Shift+Enter` adds paused, `Esc` cancels. "Next time, add with these settings without asking" (or Settings → Adding) skips the dialog. Downloads can start in an incomplete folder and move when complete, carry a `.part` suffix until done, and be pre-allocated.
