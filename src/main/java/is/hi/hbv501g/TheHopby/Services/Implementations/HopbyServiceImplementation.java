package is.hi.hbv501g.TheHopby.Services.Implementations;

import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Repositories.HobbyRepository;
import is.hi.hbv501g.TheHopby.Services.HopbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HopbyServiceImplementation implements HopbyService {

    HobbyRepository hobbyRepository;

    @Autowired
    public HopbyServiceImplementation(HobbyRepository hRepo) {
        hobbyRepository = hRepo;
    }

    @Override
    public Hobby save(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    @Override
    public void delete(Hobby hobby) {
        hobbyRepository.delete(hobby);
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
        return Optional.empty();
    }

}
