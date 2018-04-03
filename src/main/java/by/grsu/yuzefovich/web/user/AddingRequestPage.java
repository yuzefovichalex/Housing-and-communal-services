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
import by.grsu.yuzefovich.datamodel.Tenant;
import by.grsu.yuzefovich.datamodel.Request;

public class AddingRequestPage extends UserPage {
	
	private TenantDao tenantDao;

	private Tenant tenant;

	public AddingRequestPage() {
		super();		
		tenantDao = new TenantDao("testXmlFolder");
		tenant = tenantDao.getAll().get(0);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Tenant> form = new Form<Tenant>("form", new CompoundPropertyModel<Tenant>(tenant));
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
				//tenant.addRequest(typeOfWorkField.toString(), scopeOfWorkField.toString(), leadTimeField.toString());
				tenantDao.update(tenant);
				setResponsePage(new UserPage());
			}
		});

		form.add(new Link("cancel") {

			@Override
			public void onClick() {
				setResponsePage(new UserPage());
			}
		});
		add(new FeedbackPanel("feedback"));
	}

}
