package ar.agenda;

import ar.agenda.controller.exceptions.NotFoundException;
import ar.agenda.controller.services.ProfilerService;
import ar.agenda.dao.UserDao;
import ar.agenda.dao.entities.Role;
import ar.agenda.dao.entities.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class AuthRealmProvider implements Provider<AuthorizingRealm> {

    @Inject
    UserDao userDao;

    @Inject
    ProfilerService profiler;

    @Override
    public AuthorizingRealm get() {
        AuthorizingRealm realm = null;
        try {
            String profile = profiler.getActiveProfile();
            if(profile.matches("dev")) {
                realm = new IniRealm("classpath:shiro.ini");
            } else if(profile.matches("prod")) {
                realm = new JpaRealm(this.userDao);
            }
        }catch (IOException e) {
            LoggerFactory.getLogger(this.getClass()).error("Error " + e.getMessage());
        }
        return realm;
    }


    class JpaRealm extends AuthorizingRealm {
        UserDao dao;

        public JpaRealm(UserDao dao) {
            super();
            this.dao = dao;
        }

        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
            User user = dao.findByUsername(principals.getPrimaryPrincipal().toString()).get();
            if(user == null) return null;
            Set<String> roles = new HashSet<>();
            Set<String> permissions = new HashSet<>();
            permissions.add("*");
            for(Role role : user.getAccount().getRoles()) {
                roles.add(role.name());
            }
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
            authorizationInfo.setStringPermissions(permissions);
            return authorizationInfo;
        }

        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
            if(!(token instanceof UsernamePasswordToken)) throw new IllegalStateException("Token must be an instance of UsernamePasswordToken");
            final UsernamePasswordToken userPassToken = (UsernamePasswordToken)token;
            User user = dao.findByUsername(userPassToken.getUsername()).get();
            if(user == null) return null;
            SimpleAccount simpleAccount = new SimpleAccount(
                    user.getAccount().getUsername(),
                    user.getAccount().getPassword(),
                    getName()
            );
            return simpleAccount;
        }

        protected Set<String> getRoleNamesForUser(String username)  {
            User user = dao.findByUsername(username).orElseThrow(() -> new NotFoundException("Username not found!"));
            Set<String> roles = new HashSet<>();
            for(Role role : user.getAccount().getRoles()) {
                roles.add(role.name());
            }
            return roles;
        }

        protected Set<String> getPermissions(Collection<String> roleNames)  {
            Set<String> finalSet = new HashSet<>();
            finalSet.add("*");
            return finalSet;
        }
    }
}
