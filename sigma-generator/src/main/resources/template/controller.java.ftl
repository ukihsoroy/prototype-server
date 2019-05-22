package ${rootPackage}.controller;

import ${rootPackage}.bo.${domainName}Bo;
import ${rootPackage}.command.${domainName}Command;
import ${rootPackage}.entity.${domainName};
import com.panhai.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ${rootPackage}.service.${domainName}Service;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.Valid;
import java.util.List;


@Api("${domainName}Controller")
@Validated
@RestController
@RequestMapping("${variableName}")
public class ${domainName}Controller {

    @Autowired private ${domainName}Service ${variableName}Service;

    @GetMapping
    @ApiOperation("查询列表")
    public Result<List<${domainName}Bo>> query (
            @ApiParam("查询列表参数") @ModelAttribute ${domainName}Command condition,
            @ApiParam("页数") @RequestParam(required = false, defaultValue = "1") int page,
            @ApiParam("条数") @RequestParam(required = false, defaultValue = "10") int limit) {
        return ${variableName}Service.query(condition, page, limit);
    }

    @GetMapping("{id}")
    @ApiOperation("通过业务主键获取详情")
    public Result<${domainName}> detail (
            @ApiParam("业务主键") @PathVariable String id) {
        return ${variableName}Service.detail(id);
    }

    @PostMapping
    @ApiOperation("新增资源")
    public Result<${domainName}> save (
            @ApiParam("业务对象") @Valid @RequestBody ${domainName}Bo ${variableName}Bo) {
        return ${variableName}Service.save(${variableName}Bo);
    }

    @PutMapping("{id}")
    @ApiOperation("更新资源")
    public Result<${domainName}> update (
            @ApiParam("业务主键") @PathVariable String id,
            @ApiParam("业务对象") @Valid @RequestBody ${domainName}Bo ${variableName}Bo) {
        return ${variableName}Service.update(id, ${variableName}Bo);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除资源")
    public Result<String> remove (
            @ApiParam("申报单ID") @PathVariable String id) {
        return ${variableName}Service.remove(id);
    }

    @GetMapping("export")
    @ApiOperation("导出Excel")
    public void export (
            @ApiParam("查询列表参数") @ModelAttribute ${domainName}Command condition, ServletWebRequest request) {
        ${variableName}Service.export(condition, request);
    }

}