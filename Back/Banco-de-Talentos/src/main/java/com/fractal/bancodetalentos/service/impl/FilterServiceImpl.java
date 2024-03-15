package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.model.request.FilterTalentReq;
import com.fractal.bancodetalentos.model.response.FilterTalentoResp;
import com.fractal.bancodetalentos.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<FilterTalentoResp> filterTalents(FilterTalentReq filterTalentReq) {
        StoredProcedureQuery storedProcedureQueryFilterTalent = entityManager
                .createStoredProcedureQuery("SP_FILTER_TALENT")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .setParameter(1, filterTalentReq.getHabilities())
                .setParameter(2, filterTalentReq.getLanguageIds())
                .setParameter(3, filterTalentReq.getLevelIds())
                .setParameter(4, filterTalentReq.getNameJobTitle());
        storedProcedureQueryFilterTalent.execute();
        List<Object[]> result = storedProcedureQueryFilterTalent.getResultList();
        List<FilterTalentoResp> respList = new ArrayList<>();
        for (Object[] objects: result) {
            FilterTalentoResp filterTalentResp = new FilterTalentoResp();
            filterTalentResp.setId((Integer) objects[0]);
            filterTalentResp.setImage((byte[]) objects[1]);
            filterTalentResp.setNameJobTitle((String) objects[2]);
            filterTalentResp.setInitialSalary((Integer) objects[3]);
            filterTalentResp.setFinalSalary((Integer) objects[4]);
            filterTalentResp.setLocation((String) objects[5]);
            filterTalentResp.setAvgRating((Integer) objects[6]);
            respList.add(filterTalentResp);
        }
        return respList;
    }
}
