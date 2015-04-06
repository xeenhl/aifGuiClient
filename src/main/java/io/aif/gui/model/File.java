package io.aif.gui.model;

import java.net.URI;

public class File extends java.io.File {
    public File(String pathname) {
        super(pathname);
    }

    public File(String parent, String child) {
        super(parent, child);
    }

    public File(java.io.File parent, String child) {
        super(parent, child);
    }

    public File(URI uri) {
        super(uri);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
