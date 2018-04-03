package by.grsu.yuzefovich.datamodel;

public class Request {
	
	private String typeOfWork;
	private int scopeOfWork;
	private int leadTime;
	private Brigade brigade;
	private boolean isAccepted;
	
	public String getTypeOfWork() {
		return typeOfWork;
	}
	
	public void setTypeOfWork(final String typeOfWork) {
		this.typeOfWork = typeOfWork;
	}
	
	public int getScopeOfWork() {
		return scopeOfWork;
	}
	
	public void setTypeOfWork(final int scopeOfWork) {
		this.scopeOfWork = scopeOfWork;
	}
	
	public int getLeadTime() {
		return leadTime;
	}
	
	public void setLeadTime(final int leadTime) {
		this.leadTime = leadTime;
	}
	
	public Brigade getBrigade() {
		return brigade;
	}
	
	public void setBrigade(final Brigade brigade) {
		this.brigade = brigade;
	}
	
	public boolean getIsAccepted() {
		return isAccepted;
	}
	
	public void setIsAccepted(final boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	public Request(String typeOfWork, int scopeOfWork, int leadTime, boolean isAccepted) {
		this.typeOfWork = typeOfWork;
		this.scopeOfWork = scopeOfWork;
		this.leadTime = leadTime;
		this.isAccepted = isAccepted;
		brigade = null;
	}
	public Request(String typeOfWork, int scopeOfWork, int leadTime, int numberOfWorkers) {
		this.typeOfWork = typeOfWork;
		this.scopeOfWork = scopeOfWork;
		this.leadTime = leadTime;
		brigade = new Brigade(numberOfWorkers);
	}

}
