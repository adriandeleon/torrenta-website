---
title: "Headless"
group: "Engine & network"
order: 7
beta: false
summary: "<code>torrenta-nox</code> runs everything the window does but the window: for a server, a NAS, or a machine reached over SSH."
---

`torrenta --headless`, or the `torrenta-nox` launcher packaged beside the app's own, runs the same session as the window without ever loading the toolkit.

- **Everything but the window**: torrents, watch folders, RSS rules, the IP filter, share limits, the speed schedule, run-on-event commands, a remote engine profile.
- **Added without asking.** What arrives (the command line, another Torrenta on the same folder, a watch folder, a rule) goes in with the settings' defaults, through the [content check](/features/content-check).
- **The log is stdout**, one line per event, and `torrenta-session.log` in the config folder.
- **A clean stop.** SIGTERM or `Ctrl+C` stops it the way Quit does, the last resume data written first, so nothing re-checks on the next start.
- **With [Remote access](/features/remote-access)** for Sonarr, Radarr or a web UI: `torrenta-nox --set-remote-password` sets the password without a window.

On Linux it is `bin/torrenta-nox` beside `bin/Torrenta`; on Windows a console program. The settings are the window's own `settings.properties`, editable by hand while Torrenta is stopped. See [Headless](/docs/headless) for a systemd user unit.
