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

public class AcceptRequestPage extends WebPage {
	
	private RequestDao requestDao;
	private Request request;
	private BrigadeDao brigadeDao;
	private Brigade brigade;

	public AcceptRequestPage(Request request) {
		super();
		requestDao = new RequestDao("testXmlFolder");
		this.request = request;
		brigadeDao = new BrigadeDao("testXmlFolder");
		brigade = new Brigade();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Brigade> form = new Form<Brigade>("form", new CompoundPropertyModel<Brigade>(brigade));
		add(form);

		TextField<String> numberOfWorkersField = new TextField<>("numberOfWorkers");
		numberOfWorkersField.setLabel(new ResourceModel("numberOfWorkers"));
		numberOfWorkersField.setRequired(true);
		form.add(numberOfWorkersField);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				brigadeDao.saveNew(brigade);
				requestDao.updateBrigade(request, brigade.getId());
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
