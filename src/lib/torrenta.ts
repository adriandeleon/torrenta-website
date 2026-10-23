// The Torrenta release version is generated from the app's pom.xml into version.ts by
// scripts/GenCommands.java and committed, so the build never reads the Torrenta repository (it
// isn't checked out in CI). Re-exported here so pages import everything from one place.
import { version } from "./version";

export { version };

export const repo = "https://github.com/adriandeleon/torrenta";
export const latestRelease = `${repo}/releases/latest`;
