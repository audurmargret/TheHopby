package is.hi.hbv501g.TheHopby.Entities;

import javax.persistence.*;

@Entity
@Table(name = "Hobbies")
public class Hobby {

    @Id
    private long id;

    private String name;

    public Hobby(){
    }

    public Hobby(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
