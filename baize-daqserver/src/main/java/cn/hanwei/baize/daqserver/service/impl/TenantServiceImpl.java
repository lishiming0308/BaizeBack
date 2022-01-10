package cn.hanwei.baize.daqserver.service.impl;

import cn.hanwei.baize.daqserver.entity.Tenant;
import cn.hanwei.baize.daqserver.mapper.TenantMapper;
import cn.hanwei.baize.daqserver.service.ITenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户 服务实现类
 * </p>
 *
 * @author lishiming
 * @since 2021-12-29
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

}
