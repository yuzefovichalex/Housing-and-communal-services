package by.grsu.yuzefovich.datamodel;

import java.util.ArrayList;

public class Tenant {
	
	private String name;
	private Long id;
	private ArrayList<Request> acceptedRequests = new ArrayList<Request>();
	private ArrayList<Request> newRequests = new ArrayList<Request>();
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
	public ArrayList<Request> getNewRequests() {
		return newRequests;
	}
	
	public void setNewRequests(final ArrayList<Request> newRequests) {
		this.newRequests = newRequests;
	}
	
	public ArrayList<Request> getAcceptedRequests() {
		return acceptedRequests;
	}
	
	public void setAcceptedRequests(final ArrayList<Request> acceptedRequests) {
		this.acceptedRequests = acceptedRequests;
	}
	
	public void addRequest(String typeOfWork, int scopeOfWork, int leadTime) {
		newRequests.add(new Request(typeOfWork, scopeOfWork, leadTime));
	}

}
