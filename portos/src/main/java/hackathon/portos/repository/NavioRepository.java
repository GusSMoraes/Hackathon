package hackathon.portos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackathon.portos.model.NavioModel;

@Repository
public interface NavioRepository extends JpaRepository<NavioModel, Long> {

	NavioModel findByIdentificador(String identificador);

}
