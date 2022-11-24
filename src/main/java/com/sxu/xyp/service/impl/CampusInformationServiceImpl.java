package com.sxu.xyp.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.common.ErrorCode;
import com.sxu.xyp.exception.BusinessException;
import com.sxu.xyp.model.domain.CampusInformation;
import com.sxu.xyp.model.params.CampusInfo.AddCampusInfoParams;
import com.sxu.xyp.model.params.CampusInfo.UpdateCampusInfoParams;
import com.sxu.xyp.service.CampusInformationService;
import com.sxu.xyp.mapper.CampusInformationMapper;
import com.sxu.xyp.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* @author Warmly
* @description 针对表【campus_information】的数据库操作Service实现
* @createDate 2022-11-24 18:20:52
*/
@Service
public class CampusInformationServiceImpl extends ServiceImpl<CampusInformationMapper, CampusInformation>
    implements CampusInformationService{

    @Resource
    private UserService userService;

    /**
     * 添加资讯
     * @param addCampusInfoParams 接收参数
     * @param request 请求
     * @return 返回true
     */
    @Override
    public Boolean addInfo(AddCampusInfoParams addCampusInfoParams, HttpServletRequest request){
        Integer adminId = addCampusInfoParams.getAdminId();
        Boolean isAdmin = userService.isAdmin(adminId);
        if (!isAdmin){
            throw new BusinessException(ErrorCode.ROLE_ERROR);
        }
        String title = addCampusInfoParams.getTitle();
        if (StrUtil.isBlank(title)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题为空");
        }
        String summary = addCampusInfoParams.getSummary();
        if (StrUtil.isBlank(summary)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "摘要为空");
        }
        String content = addCampusInfoParams.getContent();
        if (StrUtil.isBlank(content)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容为空");
        }
        CampusInformation campusInformation = new CampusInformation();
        campusInformation.setAdminId(adminId);
        campusInformation.setTitle(title);
        campusInformation.setSummary(summary);
        campusInformation.setContent(content);
        boolean result = this.save(campusInformation);
        if (!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "添加失败");
        }
        return true;
    }


    /**
     * 删除资讯
     * @param id 资讯id
     * @param request 请求
     * @return 返回条数
     */
    @Override
    public Boolean deleteCampusInfo(Integer id, HttpServletRequest request){
        Boolean isAdmin = userService.isAdmin(request);
        if (!isAdmin){
            throw new BusinessException(ErrorCode.ROLE_ERROR);
        }
        CampusInformation info = this.getById(id);
        if (info == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "资讯不存在");
        }
        int i = this.baseMapper.deleteById(id);
        if(i == 0){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "删除失败");
        }
        return true;
    }

    /**
     * 根据id修改资讯
     * @param updateCampusInfoParams 更新参数
     * @param request 请求
     * @return 返回true
     */
    @Override
    public Boolean updateInfoById(UpdateCampusInfoParams updateCampusInfoParams, HttpServletRequest request){
        Boolean isAdmin = userService.isAdmin(request);
        if(!isAdmin){
            throw new BusinessException(ErrorCode.ROLE_ERROR);
        }
        Integer id = updateCampusInfoParams.getId();
        CampusInformation info = this.getById(id);
        if (info == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"资讯不存在");
        }
        String title = updateCampusInfoParams.getTitle();
        String content = updateCampusInfoParams.getContent();
        String summary = updateCampusInfoParams.getSummary();
        CampusInformation campusInformation = new CampusInformation();
        campusInformation.setId(id);
        campusInformation.setTitle(title);
        campusInformation.setSummary(summary);
        campusInformation.setContent(content);
        boolean result = this.updateById(campusInformation);
        if (!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改失败");
        }
        return true;
    }

    /**
     * 展示所有资讯
     * @param request 请求
     * @return 返回列表
     */
    @Override
    public List<CampusInformation> listInfo(HttpServletRequest request){
        Boolean isAdmin = userService.isAdmin(request);
        if (!isAdmin){
            throw new BusinessException(ErrorCode.ROLE_ERROR);
        }
        List<CampusInformation> infoList = this.list();
        if (CollectionUtil.isEmpty(infoList)){
            return new ArrayList<>();
        }
        return infoList;
    }

}




