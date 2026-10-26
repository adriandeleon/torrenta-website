---
title: "The omnibox"
group: "Keyboard & window"
order: 2
beta: false
summary: "One box above the list (<kbd>Ctrl+K</kbd>) that filters by name or by <code>state:seeding</code>, <code>size&gt;4g</code>, <code>added&lt;7d</code>; runs a command after <code>&gt;</code>; and adds a magnet you paste into it."
---

The filter box is an omnibox. Words filter the list by name as you type, and a `key:value` term becomes a chip the moment you type the space after it.

- **Terms** such as `state:seeding`, `cat:Films`, `tag:keep`, `-tag:keep`, `ratio<1`, `size>4g`, `added<7d`, `seeding>2d`, `eta<30m`, `seeds<3`, `uploaded>10g`, `tracker:host`, `hash:7a3f` and `is:link`. A term the box cannot read becomes a red chip that says why.
- **Chips are editable.** `Backspace` on an empty box hands the last chip back as text; a click removes one.
- **`>` runs a command**: the palette opens with what you typed after it. A command with an argument, such as `>set-category Films` or `>limit-down 500k`, acts on [every torrent the filter shows](/features/bulk-commands).
- **`Ctrl+S` saves the filter** as a [view](/features/saved-views) in the sidebar.
- **A pasted magnet, `.torrent` link or path** opens the Add dialog; a link to any other file is [downloaded from the link](/features/link-downloads).
- **`Enter` selects every match** and returns to the list, so the next key (`Space`, `Delete`, `Ctrl+Shift+G`…) acts on all of them.
- **`Esc` peels one layer at a time**: the box, then the list's filter, then the selection.

`Ctrl+F` reaches the box too.
