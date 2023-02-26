package com.colgsam.springboot.service.impl;

import com.colgsam.springboot.entity.Type;
import com.colgsam.springboot.mapper.TypeMapper;
import com.colgsam.springboot.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 肖海冰
 * @since 2023-02-17
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

}
