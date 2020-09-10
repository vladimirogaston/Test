package ar.agenda;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.guice.aop.ShiroAopModule;

public class ApplicationAuthorizingModule extends ShiroAopModule {

    protected void configureInterceptors(AnnotationResolver resolver) {
    }
}
