package ${rootPackage}.repository;

import ${rootPackage}.bo.${domainName}Bo;
import ${rootPackage}.command.${domainName}Command;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import ${rootPackage}.${mapperPackage}.${domainName}Mapper;
import ${rootPackage}.${domainPackage}.${domainName};
import java.util.List;

@Repository
@Mapper
public interface ${domainName}Repository extends ${domainName}Mapper{

    List<${domainName}Bo> queryList(${domainName}Command command);

    int insertList (List<${domainName}> ${variableName}s);
}