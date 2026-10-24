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
| Paused | `‖` | muted, dimmed | "Paused by you" |
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

A leading `-` negates a term (`-tag:keep`), and double quotes keep spaces in a value
(`cat:"Linux ISOs"`). A term the box cannot read becomes a red chip saying why. `Backspace` on an
empty box hands the last chip back for editing, and a click on a chip removes it.

**Run a command.** Type `>` and a name: the palette opens with what you typed.

**Add.** Paste a magnet, a link to a `.torrent` or a path to one, and the Add dialog opens. A
link to any other file opens Download Link (see [Adding torrents](/docs/adding-torrents#links-that-are-not-torrents)).

`Enter` selects every match and returns to the list, so the next key acts on all of them. `Esc`
peels one layer at a time: the box, then the list's filter, then the selection.

## The sidebar

The sidebar has **All**, then four sections, each entry with a live count:

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
- **The port**, with a dot: **open** once a peer from outside your network has connected,
  **checking reachability** until then, **blocked** after five minutes of running torrents with no
  such peer, or **not listening**. Its tooltip also says whether UPnP or NAT-PMP forwarded it.
  See [Connection](/docs/connection#what-the-port-state-means).
- **DHT**: off, or the number of nodes in the routing table.
- **Rates**, `↓` and `↑`, each with a sparkline of the last minute.
- **Alt speed**: a click switches the alternative speed limits (also `Ctrl+Shift+J`).
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
| `Ctrl+A` | Select all |

**Set Location…** (Torrent menu and palette) moves the selection's files to another folder,
typed or browsed, with that volume's free space under the field. A file already at the
destination is kept, never replaced; the row reads "Moving files" in between. Untick **Move the
files there** when the files are already in that folder: they are checked there instead.

**Super Seeding** (Torrent menu) applies to the complete torrents of the selection.
