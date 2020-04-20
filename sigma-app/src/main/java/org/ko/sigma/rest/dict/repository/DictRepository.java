package org.ko.sigma.rest.dict.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ko.sigma.data.entity.Dict;
import org.springframework.stereotype.Repository;
import org.ko.sigma.rest.dict.dto.DictDTO;
import org.ko.sigma.rest.dict.condition.QueryDictCondition;


import java.util.List;

@Repository
public interface DictRepository extends BaseMapper<Dict> {

    List<DictDTO> queryList(QueryDictCondition condition);

    int insertList (List<Dict> dicts);

}
