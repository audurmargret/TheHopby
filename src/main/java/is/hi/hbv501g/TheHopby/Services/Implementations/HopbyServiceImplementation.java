package is.hi.hbv501g.TheHopby.Services.Implementations;

import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Entities.Session;
import is.hi.hbv501g.TheHopby.Repositories.HobbyRepository;
import is.hi.hbv501g.TheHopby.Repositories.SessionRepository;
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

    @Autowired
    public HopbyServiceImplementation(HobbyRepository hRepo, SessionRepository sRepo) {
        hobbyRepository = hRepo;
        sessionRepository = sRepo;
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
    public List<Session> findSessionByHobby(long hobbyId) {
        return sessionRepository.findSessionByHobby(hobbyId);
    }

    @Override
    public Session findSessionById(long id) {
        return sessionRepository.findSessionById(id);
    }




}
