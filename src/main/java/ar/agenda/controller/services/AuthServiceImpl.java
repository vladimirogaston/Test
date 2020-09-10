package ar.agenda.controller.services;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthServiceImpl implements LoginService, LogoutService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public AuthServiceImpl(){
        super();
    }

    @Override
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        logger.info("Log out user " + currentUser.toString());
        currentUser.logout();
    }

    @Override
    public void auth(String username, String password) throws AuthenticationException {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            //user's credentials format -> principal (username) = password, role1, role2, …, role.
            //user's principal (username) and credential (password).
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                //If the supplied credentials are correct, everything should go fine.
                currentUser.login(token);
                logger.info("Authentication status: [success-true]");
                logger.info("User logged in: "+username);
            } catch (UnknownAccountException uae) {
                logger.error("Username Not Found!");
            } catch (IncorrectCredentialsException ice) {
                logger.error("Invalid Credentials!", ice);
            } catch (LockedAccountException lae) {
                logger.error("Your Account is Locked!", lae);
            } catch (AuthenticationException ae) {
                logger.error("Unexpected Error!", ae);
            }
        }
    }
}
