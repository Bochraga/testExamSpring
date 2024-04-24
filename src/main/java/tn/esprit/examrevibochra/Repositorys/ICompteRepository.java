package tn.esprit.examrevibochra.Repositorys;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.examrevibochra.Entities.Compte;

public interface ICompteRepository extends CrudRepository<Compte,Long> {
}
