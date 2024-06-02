package com.gabriel.minhacasa.repository;

import com.gabriel.minhacasa.domain.DTO.SearchParamsDTO;
import com.gabriel.minhacasa.domain.Immobile;

import java.util.List;

public interface ImmobileRepositoryCustomInterface {
    List<Immobile> searchByParams(SearchParamsDTO params);
}
