package org.jenkinsci.plugins.globalEventsPlugin;

import hudson.Extension;
import hudson.Plugin;
import hudson.model.TaskListener;
import hudson.model.listeners.SaveableListener;
import hudson.model.Saveable;
import hudson.XmlFile;
import jenkins.model.Jenkins;

import java.util.HashMap;
import java.util.logging.Logger;
/**
 * Warning: This MUST stay a Java class, Groovy cannot compile (for some reason??).
 */
@Extension
public class GlobalSaveableListener extends SaveableListener {

    protected static Logger log = Logger.getLogger(GlobalSaveableListener.class.getName());

    /**
     * This class is lazy loaded (as required).
     */
    public GlobalSaveableListener() {
        log.fine(">>> Initialised");
    }

    GlobalEventsPlugin.DescriptorImpl parentPluginDescriptorOverride = null;

    GlobalEventsPlugin.DescriptorImpl getParentPluginDescriptor() {
        if (parentPluginDescriptorOverride != null){
            return parentPluginDescriptorOverride;
        } else {
            GlobalEventsPlugin plugin = Jenkins.getInstance().getPlugin(GlobalEventsPlugin.class);
            return plugin != null ? plugin.getDescriptor() : null;
        }
    }

    @Override
    public void onChange(final Saveable o, final XmlFile file) {
        GlobalEventsPlugin.DescriptorImpl pluginImpl = this.getParentPluginDescriptor();
        if (pluginImpl != null) {
          pluginImpl.processEvent(Event.SAVEABLE_CHANGE, log, new HashMap<Object, Object>() {{
            put("o", o);
            put("file", file);
          }});
        }
    }


}

