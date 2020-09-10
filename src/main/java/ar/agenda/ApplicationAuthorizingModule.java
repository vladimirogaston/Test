package ar.agenda;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.guice.aop.ShiroAopModule;

public class ApplicationAuthorizingModule extends ShiroAopModule {

    @Override
    protected void configureInterceptors(AnnotationResolver resolver) {
        //Empty, nothing to bind yet.
    }
}
