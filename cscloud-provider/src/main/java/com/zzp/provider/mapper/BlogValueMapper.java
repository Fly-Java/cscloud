package com.zzp.provider.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zzp.api.entity.BlogValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName BlogValueMapper
 * @Description
 * @Date
 * @Author Administrator
 **/
@Mapper
public interface BlogValueMapper extends BaseMapper<BlogValue> {

    Integer insertSelective(@Param("blogValue") BlogValue blogValue);

    BlogValue selectBlogValueByTandId(@Param("tranId") String tranId);

}
