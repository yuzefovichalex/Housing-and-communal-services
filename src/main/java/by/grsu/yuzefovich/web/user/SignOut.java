package by.grsu.yuzefovich.web.user;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.grsu.yuzefovich.web.authentication.HomePage;

public class SignOut extends WebPage
{
    /**
     * Constructor
     * 
     * @param parameters
     *            Page parameters (ignored since this is the home page)
     */
    public SignOut(final PageParameters parameters)
    {    	
        getSession().invalidate();
        setResponsePage(new HomePage());
        //throw new RestartResponseException(HomePage.class);
    }
}
