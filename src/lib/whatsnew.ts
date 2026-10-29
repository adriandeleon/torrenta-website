// Hand-written highlights of the current release for the home page's "What's new" block. Update
// it with each release, from that release's news post.

export type NewsItem = { title: string; detail: string };

export const whatsNew: NewsItem[] = [
  { title: "Torrenta 0.4.1", detail: "Out for Linux (x64), macOS (Apple Silicon) and Windows (x64). It fixes 0.4.0 downloading finished torrents again after a restart, and repairs the ones it caught." },
  { title: "Simple mode", detail: "View → Simple Mode leaves the list, the ways a torrent arrives and the actions someone new needs; turning it off brings everything back as it was." },
  { title: "Report a Problem", detail: "Help → Report a Problem… writes a GitHub issue from the debug log, with paths, names, hashes and addresses taken out first. Torrenta sends nothing itself." },
  { title: "Tags at hand", detail: "The Add dialog shows a chip for every tag you have and suggests them as you type." },
  { title: "Thousands of torrents", detail: "The list sorts once a second, the detail pane keeps up with 50,000 files, and a 300,000-line blocklist parses in about 70 ms." },
  { title: "Calmer logs", detail: "A repeated warning is counted instead of written 28,000 times, and the last session's log survives a crash for the report." },
];
