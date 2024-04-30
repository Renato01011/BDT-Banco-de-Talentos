package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.Config.DataSourceConfig;
import com.fractal.bancodetalentos.exception.ResourceNotFoundException;
import com.fractal.bancodetalentos.model.request.FilterTalentReq;
import com.fractal.bancodetalentos.model.response.FilterResponse;
import com.fractal.bancodetalentos.model.response.FilterTalentoResp;
import com.fractal.bancodetalentos.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.fractal.bancodetalentos.util.ValidationUtil.multiDataFromDb;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

    @PersistenceContext
    private final EntityManager entityManager;

    private final DataSourceConfig dataSource;

    @Override
    public FilterResponse filterTalents(FilterTalentReq filterTalentReq) {
        List<List<Object[]>> r = multiDataFromDb("CALL SP_FILTER_TALENT(?,?,?,?,?,?,?)", true, dataSource, filterTalentReq.getUserId(), filterTalentReq.getHabilities(), filterTalentReq.getLanguageIds(), filterTalentReq.getLevelIds(), filterTalentReq.getNameJobTitle(), filterTalentReq.getUserListIds(), filterTalentReq.getPagina());
        FilterResponse response = new FilterResponse();
        List<FilterTalentoResp> respList = new ArrayList<>();
        List<Object[]> result = r.get(0);
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
            filterTalentResp.setIconCoin((String) objects[10]);
            respList.add(filterTalentResp);
        }
        response.setList(respList);
        if (!r.isEmpty() && r.get(1).size() > 0) {
            response.setTotal(((Number) r.get(1).get(0)[0]).intValue());
        }
        return response;
    }
}
