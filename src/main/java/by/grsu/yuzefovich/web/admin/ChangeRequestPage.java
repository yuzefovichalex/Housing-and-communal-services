package by.grsu.yuzefovich.web.admin;

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
import by.grsu.yuzefovich.dataaccess.impl.RequestDao;
import by.grsu.yuzefovich.dataaccess.impl.BrigadeDao;
import by.grsu.yuzefovich.datamodel.Tenant;
import by.grsu.yuzefovich.datamodel.Request;
import by.grsu.yuzefovich.datamodel.Brigade;

public class ChangeRequestPage extends WebPage {
	
	private RequestDao requestDao;
	private Request request;
	private BrigadeDao brigadeDao;
	private Brigade brigade;

	public ChangeRequestPage(Request request) {
		super();
		requestDao = new RequestDao("testXmlFolder");
		this.request = request;
		brigadeDao = new BrigadeDao("testXmlFolder");
		for(Brigade brigade : brigadeDao.getAll())
			if(request.getBrigade().getId().equals(brigade.getId()))
				this.brigade = brigade;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		Form<Void> form = new Form<Void>("form");
		add(form);

		Form<Request> form1 = new Form<Request>("form1", new CompoundPropertyModel<Request>(request));
		form.add(form1);

		TextField<String> typeOfWorkField = new TextField<>("typeOfWork");
		typeOfWorkField.setLabel(new ResourceModel("typeOfWork"));
		typeOfWorkField.setRequired(true);
		form1.add(typeOfWorkField);

		TextField<Integer> scopeOfWorkField = new TextField<>("scopeOfWork");
		scopeOfWorkField.setLabel(new ResourceModel("scopeOfWork"));
		scopeOfWorkField.setRequired(true);
		form1.add(scopeOfWorkField);

		TextField<Integer> leadTimeField = new TextField<>("leadTime");
		leadTimeField.setLabel(new ResourceModel("leadTime"));
		leadTimeField.setRequired(true);
		form1.add(leadTimeField);
		
		Form<Brigade> form2 = new Form<Brigade>("form2", new CompoundPropertyModel<Brigade>(brigade));
		form.add(form2);

		TextField<String> numberOfWorkersField = new TextField<>("numberOfWorkers");
		numberOfWorkersField.setLabel(new ResourceModel("numberOfWorkers"));
		numberOfWorkersField.setRequired(true);
		form2.add(numberOfWorkersField);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				requestDao.update(request);
				brigadeDao.update(brigade);
				setResponsePage(new ShowAllRequestsPage());
			}
		});

		form.add(new Link("cancel") {

			@Override
			public void onClick() {
				setResponsePage(new ShowAllRequestsPage());
			}
		});
		add(new FeedbackPanel("feedback"));
	}

}
