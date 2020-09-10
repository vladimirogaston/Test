package ar.agenda.controller.services;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

@NoArgsConstructor
@Slf4j
public class AuthServiceImpl implements LoginService, LogoutService {

    @Override
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

    @Override
    public void auth(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
                log.info("User logged in: "+username);
            } catch (UnknownAccountException uae) {
                log.error("Username Not Found!");
            } catch (IncorrectCredentialsException ice) {
                log.error("Invalid Credentials!", ice);
            } catch (LockedAccountException lae) {
                log.error("Your Account is Locked!", lae);
            } catch (AuthenticationException ae) {
                log.error("Unexpected Error!", ae);
            }
        }
    }
}
