package org.ko.shin.rest.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ko.shin.core.exception.BusinessException;
import org.ko.shin.core.constant.SystemCode;
import org.ko.shin.data.constants.DictConstants;
import org.ko.shin.data.entity.Dict;
import org.ko.shin.rest.dict.condition.QueryDictCondition;
import org.ko.shin.rest.dict.repository.DictRepository;
import org.ko.shin.rest.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class DictServiceImpl extends ServiceImpl<DictRepository, Dict> implements DictService {

    /**
     * 字典表数据库对象
     */
    @Autowired private DictRepository dictRepository;

    @Override
    public List<Dict> queryDictList(QueryDictCondition condition) {
        return dictRepository.selectList(new QueryWrapper<>());
    }

    @Override
    public Dict queryDictInfo(Long id) {
        return dictRepository.selectById(id);
    }

    @Override
    public Long createDict(Dict dict) {
        if (dictRepository.insert(dict) == 0) {
            throw new BusinessException(SystemCode.UNKNOWN_ERROR);
        }
        return dict.getId();
    }

    @Override
    public Dict updateDict(Long id, Dict dict) {
        dict.setId(id);
        if (dictRepository.updateById(dict) == 0) {
            throw new BusinessException(SystemCode.UNKNOWN_ERROR);
        }
        return dict;
    }

    @Override
    public Long deleteDict(Long id) {
        if (dictRepository.deleteById(id) == 0) {
            throw new BusinessException(SystemCode.UNKNOWN_ERROR);
        }
        return id;
    }

    @Override
    public Dict findDictByCodeAndType(String code, String type) {
        return dictRepository.selectOne(new QueryWrapper<Dict>()
                .eq(DictConstants.Columns.CODE, code)
                .eq(DictConstants.Columns.TYPE, type));
    }

    @Override
    public List<Dict> findDictByCode(String code) {
        return dictRepository.selectList(new QueryWrapper<Dict>()
                .eq(DictConstants.Columns.CODE, code));
    }

}