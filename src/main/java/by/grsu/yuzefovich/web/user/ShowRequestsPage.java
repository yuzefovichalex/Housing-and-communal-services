package by.grsu.yuzefovich.web.user;

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
import by.grsu.yuzefovich.dataaccess.impl.UserAccessDataDao;
import by.grsu.yuzefovich.dataaccess.impl.BrigadeDao;
import by.grsu.yuzefovich.dataaccess.impl.RequestDao;
import by.grsu.yuzefovich.datamodel.Tenant;
import by.grsu.yuzefovich.datamodel.UserAccessData;
import by.grsu.yuzefovich.datamodel.Brigade;
import by.grsu.yuzefovich.datamodel.Request;

public class ShowRequestsPage extends WebPage{
	
	private TenantDao tenantDao;
	private Tenant tenant;
	private RequestDao requestDao;
	private Request request;
	private List<Request> list;
	private String username;
	private String password;
	
	public ShowRequestsPage(String userName, String passWord) {
		
		this.username = userName;
		this.password = passWord;
		
		Long accessDataId = 0l;
		UserAccessDataDao userAccessDataDao = new UserAccessDataDao("testXmlFolder");
		for(UserAccessData userAccessData : userAccessDataDao.getAll())
			if(userAccessData.getLogin().equals(username) && userAccessData.getPassword().equals(password)) {
				accessDataId = userAccessData.getId();
				break;
			}				
		
		TenantDao tenantDao = new TenantDao("testXmlFolder");
		for(Tenant tenant : tenantDao.getAll())
			if (tenant.getUserAccessData().getId().equals(accessDataId)) {
				this.tenant = tenant;
			}

		list = new ArrayList<Request>();
		requestDao = new RequestDao("testXmlFolder");
		for (Request request : requestDao.getAll())
			for (Request tenantRequest : this.tenant.getRequests())
				if (request.getId().equals(tenantRequest.getId()))
					list.add(request);		
		            
        
 
    }
	
	@Override
	protected void onInitialize() {
		super.onInitialize();

        final DataView<Request> dataView = new DataView<Request>("simple", new ListDataProvider(list)) {
        	
        public void populateItem(final Item item) {
            final Request user = (Request) item.getModelObject();
            item.add(new Label("id", user.getId()));
            item.add(new Label("typeOfWork", user.getTypeOfWork()));
            item.add(new Label("scopeOfWork", user.getScopeOfWork()));
            item.add(new Label("leadTime", user.getLeadTime()));
            item.add(new Label("isAccepted", user.getIsAccepted() ? "Performing" : "Processing"));
            item.add(new Label("brigade", user.getIsAccepted() ? getNumberOfWorkersInBrigade(user) : "None"));
            }
            
        };
        
        add(dataView);
        
        add(new Link("addRequest") {

			@Override
			public void onClick() {
				setResponsePage(new AddingRequestPage(username, password, "ShowRequestsPage"));
			}
		});
		
		add(new Link("userPage") {
			
			@Override
			public void onClick() {
				setResponsePage(new UserPage(username, password));
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
 
}
