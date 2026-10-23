---
title: The signal handler libtorrent took from the JVM
description: "Exit 139, no crash report, any time after a big file was touched. Why a memory-mapped disk backend and a JVM cannot share a process, and what Torrenta does instead."
date: 2026-10-22
tags: [engine, libtorrent, jvm]
---

For a while Torrenta died now and then with nothing to show for it. The shell
said `Segmentation fault`, the exit status was 139, and there was no
`hs_err_pid` file, the report the JVM writes whenever it crashes. A JVM that
crashes without writing one has usually not crashed in the way it knows about.

## The JVM uses SIGSEGV on purpose

HotSpot faults on purpose. Its safepoint polls, among other things, are reads
of a page it can make unreadable when it wants every thread to stop; the read
faults, the JVM's own `SIGSEGV` handler recognises the address, and the thread
parks. A segmentation fault inside a JVM is routine, as long as the JVM's
handler is the one that sees it.

## libtorrent's disk I/O installs its own

libtorrent 2 reads and writes pieces through memory-mapped files. A mapped
file can fault for reasons that are not bugs (a disk that fills, a file that
shrinks under the mapping), so libtorrent guards its accesses with a
`SIGSEGV` and `SIGBUS` handler of its own, installed with `sigaction`, and it
does not chain to the handler that was there before.

That handler goes in the first time a file large enough to map is touched:
one of at least `mmap_file_size_cutoff` pieces, 40 by default. From then on,
a fault that is not libtorrent's, such as the JVM's next safepoint poll, is
handed back to the default action, and the default action for `SIGSEGV` is to
end the process. No JVM code runs, so no report is written. It happened at
whatever moment the next poll fell, typically during a collection, which is why
it looked random.

The shape that reproduced it was small: a 2&nbsp;MiB file in 16&nbsp;KiB pieces
(128 pieces, over the cutoff), seeded while another thread drives the garbage
collector. That is now a test.

## Never map

The fix is a setting. Torrenta's engine sets `mmap_file_size_cutoff` to the
largest integer, so no file is ever big enough to map. The same disk I/O then
reads and writes with `pread` and `pwrite`, and the guard is never installed.

One path did not go through the session's settings: hashing pieces when
creating a torrent (`set_piece_hashes`) sits on the same guarded I/O. So the
Create Torrent dialog hashes in Java. libtorrent still lists the files, lays
them out and writes the `.torrent`; the v1 piece hashes and the v2 piece layers
come from a hasher of Torrenta's own, which a test checks bit for bit against
libtorrent's, on files small enough to be safe.

## What it taught

Two native runtimes in one process share one table of signal handlers, and the
last to install wins. When a JVM process dies with no `hs_err` file, the first
question is now: who else called `sigaction`?
