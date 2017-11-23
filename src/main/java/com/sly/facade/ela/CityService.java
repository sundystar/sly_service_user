package com.sly.facade.ela;

import java.util.List;

import com.sly.facade.entity.City;

/**
 * 城市 ES 业务接口类
 *
 */
public interface CityService {

    /**
     * 新增 ES 城市信息
     *
     * @param city
     * @return
     */
    void saveCity(City city);

    List<City> searchcity(String d);

}