---
title: Keyboard
description: How Torrenta's keys are organised, the command palette, and the keys that work inside the sidebar, dialogs and tabs.
category: Customization
order: 14
---

Torrenta is meant to be run from the keyboard. The mouse works everywhere, but nothing needs it.

The full list of commands and their chords is on the [Keybindings](/keybindings) page. This
page covers the rules behind them and the keys that belong to one part of the window.

## The principles

- **Every action is a command.** A command has one name, one key and one action, and the menu
  bar, the list's right-click menu, the toolbar and the palette all run the same command. A
  command that cannot run right now is disabled in all of them at once.
- **Chords are declared once**, on the menu item, so the menu and the key cannot disagree. A
  chord is `Ctrl+key` on Linux and Windows and `Cmd+key` on macOS. `Ctrl+Tab` and
  `Ctrl+Shift+Tab` are `Ctrl` everywhere, since `Cmd+Tab` belongs to macOS.
- **Some keys act on the list only.** `Space` (pause or resume), `Delete` or `Backspace`
  (remove), `Shift+Delete` (remove with data) and `F2` (rename) work while the list has the
  focus, never as window-wide keys. A space typed into the omnibox is just a space.
- **The chrome never takes the focus.** The omnibox, the sidebar and the toolbar are skipped by
  `Tab`; `Ctrl+K` and `Ctrl+B` reach the first two.
- **Every action applies to the whole selection.** `Ctrl+A` selects all, and `Enter` in the
  omnibox selects every match.

## The command palette

`Alt+X` opens the palette: every command, found by typing part of its name. The matched letters
are highlighted, and each row shows its key, so the palette is also how you learn them. `↑` `↓`
move, `Enter` runs, `Esc` closes.

**Settings → General → Keyboard** can make the key `Ctrl+Shift+P` instead. Typing `>` in the
omnibox opens the palette too, with what you typed.

The palette also has commands with no key, such as **Show: Downloading** and the other sidebar
statuses, **Run RSS Rules Now**, **Switch engine profile…**, **Toggle Toolbar** and **Debug
Log**.

## The omnibox

`Ctrl+K` or `Ctrl+F`. See [The transfer list](/docs/transfer-list#the-omnibox) for the filter
terms.

| Key | Does |
| --- | --- |
| `Space` after a `key:value` term | Turns it into a chip |
| `Backspace` on an empty box | Hands the last chip back for editing |
| `>` first | Opens the palette with what you typed |
| `Enter` | Selects every match and returns to the list |
| `Esc` | Empties the box, or returns to the list when it is empty |

On the list, `Esc` clears the filter first, then the selection.

## The sidebar

`Ctrl+B` focuses it, `Ctrl+Shift+B` shows or hides it.

| Key | Does |
| --- | --- |
| `↑` `↓` | Walk every section |
| `Space` | Make the entry its section's choice |
| `Enter` | Choose it and return to the list |
| `Esc` | Return to the list without choosing |
| `←` `→` | Fold or unfold a section |

## Views

| Key | Does |
| --- | --- |
| `Ctrl+1`, `Ctrl+2`, `Ctrl+3` | Transfers, Search, RSS |
| `Ctrl+Tab` / `Ctrl+Shift+Tab` | Next / previous view |
| `Esc` in Search or RSS | Back to the transfers |

Each view takes the focus as it opens: the list, the search query, or the articles (the feeds
while there are none).

## The Add dialog

| Key | Does |
| --- | --- |
| `Enter` | Add (for a magnet still fetching: Add now, pick files when ready) |
| `Shift+Enter` | Add paused (the other way round when Start paused is the default) |
| `Ctrl+Enter` | Wait here for the magnet's file list |
| `Esc` | Cancel |

In its file tree:

| Key | Does |
| --- | --- |
| `Space` | Tick or untick the file or folder |
| `+` / `−` | Step the priority: Skip, Low, Normal, High |
| `←` `→` | Fold or unfold a folder |

## The Search tab

| Key | Where | Does |
| --- | --- | --- |
| `Enter` | Query | Search every chosen provider |
| `Tab` | Anywhere | Move between the query, the chip rows and the results |
| `Space` | A chip | Toggle it |
| `Down` | Query | Into the results |
| `Enter` | A result | Open the Add dialog for it |
| `Ctrl+C` | A result | Copy its link |
| `Esc` | Results | Back to the query |

## The RSS tab

| Key | Does |
| --- | --- |
| Arrows | Walk the feeds and the articles; `←` `→` move between the two lists |
| `Down` in the filter box | Into the articles |
| `Enter` | Open the Add dialog for the article's torrent |
| `M` | Toggle read |
| `Shift+M` | Mark the feed read |
| `N` | Add a feed (`Ctrl+N` is still New Torrent) |
| `F5` / `Shift+F5` | Refresh the feed / every feed |
| `Delete` in the feed list | Remove the feed, after asking |
| `Ctrl+C` | Copy the torrent or the page link |

## Elsewhere

- **Messages** (`Ctrl+Shift+M`): `↑` `↓` move, `Ctrl+C` copies, `Esc` closes.
- **Settings** (`Ctrl+,`): `Esc` clears the search first, then closes the window.
- **Event Log** (`Ctrl+Shift+L`), **Statistics** (`Ctrl+Shift+T`) and **Debug Log** each open in
  a window of their own.
