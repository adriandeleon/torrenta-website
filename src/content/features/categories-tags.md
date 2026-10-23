---
title: "Categories and tags"
group: "Watching & managing"
order: 1
beta: false
summary: "One category per torrent, with its own save path, and any number of tags. Filed from the keyboard, filtered in the sidebar and the omnibox."
---

- **Categories** have a name and, optionally, a save path. Choosing one in the Add dialog fills the folder, so new torrents in it land there; with an incomplete folder, a completed torrent moves to its category's folder.
- **Tags** are free: any number per torrent.
- **From the keyboard**: `Ctrl+Shift+G` sets the selection's category (one that exists, none, or a new one), `Ctrl+T` edits its tags (a checkbox per tag, a field for new ones). The Torrent and context menus list the categories as a submenu.
- **Everywhere you look**: sidebar sections with counts, `cat:` and `tag:` in the omnibox, Category and Tags columns.

Settings → Categories and tags adds, repaths and deletes them. They are two small JSON files you can edit by hand; which torrent carries which sits beside its resume data.
