# global-events-plugin
A jenkins plugin, which executes groovy code when an event occurs.

---

Table of contents
---

1. [Overview](#overview)
1. [Building](#building)
1. [Basic Usage](#basic-usage)
1. [Authors](#authors)
1. [License](#license)
1. [Backlog](#backlog)

Overview
---

The reason I created the plugin was because I wanted to integrate Jenkins with an external application.
Invoking a Jenkins jobs via the REST api was simple, but getting Jenkins to callback the external application wasn't
straight forward.

All the plugins I'd seen either had to be individually configured per job (i.e. in a post build step), or their features
were limited to making a HTTP GET/POST request (a bit restrictive).

Bascially:

- I wanted to be able to write my own code
- I didn't want to repeat myself

So I wrote this plugin. Along the way, I realised it could have some other applications too:

- customised logging
- performance monitoring
- incident escalation
- integration with 3rd party applications
- ???

Building
---

Prerequisites:

- JDK 6 (or above)

To run Jenkins ([http://localhost:8080](http://localhost:8080)) locally with the plugin installed:

    ./gradlew clean server

To build the Jenkins plugin (.jpi) file:

    ./gradlew clean jpi

Basic Usage
---

To get started:

1. Install the plugin (or [run Jenkins locally](#building))
1. Navigate to the *Jenkins > Manage Jenkins > Configuration* page
1. You should now see a *Global Events Plugin* section (similar to the following screenshot).

![Version 1.0.0](src/main/site/screenshot-version-1.0.0.png "Version 1.0.0")

This plugin executes the supplied Groovy code, every time an event is triggered.

So lets get started with the simplest example.

```groovy
log.info "hello world!"
```

Now save the changes, kick off a Jenkins job, and you will see "hello world!" written to the logs three times.

Excellent! Want to limit the logging to only occur when a Job is completed?

```groovy
if (event == 'RunListener.onFinalized'){
    log.info "hello world!"
}
```

Or perhaps only for Job's whose name starts with "Foobar"?

```groovy
if (env.JOB_NAME.startsWith('Foobar')){
    log.info "hello world!"
}
```

You can also use `@Grab` annotations ([only where they are valid](https://issues.apache.org/jira/browse/GROOVY-6069))
if you'd like to import external dependencies (thanks [Daniel](https://github.com/CoreMedia/job-dsl-plugin/commit/830fae7a0fd8a046c620600e46633166804190e3)
for your solution!).

```groovy
@Grab('commons-lang:commons-lang:2.4')
import org.apache.commons.lang.WordUtils
log.info "Hello ${WordUtils.capitalize('world')}!"
```

Not bad! You can pretty much do what ever you want from here... custom logging to a file, sending performance metrics to
an elastic server, sending email or messenger notifications, calling a SOAP service... the world's your oyster.

For more details on which events trigger the code, what variables are available and details on configuring logging,
please see the [plugin's help file](https://cdn.rawgit.com/nickgrealy/global-events/master/src/main/resources/org/jenkinsci/plugins/globalEventsPlugin/GlobalEventsPlugin/help-onEventGroovyCode.html).

Authors
---

Nick Grealy - <nickgrealy@gmail.com>

License
---

Licensed under the [MIT License (MIT)](LICENSE)

Backlog
---

- add more listeners
- add a "Run now" button
- cache compilation of script
- only execute script for registered events (to mitigate performance impacts)
- optionally use script file or integrate with scriptler