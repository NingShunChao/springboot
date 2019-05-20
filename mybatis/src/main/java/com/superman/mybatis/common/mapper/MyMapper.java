package com.superman.mybatis.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Auther: Ningsc
 * @Date: 2019/5/20 22:02
 * @Description: 自定义mapper，该接口不能被扫描到，否则会出错
 */
public interface MyMapper<T> extends Mapper<T> , MySqlMapper<T> {
}
