package com.kbldemo.service.impl;

import com.kbldemo.entity.Log;
import com.kbldemo.mapper.LogMapper;
import com.kbldemo.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kbl
 * @since 2021-03-11
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
