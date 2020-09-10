package ar.agenda.dao.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;
    
    String surname;
    
    @Column(unique = true)
    Integer dni;

    @Embedded
    Contact contact;

    @Enumerated(value = EnumType.STRING)
    Gender gender;

    @Temporal(value = TemporalType.DATE)
    Date bornDate;

    @Basic(optional = true)
    @Embedded
    Address address;

    @Embedded
    Account account;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn
    City city;

    public static User buildTestUser() {
    	User user = new User();
    	user.setName("un000");
    	user.setSurname("us000");
    	user.setDni(44222333);
    	user.setBornDate(new Date());
    	Contact contact = new Contact("unus@xxxmail.com",31234444);
    	user.setContact(contact);
    	user.setGender(Gender.FEMALE);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.Admin);
    	user.setAccount(new Account("us000","un000pass",new Date(), null, roles));
    	Address address = new Address();
    	address.setStreet("Lenin av.");
    	address.setNumber(3233);
    	address.setDpt("A");
    	address.setFlat(1);
    	user.setAddress(address);
    	user.setCity(new City(null,"Boomerang city",312,"Blink state"));
    	return user;
    }
}
