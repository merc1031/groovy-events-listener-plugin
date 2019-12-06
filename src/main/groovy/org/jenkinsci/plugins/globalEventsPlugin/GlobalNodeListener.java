package org.jenkinsci.plugins.globalEventsPlugin;

import hudson.Extension;
import hudson.model.Node;
import jenkins.model.NodeListener;
import jenkins.model.Jenkins;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Warning: This MUST stay a Java class, Groovy cannot compile (for some reason??).
 */
@Extension
public class GlobalNodeListener extends NodeListener {

    protected static Logger log = Logger.getLogger(GlobalNodeListener.class.getName());

    /**
     * This class is lazy loaded (as required).
     */
    public GlobalNodeListener() {
        log.fine(">>> Initialised");
    }

    GlobalEventsPlugin.DescriptorImpl parentPluginDescriptorOverride = null;

    GlobalEventsPlugin.DescriptorImpl getParentPluginDescriptor() {
        if (parentPluginDescriptorOverride != null) {
            return parentPluginDescriptorOverride;
        } else {
            return Jenkins.getInstance().getPlugin(GlobalEventsPlugin.class).getDescriptor();
        }
    }

    @Override
    public void onCreated(final Node node) {
        this.getParentPluginDescriptor().processEvent(Event.NODE_CREATED, log, new HashMap<Object, Object>() {{
            put("node", node);
        }});
    }

    @Override
    public void onUpdated(final Node oldOne, final Node newOne) {
        this.getParentPluginDescriptor().processEvent(Event.NODE_UPDATED, log, new HashMap<Object, Object>() {{
            put("oldOne", oldOne);
            put("newOne", newOne);
        }});
    }

    @Override
    public void onDeleted(final Node node) {
        this.getParentPluginDescriptor().processEvent(Event.NODE_DELETED, log, new HashMap<Object, Object>() {{
            put("node", node);
        }});
    }

}

