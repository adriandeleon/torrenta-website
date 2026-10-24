// Hand-written highlights of the current release for the home page's "What's new" block. Update
// it with each release, from that release's news post.

export type NewsItem = { title: string; detail: string };

export const whatsNew: NewsItem[] = [
  { title: "Torrenta 0.2.0", detail: "Out for Linux (x64), macOS (Apple Silicon) and Windows (x64), each installer with its own Java runtime." },
  { title: "Downloads from links", detail: "A link to any file, not only a torrent, joins the list: fetched by curl, paused and resumed where it stopped, limited with the rest." },
  { title: "Told apart at a glance", detail: "A chain before a download's name, a From links entry in the sidebar, and is:link / is:torrent in the omnibox." },
  { title: "A sidebar that folds and resizes", detail: "A click folds a section, the edge drags wider or narrower, and both are remembered." },
  { title: "Details at once", detail: "The details pane fills in the moment you click a torrent, not at the next tick, and Ctrl+I shows or hides it." },
  { title: "Glyphs where they help", detail: "Beside each Settings page, and in colour on the details pane's Pause, Reannounce and Open folder." },
];
