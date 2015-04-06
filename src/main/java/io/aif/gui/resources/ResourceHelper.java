package io.aif.gui.resources;

import java.net.URL;

public class ResourceHelper {

    private static final ResourceHelper helper = InstanceHolder.getInstance();

    public static URL getResourceURL(String resource) {
        return helper.getClass().getResource(resource);
    }


    private static class InstanceHolder {
        private final static ResourceHelper INSTANCE = new ResourceHelper();
        public static ResourceHelper getInstance() {
            return INSTANCE;
        }
    }
}
