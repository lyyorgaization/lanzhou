package com.lucien.dap.user.authority.server.controller.system;

import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.framework.core.domain.Response;
import com.lucien.dap.framework.core.utils.CollectionUtil;
import com.lucien.dap.user.authority.server.common.MenuUtil;
import com.lucien.dap.user.authority.server.controller.BaseController;
import com.lucien.dap.user.authority.server.dto.system.QueryUserDto;
import com.lucien.dap.user.authority.server.dto.system.UserDto;
import com.lucien.dap.user.authority.server.dto.system.UserRoleDto;
import com.lucien.dap.user.authority.server.model.MenuEntity;
import com.lucien.dap.user.authority.server.model.UserEntity;
import com.lucien.dap.user.authority.server.service.system.UserService;
import com.lucien.dap.user.authority.server.vo.system.MenuTreeVo;
import com.lucien.dap.user.authority.server.vo.system.RoleVo;
import com.lucien.dap.user.authority.server.vo.system.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName UserController
 * @Description
 * @Author MALUHAN
 * @Date 2019/6/26 18:36
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/")
public class UserController extends BaseController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/queryUser", method = RequestMethod.POST)
    public Response<BaseListVo<UserVo>> queryUser(HttpServletRequest request, @RequestBody QueryUserDto dto) {
        log.info(getCurrentUser(request).getId().toString());
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(request, entity);

        BaseListVo<UserEntity> userList = userService.getUserList(entity, dto.getPSize(), dto.getPNo());
        BaseListVo<UserVo> baseListVo = new BaseListVo<>();
        if (CollectionUtil.isNotEmpty(userList.getList())) {
            List<UserVo> userListVos = new ArrayList<>();
            for (UserEntity userEntity : userList.getList()) {
                UserVo userListVo = new UserVo();
                BeanUtils.copyProperties(userEntity, userListVo);
                userListVos.add(userListVo);
            }
            baseListVo.setList(userListVos);
            baseListVo.setPNo(userList.getPNo());
            baseListVo.setPSize(userList.getPSize());
            baseListVo.setTotalCount(userList.getTotalCount());
        }
        return Response.success(baseListVo);

    }

    @RequestMapping("/edit")
    public Response<Boolean> edit(@RequestBody UserDto dto) {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(dto, user);
        Boolean b = userService.edit(user);

        return Response.success(b);
    }

    @RequestMapping("/getUserRoleList")
    public Response<List<RoleVo>> getUserRoleList(@RequestBody UserDto userDto) {
        List<RoleVo> userRoleList = new ArrayList<>();
        List<Long> roleIds = userService.getUserRoleList(userDto.getId());
        for (Long roleId : roleIds) {
            RoleVo vo = new RoleVo();
            vo.setId(roleId);
            userRoleList.add(vo);
        }
        return Response.success(userRoleList);
    }

    @RequestMapping("/editUserRole")
    public Response<Boolean> editUserRole(@RequestBody UserRoleDto dto) {
        userService.editUserRole(dto.getUserId(), dto.getRoleIdList());
        return Response.success(true);
    }

    @RequestMapping("/getMenuTree")
    public Response<List<MenuTreeVo>> getMenuTree(HttpServletRequest request) {
        List<MenuEntity> menuList = userService.getUserMenu(getCurrentUser(request).getId());
        List<MenuTreeVo> menuTreeVoList = new ArrayList<>();
        if (!CollectionUtil.isEmpty(menuList)) {
            List<MenuEntity> topMenuList = menuList.stream().filter(Entity -> Entity.getParentId().equals(0L)).collect(Collectors.toList());
            Map<Long, List<MenuEntity>> map = menuList.stream().collect(Collectors.groupingBy(MenuEntity::getParentId));
            for (MenuEntity entity : topMenuList) {
                menuTreeVoList.add(MenuUtil.convertVo(entity, map));
            }
        }
        return Response.success(menuTreeVoList);
    }

    @RequestMapping("/deleteUser")
    public Response<Boolean> deleteUser(@RequestBody UserDto user) {
        userService.deleteUser(user.getId());
        return Response.success(true);
    }

    @RequestMapping("/initPassword")
    public Response<Boolean> initPassword(@RequestBody UserDto user) {
        userService.initPassword(user.getId());
        return Response.success(true);
    }
}
