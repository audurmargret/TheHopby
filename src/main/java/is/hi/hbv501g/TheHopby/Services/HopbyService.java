package is.hi.hbv501g.TheHopby.Services;

import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Entities.Session;
import is.hi.hbv501g.TheHopby.Entities.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface HopbyService {

    // Hobby
    public Hobby save(Hobby hobby);
    public List<Hobby> findAllHobby();
    public List<Hobby> findByName(String name);
    public Optional<Hobby> findById(long id);

    // Session
    public Session save(Session session);
    public void delete(Session session);
    /*public Session updateSession(long id);*/
    public List<Session> findSessionByHobby(long hobbyId);
    public List<Session> findAllSession();
    public List<Session> findMySessions(String username);

    public Session findSessionById(long id);
    public Session joinSession(long id, User user);
    public Session leaveSession(long id, User user);

    // User
    public User save(User user);
    public void delete(User user);
    public List<User> findAll();
    public User findByUserName(String username);

    User login(User user);

    public boolean validTime(LocalDate date, LocalTime time);

}
