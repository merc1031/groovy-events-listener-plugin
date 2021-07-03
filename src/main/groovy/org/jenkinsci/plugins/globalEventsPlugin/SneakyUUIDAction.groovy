package org.jenkinsci.plugins.globalEventsPlugin

import hudson.EnvVars
import hudson.model.Run
import hudson.model.Label
import hudson.model.AbstractBuild
import hudson.model.EnvironmentContributingAction
import hudson.model.InvisibleAction
import hudson.model.labels.LabelAssignmentAction
import hudson.model.queue.SubTask
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;


@ExportedBean
public  class SneakyUUIDAction extends InvisibleAction implements LabelAssignmentAction, EnvironmentContributingAction  {
  public String uuid
  public Boolean mammon_controlled_jenkins
  public Boolean job_controlled_by_mammon

  public SneakyUUIDAction(String uuid, Boolean mammon_controlled_jenkins, Boolean job_controlled_by_mammon) {
    super();
    this.uuid = uuid
    this.mammon_controlled_jenkins = mammon_controlled_jenkins
    this.job_controlled_by_mammon = job_controlled_by_mammon
  }

  public Label getAssignedLabel(SubTask task) {
    if (mammon_controlled_jenkins == true && job_controlled_by_mammon == false){
      return Label.get(uuid)
    }
    else {
      return null
    }
  }

  @Exported(visibility = 2)
  public String getUuid() {
    return this.uuid; 
  }

  public void buildEnvironment(Run run, EnvVars env) {
    env.put("UUID", uuid)
  }
}