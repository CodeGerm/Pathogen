package org.cg.rule.engine.common.session;


public class KieSessionBase {
	
	protected String settingPath;
	protected String groupId;
	protected String artifactId;
	protected String version;
	protected String session;
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
	
	public String getSettingPath() {
		return settingPath;
	}

	public void setSettingPath(String settingPath) {
		this.settingPath = settingPath;
	}

}
