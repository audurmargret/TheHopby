package is.hi.hbv501g.TheHopby.Services;

import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Entities.Session;
import is.hi.hbv501g.TheHopby.Entities.User;

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
    public List<Session> findSessionByHobby(long hobbyId);
    public List<Session> findAllSession();

    public Session findSessionById(long id);

    // User
    public User save(User user);
    public void delete(User user);
    public List<User> findAll();
    public User findByUserName(String username);

    User login(User user);

}
