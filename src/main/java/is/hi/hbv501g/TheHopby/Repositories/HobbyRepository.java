package is.hi.hbv501g.TheHopby.Repositories;

import is.hi.hbv501g.TheHopby.Entities.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HobbyRepository extends JpaRepository<Hobby, Long>{


    Hobby save(Hobby hobby);
    void delete(Hobby hobby);
    List<Hobby> findAll();
    List<Hobby> findByName(String name);
    Optional<Hobby> findById(long id);

}
