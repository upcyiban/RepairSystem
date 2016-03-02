package com.lihuanyu.repairsystem.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by echao on 2016/2/28.
 */
public interface RepairListDao extends CrudRepository<RepairList,Integer>{
    public  RepairList findByYibanid(int yibanid);
    public RepairList findById(int  id);
    public  Iterable<RepairList> findByYibanname(String yibanname);
}
