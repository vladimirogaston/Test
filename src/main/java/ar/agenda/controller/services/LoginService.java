package ar.agenda.controller.services;

import org.apache.shiro.authc.AuthenticationException;

public interface LoginService {

    void auth(String username, String password) throws AuthenticationException;
}
