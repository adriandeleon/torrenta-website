---
title: Settings, themes and sync
description: The Settings window, appearance and languages, the tray and the desktop, settings sync through Git, and the files Torrenta keeps.
category: Customization
order: 13
---

## The Settings window

`Ctrl+,` (**Settings…**) opens it. A sidebar holds the pages, each with its glyph, in two groups:

| Group | Pages |
| --- | --- |
| Application | General, Adding torrents, Watch folders, Automation, Search, RSS, Sync, Categories and tags |
| Engine | Engine, Connection, Protocol, IP filter, Speed, Queue and share limits |

There is no OK button: every control applies the moment it changes, and the settings file is
written at once.

**Search settings** at the top filters every row of every page. The page that answers best opens:
a row whose title matches beats one whose description does, which beats a keyword, so "proxy"
opens Connection. `Esc` clears a search before it closes the window.

**Reset to defaults** asks first, then puts every setting on these pages back, the theme
included, and re-applies what they drive. Categories and tags, watch folders, feeds, search
providers and engine profiles are kept.

The settings file, `settings.properties`, can also be edited by hand.

## Appearance

**Settings → General → Appearance**:

- **Theme**: Editora Light, Editora Dark, Nord Light, Nord Dark, Cupertino Light or Cupertino
  Dark, applied the moment you pick one.
- **Row density**: Comfortable (two lines per torrent, the default) or Compact.
- **Colour rows by state**: on by default; off leaves the glyphs and sentences in monochrome.
- **Sidebar**: Left, Right or Hidden.
- **Details pane**: Bottom, Right or Collapsed.
- **Merge the menu bar into the title bar**: off by default and experimental. The window is drawn
  without a system title bar, with the menus, the title and the window buttons on one row. The
  platform keeps its own buttons, drag, double-click and edge resize. It applies on restart, and
  is not offered on macOS, where the menu belongs to the system menu bar.

The interface font is Inter, bundled. **View → Toolbar** hides the icon toolbar.

## Language

**Settings → General → Language → Interface language**: System (the operating system's
language, or English when that is not one of these), English, Deutsch, Español, Français,
Italiano or Português. The choice takes effect after a restart, and it syncs. What libtorrent
itself reports stays as it is.

## Keyboard

**Settings → General → Keyboard → Command palette** chooses the palette's key: `Alt+X` (the
default) or `Ctrl+Shift+P`. It applies at once, and the menus, the toolbar tooltip and the
palette show whichever is in force. See [Keyboard](/docs/keyboard).

## Tray, badge and file handler

**System tray**

- **Show a tray icon** (on by default): Torrenta's arrow-in-a-tray mark, teal while something
  downloads, red while a torrent has an error, muted otherwise. Its tooltip and the first line of
  its menu carry the rates and what the torrents are doing; the menu has **Show Torrenta** and
  **Quit**.
- **Closing the window keeps Torrenta in the tray** (on by default): closing hides the window and
  the torrents go on. **File → Quit**, `Ctrl+Q` or the tray menu's Quit ends the program. This
  applies only once an icon has actually appeared: without one, closing quits, and the status bar
  says why.

On Linux the icon is a StatusNotifierItem (KDE, or GNOME with the AppIndicator extension; X11
and Wayland alike). Windows and macOS use the system's own tray; there, turning the icon on takes
effect after a restart.

**Operating system** (both on by default, both this machine's)

- **Register as handler for .torrent files and magnet links**: makes Torrenta the default at
  each start. See [Adding torrents](/docs/adding-torrents#from-the-desktop-and-a-second-launch).
- **Show download activity on the dock icon**: the download rate on the macOS dock icon, or the
  number of downloading torrents on a Linux launcher that reads Unity's badge (KDE's task manager,
  GNOME's Dash to Dock), cleared when nothing downloads. Windows has no badge for Torrenta's
  window.

## Updates and diagnostics

**Settings → General → Updates → Check for updates**: after startup, at most once a day, Torrenta
asks GitHub whether there is a newer release and says so in the status bar and the Help menu.
Nothing about you or the machine is sent beyond the request itself. **Help → Check for Updates**
asks now; **Skip Version** keeps one release quiet.

**Settings → General → Diagnostics → Show debug log** (also **View → Debug Log**) shows every
warning and error logged this session and every uncaught exception, with Copy. The same lines go
to `torrenta-session.log` in the config folder, started afresh each run.

## Settings sync

**Settings → Sync** keeps the settings file the same on every computer through a Git repository
of yours, such as a private one on GitHub. There is no Torrenta account or server.

1. Paste the repository's URL and check the branch. With the GitHub CLI (`gh`) installed and
   signed in, **Create on GitHub…** makes a private repository for you.
2. Turn on **Keep settings in sync**. If the repository already holds settings, Torrenta says how
   many would come to this machine and asks before connecting.

Settings are merged one key at a time. A key changed on both sides keeps this machine's value,
and the report says so. Sync runs after startup, half a minute after a change, and every few
minutes while the window is in front (**Every, in minutes**); **Sync now** runs it at once.
**Disconnect** stops it and removes the local copy, leaving your settings as they are.

It never asks for a password: git signs in the way it does for you, and with two `gh` accounts,
the one that can read the repository is used. Put no password or token in the URL.

**What stays on each machine**: the download and incomplete folders, the network interface, the
local IP filter list, the clocks of the last checks, the proxy password, the per-torrent entries,
the sidebar's choices, the window's size and place, the run-on-event commands, the New Torrent
dialog's last folder and trackers, a skipped update, the engine profile
in use, the desktop handler and badge switches, and the sync connection itself. Everything else
in the settings file travels, including the theme, the language, limits, bans and default
trackers.

## The files Torrenta keeps

Everything is in `~/.torrenta`, unless moved with `--config-dir DIR` or `TORRENTA_CONFIG_DIR`.

| File | Holds | Syncs |
| --- | --- | --- |
| `settings.properties` | Every setting; hand-editable | Per key, as above |
| `torrents/<info-hash>.fastresume` | One per torrent: libtorrent's resume data, info dictionary inside. Deleting one forgets one torrent | No |
| `torrents/<info-hash>.labels` | That torrent's category and tags | No |
| `torrents/<info-hash>.storage` | What is still owed to its files: a move, names to take back | No |
| `categories.json`, `tags.json` | Categories (name, save path) and tags | No |
| `watch-folders.json` | Watched folders and their choices | No |
| `search-providers.json` | Torznab providers, API keys included | No |
| `search-providers/<id>.json` | Definition-file providers | No (which are off syncs) |
| `rss/feeds.json`, `rss/articles/<id>.json`, `rss/rules.json` | Feeds, articles (at most 500 per feed), rules | No |
| `profiles.json` | Remote engine profiles; readable by you alone | No |
| `stats.properties` | All-time totals: bytes down and up, time active | No |
| `blocklist.txt` | The downloaded IP blocklist | No |
| `torrenta-session.log` | This session's warnings and errors | No |
| `native/<version>/` | The unpacked engine, when not run from an installed app | No |
| `instance.lock`, `instance.port`, `instance.token` | Present while Torrenta runs, for a second launch to hand over | No |
