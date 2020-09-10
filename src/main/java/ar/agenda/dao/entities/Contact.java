package ar.agenda.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Contact implements Serializable {

    private static final long serialVersionUID = 7864195636319163886L;

    private String email;

    private Integer phone;
}
