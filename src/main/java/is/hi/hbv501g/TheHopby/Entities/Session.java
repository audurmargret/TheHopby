package is.hi.hbv501g.TheHopby.Entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "Sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String location;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    @DateTimeFormat(pattern="HH:mm")
    private LocalTime time;
    @OneToMany
    private List<User> users;
    private int slotsAvailable;
    private int slots;
    private long hobbyId;
    private String description;

    public Session() {
    }

    public Session(String title, String location, LocalDate date, LocalTime time, int slots, long hobbyId, String description) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
        //users = new ArrayList<User>();
        this.slots = slots;
        this.slotsAvailable = slots;
        this.hobbyId = hobbyId;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(User user) {
        this.users.add(user);
        System.out.println("Bæta við user í session lista");
        System.out.println("listinn: " + users);
        slotsAvailable--;

    }

    public int getSlotsAvailable() {
        return slotsAvailable;
    }

    public void setSlotsAvailable(int slotsAvailable) {
        this.slotsAvailable = slotsAvailable;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public long getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(long hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
