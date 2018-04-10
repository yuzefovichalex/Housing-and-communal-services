package by.grsu.yuzefovich.web.admin;

import java.util.ArrayList;

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
import by.grsu.yuzefovich.dataaccess.impl.BrigadeDao;
import by.grsu.yuzefovich.datamodel.Tenant;
import by.grsu.yuzefovich.datamodel.UserAccessData;
import by.grsu.yuzefovich.datamodel.Request;
import by.grsu.yuzefovich.datamodel.Brigade;

public class AddingTenantPage extends WebPage {
	
	private TenantDao tenantDao;
	private Tenant tenant;
	private UserAccessDataDao userAccessDataDao;
	private UserAccessData userAccessData;

	public AddingTenantPage() {
		super();
		tenantDao = new TenantDao("testXmlFolder");
		tenant = new Tenant();
		userAccessDataDao = new UserAccessDataDao("testXmlFolder");
		userAccessData = new UserAccessData();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		Form<Void> form = new Form<Void>("form");
		add(form);

		Form<Tenant> form1 = new Form<Tenant>("form1", new CompoundPropertyModel<Tenant>(tenant));
		form.add(form1);

		TextField<String> nameField = new TextField<>("name");
		nameField.setLabel(new ResourceModel("name"));
		nameField.setRequired(true);
		form1.add(nameField);
		
		Form<UserAccessData> form2 = new Form<UserAccessData>("form2", new CompoundPropertyModel<UserAccessData>(userAccessData));
		form.add(form2);

		TextField<String> loginField = new TextField<>("login");
		loginField.setLabel(new ResourceModel("login"));
		loginField.setRequired(true);
		form2.add(loginField);
		
		TextField<String> passwordField = new TextField<>("password");
		passwordField.setLabel(new ResourceModel("password"));
		passwordField.setRequired(true);
		form2.add(passwordField);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				userAccessDataDao.saveNew(userAccessData);
				tenant.setUserAccessData(new UserAccessData());
				tenant.setRequests(new ArrayList<Request>());
				tenantDao.saveNew(tenant, userAccessData);
				setResponsePage(new AdminPage());
			}
		});

		form.add(new Link("cancel") {

			@Override
			public void onClick() {
				setResponsePage(new AdminPage());
			}
		});
		add(new FeedbackPanel("feedback"));
	}

}
