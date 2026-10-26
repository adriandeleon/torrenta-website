---
title: "Commands over every match"
group: "Keyboard & window"
order: 7
beta: false
summary: "<code>&gt;set-category Films</code>, <code>&gt;add-tag keep</code>, <code>&gt;limit-down 500k</code>: typed in the omnibox, run on every torrent the filter shows."
---

Filter, then act on everything that is left, without selecting a row. In the omnibox, `>` and a command with what it takes:

| Command | Takes |
| --- | --- |
| `>set-category Films` | a category |
| `>add-tag keep`, `>remove-tag old` | a tag |
| `>set-location /srv/media` | a folder |
| `>limit-down 500k`, `>limit-up 1m` | a rate, or `none` |
| `>sequential on` | `on` or `off` |
| `>queue top` | `top`, `up`, `down` or `bottom` |
| `>pause`, `>resume`, `>recheck`, `>reannounce`, `>remove` | nothing |

- **Every match, or the selection.** While a filter is in the box the command acts on every torrent the filter shows; otherwise on the selection. The palette's row says which and how many before `Enter`.
- **Asks when it matters.** A removal asks first, and so does anything on more than 20 torrents.
- **Undoable.** A category, tags, a location or a limit set this way can be [taken back](/features/undo).

See [The transfer list](/docs/transfer-list#commands-with-arguments).
