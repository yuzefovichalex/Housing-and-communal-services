package by.grsu.yuzefovich.web.authentication;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

import by.grsu.yuzefovich.web.admin.AdminPage;
import by.grsu.yuzefovich.web.user.UserPage;
import by.grsu.yuzefovich.datamodel.UserAccessData;
import by.grsu.yuzefovich.dataaccess.impl.UserAccessDataDao;




/**
 * Authenticated session subclass. Note that it is derived from AuthenticatedWebSession which is
 * defined in the auth-role module.
 * 
 * @author Jonathan Locke
 */
public class MyAuthenticatedWebSession extends AuthenticatedWebSession
{
    /**
     * Construct.
     * 
     * @param request
     *            The current request object
     */
	
    public MyAuthenticatedWebSession(Request request)
    {
        super(request);
    }

    /**
     * @see org.apache.wicket.authroles.authentication.AuthenticatedWebSession#authenticate(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public boolean authenticate(final String username, final String password)
    {
        final String adminData = "admin";
        final String userData = "user";
        UserAccessDataDao userAccessDataDao = new UserAccessDataDao("testXmlFolder");
        List<UserAccessData> users = userAccessDataDao.getAll();
        
        
        if (adminData.equals(username) && adminData.equals(password)) {       	
        	throw new RestartResponseException(AdminPage.class);        	
        }        	
        else {
        	for (UserAccessData userAccessData : users)
        		if (userAccessData.getLogin().equals(username) && userAccessData.getPassword().equals(password)) {
        			//PageParameters pageParameters = new PageParameters();
                	//pageParameters.add("username", username);
                	//pageParameters.add("password", password);               	
        			throw new RestartResponseException(UserPage.class);
        		}        	        
        }
        return false;
        // Check username and password
        //return adminData.equals(username) && adminData.equals(password);
    }

    /**
     * @see org.apache.wicket.authroles.authentication.AuthenticatedWebSession#getRoles()
     */
    @Override
    public Roles getRoles()
    {
        return null;
    }
}
