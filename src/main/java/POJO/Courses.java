package POJO;

import java.util.List;

public class Courses {

	public List<webAutomation> getWebAutomation() {
		return WebAutomation;
	}
	public void setWebAutomation(List<webAutomation> webAutomation) {
		this.WebAutomation = webAutomation;
	}
	public List<POJO.API> getAPI() {
		return API;
	}
	public void setAPI(List<POJO.API> API) {
		this.API = API;
	}
	public List<POJO.Mobile> getMobile() {
		return Mobile;
	}
	public void setMobile(List<POJO.Mobile> mobile) {
		this.Mobile = mobile;
	}
	private List<webAutomation> WebAutomation;
	private List<API> API;
	private List<Mobile> Mobile;
	
	
}
