package is.hi.hbv501g.TheHopby.Services.Implementations;

import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Entities.Session;
import is.hi.hbv501g.TheHopby.Entities.User;
import is.hi.hbv501g.TheHopby.Repositories.HobbyRepository;
import is.hi.hbv501g.TheHopby.Repositories.SessionRepository;
import is.hi.hbv501g.TheHopby.Repositories.UserRepository;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.client.Hop;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HopbyServiceImplementation implements HopbyService {

    HobbyRepository hobbyRepository;
    SessionRepository sessionRepository;
    UserRepository userRepository;

    @Autowired
    public HopbyServiceImplementation(HobbyRepository hRepo, SessionRepository sRepo, UserRepository uRepo) {
        hobbyRepository = hRepo;
        sessionRepository = sRepo;
        userRepository = uRepo;
    }


    @Override
    public Hobby save(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    @Override
    public List<Hobby> findAllHobby() {
        return hobbyRepository.findAll();
    }

    @Override
    public List<Hobby> findByName(String name) {
        return hobbyRepository.findByName(name);
    }

    @Override
    public Optional<Hobby> findById(long id) {
        return hobbyRepository.findById(id);
    }

    @Override
    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public void delete(Session session) {
        sessionRepository.delete(session);
    }


    @Override
    public List<Session> findSessionByHobby(long hobbyId) {
        return sessionRepository.findSessionByHobby(hobbyId);
    }

    @Override
    public List<Session> findAllSession() {
        return sessionRepository.findAllSession();
    }

    @Override
    public Session findSessionById(long id) {
        return sessionRepository.findSessionById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User login(User user) {
        User exist = userRepository.findByUserName(user.getUserName());
        System.out.println("user user: " + user.getUserName());
        System.out.println("user pass: " + user.getPassword());
        System.out.println("exsist : " + exist);
        System.out.println("pass ex: " + exist.getPassword());
        if(exist != null) {
            //Notandi er til
            if(exist.getPassword().equals(user.getPassword())){
                //Password er rétt
                return user;
            }
        }

        //Notandi er ekki til
        return null;
    }


}
