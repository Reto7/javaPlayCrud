package model;

//import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * MODEL , Achtung bei JPA muss die Tabelle genauso heissen wie die Model Klasse !
 */

@Entity
@Table(name = "ProductJPA", schema = "public")
public class ProductJPA {   //extends Model{

    //@Constraints.Min(10)
    //@Formats.DateTime(pattern="dd/MM/yyyy")
    //@Constraints.Required


    //@Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    //public int id;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="product_seq")
    @SequenceGenerator(name="product_seq", sequenceName="product_seq", allocationSize=1)
    @Column(name = "id")
    private int id;

    public String ean;
    public String name;
    public String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}