---
title: The transfer list
description: Rows and columns, the omnibox, the sidebar, the view strip, the status bar, and the actions on a selection.
category: Torrents
order: 4
---

The centre of the window is the transfer list. Above it are the view strip and the omnibox; to
one side the sidebar; below it the status bar.

## Rows and columns

Rows update in place once a second, so the selection, the scroll position and the sort survive
every tick.

There are two row densities:

- **Comfortable** (the default): two lines per torrent, a state glyph beside the name, a thin
  progress bar and a status sentence.
- **Compact**: one line per torrent.

Switch with **View → Comfortable Rows / Compact Rows**, the palette's **Toggle Row Density**, or
**Settings → General → Appearance → Row density**. Each density keeps its own column layout.

The header's **⋮** menu chooses among eighteen columns: Name, Size, Done, Progress, Status,
Seeds, Peers, Down, Up, ETA, Ratio, Added, Completed, Tracker, Category, Tags, Save path and
Queue. Category, Tags and Queue are hidden by default. Order, widths and visibility are
remembered. Columns sort as numbers where they are numbers.

### Row states

Each row has one state, shown as a glyph and the start of its status sentence:

| State | Glyph | Colour | Status sentence starts |
| --- | --- | --- | --- |
| Error | `×` | red | "Error" |
| Stalled | `!` | amber | "Stalled" (downloading, but nothing received for a minute) |
| Downloading | `↓` | accent | the peers and seeds connected |
| Fetching metadata | `◌` | muted | "Fetching metadata" |
| Checking | `⟳` | muted | "Checking files" |
| Moving | `→` | muted | "Moving files" |
| Queued | `…` | muted, dimmed | "Queued #2", "starts when a download slot frees" |
| Seeding | `↑` | indigo | "Seeding", to how many peers |
| Paused | `‖` | muted, dimmed | "Paused by you", or "Files to choose" for one added to pick its files later |
| Done | `✓` | green, dimmed | "Done" |

"Paused" means paused by a person. A torrent the queue is holding back is "Queued", not paused,
and the queue will not resume a torrent you paused. **Settings → General → Appearance → Colour
rows by state** turns the colours off; the glyphs and sentences say the same in monochrome.

## The omnibox

`Ctrl+K` (or `Ctrl+F`) focuses the box above the list. It does three things.

**Filter.** Plain words filter by name as you type. A `key:value` term becomes a chip when you
type the space after it:

| Term | Matches |
| --- | --- |
| `state:seeding` | the state: `downloading`, `seeding`, `paused`, `checking`, `metadata`, `finished` or `error` |
| `cat:Films` or `category:Films` | the category, ignoring case |
| `tag:keep` | one of the tags |
| `tracker:host` | part of any tracker's host |
| `hash:7a3f` | the start of the info-hash |
| `is:sequential` | torrents downloading in order |
| `is:link` / `is:torrent` | downloads from links, or torrents |
| `ratio<1` | ratio compared with `<`, `<=`, `>`, `>=`, `=` |
| `size>4g` | size, with `k`, `m`, `g` or `t` |
| `added<7d` | added within the last seven days (`h`, `d`, `w`) |
| `seeding>2d` | time spent seeding, in all (`m`, `h`, `d`, `w`) |
| `eta<30m` | the time left the ETA column shows |
| `seeds<3` | connected seeds |
| `uploaded>10g` | sent over the torrent's life, with `k`, `m`, `g` or `t` |

A leading `-` negates a term (`-tag:keep`), and double quotes keep spaces in a value
(`cat:"Linux ISOs"`). A term the box cannot read becomes a red chip saying why. `Backspace` on an
empty box hands the last chip back for editing, and a click on a chip removes it.

**Run a command.** Type `>` and a name: the palette opens with what you typed. A command can
take an argument and act on every match; see [Commands with arguments](#commands-with-arguments).

**Add.** Paste a magnet, a link to a `.torrent` or a path to one, and the Add dialog opens. A
link to any other file opens Download Link (see [Adding torrents](/docs/adding-torrents#links-that-are-not-torrents)).

`Enter` selects every match and returns to the list, so the next key acts on all of them. `Esc`
peels one layer at a time: the box, then the list's filter, then the selection. `Ctrl+S` saves
what is in the box as a [view](#saved-views).

### Commands with arguments

In the omnibox, `>` and a command with what it takes:

| Command | Takes |
| --- | --- |
| `>set-category Films` | a category |
| `>add-tag keep`, `>remove-tag old` | a tag |
| `>set-location /srv/media` | a folder |
| `>limit-down 500k`, `>limit-up 1m` | a rate (`k`, `m`, `g`), or `none` |
| `>sequential on` | `on` or `off` |
| `>queue top` | `top`, `up`, `down` or `bottom` |
| `>pause`, `>resume`, `>recheck`, `>reannounce`, `>remove` | nothing |

While a filter is in the box, the command acts on **every torrent the filter shows**; otherwise
on the selection. The palette's row says which and how many before `Enter` ("Pause: 14 matching
torrents"). A removal asks first, and so does anything on more than 20 torrents. A category,
tags, a location or a limit set this way can be [undone](#undo).

### Saved views

Type a filter (`state:seeding ratio<1 added<30d`, say), press `Ctrl+S` (**View → Save Filter as
View…**) and name it. It joins a **Views** section at the top of the sidebar with a live count,
and choosing it filters the list as the query does, together with the box and the other
sections. `Ctrl+Alt+1` to `Ctrl+Alt+9` choose the first nine.

A view's right-click menu renames it, replaces its query with the current filter, or deletes it.
A view whose query no longer reads is struck through, the reason in its tooltip, rather than
silently empty. Views [sync](/docs/settings#settings-sync) with the other settings, each on its
own.

## The sidebar

The sidebar has your [saved views](#saved-views) at the top, then **All**, then four sections,
each entry with a live count:

- **Status**: Downloading, Seeding, Completed, Paused, Active (moving now), Inactive, Stalled,
  Checking, Errored, and From links (downloads that are not torrents).
- **Categories**, plus Uncategorised.
- **Tags**, plus Untagged.
- **Trackers**: one entry per tracker host, plus Trackerless, and Tracker error while a tracker
  answers with one.

Each section keeps one choice, and the list shows what passes all of them and the omnibox. The
palette has a **Show: …** command for each status.

From the keyboard: `Ctrl+B` focuses the sidebar; the arrows walk every section, `Space` makes an
entry its section's choice, `Enter` chooses and returns to the list, `Esc` returns without
choosing, and `Left`/`Right` fold a section. With the mouse, a click on a section's heading folds
or opens it; a chevron at its right edge says which. `Ctrl+Shift+B` shows or hides it.

Drag the sidebar's edge that faces the list to make it wider or narrower (150 to 480 px); a
double-click on the edge goes back to the default.

The sidebar sits on the left or the right, or is hidden: **View → Sidebar on the Right**, the
palette's **Move Sidebar to the Other Side**, or **Settings → General → Appearance → Sidebar**.
The choices, the folded sections, the width and whether it shows are remembered.

## Simple mode

**View → Simple Mode** (also the toolbar's switch beside Settings, **Settings → General**, or
`torrenta --simple` for one session) leaves only what someone new to torrents needs:

- **Kept**: the list; every way a torrent arrives (a file, a magnet, the clipboard, a drop);
  pause and resume, remove, choose files, open the folder, copy the magnet; and the diagnosis
  card's remedies.
- **Put away**: the sidebar, the Search and RSS views, the rarer commands (in the menus, the
  right-click menu, the toolbar, the palette and on their shortcuts), the status bar's port and
  DHT, the detail pane's Peers, Trackers, Log and Speed tabs, and the Add dialog's category,
  tags, stop condition, layout and options.

The list shows comfortable rows, whatever the saved density, with the name (its progress bar and
status sentence inside), the size, the two rates and the time left. Every other column is still
in the column menu; columns moved or shown in Simple mode are remembered for it alone.

Nothing is changed by the mode. The sidebar keeps its place and its choice (which stops narrowing
the list while it is hidden), the detail pane its tab, and turning the mode off brings each back
as it was. RSS rules, watch folders and automation keep running.

## The view strip

Transfers, Search and RSS share the centre of the window. A thin strip above it names the three,
marks the one showing, and shows the key for each; a click switches.

| Key | View |
| --- | --- |
| `Ctrl+1` (or `Esc` from Search or RSS) | Transfers |
| `Ctrl+2` (or `Ctrl+Shift+F`) | [Search](/docs/search) |
| `Ctrl+3` (or `Ctrl+R`) | [RSS](/docs/rss) |
| `Ctrl+Tab` / `Ctrl+Shift+Tab` | Next / previous view (`Ctrl` on macOS too) |

## The status bar

It shows:

- **The latest message.** A click on it, `Ctrl+Shift+M` or **View → Messages** shows everything
  the status bar said this session, newest first, with a filter box and Copy. A long message is
  shortened; the rest of the bar keeps its width.
- **The port**, with a dot. With **Bind to** set, it turns red when the interface is down or
  missing ("tun0 missing · reaching no one"), and a shield beside it gives the
  [leak check](/docs/connection#the-leak-check)'s verdict. Otherwise: **open** once a peer from outside your network has connected,
  **checking reachability** until then, **blocked** after five minutes of running torrents with no
  such peer, or **not listening**. Its tooltip also says whether UPnP or NAT-PMP forwarded it.
  See [Connection](/docs/connection#what-the-port-state-means).
- **DHT**: off, or the number of nodes in the routing table.
- **Rates**, `↓` and `↑`, each with a sparkline of the last minute.
- **Alt speed**: a click switches the alternative speed limits (also `Ctrl+Shift+J`). The pill
  says why they are on: by hand, scheduled, [on battery or a metered network](/docs/queue-and-limits#battery-and-metered-networks).
- **Free space** where downloads go; a click opens the folder.
- **Errors**: a count that appears while there are errors; a click opens the Event Log.

The Event Log (`Ctrl+Shift+L`) lists what the engine reported, filterable by level, with Copy and
Clear. The same message repeated within ten seconds is one line with a count. **Statistics**
(`Ctrl+Shift+T`) shows session and all-time totals and a speed graph.

## Acting on the selection

Every action applies to the whole selection and is in the **Torrent** menu, the right-click menu
and the palette:

| Key | Action |
| --- | --- |
| `Space` | Pause, or resume if all are paused |
| `Ctrl+Shift+U` | Force resume, ignoring the queue |
| `Ctrl+Shift+R` / `Ctrl+Shift+A` | Force recheck / force reannounce |
| `Ctrl+Shift+S` | Sequential download on or off |
| `Ctrl+L` | Limits… ([Categories, queue and limits](/docs/queue-and-limits)) |
| `Ctrl+Shift+G` / `Ctrl+T` | Set category / edit tags |
| `F2` | Rename: the name the list shows; the files keep theirs |
| `Ctrl+Shift+E` | Export `.torrent` |
| `Ctrl+C` / `Ctrl+Shift+H` / `Ctrl+Shift+C` | Copy name / info-hash / magnet link |
| `Ctrl+Shift+O` | Open the destination folder |
| `Delete` / `Shift+Delete` | Remove, or remove with the downloaded files, after asking |
| `Ctrl+Z` | Undo |
| `Ctrl+A` | Select all |

**Set Location…** (Torrent menu and palette) moves the selection's files to another folder,
typed or browsed, with that volume's free space under the field. A file already at the
destination is kept, never replaced; the row reads "Moving files" in between. Untick **Move the
files there** when the files are already in that folder: they are checked there instead.

**Super Seeding** (Torrent menu) applies to the complete torrents of the selection.

## Undo

Remove (keeping the files), Set Location, a category and tags can be taken back, whether they
came from a menu, a key or a [command in the omnibox](#commands-with-arguments). The status bar
offers **Undo** for ten seconds after each, and `Ctrl+Z` (**Torrent → Undo**) takes back the last
ten actions for ten minutes. In a text field, `Ctrl+Z` is the field's own.

A removed torrent comes back exactly as it was, with its category, tags, place in the queue and
share limit, and without a recheck. It is held in memory, so quitting ends the chance to bring
it back. With a [Transmission daemon](/docs/remote), a remove cannot be undone.

### Removed files go to the trash

Remove with files is final in Torrenta, but the files are moved to the desktop's trash rather than
deleted, so a mistake can be undone from the file manager. A whole torrent folder goes as one
item. A drive without a trash keeps the files and says so. The share limit's "remove with data"
and the [web API's](/docs/remote-access) delete do the same.

- **Linux**: the freedesktop.org trash, your home's, or the drive's own `.Trash-<uid>`, as file
  managers use.
- **macOS**: your Trash (`~/.Trash`, or the drive's `.Trashes`).
- **Windows**: the Recycle Bin.

**Settings → General → Move removed files to the trash** (on by default) turns it off, and the
files are deleted. The trash has not been tried by hand on macOS or Windows yet.
