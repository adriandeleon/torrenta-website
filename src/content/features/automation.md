---
title: "Run on event and notifications"
group: "Watching & managing"
order: 4
beta: false
summary: "A program to run when a torrent is added, finishes or is done seeding, with qBittorrent's <code>%N</code> <code>%F</code> <code>%D</code> placeholders, and a desktop notification for each."
---

Settings → Automation has one optional command line per event: **torrent added**, **download finished** and **seeding done** (the share limit paused or removed it).

The placeholders are qBittorrent's, spelled the same: `%N` name, `%L` category, `%G` tags, `%F` content path, `%R` root path, `%D` save path, `%C` file count, `%Z` size in bytes, `%T` current tracker, `%I` info-hash v1, `%J` info-hash v2, `%K` torrent id, `%%` a percent sign.

The line is split as a shell would split it, but **no shell runs it**: each argument is filled in on its own, so a torrent's name can never become a command. It runs off the window's threads, with no input, its output capped and a ten-minute ceiling, and what happened is in the event log.

**Desktop notifications** for added, finished, seeding done and errors, each a switch, through `notify-send` on Linux, `osascript` on macOS and a PowerShell toast on Windows, with the text passed as arguments.
