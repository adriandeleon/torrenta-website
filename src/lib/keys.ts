import type { Chord } from "./commands";

// How a chord reads. "Shortcut" is Ctrl off macOS and ⌘ on it; the pages render the Ctrl form and
// a small script swaps in the Mac form on a Mac (see Layout's `data-shortcut` handling), so the
// static HTML is right for most visitors and for search engines.
export function chordText(chord: Chord, mac = false): string {
  if (mac) {
    const glyph: Record<string, string> = { Shortcut: "⌘", Ctrl: "⌃", Shift: "⇧", Alt: "⌥" };
    return chord.map((k) => glyph[k] ?? k).join("");
  }
  return chord.map((k) => (k === "Shortcut" ? "Ctrl" : k)).join("+");
}
