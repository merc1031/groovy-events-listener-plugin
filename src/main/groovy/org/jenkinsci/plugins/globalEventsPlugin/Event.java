package org.jenkinsci.plugins.globalEventsPlugin;

/**
 * Contains constants for all available event names.
 */
public final class Event {

    private Event() {}

    public static final String PLUGIN_STARTED = "GlobalEventsPlugin.start";
    public static final String PLUGIN_STOPPED = "GlobalEventsPlugin.stop";
    public static final String PLUGIN_SCHEDULE = "GlobalEventsPlugin.schedule";

    public static final String JOB_INITIALIZE = "RunListener.onInitialize";
    public static final String JOB_DELETED = "RunListener.onDeleted";
    public static final String JOB_STARTED = "RunListener.onStarted";
    public static final String JOB_FINALIZED = "RunListener.onFinalized";
    public static final String JOB_COMPLETED = "RunListener.onCompleted";

    public static final String COMPUTER_LAUNCH_FAILURE = "ComputerListener.onLaunchFailure";
    public static final String COMPUTER_ONLINE = "ComputerListener.onOnline";
    public static final String COMPUTER_OFFLINE = "ComputerListener.onOffline";
    public static final String COMPUTER_TEMP_ONLINE = "ComputerListener.onTemporarilyOnline";
    public static final String COMPUTER_TEMP_OFFLINE = "ComputerListener.onTemporarilyOffline";
    public static final String COMPUTER_CONFIG_CHANGE = "ComputerListener.onConfigurationChange";

    public static final String NODE_CREATED = "NodeListener.onNodeCreated";
    public static final String NODE_UPDATED = "NodeListener.onNodeUpdated";
    public static final String NODE_DELETED = "NodeListener.onNodeDeleted";

    public static final String QUEUE_ENTER_WAITING = "QueueListener.onEnterWaiting";
    public static final String QUEUE_LEAVE_WAITING = "QueueListener.onLeaveWaiting";
    public static final String QUEUE_ENTER_BLOCKED = "QueueListener.onEnterBlocked";
    public static final String QUEUE_LEAVE_BLOCKED = "QueueListener.onLeaveBlocked";
    public static final String QUEUE_ENTER_BUILDABLE = "QueueListener.onEnterBuildable";
    public static final String QUEUE_LEAVE_BUILDABLE = "QueueListener.onLeaveBuildable";
    public static final String QUEUE_LEFT = "QueueListener.onLeft";

    public static final String ITEM_UPDATED = "ItemListener.onUpdated";
    public static final String ITEM_LOCATION_CHANGED = "ItemListener.onLocationChanged";
    public static final String ITEM_RENAMED = "ItemListener.onRenamed";
    public static final String ITEM_DELETED = "ItemListener.onDeleted";
    public static final String ITEM_COPIED = "ItemListener.onCopied";
    public static final String ITEM_CREATED = "ItemListener.onCreated";

    public static final String SAVEABLE_CHANGE = "SaveableListener.onChange";

    public static String[] getAll() {
        return new String[] {
            PLUGIN_STARTED,
            PLUGIN_STOPPED,
            PLUGIN_SCHEDULE,
            JOB_INITIALIZE,
            JOB_DELETED,
            JOB_STARTED,
            JOB_FINALIZED,
            JOB_COMPLETED,
            COMPUTER_LAUNCH_FAILURE,
            COMPUTER_ONLINE,
            COMPUTER_OFFLINE,
            COMPUTER_TEMP_ONLINE,
            COMPUTER_TEMP_OFFLINE,
            COMPUTER_CONFIG_CHANGE,
            QUEUE_ENTER_WAITING,
            QUEUE_LEAVE_WAITING,
            QUEUE_ENTER_BLOCKED,
            QUEUE_LEAVE_BLOCKED,
            QUEUE_ENTER_BUILDABLE,
            QUEUE_LEAVE_BUILDABLE,
            QUEUE_LEFT,
            ITEM_CREATED,
            ITEM_COPIED,
            ITEM_DELETED,
            ITEM_RENAMED,
            ITEM_LOCATION_CHANGED,
            ITEM_UPDATED,
            SAVEABLE_CHANGE,
        };
    }
}
