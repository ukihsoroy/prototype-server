package ${rootPackage}.service;

import com.github.pagehelper.PageHelper;
import ${rootPackage}.bo.${domainName}Bo;
import ${rootPackage}.command.${domainName}Command;
import ${rootPackage}.${domainPackage}.${domainName};
import ${rootPackage}.${domainPackage}.${domainName}Example;
import ${rootPackage}.repository.${domainName}Repository;
import com.panhai.common.model.Result;
import com.panhai.sys.builder.FilePathBuilder;
import com.panhai.sys.utils.ExcelHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import com.panhai.sys.bo.FilePath;
import org.springframework.web.context.request.ServletWebRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.*;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ${domainName}Service {

    private static final Logger _LOGGER = LoggerFactory.getLogger(${domainName}Service.class);

    @Autowired private ${domainName}Repository ${variableName}Repository;

    /**
     * 文件操作
     */
    @Autowired private FilePathBuilder filePathBuilder;

    public Result<List<${domainName}Bo>> query (${domainName}Command condition, int page, int limit) {
        Result<List<${domainName}Bo>> result = new Result<>();
        PageHelper.startPage(page, limit);
        List<${domainName}Bo> ${variableName}Bos = ${variableName}Repository.queryList(condition);
        result.setSuccess(true);
        result.setData(${variableName}Bos);
        return result;
    }

    public Result<${domainName}> detail (String id) {
        Result<${domainName}> result = new Result<>();
        ${domainName} ${variableName} = ${variableName}Repository.selectByPrimaryKey(id);
        result.setSuccess(true);
        result.setData(${variableName});
        return result;
    }

    public Result<${domainName}> save (${domainName}Bo ${variableName}Bo) {
        ${domainName} ${variableName} = new ${domainName}();
        BeanUtils.copyProperties(${variableName}Bo, ${variableName});

        int ret = ${variableName}Repository.insert(${variableName});
        if (ret == 0) {
            throw new RuntimeException("新增异常");
        }

        Result<${domainName}> result = new Result<>();
        result.setSuccess(true);
        result.setData(${variableName});
        return result;
    }

    public Result<${domainName}> update (String id, ${domainName}Bo ${variableName}Bo) {
        ${domainName}Example ${abbr}e = new ${domainName}Example();
        ${abbr}e.createCriteria()
                .andIdEqualTo(id);

        ${domainName} ${variableName} = new ${domainName}();
        BeanUtils.copyProperties(${variableName}Bo, ${variableName});
        int ret = ${variableName}Repository.updateByExampleSelective(${variableName}, ${abbr}e);
        if (ret == 0) {
            throw new RuntimeException("更新异常");
        }

        Result<${domainName}> result = new Result<>();
        result.setSuccess(true);
        result.setData(${variableName});

        return result;
    }

    public Result<String> remove (String id) {
        ${variableName}Repository.deleteByPrimaryKey(id);
        Result<String> result = new Result<>();
        result.setSuccess(true);
        result.setData(id);
        return result;
    }

    public void export(${domainName}Command condition, ServletWebRequest request) {
        List<${domainName}Bo> ${variableName}Bos = ${variableName}Repository.queryList(condition);

        if (CollectionUtils.isNotEmpty(${variableName}Bos)) {
            //构建数据表格头
            List<String> heads = Arrays.asList(
                <#list meta as m>
                    "${m.comment}"<#if m_has_next>,</#if>
                </#list>
            );

            //构建表格数据
            List<List<String>> rows = new ArrayList<>();
                for (int i = 0; i < ${variableName}Bos.size(); i++) {
                    List<String> row = new ArrayList<>();
                    ${domainName} ${variableName} = ${variableName}Bos.get(i);
                    row.add(String.valueOf(i + 1));
                <#list meta as m>
                    row.add(ExcelHelper.getStringValue(${variableName}.get${m.fieldName?cap_first}()));
                </#list>
                    rows.add(row);
                }

            FilePath excel = filePathBuilder.buildArchivePath("${domainName}.xlsx");
            File dir = new File(FilenameUtils.getFullPath(excel.getLocalPath()));
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                ExcelHelper.export(heads, rows, excel.getLocalPath());
            } catch (Exception e) {
                _LOGGER.error("导出异常>>>",e);
            }
            filePathBuilder.download(request.getRequest(), request.getResponse(), excel.getLocalPath(), "${domainName}.xlsx");
        } else {
            throw new RuntimeException("Excel导出异常");
        }

    }
}