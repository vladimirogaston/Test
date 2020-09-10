package ar.agenda.controller.services;

import ar.agenda.dao.entities.City;
import ar.agenda.dao.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class DatabaseGraph {
	List<User> userList;
    List<City> cityList;

    public DatabaseGraph() {
        setUserList(new LinkedList<>());
        setCityList(new LinkedList<>());
    }
}
