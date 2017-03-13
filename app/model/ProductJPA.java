package model;

//import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * MODEL
 */
@Entity
public class ProductJPA {   //extends Model{

    //@Constraints.Min(10)
    //@Formats.DateTime(pattern="dd/MM/yyyy")
    //@Constraints.Required


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int id;
    public String ean;
    public String name;
    public String description;

}