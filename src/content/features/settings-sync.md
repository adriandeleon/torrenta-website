---
title: "Settings and sync"
group: "Your desktop"
order: 2
beta: false
summary: "A searchable Settings window where every change applies at once, a plain file under it, and the same settings on every computer through a Git repository of yours."
---

- **Settings** (`Ctrl+,`): General, Keyboard, Adding torrents, Watch folders, Automation, Search, RSS, Sync, Remote access and Categories and tags for the application; Engine, Connection, Protocol, IP filter, Speed and Queue and share limits for the engine. Every control applies as you change it, with no OK button. The search box finds a setting by its title, its description or the words you would use for it, and opens the page that answers best.
- **A plain file**: everything is `settings.properties` in `~/.torrenta`, readable and editable by hand; `--config-dir` moves the folder.
- **Sync through Git**: connect Settings → Sync to a private repository (Create on GitHub makes one through the `gh` CLI) and every computer that connects to it keeps the same settings, merged one key at a time, this machine winning a key changed on both. It runs after startup, half a minute after a change and every few minutes, never asking for a password. Your [shortcuts](/features/custom-shortcuts), [saved views](/features/saved-views), the names of categories and tags, and the search providers travel too; folders, the network interface, passwords and API keys, Remote access and publishing keys stay on each machine.
