package by.grsu.yuzefovich.datamodel;

import java.util.ArrayList;
import java.io.Serializable;

public class Tenant extends AbstractModel implements Serializable {
	
	private String name;
	private UserAccessData userAccessData;
	private ArrayList<Request> requests = new ArrayList<Request>();
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public ArrayList<Request> getRequests() {
		return requests;
	}
	
	public void setRequests(final ArrayList<Request> requests) {
		this.requests = requests;
	}
	
	public UserAccessData getUserAccessData() {
		return userAccessData;
	}
	
	public void setUserAccessData(UserAccessData userAccessData) {
		this.userAccessData = userAccessData;
	}
	
	public void addRequest(String typeOfWork, int scopeOfWork, int leadTime) {
		requests.add(new Request(typeOfWork, scopeOfWork, leadTime, false));
	}

}
