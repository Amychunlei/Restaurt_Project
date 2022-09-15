package com.pro.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pro.reggie.entity.AddressBook;
import com.pro.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
