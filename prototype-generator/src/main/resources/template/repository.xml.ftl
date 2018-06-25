<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${rootPackage}.repository.${domainName}Repository">

    <select id="queryList" resultType="${rootPackage}.bo.${domainName}Bo">
        SELECT
        <#list meta as m>
            ${abbr}.${m.columnName}<#if m_has_next>,</#if>
        </#list>
        FROM ${table} ${abbr}
    </select>

    <insert id="insertList">
        INSERT INTO ${table}
          (
          <#list meta as m>
            ${m.columnName}<#if m_has_next>,</#if>
          </#list>
          )
        <foreach collection="${variableName}s" item="${abbr}" open="VALUES (" separator="), (" close=")">
          <#list meta as m>
            #${r'{'}${abbr}.${m.columnName},jdbcType=${m.dataType ? upper_case}${r'}'}<#if m_has_next>,</#if>
          </#list>
        </foreach>
    </insert>
</mapper>