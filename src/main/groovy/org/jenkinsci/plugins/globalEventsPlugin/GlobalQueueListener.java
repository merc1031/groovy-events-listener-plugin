package org.jenkinsci.plugins.globalEventsPlugin;

import hudson.Extension;
import hudson.model.Queue.BlockedItem;
import hudson.model.Queue.BuildableItem;
import hudson.model.Queue.LeftItem;
import hudson.model.Queue.WaitingItem;
import hudson.model.queue.QueueListener;
import jenkins.model.Jenkins;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Warning: This MUST stay a Java class, Groovy cannot compile (for some reason??).
 */
@Extension
public class GlobalQueueListener extends QueueListener {

    protected static Logger log = Logger.getLogger(GlobalQueueListener.class.getName());

    /**
     * This class is lazy loaded (as required).
     */
    public GlobalQueueListener() {
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
    public void onEnterWaiting(final WaitingItem item) {
        this.getParentPluginDescriptor().processEvent(Event.QUEUE_ENTER_WAITING, log, new HashMap<Object, Object>() {{
            put("item", item);
        }});
    }

    @Override
    public void onLeaveWaiting(final WaitingItem item) {
        this.getParentPluginDescriptor().processEvent(Event.QUEUE_LEAVE_WAITING, log, new HashMap<Object, Object>() {{
            put("item", item);
        }});
    }

    @Override
    public void onEnterBlocked(final BlockedItem item) {
        this.getParentPluginDescriptor().processEvent(Event.QUEUE_ENTER_BLOCKED, log, new HashMap<Object, Object>() {{
            put("item", item);
        }});
    }

    @Override
    public void onLeaveBlocked(final BlockedItem item) {
        this.getParentPluginDescriptor().processEvent(Event.QUEUE_LEAVE_BLOCKED, log, new HashMap<Object, Object>() {{
            put("item", item);
        }});
    }

    @Override
    public void onEnterBuildable(final BuildableItem item) {
        this.getParentPluginDescriptor().processEvent(Event.QUEUE_ENTER_BUILDABLE, log, new HashMap<Object, Object>() {{
            put("item", item);
        }});
    }

    @Override
    public void onLeaveBuildable(final BuildableItem item) {
        this.getParentPluginDescriptor().processEvent(Event.QUEUE_LEAVE_BUILDABLE, log, new HashMap<Object, Object>() {{
            put("item", item);
        }});
    }

    @Override
    public void onLeft(final LeftItem item) {
        this.getParentPluginDescriptor().processEvent(Event.QUEUE_LEFT, log, new HashMap<Object, Object>() {{
            put("item", item);
        }});
    }
}

