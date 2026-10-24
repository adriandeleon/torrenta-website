// Generates src/lib/commands.ts (the Keybindings page, the palette demo) and src/lib/version.ts
// from a Torrenta checkout: the Command records in MainWindow.buildCommands(), their labels in
// messages.properties, their chords in ui.Chords, the scene accelerators beside them, and the pom's
// version.
//
//   java scripts/GenCommands.java [/path/to/torrenta]
//
// The checkout is the argument, else $TORRENTA_REPO, else ../torrenta. Run it from the site's root.
// A JDK 25 compact source file (JEP 512): no build step, nothing outside java.base.

static final Path OUT = Path.of("src/lib");

// Which group of the Keybindings page each command sits in, in the page's order. The menus are
// built from helpers shared with the context menu, so the source cannot answer this reliably; an id
// missing here lands in "Other" and is reported, so a new command is never silently dropped.
static final LinkedHashMap<String, List<String>> GROUPS = groups();

// A compact source file has no static initialiser blocks, hence the method.
static LinkedHashMap<String, List<String>> groups() {
    var GROUPS = new LinkedHashMap<String, List<String>>();
    GROUPS.put("Adding torrents", List.of("create", "add-file", "add-magnet", "add-clipboard"));
    GROUPS.put(
            "The selection",
            List.of(
                    "pause-resume",
                    "force-resume",
                    "recheck",
                    "reannounce",
                    "sequential",
                    "super-seed",
                    "limits",
                    "set-location",
                    "choose-files",
                    "rename",
                    "export-torrent",
                    "open-folder",
                    "remove",
                    "remove-data",
                    "select-all"));
    GROUPS.put("Queue", List.of("queue-top", "queue-up", "queue-down", "queue-bottom"));
    GROUPS.put("Categories and tags", List.of("set-category", "edit-tags", "manage-categories"));
    GROUPS.put("Copy", List.of("copy-name", "copy-hash", "copy-magnet"));
    GROUPS.put("Views", List.of("transfers", "search", "rss", "view-next", "view-previous", "rss-run-rules"));
    GROUPS.put(
            "The window",
            List.of(
                    "palette",
                    "filter",
                    "focus-sidebar",
                    "show-all",
                    "toggle-sidebar",
                    "sidebar-side",
                    "toggle-detail",
                    "detail-side",
                    "toggle-toolbar",
                    "toggle-density"));
    GROUPS.put("Logs and statistics", List.of("event-log", "message-log", "statistics", "debug-log"));
    GROUPS.put(
            "Application",
            List.of("settings", "alt-speed", "switch-profile", "retry-engine", "check-updates", "about", "quit"));
    return GROUPS;
}

// Keys reached by more than the command's own accelerator: the scene accelerators registered in
// MainWindow beside the commands (Ctrl+F for the omnibox, the view digits).
static final Map<String, List<List<String>>> EXTRA_KEYS = Map.of(
        "filter", List.of(List.of("Shortcut", "F")),
        "transfers", List.of(List.of("Shortcut", "1")),
        "search", List.of(List.of("Shortcut", "2")),
        "rss", List.of(List.of("Shortcut", "3")));

record Cmd(String id, String title, List<List<String>> keys) {}

void main(String[] args) throws IOException {
    Path repo = Path.of(args.length > 0
                    ? args[0]
                    : Objects.requireNonNullElse(System.getenv("TORRENTA_REPO"), "../torrenta"))
            .toAbsolutePath()
            .normalize();
    Path window = repo.resolve("src/main/java/com/torrenta/ui/MainWindow.java");
    Path catalogue = repo.resolve("src/main/resources/com/torrenta/i18n/messages.properties");
    if (!Files.exists(window)) {
        System.err.println("No Torrenta checkout at " + repo + " (pass its path, or set TORRENTA_REPO)");
        System.exit(1);
    }

    Properties messages = new Properties();
    try (Reader r = Files.newBufferedReader(catalogue, StandardCharsets.UTF_8)) {
        messages.load(r);
    }
    Path chords = window.resolveSibling("Chords.java");
    if (Files.exists(chords)) CHORDS.putAll(chordTable(Files.readString(chords)));

    String source = Files.readString(window);
    int start = source.indexOf("private List<Command> buildCommands()");
    int end = source.indexOf("return List.copyOf(all);", start);
    if (start < 0 || end < 0) throw new IllegalStateException("buildCommands() not found in " + window);
    String body = source.substring(start, end);

    Map<String, Cmd> byId = new LinkedHashMap<>();
    for (int at = body.indexOf("new Command("); at >= 0; at = body.indexOf("new Command(", at + 1)) {
        List<String> a = arguments(body, at + "new Command(".length());
        String id = unquote(a.get(0));
        if (id == null) continue; // "show-" + status.key(): the generated Show: entries
        String title = label(a.get(1), messages);
        List<List<String>> keys = new ArrayList<>();
        List<String> accelerator = chord(a.get(2), messages);
        if (accelerator != null) keys.add(accelerator);
        List<String> hint = chord(a.get(3), messages);
        if (hint != null && !keys.contains(hint)) keys.add(hint);
        for (List<String> extra : EXTRA_KEYS.getOrDefault(id, List.of())) {
            if (!keys.contains(extra)) keys.add(extra);
        }
        byId.put(id, new Cmd(id, title, keys));
    }

    // The palette is not a Command (it lists them); its chord is PaletteKey.DEFAULT, Alt+X.
    String paletteTitle = messages.getProperty("toolbar.palette.tooltip", "Command Palette ({0})")
            .replaceAll("\\s*\\(\\{0\\}\\)", "");
    byId.put("palette", new Cmd("palette", paletteTitle, List.of(List.of("Alt", "X"))));

    List<String> warnings = new ArrayList<>();
    Set<String> grouped = new HashSet<>();
    StringBuilder ts = new StringBuilder();
    ts.append("// AUTO-GENERATED by scripts/GenCommands.java — do not edit by hand.\n")
            .append("// Source: MainWindow.buildCommands() and messages.properties in the Torrenta repository.\n")
            .append("// Re-run the script after a command or a chord changes.\n\n")
            .append("// A chord is its keys in order. \"Shortcut\" is Ctrl, or ⌘ on macOS; \"Ctrl\" is Control on\n")
            .append("// every platform (Ctrl+Tab). A key that is not a chord (Space, Delete) is a chord of one.\n")
            .append("export type Chord = string[];\n")
            .append("export type Cmd = { id: string; title: string; keys: Chord[] };\n")
            .append("export type CmdGroup = { title: string; commands: Cmd[] };\n\n")
            .append("export const commandGroups: CmdGroup[] = [\n");
    for (var group : GROUPS.entrySet()) {
        List<Cmd> cmds = new ArrayList<>();
        for (String id : group.getValue()) {
            Cmd c = byId.get(id);
            if (c == null) {
                warnings.add("grouped but no longer a command: " + id);
                continue;
            }
            cmds.add(c);
            grouped.add(id);
        }
        appendGroup(ts, group.getKey(), cmds);
    }
    List<Cmd> other =
            byId.values().stream().filter(c -> !grouped.contains(c.id())).toList();
    for (Cmd c : other) warnings.add("not in any group, listed under Other: " + c.id());
    if (!other.isEmpty()) appendGroup(ts, "Other", other);
    ts.append("];\n");

    // Two commands on one chord: only one of them can fire, so it is worth knowing.
    Map<List<String>, List<String>> owners = new LinkedHashMap<>();
    for (Cmd c : byId.values()) {
        for (List<String> k : c.keys()) owners.computeIfAbsent(k, x -> new ArrayList<>()).add(c.id());
    }
    owners.forEach((k, ids) -> {
        if (ids.size() > 1) warnings.add("chord " + String.join("+", k) + " is on " + String.join(", ", ids));
    });

    Files.createDirectories(OUT);
    Files.writeString(OUT.resolve("commands.ts"), ts.toString());
    String version = version(repo);
    Files.writeString(
            OUT.resolve("version.ts"),
            "// AUTO-GENERATED by scripts/GenCommands.java — do not edit by hand.\n"
                    + "// Source: Torrenta's pom.xml <version>, or the newest released CHANGELOG.md section\n"
                    + "// while the pom is on a -SNAPSHOT between releases.\n\n"
                    + "export const version = \"" + version + "\";\n");

    System.out.println("commands.ts: " + byId.size() + " commands in " + (GROUPS.size() + (other.isEmpty() ? 0 : 1))
            + " groups; version.ts: " + version + " (from " + repo + ")");
    for (String w : warnings) System.err.println("warning: " + w);
}

static void appendGroup(StringBuilder ts, String title, List<Cmd> cmds) {
    if (cmds.isEmpty()) return;
    ts.append("  {\n    title: ").append(js(title)).append(",\n    commands: [\n");
    for (Cmd c : cmds) {
        ts.append("      { id: ").append(js(c.id())).append(", title: ").append(js(c.title())).append(", keys: [");
        ts.append(String.join(
                ", ",
                c.keys().stream()
                        .map(k -> "[" + String.join(", ", k.stream().map(x -> js(x)).toList()) + "]")
                        .toList()));
        ts.append("] },\n");
    }
    ts.append("    ],\n  },\n");
}

/** The top-level arguments of the call whose argument list starts at {@code from}. */
static List<String> arguments(String s, int from) {
    List<String> out = new ArrayList<>();
    int depth = 0;
    boolean inString = false;
    StringBuilder cur = new StringBuilder();
    for (int i = from; i < s.length(); i++) {
        char ch = s.charAt(i);
        if (inString) {
            cur.append(ch);
            if (ch == '\\') cur.append(s.charAt(++i));
            else if (ch == '"') inString = false;
            continue;
        }
        switch (ch) {
            case '"' -> {
                inString = true;
                cur.append(ch);
            }
            case '(', '{', '[' -> {
                depth++;
                cur.append(ch);
            }
            case ')', '}', ']' -> {
                if (depth == 0) {
                    out.add(cur.toString().strip());
                    return out;
                }
                depth--;
                cur.append(ch);
            }
            case ',' -> {
                if (depth == 0) {
                    out.add(cur.toString().strip());
                    cur.setLength(0);
                } else cur.append(ch);
            }
            default -> cur.append(ch);
        }
    }
    throw new IllegalStateException("unbalanced call at " + from);
}

static final Pattern TR = Pattern.compile("tr\\(\"([^\"]+)\"(?:,\\s*([^)]+))?\\)");
static final Pattern CHORD = Pattern.compile("(chord|shiftChord)\\(KeyCode\\.(\\w+)\\)");
static final Pattern CYCLE = Pattern.compile("cycleChord\\((true|false)\\)");
static final Pattern CHORDS_OF = Pattern.compile("Chords\\.of\\(\"([^\"]+)\"\\)");

// ui.Chords, by name: every chord the window binds, in one table since Torrenta 0.2.0.
static final Map<String, List<String>> CHORDS = new LinkedHashMap<>();

/** The {@code t.put(name, …)} lines of Chords.java as keys: chord, shiftChord or a KeyCodeCombination. */
static Map<String, List<String>> chordTable(String source) {
    Map<String, List<String>> table = new LinkedHashMap<>();
    Matcher m = Pattern.compile("t\\.put\\(\\s*\"([^\"]+)\",\\s*(chord|shiftChord)\\(KeyCode\\.(\\w+)\\)\\)")
            .matcher(source);
    while (m.find()) {
        List<String> keys = new ArrayList<>(List.of("Shortcut"));
        if (m.group(2).equals("shiftChord")) keys.add("Shift");
        keys.add(keyName(m.group(3)));
        table.put(m.group(1), keys);
    }
    m = Pattern.compile("t\\.put\\(\\s*\"([^\"]+)\",\\s*new KeyCodeCombination\\(KeyCode\\.(\\w+)((?:\\s*,\\s*KeyCombination\\.\\w+)*)\\)\\)")
            .matcher(source);
    while (m.find()) {
        List<String> keys = new ArrayList<>();
        String mods = m.group(3);
        if (mods.contains("SHORTCUT_DOWN")) keys.add("Shortcut");
        if (mods.contains("CONTROL_DOWN")) keys.add("Ctrl");
        if (mods.contains("ALT_DOWN")) keys.add("Alt");
        if (mods.contains("SHIFT_DOWN")) keys.add("Shift");
        keys.add(keyName(m.group(2)));
        table.put(m.group(1), keys);
    }
    return table;
}

static String label(String expr, Properties messages) {
    Matcher m = TR.matcher(expr);
    if (!m.find()) throw new IllegalStateException("label is not a tr(...): " + expr);
    String text = messages.getProperty(m.group(1));
    if (text == null) throw new IllegalStateException("no message for " + m.group(1));
    // The one argument a command label takes is the application's name.
    return m.group(2) == null ? text : text.replace("{0}", "Torrenta");
}

/** A chord or hint expression as its keys, or null for {@code null}. */
static List<String> chord(String expr, Properties messages) {
    if (expr.equals("null")) return null;
    Matcher m = CHORD.matcher(expr);
    if (m.find()) {
        List<String> keys = new ArrayList<>(List.of("Shortcut"));
        if (m.group(1).equals("shiftChord")) keys.add("Shift");
        keys.add(keyName(m.group(2)));
        return keys;
    }
    m = CHORDS_OF.matcher(expr);
    if (m.find()) {
        List<String> keys = CHORDS.get(m.group(1));
        if (keys == null) throw new IllegalStateException("no chord named " + m.group(1) + " in ui.Chords");
        return keys;
    }
    m = CYCLE.matcher(expr);
    if (m.find()) return m.group(1).equals("true") ? List.of("Ctrl", "Shift", "Tab") : List.of("Ctrl", "Tab");
    String literal = unquote(expr);
    if (literal != null) return List.of(literal.split("\\+"));
    m = TR.matcher(expr);
    if (m.find()) return List.of(messages.getProperty(m.group(1)).split("\\+"));
    throw new IllegalStateException("cannot read the chord in: " + expr);
}

static String keyName(String code) {
    return switch (code) {
        case "COMMA" -> ",";
        case "HOME" -> "Home";
        case "END" -> "End";
        case "UP" -> "Up";
        case "DOWN" -> "Down";
        case "TAB" -> "Tab";
        default -> code.startsWith("DIGIT") ? code.substring(5) : code;
    };
}

static String unquote(String expr) {
    return expr.length() >= 2 && expr.startsWith("\"") && expr.endsWith("\"")
            ? expr.substring(1, expr.length() - 1)
            : null;
}

static String version(Path repo) throws IOException {
    String pom = Files.readString(repo.resolve("pom.xml"));
    // The project's own <version> is the first one, ahead of the parent-less dependency list.
    Matcher m = Pattern.compile("<version>([^<]+)</version>").matcher(pom);
    String v = m.find() ? m.group(1).strip() : "";
    if (!v.isEmpty() && !v.endsWith("-SNAPSHOT")) return v;
    Matcher released = Pattern.compile("(?m)^## (\\d+\\.\\d+\\.\\d+)\\b")
            .matcher(Files.readString(repo.resolve("CHANGELOG.md")));
    if (released.find()) return released.group(1);
    throw new IllegalStateException("no released version in pom.xml or CHANGELOG.md");
}

static String js(String s) {
    return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
}
