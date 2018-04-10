package by.grsu.yuzefovich.web.user;

import org.apache.wicket.feedback.ExactLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;


import by.grsu.yuzefovich.dataaccess.impl.TenantDao;
import by.grsu.yuzefovich.dataaccess.impl.UserAccessDataDao;
import by.grsu.yuzefovich.dataaccess.impl.RequestDao;
import by.grsu.yuzefovich.datamodel.Tenant;
import by.grsu.yuzefovich.datamodel.UserAccessData;
import by.grsu.yuzefovich.datamodel.Request;

public class AddingRequestPage extends WebPage {
	
	private TenantDao tenantDao;
	private Tenant tenant;
	private RequestDao requestDao;
	private Request request;
	private String username;
	private String password;
	private String returnTo;

	public AddingRequestPage(String userName, String passWord, String returnTo) {
		super();
		this.username = userName;
		this.password = passWord;
		this.returnTo = returnTo;
		tenantDao = new TenantDao("testXmlFolder");
		requestDao = new RequestDao("testXmlFolder");
		
		Long accessDataId = 0l;
		UserAccessDataDao userAccessDataDao = new UserAccessDataDao("testXmlFolder");
		for(UserAccessData userAccessData : userAccessDataDao.getAll())
			if(userAccessData.getLogin().equals(username) && userAccessData.getPassword().equals(password)) {
				accessDataId = userAccessData.getId();
				break;
			}				
		
		for(Tenant tenant : tenantDao.getAll())
			if (tenant.getUserAccessData().getId().equals(accessDataId)) {
				this.tenant = tenant;
			}
		request = new Request();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Request> form = new Form<Request>("form", new CompoundPropertyModel<Request>(request));
		add(form);

		TextField<String> typeOfWorkField = new TextField<>("typeOfWork");
		typeOfWorkField.setLabel(new ResourceModel("typeOfWork"));
		typeOfWorkField.setRequired(true);
		form.add(typeOfWorkField);

		TextField<Integer> scopeOfWorkField = new TextField<>("scopeOfWork");
		// radiusField.add(RangeValidator.<Double> range(0d, 1_000_000d));
		scopeOfWorkField.setLabel(new ResourceModel("scopeOfWork"));
		scopeOfWorkField.setRequired(true);
		form.add(scopeOfWorkField);

		TextField<Integer> leadTimeField = new TextField<>("leadTime");
		// radiusField.add(RangeValidator.<Double> range(0d, 1_000_000d));
		leadTimeField.setLabel(new ResourceModel("leadTime"));
		leadTimeField.setRequired(true);
		form.add(leadTimeField);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				request.setIsAccepted(false);
				requestDao.saveNew(request);
				tenantDao.updateRequests(tenant, request.getId());
				if (returnTo == "UserPage")
				    setResponsePage(new UserPage(username, password));
				if (returnTo == "ShowRequestsPage")
					setResponsePage(new ShowRequestsPage(username, password));
			}
		});

		form.add(new Link("cancel") {

			@Override
			public void onClick() {
				if (returnTo == "UserPage")
				    setResponsePage(new UserPage(username, password));
				if (returnTo == "ShowRequestsPage")
					setResponsePage(new ShowRequestsPage(username, password));
			}
		});
		add(new FeedbackPanel("feedback"));
	}

}
