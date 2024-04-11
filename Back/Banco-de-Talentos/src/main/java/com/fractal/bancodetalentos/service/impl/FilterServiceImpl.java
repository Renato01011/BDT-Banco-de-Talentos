package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
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
        StoredProcedureQuery storedProcedureQueryCheckUser = entityManager.createStoredProcedureQuery("SP_CHECK_USER_ID")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, filterTalentReq.getUserId());
        storedProcedureQueryCheckUser.execute();
        Integer exists = (Integer) storedProcedureQueryCheckUser.getOutputParameterValue(2);

        if (exists == 0) {
            throw new ResourceNotFoundException("User", "id", filterTalentReq.getUserId());
        }

        StoredProcedureQuery storedProcedureQueryFilterTalent = entityManager
                .createStoredProcedureQuery("SP_FILTER_TALENT")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                .setParameter(1, filterTalentReq.getUserId())
                .setParameter(2, filterTalentReq.getHabilities())
                .setParameter(3, filterTalentReq.getLanguageIds())
                .setParameter(4, filterTalentReq.getLevelIds())
                .setParameter(5, filterTalentReq.getNameJobTitle())
                .setParameter(6, filterTalentReq.getUserListIds());
        storedProcedureQueryFilterTalent.execute();
        List<Object[]> result = storedProcedureQueryFilterTalent.getResultList();
        List<FilterTalentoResp> respList = new ArrayList<>();
        for (Object[] objects: result) {
            FilterTalentoResp filterTalentResp = new FilterTalentoResp();
            filterTalentResp.setId((Integer) objects[0]);
            filterTalentResp.setImage((byte[]) objects[1]);
            filterTalentResp.setNameJobTitle((String) objects[2]);
            filterTalentResp.setInitialSalaryPlanilla((Integer) objects[3]);
            filterTalentResp.setFinalSalaryPlanilla((Integer) objects[4]);
            filterTalentResp.setInitialSalaryRxH((Integer) objects[5]);
            filterTalentResp.setFinalSalaryRxH((Integer) objects[6]);
            filterTalentResp.setLocation((String) objects[7]);
            filterTalentResp.setAvgRating((Integer) objects[8]);
            filterTalentResp.setInUserList((Integer) objects[9]);
            respList.add(filterTalentResp);
        }
        return respList;
    }
}
