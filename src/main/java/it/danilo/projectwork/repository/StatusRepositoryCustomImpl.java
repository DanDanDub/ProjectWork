package it.danilo.projectwork.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.Device;
import it.danilo.projectwork.entity.District;
import it.danilo.projectwork.entity.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class StatusRepositoryCustomImpl implements StatusRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Status> findStatusCustom(City city, District district, Device device, Date dateFrom, Date dateTo, Integer co2Min, Integer co2Max) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Status> cq = cb.createQuery(Status.class);
        Root<Status> root = cq.from(Status.class);
        root.fetch("city");
        List<Predicate> predicates = new ArrayList<>();

        if(city!=null) { predicates.add(cb.equal(root.get("city"), city)); }
        if(district!=null) { predicates.add(cb.equal(root.get("district"), district)); }
        if(device!=null) { predicates.add(cb.equal(root.get("device"), device)); }
        if(dateFrom!=null) { predicates.add(cb.greaterThan(root.get("timestamp"), dateFrom)); }
        if(dateTo!=null) { predicates.add(cb.lessThan(root.get("timestamp"), dateTo)); }
        if(co2Min!=null) { predicates.add(cb.gt(root.get("co2Level"), co2Min)); }
        if(co2Max!=null) { predicates.add(cb.lt(root.get("co2Level"), co2Max)); }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

}