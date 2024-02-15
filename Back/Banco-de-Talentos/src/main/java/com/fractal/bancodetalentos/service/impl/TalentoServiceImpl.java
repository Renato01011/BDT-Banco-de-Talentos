package com.fractal.bancodetalentos.service.impl;

import com.fractal.bancodetalentos.model.entity.BtTmTalento;
import com.fractal.bancodetalentos.model.request.NewTalentReq;
import com.fractal.bancodetalentos.model.response.PostResp;
import com.fractal.bancodetalentos.service.TalentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class TalentoServiceImpl implements TalentoService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public PostResp addNewTalent(NewTalentReq newTalentRequest) {
        PostResp temp = new PostResp();
        return temp;
    }
}
