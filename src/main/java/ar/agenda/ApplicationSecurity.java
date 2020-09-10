package ar.agenda;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationSecurity {

    @Inject
    AuthorizingRealm realm;

    public void init() {
        SecurityManager manager = new DefaultSecurityManager(realm);
        SecurityUtils.setSecurityManager(manager);
        LoggerFactory.getLogger(this.getClass());
    }
}
