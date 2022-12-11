package org.heavenmc.heavenmc.server.configs;

import org.heavenmc.heavenmc.MainClass;
import org.heavenmc.heavenmc.utils.FileManager;

public class ActionBarConfig {
    public static final FileManager fileManager = new FileManager(MainClass.getPlugin());
    public static final FileManager.Config loc = fileManager.getConfig("actionbar.yml");

    public static FileManager.Config getLocation() { loc.reload(); loc.save(); return loc; }
}
