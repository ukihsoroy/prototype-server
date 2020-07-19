package org.ko.prototype.rest.dict.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ko.prototype.data.entity.Dict;
import org.springframework.stereotype.Repository;
import org.ko.prototype.rest.dict.dto.DictDTO;
import org.ko.prototype.rest.dict.condition.QueryDictCondition;


import java.util.List;

@Repository
public interface DictRepository extends BaseMapper<Dict> {

    List<DictDTO> queryList(QueryDictCondition condition);

    int insertList (List<Dict> dicts);

}
