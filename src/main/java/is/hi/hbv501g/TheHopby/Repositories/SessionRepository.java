package is.hi.hbv501g.TheHopby.Repositories;

import is.hi.hbv501g.TheHopby.Entities.Hobby;
import is.hi.hbv501g.TheHopby.Entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Hobby, Long> {


    Session save(Session session);
    //void delete(Session session);

    @Query(value = "SELECT p FROM Session p WHERE p.hobbyId = ?1")
    List<Session> findSessionByHobby(long id);

}