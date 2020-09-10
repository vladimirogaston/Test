package ar.agenda;

import ar.agenda.controller.services.ProfilerService;
import ar.agenda.presentation.Profile;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Optional;

public class ApplicationProfiler implements MethodInterceptor {

    @Inject
    ProfilerService profiler;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        final Profile annotation = invocation.getMethod().getAnnotation(Profile.class);
        if(getActiveProfile().isPresent()) {
            if(getActiveProfile().get().matches(annotation.value())) {
                return invocation.proceed();
            }
        }
        throw new IllegalStateException(invocation.getMethod().getName() + " Only allowed for " + annotation.value() + " profile.");
    }

    Optional<String> getActiveProfile() {
        Optional<String> ret = Optional.empty();
        try {
            ret = Optional.ofNullable(profiler.getActiveProfile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
