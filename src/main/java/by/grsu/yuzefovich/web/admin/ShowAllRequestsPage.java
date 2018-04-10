package by.grsu.yuzefovich.web.admin;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;


import by.grsu.yuzefovich.dataaccess.impl.TenantDao;
import by.grsu.yuzefovich.dataaccess.impl.BrigadeDao;
import by.grsu.yuzefovich.dataaccess.impl.RequestDao;
import by.grsu.yuzefovich.datamodel.Tenant;
import by.grsu.yuzefovich.datamodel.Brigade;
import by.grsu.yuzefovich.datamodel.Request;

public class ShowAllRequestsPage extends WebPage{
	
	private List<Request> list;
	
	public ShowAllRequestsPage() {
		
		RequestDao requestDao = new RequestDao("testXmlFolder");
		list = requestDao.getAll();

    }
	
	@Override
	protected void onInitialize() {
		super.onInitialize();

        final DataView<Request> dataView = new DataView<Request>("simple", new ListDataProvider(list)) {
        	
        public void populateItem(final Item item) {
            final Request user = (Request) item.getModelObject();
                     
            item.add(new Label("id", user.getId()));
            item.add(new Label("tenantName", getTenantName(user)));
            item.add(new Label("typeOfWork", user.getTypeOfWork()));
            item.add(new Label("scopeOfWork", user.getScopeOfWork()));
            item.add(new Label("leadTime", user.getLeadTime()));
            item.add(new Label("isAccepted", user.getIsAccepted() ? "Performing" : "Processing"));
            item.add(new Label("brigade", user.getIsAccepted() ? getNumberOfWorkersInBrigade(user) : "None"));
            item.add(new Link("acceptionAndChanging") {
            	
            	@Override
    			public void onClick() {
            		if(user.getIsAccepted())
            			//setResponsePage(new ShowAllRequestsPage());
            			setResponsePage(new ChangeRequestPage(user));
            		else
            			setResponsePage(new AcceptRequestPage(user));
    			}
            	
            }.add(new Label("label", user.getIsAccepted() ? "Change" : "Accept")));
            item.add(new Link("deleting") {
            	
            	@Override
    			public void onClick() {
            		DeleteRequest(user);
    				setResponsePage(new ShowAllRequestsPage());
    			}
            	
            });
            }
            
        };
        
        add(dataView);
        
		add(new Link("userPage") {
			
			@Override
			public void onClick() {
				setResponsePage(new AdminPage());
			}
		});
	}
	
	public Integer getNumberOfWorkersInBrigade(Request request) {
		BrigadeDao brigadeDao = new BrigadeDao("testXmlFolder");
		for(Brigade brigade : brigadeDao.getAll())
			if (request.getBrigade().getId().equals(brigade.getId()))
				return brigade.getNumberOfWorkers();
		return null;
	}
	
	public String getTenantName(Request request) {		
        TenantDao tenantDao = new TenantDao("testXmlFolder");
		for(Tenant tenant : tenantDao.getAll())
        	for(Request requestLocal : tenant.getRequests())
        		if(requestLocal.getId().equals(request.getId()))
        			return tenant.getName();
		return null;
	}
	
	public void DeleteRequest(Request request) {
		TenantDao tenantDao = new TenantDao("testXmlFolder");
		RequestDao requestDao = new RequestDao("testXmlFolder");
		BrigadeDao brigadeDao = new BrigadeDao("testXmlFolder");
		
		requestDao.delete(request.getId());
		if(request.getBrigade() != null)
		    brigadeDao.delete(request.getBrigade().getId());
		
		for(Tenant tenant : tenantDao.getAll())
			for(int i = 0; i < tenant.getRequests().size(); i++)
				if(tenant.getRequests().get(i).getId().equals(request.getId())) {
					ArrayList list = tenant.getRequests();
					list.remove(i);
					tenant.setRequests(list);
					tenantDao.update(tenant);
				}
		
	}
 
}
